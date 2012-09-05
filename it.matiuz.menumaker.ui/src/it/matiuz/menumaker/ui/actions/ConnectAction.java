package it.matiuz.menumaker.ui.actions;

import it.matiuz.menumaker.ui.MenumakerPluginUI;
import it.matiuz.menumaker.ui.dialogs.ConnectionDialog;
import it.matiuz.menumaker.ui.tools.DatabaseException;
import it.matiuz.menumaker.ui.tools.DatabaseManager;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

public class ConnectAction extends Action implements IWorkbenchAction
{
  public static final String ID = "it.matiuz.menumaker.ui.actions.connect_action";

  private IWorkbenchWindow window;

  public ConnectAction (IWorkbenchWindow uWindow, String uLabel)
  {
    window = uWindow;
    setText (uLabel);
    setId (ID);
    setActionDefinitionId (ID);
  }

  @Override
  public void run ()
  {
    final ConnectionDialog dialog = new ConnectionDialog (window.getShell ());

    if (dialog.open () != Window.OK)
      return;

    final DatabaseManager database = MenumakerPluginUI.getDatabase ();

    if (database.IsConnected ())
      try
      {
        database.closeConnection ();
      } catch (final DatabaseException uException)
      {
        MessageDialog.openError (window.getShell (), "Error", uException.getMessage ());
        return;
      }

    try
    {
      database.createConnection (dialog.getAddress (), dialog.getPort (), dialog.getName (), dialog.getLogin (), dialog.getPassword ());
      MenumakerPluginUI.getMenuModelNotificator ().notifyConnect ();
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
