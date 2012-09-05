package it.matiuz.menumaker.ui.actions;

import it.matiuz.menumaker.ui.MenumakerPluginUI;
import it.matiuz.menumaker.ui.tools.DatabaseException;
import it.matiuz.menumaker.ui.tools.DatabaseManager;
import it.matiuz.menumaker.ui.tools.IMenuModelHandler.HandlerState;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

public class DisconnectAction extends Action implements IWorkbenchAction
{
  public static final String ID = "it.matiuz.menumaker.ui.actions.disconnect_action";

  private IWorkbenchWindow window;

  public DisconnectAction (IWorkbenchWindow uWindow, String uLabel)
  {
    window = uWindow;
    setText (uLabel);
    setId (ID);
    setActionDefinitionId (ID);
  }

  @Override
  public void run ()
  {
    if (!MenumakerPluginUI.getMenuModelHandler ().getHandlerState ().equals (HandlerState.IDLE))
    {
      MessageDialog.openWarning (window.getShell (), "Warning", "First, close current menu");
      return;
    }

    final DatabaseManager database = MenumakerPluginUI.getDatabase ();
    try
    {
      database.closeConnection ();
      MenumakerPluginUI.getMenuModelNotificator ().notifyDisconnect ();
    } catch (final DatabaseException uException)
    {
      MessageDialog.openError (window.getShell (), "Error", uException.getMessage ());
    }
  }

  @Override
  public void dispose ()
  {
    window = null;
  }
}
