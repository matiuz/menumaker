package it.matiuz.menumaker.ui.tools;

import it.matiuz.menumaker.model.Item;
import it.matiuz.menumaker.model.Menu;

public interface IMenuModelHandler
{
  public static enum HandlerState {
    IDLE, CREATE, EDIT
  };

  public static enum ItemMovingDirection {
    UP, DOWN
  };

  public abstract Menu getMenu ();

  public abstract void loadMenu (int uMenuId) throws MenuHandlerException;

  public abstract void addMenu (Menu uMenu);

  public abstract void updateMenu (Menu uMenu);

  public abstract void closeMenu ();

  public abstract void removeMenu (Menu uMenu) throws MenuHandlerException;

  public abstract void saveMenu () throws MenuHandlerException;

  public abstract void addItem (Item uItem);

  public abstract void removeItem (Item uItem);

  public HandlerState getHandlerState ();

  public boolean isDirty ();

  public abstract void updateItem (Item uItem);

  public abstract void moveItem (Item uItem, ItemMovingDirection uDirection);
}
