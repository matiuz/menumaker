package it.matiuz.menumaker.ui.tools;

import it.matiuz.menumaker.model.Category;
import it.matiuz.menumaker.model.Item;
import it.matiuz.menumaker.model.Menu;
import it.matiuz.menumaker.model.MenuItem;
import it.matiuz.menumaker.model.MenuModelFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseManager
{
  private static final String ITEM_ITEM_ID = "item_id";
  private static final String ITEM_DESCRIPTION = "description";
  private static final String ITEM_CREATION_DATE = "creation_date";
  private static final String ITEM_PRICE = "price";
  private static final String ITEM_PRICE_NOTES = "price_notes";
  private static final String ITEM_CATEGORY_ID = "category_id";
  private static final String CATEGORY_CATEGORY_ID = "category_id";
  private static final String CATEGORY_DESCRIPTION = "description";
  private static final String CATEGORY_PRIORITY = "priority";
  private static final String MENU_ITEM_MENU_ID = "menu_id";
  private static final String MENU_ITEM_ITEM_ID = "item_id";
  private static final String MENU_ITEM_ITEM_POSITION = "item_position";
  private static final String MENU_MENU_ID = "menu_id";
  private static final String MENU_DESCRIPTION = "description";
  private static final String MENU_CREATION_DATE = "creation_date";
  private static final String ERROR_COULD_NOT_CLOSE_CONNECTION = "Could not close connection";
  private static final String ERROR_COULD_NOT_DELETE_DATA = "Could not delete data";
  private static final String ERROR_COULD_NOT_INSERT_DATA = "Could not insert data";
  private static final String ERROR_COULD_NOT_UPDATE_DATA = "Could not update data";
  private static final String ERROR_COULD_NOT_RETRIEVE_DATA = "Could not retrieve data";
  private static final String ERROR_COULD_NOT_CONNECT_TO_DATABASE = "Connection error";
  private static final String ERROR_COULD_NOT_LOAD_DRIVER = "Could not load driver";
  private static final String ERROR_CATEGORY_DESCRIPTION_ALREADY_DEFINED = "Category description already defined";
  private static final String ERROR_COULD_NOT_CLOSE_STATEMENT = "Could not close statement";
  private static final String ERROR_COULD_NOT_LOAD_CATEGORY_DECRIPTION = "Could not load category description";

  private Connection connection = null;
  private boolean isConnected = false;

  public DatabaseManager () throws DatabaseException
  {
    try
    {
      Class.forName ("org.postgresql.Driver");
    } catch (final ClassNotFoundException e)
    {
      throw new DatabaseException (ERROR_COULD_NOT_LOAD_DRIVER);
    }
  }

  public void createConnection (String uAddress, int uPort, String uDatabase, String uLogin, String uPassword) throws DatabaseException
  {
    final String databaseURL = "jdbc:postgresql://" + uAddress + ":" + uPort + "/" + uDatabase;
    try
    {
      connection = DriverManager.getConnection (databaseURL, uLogin, uPassword);
      connection.setAutoCommit (false);
      isConnected = true;
    } catch (final SQLException e)
    {
      throw new DatabaseException (ERROR_COULD_NOT_CONNECT_TO_DATABASE);
    }
  }

  public Category[] getCategoryList () throws DatabaseException
  {
    final ArrayList<Category> categoryList = new ArrayList<Category> ();
    final String query = "SELECT * FROM category";
    ResultSet results = null;
    Statement statement = null;
    try
    {
      statement = connection.createStatement ();
      results = statement.executeQuery (query);
      if (results != null)
        while (results.next ())
        {
          final Category category = MenuModelFactory.eINSTANCE.createCategory ();
          category.setId (results.getInt (CATEGORY_CATEGORY_ID));
          category.setDescription (results.getString (CATEGORY_DESCRIPTION));
          category.setPriority (results.getInt (CATEGORY_PRIORITY));
          categoryList.add (category);
        }
    } catch (final SQLException e)
    {
      throw new DatabaseException (ERROR_COULD_NOT_RETRIEVE_DATA);
    } finally
    {
      try
      {
        if (results != null)
          results.close ();
        if (statement != null)
          statement.close ();
      } catch (final SQLException e)
      {
        throw new DatabaseException (ERROR_COULD_NOT_CLOSE_STATEMENT);
      }
    }
    return categoryList.toArray (new Category[categoryList.size ()]);
  }

  public Menu getMenuFromId (int uMenuId) throws DatabaseException
  {
    final String query = "SELECT * FROM menu WHERE menu_id = ?";
    ResultSet results = null;
    PreparedStatement preparedStatement = null;
    Menu menu = null;
    try
    {
      preparedStatement = connection.prepareStatement (query);
      preparedStatement.setInt (1, uMenuId);
      results = preparedStatement.executeQuery ();
      if (results != null)
      {
        results.next ();
        menu = MenuModelFactory.eINSTANCE.createMenu ();
        menu.setId (results.getInt (MENU_MENU_ID));
        menu.setDescription (results.getString (MENU_DESCRIPTION));
        menu.setCreationDate (results.getDate (MENU_CREATION_DATE));
      }
    } catch (final SQLException e)
    {
      throw new DatabaseException (ERROR_COULD_NOT_RETRIEVE_DATA);
    } finally
    {
      try
      {
        if (results != null)
          results.close ();
        if (preparedStatement != null)
          preparedStatement.close ();
      } catch (final SQLException e)
      {
        throw new DatabaseException (ERROR_COULD_NOT_CLOSE_STATEMENT);
      }
    }
    return menu;
  }

  public Category getCategoryFromId (int uCategoryId) throws DatabaseException
  {
    final String query = "SELECT * FROM category WHERE category_id = ?";
    ResultSet results = null;
    PreparedStatement preparedStatement = null;
    Category category = null;
    try
    {
      preparedStatement = connection.prepareStatement (query);
      preparedStatement.setInt (1, uCategoryId);
      results = preparedStatement.executeQuery ();
      if (results != null)
      {
        results.next ();
        category = MenuModelFactory.eINSTANCE.createCategory ();
        category.setId (results.getInt (CATEGORY_CATEGORY_ID));
        category.setDescription (results.getString (CATEGORY_DESCRIPTION));
      }
    } catch (final SQLException e)
    {
      throw new DatabaseException (ERROR_COULD_NOT_RETRIEVE_DATA);
    } finally
    {
      try
      {
        if (results != null)
          results.close ();
        if (preparedStatement != null)
          preparedStatement.close ();
      } catch (final SQLException e)
      {
        throw new DatabaseException (ERROR_COULD_NOT_CLOSE_STATEMENT);
      }
    }
    return category;
  }

  public void updateItem (Item uItem) throws DatabaseException
  {
    final String query = "UPDATE item SET description = ?, creation_date = NOW (), price = ?, price_notes = ?, category_id = ? WHERE item_id = ?";
    PreparedStatement preparedStatement = null;
    try
    {
      preparedStatement = connection.prepareStatement (query);
      preparedStatement.setString (1, uItem.getDescription ());
      preparedStatement.setDouble (2, uItem.getPrice ());
      preparedStatement.setString (3, uItem.getPriceNotes ());
      preparedStatement.setInt (4, uItem.getCategoryId ());
      preparedStatement.setInt (5, uItem.getId ());
      preparedStatement.executeUpdate ();
    } catch (final SQLException e)
    {
      throw new DatabaseException (ERROR_COULD_NOT_UPDATE_DATA);
    } finally
    {
      try
      {
        if (preparedStatement != null)
          preparedStatement.close ();
      } catch (final SQLException e)
      {
        throw new DatabaseException (ERROR_COULD_NOT_CLOSE_STATEMENT);
      }
    }
  }

  public void updateCategory (Category uMenuCategory) throws DatabaseException
  {
    final String query = "UPDATE category SET description = ?, priority = ? WHERE category_id = ?";
    PreparedStatement preparedStatement = null;
    try
    {
      preparedStatement = connection.prepareStatement (query);
      preparedStatement.setString (1, uMenuCategory.getDescription ());
      preparedStatement.setInt (2, uMenuCategory.getPriority ());
      preparedStatement.setInt (3, uMenuCategory.getId ());
      preparedStatement.executeUpdate ();
    } catch (final SQLException e)
    {
      throw new DatabaseException (ERROR_COULD_NOT_UPDATE_DATA);
    } finally
    {
      try
      {
        if (preparedStatement != null)
          preparedStatement.close ();
      } catch (final SQLException e)
      {
        throw new DatabaseException (ERROR_COULD_NOT_CLOSE_STATEMENT);
      }
    }
  }

  public void createCategory (Category uMenuCategory) throws DatabaseException
  {
    if (!checkUniqueCategoryDescription (uMenuCategory.getDescription ()))
      throw new DatabaseException (ERROR_CATEGORY_DESCRIPTION_ALREADY_DEFINED);

    final String query = "INSERT INTO category (description, priority) VALUES (?, ?)";
    PreparedStatement preparedStatement = null;
    try
    {
      preparedStatement = connection.prepareStatement (query);
      preparedStatement.setString (1, uMenuCategory.getDescription ());
      preparedStatement.setInt (2, uMenuCategory.getPriority ());
      preparedStatement.executeUpdate ();
    } catch (final SQLException e)
    {
      throw new DatabaseException (ERROR_COULD_NOT_INSERT_DATA);
    } finally
    {
      try
      {
        if (preparedStatement != null)
          preparedStatement.close ();
      } catch (final SQLException e)
      {
        throw new DatabaseException (ERROR_COULD_NOT_CLOSE_STATEMENT);
      }
    }
  }

  public void deleteCategory (int uCategoryId) throws DatabaseException
  {
    final String query = "DELETE FROM category WHERE category_id = ?";
    PreparedStatement preparedStatement = null;
    try
    {
      preparedStatement = connection.prepareStatement (query);
      preparedStatement.setInt (1, uCategoryId);
      preparedStatement.executeUpdate ();
    } catch (final SQLException e)
    {
      throw new DatabaseException (ERROR_COULD_NOT_DELETE_DATA);
    } finally
    {
      try
      {
        if (preparedStatement != null)
          preparedStatement.close ();
      } catch (final SQLException e)
      {
        throw new DatabaseException (ERROR_COULD_NOT_CLOSE_STATEMENT);
      }
    }
  }

  public void updateMenu (Menu uMenu) throws DatabaseException
  {
    final String query = "UPDATE menu SET description = ?, creation_date = NOW() WHERE menu_id = ?";
    PreparedStatement preparedStatement = null;
    try
    {
      preparedStatement = connection.prepareStatement (query);
      preparedStatement.setString (1, uMenu.getDescription ());
      preparedStatement.setInt (2, uMenu.getId ());
      preparedStatement.executeUpdate ();
    } catch (final SQLException e)
    {
      throw new DatabaseException (ERROR_COULD_NOT_UPDATE_DATA);
    } finally
    {
      try
      {
        if (preparedStatement != null)
          preparedStatement.close ();
      } catch (final SQLException e)
      {
        throw new DatabaseException (ERROR_COULD_NOT_CLOSE_STATEMENT);
      }
    }
  }

  public void createMenu (Menu uMenu) throws DatabaseException
  {
    final String query = "INSERT INTO menu (description, creation_date) VALUES (?, NOW ())";
    PreparedStatement preparedStatement = null;
    try
    {
      preparedStatement = connection.prepareStatement (query);
      preparedStatement.setString (1, uMenu.getDescription ());
      preparedStatement.executeUpdate ();
    } catch (final SQLException e)
    {
      throw new DatabaseException (ERROR_COULD_NOT_INSERT_DATA);
    } finally
    {
      try
      {
        if (preparedStatement != null)
          preparedStatement.close ();
      } catch (final SQLException e)
      {
        throw new DatabaseException (ERROR_COULD_NOT_CLOSE_STATEMENT);
      }
    }
  }

  public void deleteMenuItem (MenuItem uMenuItem) throws DatabaseException
  {
    final String query = "DELETE FROM menu_item WHERE item_id = ? AND menu_id = ?";
    PreparedStatement preparedStatement = null;
    try
    {
      preparedStatement = connection.prepareStatement (query);
      preparedStatement.setInt (1, uMenuItem.getItemId ());
      preparedStatement.setInt (2, uMenuItem.getMenuId ());
      preparedStatement.executeUpdate ();
    } catch (final SQLException e)
    {
      throw new DatabaseException (ERROR_COULD_NOT_DELETE_DATA);
    } finally
    {
      try
      {
        if (preparedStatement != null)
          preparedStatement.close ();
      } catch (final SQLException e)
      {
        throw new DatabaseException (ERROR_COULD_NOT_CLOSE_STATEMENT);
      }
    }
  }

  public void deleteMenu (int uMenuId) throws DatabaseException
  {
    final String query = "DELETE FROM menu WHERE menu_id = ?";
    PreparedStatement preparedStatement = null;
    try
    {
      preparedStatement = connection.prepareStatement (query);
      preparedStatement.setInt (1, uMenuId);
      preparedStatement.executeUpdate ();
    } catch (final SQLException e)
    {
      throw new DatabaseException (ERROR_COULD_NOT_DELETE_DATA);
    } finally
    {
      try
      {
        if (preparedStatement != null)
          preparedStatement.close ();
      } catch (final SQLException e)
      {
        throw new DatabaseException (ERROR_COULD_NOT_CLOSE_STATEMENT);
      }
    }
  }

  public Menu[] getMenuList () throws DatabaseException
  {
    final ArrayList<Menu> menuList = new ArrayList<Menu> ();
    final String query = "SELECT * FROM menu";
    ResultSet results = null;
    Statement statement = null;
    try
    {
      statement = connection.createStatement ();
      results = statement.executeQuery (query);
      if (results != null)
        while (results.next ())
        {

          final Menu menu = MenuModelFactory.eINSTANCE.createMenu ();
          menu.setCreationDate (results.getDate (MENU_CREATION_DATE));
          menu.setId (results.getInt (MENU_MENU_ID));
          menu.setDescription (results.getString (MENU_DESCRIPTION));

          menuList.add (menu);
        }
    } catch (final SQLException e)
    {
      throw new DatabaseException (ERROR_COULD_NOT_RETRIEVE_DATA);
    } finally
    {
      try
      {
        if (results != null)
          results.close ();
        if (statement != null)
          statement.close ();
      } catch (final SQLException e)
      {
        throw new DatabaseException (ERROR_COULD_NOT_CLOSE_STATEMENT);
      }
    }
    return menuList.toArray (new Menu[menuList.size ()]);
  }

  public Item[] getItemList () throws DatabaseException
  {
    final ArrayList<Item> itemList = new ArrayList<Item> ();
    final String query = "SELECT * FROM item";
    ResultSet results = null;
    Statement statement = null;
    try
    {
      statement = connection.createStatement ();
      results = statement.executeQuery (query);
      if (results != null)
        while (results.next ())
        {
          final Item item = MenuModelFactory.eINSTANCE.createItem ();
          item.setCategoryId (results.getInt (ITEM_CATEGORY_ID));
          item.setCreationDate (results.getDate (ITEM_CREATION_DATE));
          item.setId (results.getInt (ITEM_ITEM_ID));
          item.setDescription (results.getString (ITEM_DESCRIPTION));
          item.setPrice (results.getDouble (ITEM_PRICE));
          item.setPriceNotes (results.getString (ITEM_PRICE_NOTES));

          itemList.add (item);
        }
    } catch (final SQLException e)
    {
      throw new DatabaseException (ERROR_COULD_NOT_RETRIEVE_DATA);
    } finally
    {
      try
      {
        if (results != null)
          results.close ();
        if (statement != null)
          statement.close ();
      } catch (final SQLException e)
      {
        throw new DatabaseException (ERROR_COULD_NOT_CLOSE_STATEMENT);
      }
    }
    return itemList.toArray (new Item[itemList.size ()]);
  }

  public int getMaxMenuId () throws DatabaseException
  {
    int maxId = -1;

    final String query = "SELECT MAX (menu_id) FROM menu";
    ResultSet results = null;
    Statement statement = null;
    try
    {
      statement = connection.createStatement ();
      results = statement.executeQuery (query);

      if (results != null)
      {
        results.next ();
        maxId = results.getInt (1);
      }
    } catch (final SQLException e)
    {
      throw new DatabaseException (ERROR_COULD_NOT_RETRIEVE_DATA);
    } finally
    {
      try
      {
        if (results != null)
          results.close ();
        if (statement != null)
          statement.close ();
      } catch (final SQLException e)
      {
        throw new DatabaseException (ERROR_COULD_NOT_CLOSE_STATEMENT);
      }
    }
    return maxId;
  }

  public Item[] getItemListFromMenuId (int uMenuId) throws DatabaseException
  {
    final ArrayList<Item> itemList = new ArrayList<Item> ();
    final String query = "SELECT item.item_id, item.description, item.creation_date, item.price, item.price_notes, item.category_id FROM menu_item, item WHERE menu_item.item_id = item.item_id AND menu_item.menu_id = ?";
    ResultSet results = null;
    PreparedStatement preparedStatement = null;
    try
    {
      preparedStatement = connection.prepareStatement (query);
      preparedStatement.setInt (1, uMenuId);
      results = preparedStatement.executeQuery ();
      if (results != null)
        while (results.next ())
        {
          final Item item = MenuModelFactory.eINSTANCE.createItem ();
          item.setCategoryId (results.getInt (ITEM_CATEGORY_ID));
          item.setCreationDate (results.getDate (ITEM_CREATION_DATE));
          item.setId (results.getInt (ITEM_ITEM_ID));
          item.setDescription (results.getString (ITEM_DESCRIPTION));
          item.setPrice (results.getDouble (ITEM_PRICE));
          item.setPriceNotes (results.getString (ITEM_PRICE_NOTES));

          itemList.add (item);
        }
    } catch (final SQLException e)
    {
      throw new DatabaseException (ERROR_COULD_NOT_RETRIEVE_DATA);
    } finally
    {
      try
      {
        if (results != null)
          results.close ();
        if (preparedStatement != null)
          preparedStatement.close ();
      } catch (final SQLException e)
      {
        throw new DatabaseException (ERROR_COULD_NOT_CLOSE_STATEMENT);
      }
    }
    return itemList.toArray (new Item[itemList.size ()]);
  }

  public Item[] getItemListFromCategoryId (int uCategoryId) throws DatabaseException
  {
    final ArrayList<Item> itemList = new ArrayList<Item> ();
    final String query = "SELECT * FROM item WHERE category_id = ?";
    final String queryNull = "SELECT * FROM item WHERE category_id is NULL";
    ResultSet results = null;
    PreparedStatement preparedStatement = null;
    try
    {
      if (uCategoryId != 0)
      {
        preparedStatement = connection.prepareStatement (query);
        preparedStatement.setInt (1, uCategoryId);
      } else
        preparedStatement = connection.prepareStatement (queryNull);

      results = preparedStatement.executeQuery ();
      if (results != null)
        while (results.next ())
        {
          final Item item = MenuModelFactory.eINSTANCE.createItem ();
          item.setCategoryId (results.getInt (ITEM_CATEGORY_ID));
          item.setCreationDate (results.getDate (ITEM_CREATION_DATE));
          item.setId (results.getInt (ITEM_ITEM_ID));
          item.setDescription (results.getString (ITEM_DESCRIPTION));
          item.setPrice (results.getDouble (ITEM_PRICE));
          item.setPriceNotes (results.getString (ITEM_PRICE_NOTES));

          itemList.add (item);
        }
    } catch (final SQLException e)
    {
      throw new DatabaseException (ERROR_COULD_NOT_RETRIEVE_DATA);
    } finally
    {
      try
      {
        if (results != null)
          results.close ();
        if (preparedStatement != null)
          preparedStatement.close ();
      } catch (final SQLException e)
      {
        throw new DatabaseException (ERROR_COULD_NOT_CLOSE_STATEMENT);
      }
    }
    return itemList.toArray (new Item[itemList.size ()]);
  }

  public void createMenuItem (MenuItem uMenuItem) throws DatabaseException
  {
    final String query = "INSERT INTO menu_item (item_id, menu_id, item_position) VALUES (?, ?, ?)";
    PreparedStatement preparedStatement = null;
    try
    {
      preparedStatement = connection.prepareStatement (query);
      preparedStatement.setInt (1, uMenuItem.getItemId ());
      preparedStatement.setInt (2, uMenuItem.getMenuId ());
      preparedStatement.setInt (3, uMenuItem.getItemPosition ());
      preparedStatement.executeUpdate ();
    } catch (final SQLException e)
    {
      throw new DatabaseException (ERROR_COULD_NOT_INSERT_DATA);
    } finally
    {
      try
      {
        if (preparedStatement != null)
          preparedStatement.close ();
      } catch (final SQLException e)
      {
        throw new DatabaseException (ERROR_COULD_NOT_CLOSE_STATEMENT);
      }
    }
  }

  public void createItem (Item uItem) throws DatabaseException
  {
    final String query = "INSERT INTO item (description, creation_date, price, price_notes, category_id) VALUES (?, NOW (), ?, ?, ?)";
    PreparedStatement preparedStatement = null;
    try
    {
      preparedStatement = connection.prepareStatement (query);
      preparedStatement.setString (1, uItem.getDescription ());
      preparedStatement.setDouble (2, uItem.getPrice ());
      preparedStatement.setString (3, uItem.getPriceNotes ());
      preparedStatement.setInt (4, uItem.getCategoryId ());
      preparedStatement.executeUpdate ();
    } catch (final SQLException e)
    {
      throw new DatabaseException (ERROR_COULD_NOT_INSERT_DATA);
    } finally
    {
      try
      {
        if (preparedStatement != null)
          preparedStatement.close ();
      } catch (final SQLException e)
      {
        throw new DatabaseException (ERROR_COULD_NOT_CLOSE_STATEMENT);
      }
    }
  }

  public void deleteItem (int uItemId) throws DatabaseException
  {
    final String query = "DELETE FROM item WHERE item_id = ?";
    PreparedStatement preparedStatement = null;
    try
    {
      preparedStatement = connection.prepareStatement (query);
      preparedStatement.setInt (1, uItemId);
      preparedStatement.executeUpdate ();
    } catch (final SQLException e)
    {
      throw new DatabaseException (ERROR_COULD_NOT_DELETE_DATA);
    } finally
    {
      try
      {
        if (preparedStatement != null)
          preparedStatement.close ();
      } catch (final SQLException e)
      {
        throw new DatabaseException (ERROR_COULD_NOT_CLOSE_STATEMENT);
      }
    }
  }

  private boolean checkUniqueCategoryDescription (String uDescription) throws DatabaseException
  {
    final Category[] elements = getCategoryList ();
    for (int i = 0; i < elements.length; i++)
    {
      final Category item = elements[i];
      if (item.getDescription ().compareToIgnoreCase (uDescription) == 0)
        return false;
    }
    return true;
  }

  public boolean checkUniqueMenuItem (int uItemId, int uMenuId) throws DatabaseException
  {
    final Item[] elements = getItemListFromMenuId (uMenuId);
    for (int i = 0; i < elements.length; i++)
    {
      final Item item = elements[i];
      if (item.getId () == uItemId)
        return false;
    }
    return true;
  }

  public boolean IsConnected ()
  {
    return isConnected;
  }

  public void closeConnection () throws DatabaseException
  {
    if (connection != null)
      try
      {
        connection.close ();
        isConnected = false;
      } catch (final SQLException e)
      {
        throw new DatabaseException (ERROR_COULD_NOT_CLOSE_CONNECTION);
      }
  }

  public Connection getConnection ()
  {
    return connection;
  }

  public String getCategoryDescription (int uCategoryId) throws DatabaseException
  {
    PreparedStatement preparedStatement = null;
    ResultSet results = null;
    String pageData = null;
    try
    {
      preparedStatement = connection.prepareStatement ("SELECT description FROM category WHERE category_id = ?");
      preparedStatement.setInt (1, uCategoryId);
      results = preparedStatement.executeQuery ();
      if (results != null)
        while (results.next ())
          pageData = results.getString (1);
    } catch (final SQLException e)
    {
      throw new DatabaseException (ERROR_COULD_NOT_LOAD_CATEGORY_DECRIPTION);
    } finally
    {
      try
      {
        if (results != null)
          results.close ();
        if (preparedStatement != null)
          preparedStatement.close ();
      } catch (final SQLException e)
      {
        throw new DatabaseException (ERROR_COULD_NOT_CLOSE_STATEMENT);
      }
    }
    return pageData;
  }

  public void deleteAllMenuItems (int uMenuId) throws DatabaseException
  {
    final String query = "DELETE FROM menu_item WHERE menu_id = ?";
    PreparedStatement preparedStatement = null;
    try
    {
      preparedStatement = connection.prepareStatement (query);
      preparedStatement.setInt (1, uMenuId);
      preparedStatement.executeUpdate ();
    } catch (final SQLException e)
    {
      throw new DatabaseException (ERROR_COULD_NOT_DELETE_DATA);
    } finally
    {
      try
      {
        if (preparedStatement != null)
          preparedStatement.close ();
      } catch (final SQLException e)
      {
        throw new DatabaseException (ERROR_COULD_NOT_CLOSE_STATEMENT);
      }
    }
  }

  public MenuItem[] getMenuItemListFromMenuId (int uMenuId) throws DatabaseException
  {
    final ArrayList<MenuItem> menuItemList = new ArrayList<MenuItem> ();
    final String query = "SELECT * FROM menu_item WHERE menu_id = ?";
    ResultSet results = null;
    PreparedStatement preparedStatement = null;
    try
    {
      preparedStatement = connection.prepareStatement (query);
      preparedStatement.setInt (1, uMenuId);
      results = preparedStatement.executeQuery ();
      if (results != null)
        while (results.next ())
        {
          final MenuItem menuItem = MenuModelFactory.eINSTANCE.createMenuItem ();
          menuItem.setItemId (results.getInt (MENU_ITEM_ITEM_ID));
          menuItem.setMenuId (results.getInt (MENU_ITEM_MENU_ID));
          menuItem.setItemPosition (results.getInt (MENU_ITEM_ITEM_POSITION));

          menuItemList.add (menuItem);
        }
    } catch (final SQLException e)
    {
      throw new DatabaseException (ERROR_COULD_NOT_RETRIEVE_DATA);
    } finally
    {
      try
      {
        if (results != null)
          results.close ();
        if (preparedStatement != null)
          preparedStatement.close ();
      } catch (final SQLException e)
      {
        throw new DatabaseException (ERROR_COULD_NOT_CLOSE_STATEMENT);
      }
    }
    return menuItemList.toArray (new MenuItem[menuItemList.size ()]);
  }
}
