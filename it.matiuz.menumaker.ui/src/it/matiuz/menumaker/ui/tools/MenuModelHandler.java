package it.matiuz.menumaker.ui.tools;

import it.matiuz.menumaker.model.Category;
import it.matiuz.menumaker.model.Item;
import it.matiuz.menumaker.model.Menu;
import it.matiuz.menumaker.model.MenuItem;
import it.matiuz.menumaker.model.MenuModelFactory;
import it.matiuz.menumaker.ui.MenumakerPluginUI;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MenuModelHandler implements IMenuModelHandler
{
  private HandlerState handlerState;

  private Menu menu;

  private boolean isDirty;

  private Map<Integer, Integer> categoryMap;
  private Map<Integer, Integer> menuItemMap;

  public MenuModelHandler ()
  {
    isDirty = false;
    handlerState = HandlerState.IDLE;
  }

  @Override
  public boolean isDirty ()
  {
    return isDirty;
  }

  public Item[] getItemList ()
  {
    return menu.getItems ().toArray (new Item[menu.getItems ().size ()]);
  }

  public String getMenuDescription ()
  {
    return menu.getDescription ();
  }

  public Date getMenuCreationDate ()
  {
    return menu.getCreationDate ();
  }

  @Override
  public void updateMenu (Menu uMenu)
  {
    menu.setDescription (uMenu.getDescription ());
    menu.setCreationDate (uMenu.getCreationDate ());
    isDirty = true;
  }

  @Override
  public void loadMenu (int uMenuId) throws MenuHandlerException
  {
    menu = MenuModelFactory.eINSTANCE.createMenu ();
    handlerState = HandlerState.EDIT;

    final DatabaseManager dbManager = MenumakerPluginUI.getDatabase ();

    if (dbManager.IsConnected ())
      try
      {
        menu = dbManager.getMenuFromId (uMenuId);
        menu.getItems ().addAll (Arrays.asList (dbManager.getItemListFromMenuId (uMenuId)));
        sortMenuItemList ();
        isDirty = false;
      } catch (final DatabaseException uDatabaseException)
      {
        throw new MenuHandlerException (uDatabaseException.getMessage ());
      }
  }

  @Override
  public void saveMenu () throws MenuHandlerException
  {
    final DatabaseManager dbManager = MenumakerPluginUI.getDatabase ();
    final Connection connection = dbManager.getConnection ();

    final Item[] items = menu.getItems ().toArray (new Item[menu.getItems ().size ()]);

    if (dbManager.IsConnected ())
      try
      {
        if (handlerState.equals (HandlerState.CREATE))
        {
          dbManager.createMenu (menu);
          menu.setId (dbManager.getMaxMenuId ());
        } else if (handlerState.equals (HandlerState.EDIT))
          dbManager.updateMenu (menu);

        dbManager.deleteAllMenuItems (menu.getId ());

        for (int i = 0; i < items.length; i++)
        {
          dbManager.updateItem (items[i]);

          final MenuItem menuItem = MenuModelFactory.eINSTANCE.createMenuItem ();
          menuItem.setItemId (items[i].getId ());
          menuItem.setMenuId (menu.getId ());
          menuItem.setItemPosition (i + 1);

          dbManager.createMenuItem (menuItem);
        }

        try
        {
          connection.commit ();
        } catch (final SQLException e)
        {
          throw new MenuHandlerException (e.getMessage ());
        }

        handlerState = HandlerState.EDIT;
        isDirty = false;

      } catch (final DatabaseException uDatabaseException)
      {
        try
        {
          connection.rollback ();
        } catch (final SQLException e1)
        {
          throw new MenuHandlerException (e1.getMessage ());
        }
        throw new MenuHandlerException (uDatabaseException.getMessage ());
      }
    else
      throw new MenuHandlerException ("Connection error");
  }

  @Override
  public void closeMenu ()
  {
    isDirty = false;
    menu = null;
    handlerState = HandlerState.IDLE;
  }

  @Override
  public void removeMenu (Menu uMenu) throws MenuHandlerException
  {
    if (!handlerState.equals (HandlerState.IDLE))
      if (uMenu.getId () == menu.getId ())
        closeMenu ();

    final DatabaseManager dbManager = MenumakerPluginUI.getDatabase ();
    final Connection connection = dbManager.getConnection ();

    if (dbManager.IsConnected ())
      try
      {
        dbManager.deleteMenu (uMenu.getId ());
        try
        {
          connection.commit ();
        } catch (final SQLException e)
        {
          throw new MenuHandlerException (e.getMessage ());
        }

      } catch (final DatabaseException uDatabaseException)
      {
        try
        {
          connection.rollback ();
        } catch (final SQLException e1)
        {
          throw new MenuHandlerException (e1.getMessage ());
        }
        throw new MenuHandlerException (uDatabaseException.getMessage ());
      }
  }

  @Override
  public void addMenu (Menu uMenu)
  {
    handlerState = HandlerState.CREATE;
    menu = MenuModelFactory.eINSTANCE.createMenu ();
    menu.setDescription (uMenu.getDescription ());
    menu.setCreationDate (uMenu.getCreationDate ());

    isDirty = true;
  }

  @Override
  public void addItem (Item uItem)
  {
    for (final Item item : menu.getItems ())
      if (item.getId () == uItem.getId ())
        return;

    final Item item = MenuModelFactory.eINSTANCE.createItem ();

    item.setCategoryId (uItem.getCategoryId ());
    item.setId (uItem.getId ());
    item.setDescription (uItem.getDescription ());
    item.setPrice (uItem.getPrice ());
    item.setPriceNotes (uItem.getPriceNotes ());

    menu.getItems ().add (item);

    sortMenuItemList ();

    isDirty = true;
  }

  @Override
  public void removeItem (Item uItem)
  {
    menu.getItems ().remove (uItem);
    isDirty = true;
  }

  @Override
  public void updateItem (Item uItem)
  {
    final Item[] items = getItemList ();
    int index = 0;

    for (int i = 0; i < items.length; i++)
      if (items[i].getId () == uItem.getId ())
      {
        index = i;
        break;
      }

    menu.getItems ().set (index, uItem);
    isDirty = true;
  }

  @Override
  public HandlerState getHandlerState ()
  {
    return handlerState;
  }

  @Override
  public Menu getMenu ()
  {
    return menu;
  }

  private void loadCategoriesPriorityMap ()
  {
    final DatabaseManager dbManager = MenumakerPluginUI.getDatabase ();
    Category[] categories = null;
    if (dbManager.IsConnected ())
      try
      {
        categories = dbManager.getCategoryList ();
      } catch (final DatabaseException uDatabaseException)
      {
        return;
      }
    else
      return;

    categoryMap = new HashMap<Integer, Integer> ();

    for (int i = 0; i < categories.length; i++)
      categoryMap.put (Integer.valueOf (categories[i].getId ()), Integer.valueOf (categories[i].getPriority ()));
  }

  private void loadMenuItemsOrderMap ()
  {
    final DatabaseManager dbManager = MenumakerPluginUI.getDatabase ();
    MenuItem[] menuItems = null;
    if (dbManager.IsConnected ())
      try
      {
        menuItems = dbManager.getMenuItemListFromMenuId (menu.getId ());
      } catch (final DatabaseException uDatabaseException)
      {
        return;
      }
    else
      return;

    menuItemMap = new HashMap<Integer, Integer> ();

    for (int i = 0; i < menuItems.length; i++)
      menuItemMap.put (Integer.valueOf (menuItems[i].getItemId ()), Integer.valueOf (menuItems[i].getItemPosition ()));
  }

  private void sortMenuItemList ()
  {
    loadCategoriesPriorityMap ();
    loadMenuItemsOrderMap ();

    final Item[] items = getItemList ();

    for (int i = items.length - 1; i >= 0; i--)
      for (int j = 1; j <= i; j++)
      {
        Integer previousId = categoryMap.get (Integer.valueOf (items[j - 1].getCategoryId ()));
        Integer currentId = categoryMap.get (Integer.valueOf (items[j].getCategoryId ()));

        if (previousId == null || currentId == null)
          continue;

        if (previousId.intValue () > currentId.intValue ())
        {
          final Item swapItem = items[j - 1];
          items[j - 1] = items[j];
          items[j] = swapItem;
          menu.getItems ().move (j - 1, items[j]);
          menu.getItems ().move (j, swapItem);

        } else if (previousId.intValue () == currentId.intValue ())
        {
          final Integer position1 = menuItemMap.get (Integer.valueOf (items[j - 1].getId ()));

          if (position1 == null)
            continue;

          final Integer position2 = menuItemMap.get (Integer.valueOf (items[j].getId ()));

          if (position2 == null)
            continue;

          if (position1.intValue () > position2.intValue ())
          {
            final Item swapItem = items[j - 1];
            items[j - 1] = items[j];
            items[j] = swapItem;
            menu.getItems ().move (j - 1, items[j]);
            menu.getItems ().move (j, swapItem);
          }
        }
      }
  }

  @Override
  public void moveItem (Item uItem, ItemMovingDirection uDirection)
  {
    loadCategoriesPriorityMap ();

    final Item[] items = getItemList ();
    int index = 0;

    for (int i = 0; i < items.length; i++)
      if (uItem.getId () == items[i].getId ())
      {
        index = i;
        break;
      }

    Integer currentId = categoryMap.get (Integer.valueOf (items[index].getCategoryId ()));
    Integer previousId = categoryMap.get (Integer.valueOf (items[index - 1].getCategoryId ()));
    Integer nextId = categoryMap.get (Integer.valueOf (items[index + 1].getCategoryId ()));

    if (uDirection.equals (IMenuModelHandler.ItemMovingDirection.UP))
    {
      if (index == 0)
        return;

      if (currentId.intValue () == previousId.intValue ())
      {
        final Item swapItem = items[index];
        menu.getItems ().move (index, items[index - 1]);
        menu.getItems ().move (index - 1, swapItem);
        isDirty = true;
      }

    } else if (uDirection.equals (IMenuModelHandler.ItemMovingDirection.DOWN))
    {
      if (index == items.length - 1)
        return;

      if (currentId.intValue () == nextId.intValue ())
      {
        final Item swapItem = items[index];
        menu.getItems ().move (index, items[index + 1]);
        menu.getItems ().move (index + 1, swapItem);
        isDirty = true;
      }
    }
  }
}
