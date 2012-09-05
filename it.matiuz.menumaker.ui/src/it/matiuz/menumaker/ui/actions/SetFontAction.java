package it.matiuz.menumaker.ui.actions;

import it.matiuz.menumaker.ui.dialogs.FontDialog;
import it.matiuz.menumaker.ui.tools.DialogTools;
import it.matiuz.menumaker.ui.tools.PrintConfigurator.FontType;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

public class SetFontAction extends Action implements IWorkbenchAction
{
  public static final String ID = "it.matiuz.menumaker.ui.actions.set_font_action";

  private IWorkbenchWindow window;
  private final FontType fontType;

  public SetFontAction (IWorkbenchWindow uWindow, String uLabel, FontType uFontType)
  {
    window = uWindow;
    fontType = uFontType;
    setText (uLabel);
    setId (ID);
    setActionDefinitionId (ID);
  }

  @Override
  public void run ()
  {
    final FontDialog dialog = new FontDialog (window.getShell ());
    DialogTools.storeFontDialogData (dialog, fontType);

    if (fontType.equals (FontType.CATEGORY_FONT))
      dialog.setTitle ("Set category font");
    else
      dialog.setTitle ("Set item font");

    if (dialog.open () == Window.OK)
      DialogTools.retrieveFontDialogData (dialog, fontType);
  }

  @Override
  public void dispose ()
  {
    window = null;
  }
}
