package it.matiuz.menumaker.ui;

import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

/**
 * This workbench advisor creates the window advisor, and specifies the
 * perspective id for the initial window.
 */
public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor
{
  private static final String PERSPECTIVE_ID = "it.matiuz.menumaker.ui.perspective";

  @Override
  public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor (IWorkbenchWindowConfigurer configurer)
  {
    return new ApplicationWorkbenchWindowAdvisor (configurer);
  }

  @Override
  public String getInitialWindowPerspectiveId ()
  {
    return PERSPECTIVE_ID;
  }
}