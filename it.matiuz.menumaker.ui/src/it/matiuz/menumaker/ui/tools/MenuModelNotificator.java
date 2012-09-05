package it.matiuz.menumaker.ui.tools;

import it.matiuz.menumaker.ui.listeners.IMenuModelListener;

import org.eclipse.core.runtime.ListenerList;

public class MenuModelNotificator
{
  private final ListenerList listenerList;

  public MenuModelNotificator ()
  {
    listenerList = new ListenerList ();
  }

  public void addMenuModelListener (IMenuModelListener uListener)
  {
    listenerList.add (uListener);
  }

  public void removeMenuModelListener (IMenuModelListener uListener)
  {
    listenerList.remove (uListener);
  }

  public void addConnectionListener (IConnectionListener uListener)
  {
    listenerList.add (uListener);
  }

  public void removeConnectionListener (IConnectionListener uListener)
  {
    listenerList.remove (uListener);
  }

  public void notifyAddItem ()
  {
    final Object[] listeners = listenerList.getListeners ();
    for (int i = 0; i < listeners.length; i++)
      if (listeners[i] instanceof IMenuModelListener)
        ((IMenuModelListener) listeners[i]).onAddItem ();
  }

  public void notifyUpdateItem ()
  {
    final Object[] listeners = listenerList.getListeners ();
    for (int i = 0; i < listeners.length; i++)
      if (listeners[i] instanceof IMenuModelListener)
        ((IMenuModelListener) listeners[i]).onUpdateItem ();
  }

  public void notifyRemoveItem ()
  {
    final Object[] listeners = listenerList.getListeners ();
    for (int i = 0; i < listeners.length; i++)
      if (listeners[i] instanceof IMenuModelListener)
        ((IMenuModelListener) listeners[i]).onRemoveItem ();
  }

  public void notifyAddCategory ()
  {
    final Object[] listeners = listenerList.getListeners ();
    for (int i = 0; i < listeners.length; i++)
      if (listeners[i] instanceof IMenuModelListener)
        ((IMenuModelListener) listeners[i]).onAddCategory ();
  }

  public void notifyUpdateCategory ()
  {
    final Object[] listeners = listenerList.getListeners ();
    for (int i = 0; i < listeners.length; i++)
      if (listeners[i] instanceof IMenuModelListener)
        ((IMenuModelListener) listeners[i]).onUpdateCategory ();
  }

  public void notifyRemoveCategory ()
  {
    final Object[] listeners = listenerList.getListeners ();
    for (int i = 0; i < listeners.length; i++)
      if (listeners[i] instanceof IMenuModelListener)
        ((IMenuModelListener) listeners[i]).onRemoveCategory ();
  }

  public void notifyLoadMenu ()
  {
    final Object[] listeners = listenerList.getListeners ();
    for (int i = 0; i < listeners.length; i++)
      if (listeners[i] instanceof IMenuModelListener)
        ((IMenuModelListener) listeners[i]).onLoadMenu ();
  }

  public void notifySaveMenu ()
  {
    final Object[] listeners = listenerList.getListeners ();
    for (int i = 0; i < listeners.length; i++)
      if (listeners[i] instanceof IMenuModelListener)
        ((IMenuModelListener) listeners[i]).onSaveMenu ();
  }

  public void notifyAddMenu ()
  {
    final Object[] listeners = listenerList.getListeners ();
    for (int i = 0; i < listeners.length; i++)
      if (listeners[i] instanceof IMenuModelListener)
        ((IMenuModelListener) listeners[i]).onAddMenu ();
  }

  public void notifyUpdateMenu ()
  {
    final Object[] listeners = listenerList.getListeners ();
    for (int i = 0; i < listeners.length; i++)
      if (listeners[i] instanceof IMenuModelListener)
        ((IMenuModelListener) listeners[i]).onUpdateMenu ();
  }

  public void notifyRemoveMenu ()
  {
    final Object[] listeners = listenerList.getListeners ();
    for (int i = 0; i < listeners.length; i++)
      if (listeners[i] instanceof IMenuModelListener)
        ((IMenuModelListener) listeners[i]).onRemoveMenu ();
  }

  public void notifyCloseMenu ()
  {
    final Object[] listeners = listenerList.getListeners ();
    for (int i = 0; i < listeners.length; i++)
      if (listeners[i] instanceof IMenuModelListener)
        ((IMenuModelListener) listeners[i]).onCloseMenu ();
  }

  public void notifyConnect ()
  {
    final Object[] listeners = listenerList.getListeners ();
    for (int i = 0; i < listeners.length; i++)
      if (listeners[i] instanceof IConnectionListener)
        ((IConnectionListener) listeners[i]).onConnect ();
  }

  public void notifyDisconnect ()
  {
    final Object[] listeners = listenerList.getListeners ();
    for (int i = 0; i < listeners.length; i++)
      if (listeners[i] instanceof IConnectionListener)
        ((IConnectionListener) listeners[i]).onDisconnect ();
  }
}
