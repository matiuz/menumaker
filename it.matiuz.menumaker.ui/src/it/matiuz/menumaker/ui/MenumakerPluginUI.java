package it.matiuz.menumaker.ui;

import it.matiuz.menumaker.ui.tools.DatabaseManager;
import it.matiuz.menumaker.ui.tools.MenuModelHandler;
import it.matiuz.menumaker.ui.tools.MenuModelNotificator;
import it.matiuz.menumaker.ui.tools.PrintConfigurator;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class MenumakerPluginUI extends AbstractUIPlugin
{
  public static final String PLUGIN_ID = "MenuMaker";

  private static MenumakerPluginUI plugin;
  private static MenuModelNotificator notificator;
  private static MenuModelHandler handler;
  private static DatabaseManager database;
  private static PrintConfigurator configurator;

  @Override
  public void start (BundleContext context) throws Exception
  {
    super.start (context);
    plugin = this;

    database = new DatabaseManager ();

    notificator = new MenuModelNotificator ();

    handler = new MenuModelHandler ();

    configurator = new PrintConfigurator ();
    configurator.loadProperties ();
  }

  @Override
  public void stop (BundleContext context) throws Exception
  {
    database = null;

    notificator = null;

    handler = null;

    configurator.saveProperties ();
    configurator = null;

    plugin = null;
    super.stop (context);
  }

  public static MenumakerPluginUI getDefault ()
  {
    return plugin;
  }

  public static DatabaseManager getDatabase ()
  {
    return database;
  }

  public static MenuModelNotificator getMenuModelNotificator ()
  {
    return notificator;
  }

  public static MenuModelHandler getMenuModelHandler ()
  {
    return handler;
  }

  public static PrintConfigurator getPrintConfigurator ()
  {
    return configurator;
  }

  public static ImageDescriptor getImageDescriptor (String path)
  {
    return imageDescriptorFromPlugin (PLUGIN_ID, path);
  }
}
