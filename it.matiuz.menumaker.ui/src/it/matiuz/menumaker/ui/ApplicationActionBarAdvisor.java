package it.matiuz.menumaker.ui;

import it.matiuz.menumaker.model.Menu;
import it.matiuz.menumaker.ui.actions.ConnectAction;
import it.matiuz.menumaker.ui.actions.DisconnectAction;
import it.matiuz.menumaker.ui.actions.PrintAction;
import it.matiuz.menumaker.ui.actions.SetFontAction;
import it.matiuz.menumaker.ui.actions.SetImageAction;
import it.matiuz.menumaker.ui.actions.SetPageAction;
import it.matiuz.menumaker.ui.listeners.IMenuModelListener;
import it.matiuz.menumaker.ui.listeners.MenuModelAdapter;
import it.matiuz.menumaker.ui.tools.DatabaseManager;
import it.matiuz.menumaker.ui.tools.IConnectionListener;
import it.matiuz.menumaker.ui.tools.MenuModelHandler;
import it.matiuz.menumaker.ui.tools.PrintConfigurator.FontType;

import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.tango.icons.ITangoIconsImages;
import org.tango.icons.TangoIconsPlugin;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor
{
  private IWorkbenchAction connectAction;
  private IWorkbenchAction disconnectAction;
  private IWorkbenchAction exitAction;
  private IWorkbenchAction setItemFontAction;
  private IWorkbenchAction setCategoryFontAction;
  private IWorkbenchAction setImagesAction;
  private IWorkbenchAction setPageAction;
  private IWorkbenchAction printAction;

  private final IMenuModelListener modelListener;
  private final IConnectionListener connectionListener;

  public ApplicationActionBarAdvisor (IActionBarConfigurer configurer)
  {
    super (configurer);
    modelListener = new MenuModelAdapter ()
    {
      @Override
      public void onLoadMenu ()
      {
        updateActions ();
      }

      @Override
      public void onCloseMenu ()
      {
        updateActions ();
      }

      @Override
      public void onUpdateMenu ()
      {
        updateActions ();
      }

      @Override
      public void onRemoveMenu ()
      {
        updateActions ();
      }
    };

    connectionListener = new IConnectionListener ()
    {

      @Override
      public void onConnect ()
      {
        updateActions ();
      }

      @Override
      public void onDisconnect ()
      {
        updateActions ();
      }
    };
    MenumakerPluginUI.getMenuModelNotificator ().addConnectionListener (connectionListener);
    MenumakerPluginUI.getMenuModelNotificator ().addMenuModelListener (modelListener);
  }

  @Override
  protected void makeActions (final IWorkbenchWindow window)
  {
    {
      setCategoryFontAction = new SetFontAction (window, "Set up category font", FontType.CATEGORY_FONT);
      setCategoryFontAction.setImageDescriptor (TangoIconsPlugin.getSmallImageDescriptor (ITangoIconsImages.APPS_PREFERENCES_DESKTOP_FONT));
      register (setCategoryFontAction);
    }
    {
      setItemFontAction = new SetFontAction (window, "Set up item font", FontType.ITEM_FONT);
      setItemFontAction.setImageDescriptor (TangoIconsPlugin.getSmallImageDescriptor (ITangoIconsImages.APPS_PREFERENCES_DESKTOP_FONT));
      register (setItemFontAction);
    }
    {
      setImagesAction = new SetImageAction (window, "Set up images");
      setImagesAction.setImageDescriptor (TangoIconsPlugin.getSmallImageDescriptor (ITangoIconsImages.CATEGORIES_APPLICATIONS_GRAPHICS));
      register (setImagesAction);
    }
    {
      setPageAction = new SetPageAction (window, "Set up page");
      setPageAction.setImageDescriptor (TangoIconsPlugin.getSmallImageDescriptor (ITangoIconsImages.CATEGORIES_PREFERENCES_SYSTEM));
      register (setPageAction);
    }
    {
      printAction = new PrintAction (window, "Print...");
      printAction.setImageDescriptor (TangoIconsPlugin.getSmallImageDescriptor (ITangoIconsImages.DEVICES_PRINTER));
      register (printAction);
    }
    {
      connectAction = new ConnectAction (window, "Connect");
      connectAction.setImageDescriptor (TangoIconsPlugin.getSmallImageDescriptor (ITangoIconsImages.DEVICES_PRINTER));
      register (connectAction);
    }
    {
      disconnectAction = new DisconnectAction (window, "Disconnect");
      disconnectAction.setImageDescriptor (TangoIconsPlugin.getSmallImageDescriptor (ITangoIconsImages.DEVICES_PRINTER));
      register (disconnectAction);
    }
    {
      exitAction = ActionFactory.QUIT.create (window);
      register (exitAction);
    }
    {
      exitAction = ActionFactory.QUIT.create (window);
      register (exitAction);
    }
    updateActions ();
  }

  private void updateActions ()
  {
    final MenuModelHandler handler = MenumakerPluginUI.getMenuModelHandler ();
    final DatabaseManager manager = MenumakerPluginUI.getDatabase ();
    final boolean connected = manager.IsConnected ();
    final Menu menu = handler.getMenu ();

    exitAction.setEnabled (true);

    connectAction.setEnabled (!connected);
    disconnectAction.setEnabled (connected);

    setItemFontAction.setEnabled (true);
    setCategoryFontAction.setEnabled (true);
    setImagesAction.setEnabled (true);
    setPageAction.setEnabled (true);
    printAction.setEnabled (true);

    if (menu != null)
    {
      if (menu.getItems ().size () == 0)
        printAction.setEnabled (false);
    } else
      printAction.setEnabled (false);
  }

  @Override
  protected void fillMenuBar (IMenuManager menuBar)
  {
    final MenuManager fileMenu = new MenuManager ("&File", IWorkbenchActionConstants.M_FILE);
    final MenuManager printMenu = new MenuManager ("&Print", "print");

    menuBar.add (fileMenu);
    menuBar.add (printMenu);

    fileMenu.add (connectAction);
    fileMenu.add (disconnectAction);
    fileMenu.add (new Separator ());
    fileMenu.add (exitAction);

    printMenu.add (setCategoryFontAction);
    printMenu.add (setItemFontAction);
    printMenu.add (new Separator ());
    printMenu.add (setImagesAction);
    printMenu.add (new Separator ());
    printMenu.add (setPageAction);
    printMenu.add (new Separator ());
    printMenu.add (printAction);
  }

  @Override
  protected void fillCoolBar (ICoolBarManager coolBar)
  {
  }

  @Override
  public void dispose ()
  {
    MenumakerPluginUI.getMenuModelNotificator ().removeConnectionListener (connectionListener);
    MenumakerPluginUI.getMenuModelNotificator ().removeMenuModelListener (modelListener);
  }
}
