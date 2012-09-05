package it.matiuz.menumaker.ui.actions;

import it.matiuz.menumaker.ui.dialogs.PageDialog;
import it.matiuz.menumaker.ui.tools.DialogTools;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

public class SetPageAction extends Action implements IWorkbenchAction
{
  public static final String ID = "it.matiuz.menumaker.ui.actions.set_page_action";

  private IWorkbenchWindow window;

  public SetPageAction (IWorkbenchWindow uWindow, String uLabel)
  {
    window = uWindow;
    setText (uLabel);
    setId (ID);
    setActionDefinitionId (ID);
  }

  @Override
  public void run ()
  {
    final PageDialog dialog = new PageDialog (window.getShell ());
    DialogTools.storePageDialogData (dialog);

    if (dialog.open () == Window.OK)
      DialogTools.retrievePageDialogData (dialog);
  }

  @Override
  public void dispose ()
  {
    window = null;
  }
}
