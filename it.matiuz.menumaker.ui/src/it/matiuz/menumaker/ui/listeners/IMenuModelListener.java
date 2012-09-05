package it.matiuz.menumaker.ui.listeners;

public interface IMenuModelListener
{
  public abstract void onSaveMenu ();

  public abstract void onLoadMenu ();

  public abstract void onAddCategory ();

  public abstract void onUpdateCategory ();

  public abstract void onRemoveCategory ();

  public abstract void onAddItem ();

  public abstract void onUpdateItem ();

  public abstract void onRemoveItem ();

  public abstract void onAddMenu ();

  public abstract void onUpdateMenu ();

  public abstract void onRemoveMenu ();

  public abstract void onCloseMenu ();
}
