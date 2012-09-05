package it.matiuz.menumaker.ui.actions;

import it.matiuz.menumaker.ui.dialogs.ImageDialog;
import it.matiuz.menumaker.ui.tools.DialogTools;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

public class SetImageAction extends Action implements IWorkbenchAction
{
  public static final String ID = "it.matiuz.menumaker.ui.actions.set_images_action";

  private IWorkbenchWindow window;

  public SetImageAction (IWorkbenchWindow uWindow, String uLabel)
  {
    window = uWindow;

    setText (uLabel);
    setId (ID);
    setActionDefinitionId (ID);
  }

  @Override
  public void run ()
  {
    final ImageDialog dialog = new ImageDialog (window.getShell ());
    DialogTools.storeImageDialogData (dialog);

    if (dialog.open () == Window.OK)
      DialogTools.retrieveImageDialogData (dialog);
  }

  @Override
  public void dispose ()
  {
    window = null;
  }
}
