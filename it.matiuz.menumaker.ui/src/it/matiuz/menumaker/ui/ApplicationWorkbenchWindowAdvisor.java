package it.matiuz.menumaker.ui;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor
{

  public ApplicationWorkbenchWindowAdvisor (IWorkbenchWindowConfigurer configurer)
  {
    super (configurer);
  }

  @Override
  public ActionBarAdvisor createActionBarAdvisor (IActionBarConfigurer configurer)
  {
    return new ApplicationActionBarAdvisor (configurer);
  }

  @Override
  public void preWindowOpen ()
  {
    final IWorkbenchWindowConfigurer configurer = getWindowConfigurer ();
    final Rectangle clientArea = PlatformUI.getWorkbench ().getDisplay ().getClientArea ();
    configurer.setInitialSize (new Point (clientArea.width, clientArea.height));
    configurer.setShowCoolBar (false);
    configurer.setShowStatusLine (false);
  }
}
