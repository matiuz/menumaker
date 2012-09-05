package it.matiuz.menumaker.ui.views;

import it.matiuz.menumaker.model.Menu;
import it.matiuz.menumaker.model.MenuModelFactory;
import it.matiuz.menumaker.ui.MenumakerPluginUI;
import it.matiuz.menumaker.ui.listeners.IMenuModelListener;
import it.matiuz.menumaker.ui.listeners.MenuModelAdapter;
import it.matiuz.menumaker.ui.tools.DatabaseException;
import it.matiuz.menumaker.ui.tools.DatabaseManager;
import it.matiuz.menumaker.ui.tools.IConnectionListener;
import it.matiuz.menumaker.ui.tools.IMenuModelHandler;
import it.matiuz.menumaker.ui.tools.IMenuModelHandler.HandlerState;
import it.matiuz.menumaker.ui.tools.MenuHandlerException;
import it.matiuz.menumaker.ui.tools.MenuModelNotificator;

import java.text.SimpleDateFormat;
import java.util.Iterator;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.tango.icons.ITangoIconsImages;
import org.tango.icons.TangoIconsPlugin;

public class MenuView extends ViewPart
{
  public static final String ID = "it.matiuz.menumaker.ui.views.MenuView";

  private TableViewer menuTableViewer;
  private Table menuTable;
  private MenuViewContentProvider menuContentProvider;
  private IStructuredSelection menuSelection;

  private Action loadMenuAction;
  private Action saveMenuAction;
  private Action deleteMenuAction;
  private Action newMenuAction;
  private Action closeMenuAction;
  private Action updateMenuAction;
  private Action refreshAction;

  private final IMenuModelHandler handler;
  private final MenuModelNotificator notificator;

  private final IMenuModelListener modelListener;
  private final IConnectionListener connectionListener;

  public MenuView ()
  {
    handler = MenumakerPluginUI.getMenuModelHandler ();
    notificator = MenumakerPluginUI.getMenuModelNotificator ();
    modelListener = new MenuModelAdapter ()
    {
      @Override
      public void onUpdateMenu ()
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
        refreshView ();
      }

      @Override
      public void onDisconnect ()
      {
        updateActions ();
        refreshView ();
      }
    };

    notificator.addMenuModelListener (modelListener);
    notificator.addConnectionListener (connectionListener);
  }

  @Override
  public void createPartControl (Composite parent)
  {
    final Composite container = new Composite (parent, SWT.NONE);
    container.setLayout (new GridLayout ());
    menuContentProvider = new MenuViewContentProvider ();
    final Composite composite = new Composite (container, SWT.NONE);
    composite.setLayoutData (new GridData (SWT.FILL, SWT.CENTER, true, false));
    final GridLayout gridLayout = new GridLayout ();
    gridLayout.numColumns = 3;
    composite.setLayout (gridLayout);

    final Composite composite_1 = new Composite (container, SWT.NONE);
    composite_1.setLayoutData (new GridData (GridData.FILL, GridData.FILL, true, true));
    composite_1.setLayout (new FillLayout (SWT.VERTICAL));

    menuTableViewer = new TableViewer (composite_1, SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER);

    menuTableViewer.setContentProvider (menuContentProvider);
    menuTableViewer.setLabelProvider (new MenuViewLabelProvider ());
    menuTableViewer.setSorter (new MenuViewSorter ());

    menuTable = menuTableViewer.getTable ();
    menuTable.setHeaderVisible (true);

    final TableColumn descriptionColumn = new TableColumn (menuTable, SWT.NONE);
    descriptionColumn.setWidth (150);
    descriptionColumn.setText ("Description");

    final TableColumn creationDateColumn = new TableColumn (menuTable, SWT.NONE);
    creationDateColumn.setWidth (150);
    creationDateColumn.setText ("Creation date");

    menuTableViewer.setInput (new Object ());
    menuTableViewer.addSelectionChangedListener (new ISelectionChangedListener ()
    {
      @Override
      public void selectionChanged (SelectionChangedEvent uEvent)
      {
        menuSelection = null;
        if (uEvent.getSelection () instanceof IStructuredSelection)
          menuSelection = (IStructuredSelection) uEvent.getSelection ();
        updateActions ();
      }
    });

    menuTable.addKeyListener (new KeyAdapter ()
    {
      @Override
      public void keyPressed (KeyEvent uEvent)
      {
        switch (uEvent.keyCode)
        {
          case SWT.F5:
            if (refreshAction.isEnabled ())
              refreshAction.run ();
            break;
          case SWT.DEL:
            if (deleteMenuAction.isEnabled ())
              deleteMenuAction.run ();
            break;
          default:
            break;
        }
      }
    });

    createActions ();
    inizializeMenu ();
    initializeToolBar ();
    updateActions ();
  }

  private void implementLoadMenu ()
  {
    final Menu menu = (Menu) menuSelection.getFirstElement ();

    if (handler.isDirty ())
      if (MessageDialog.openConfirm (getSite ().getShell (), "Warning", "Save changes?"))
        implementSaveMenu ();

    try
    {
      handler.loadMenu (menu.getId ());
    } catch (final MenuHandlerException e)
    {
      MessageDialog.openError (getSite ().getShell (), "Error", e.getMessage ());
    }

    notificator.notifyLoadMenu ();

    updateActions ();
    refreshView ();
  }

  private void implementSaveMenu ()
  {
    try
    {
      handler.saveMenu ();
    } catch (final MenuHandlerException e)
    {
      MessageDialog.openError (getSite ().getShell (), "Error", e.getMessage ());
    }

    notificator.notifySaveMenu ();

    updateActions ();
    refreshView ();
  }

  private void implementUpdateMenu ()
  {
    final InputDialog dialog = new InputDialog (getSite ().getShell (), "Menu", "Description:", handler.getMenu ().getDescription (), new IInputValidator ()
    {
      @Override
      public String isValid (String uText)
      {
        if (uText.length () == 0)
          return "Invalid description";
        return null;
      }

    });
    if (dialog.open () == Window.OK)
    {
      final Menu menu = MenuModelFactory.eINSTANCE.createMenu ();
      menu.setDescription (dialog.getValue ());

      handler.updateMenu (menu);
      notificator.notifyUpdateMenu ();

      updateActions ();
      refreshView ();
    }
  }

  private void implementCloseMenu ()
  {
    if (handler.isDirty ())
      if (MessageDialog.openConfirm (getSite ().getShell (), "Warning", "Save changes?"))
        implementSaveMenu ();

    handler.closeMenu ();
    notificator.notifyCloseMenu ();

    updateActions ();
    refreshView ();
  }

  private void implementNewMenu ()
  {
    if (handler.isDirty ())
      if (MessageDialog.openConfirm (getSite ().getShell (), "Warning", "Save changes?"))
        implementSaveMenu ();

    final InputDialog dialog = new InputDialog (getSite ().getShell (), "Menu", "Description:", "Undefined", new IInputValidator ()
    {

      @Override
      public String isValid (String uText)
      {
        if (uText.length () == 0)
          return "Invalid description";
        return null;
      }
    });

    if (dialog.open () == Window.OK)
    {
      final Menu menu = MenuModelFactory.eINSTANCE.createMenu ();
      menu.setDescription (dialog.getValue ());

      handler.addMenu (menu);
      notificator.notifyAddMenu ();

      updateActions ();
      refreshView ();
    }
  }

  private void implementDeleteMenu ()
  {
    if (!MessageDialog.openConfirm (getSite ().getShell (), "Delete", "Remove menu?"))
      return;

    final Iterator itemIterator = menuSelection.iterator ();

    while (itemIterator.hasNext ())
    {
      final Menu currentMenu = (Menu) itemIterator.next ();

      try
      {
        handler.removeMenu (currentMenu);
      } catch (final MenuHandlerException uMenuHandlerException)
      {
        MessageDialog.openError (getSite ().getShell (), "Error", uMenuHandlerException.getMessage ());
      }

      notificator.notifyRemoveMenu ();
    }

    menuSelection = null;
    updateActions ();
    refreshView ();
  }

  private void createActions ()
  {
    updateMenuAction = new Action ("Update", TangoIconsPlugin.getSmallImageDescriptor (ITangoIconsImages.APPS_ACCESSORIES_TEXT_EDITOR))
    {
      @Override
      public void run ()
      {
        implementUpdateMenu ();
      }
    };
    updateMenuAction.setToolTipText ("Update menu description");
    updateMenuAction.setEnabled (true);

    loadMenuAction = new Action ("Load", TangoIconsPlugin.getSmallImageDescriptor (ITangoIconsImages.ACTIONS_DOCUMENT_OPEN))
    {
      @Override
      public void run ()
      {
        implementLoadMenu ();
      }
    };
    loadMenuAction.setToolTipText ("Load menu");
    loadMenuAction.setEnabled (true);

    saveMenuAction = new Action ("Save", TangoIconsPlugin.getSmallImageDescriptor (ITangoIconsImages.ACTIONS_DOCUMENT_SAVE))
    {
      @Override
      public void run ()
      {
        implementSaveMenu ();
      }
    };
    saveMenuAction.setToolTipText ("Save menu");
    saveMenuAction.setEnabled (true);

    deleteMenuAction = new Action ("Delete", TangoIconsPlugin.getSmallImageDescriptor (ITangoIconsImages.ACTIONS_LIST_REMOVE))
    {
      @Override
      public void run ()
      {
        implementDeleteMenu ();
      }
    };
    deleteMenuAction.setToolTipText ("Delete menu");
    deleteMenuAction.setEnabled (true);

    newMenuAction = new Action ("New", TangoIconsPlugin.getSmallImageDescriptor (ITangoIconsImages.ACTIONS_LIST_ADD))
    {
      @Override
      public void run ()
      {
        implementNewMenu ();
      }
    };
    newMenuAction.setToolTipText ("New menu");
    newMenuAction.setEnabled (true);

    closeMenuAction = new Action ("Close", TangoIconsPlugin.getSmallImageDescriptor (ITangoIconsImages.ACTIONS_SYSTEM_LOG_OUT))
    {
      @Override
      public void run ()
      {
        implementCloseMenu ();
      }
    };
    closeMenuAction.setToolTipText ("Close menu");
    closeMenuAction.setEnabled (true);

    refreshAction = new Action ("Refresh")
    {
      @Override
      public void run ()
      {
        refreshView ();
      }
    };
    refreshAction.setEnabled (true);
  }

  private void inizializeMenu ()
  {
    final IMenuManager menuManager = getViewSite ().getActionBars ().getMenuManager ();
    menuManager.add (newMenuAction);
    menuManager.add (updateMenuAction);
    menuManager.add (closeMenuAction);
    menuManager.add (new Separator ());
    menuManager.add (loadMenuAction);
    menuManager.add (saveMenuAction);
    menuManager.add (deleteMenuAction);
  }

  private void initializeToolBar ()
  {
    final IToolBarManager toolbarManager = getViewSite ().getActionBars ().getToolBarManager ();
    toolbarManager.add (newMenuAction);
    toolbarManager.add (updateMenuAction);
    toolbarManager.add (closeMenuAction);
    toolbarManager.add (new Separator ());
    toolbarManager.add (loadMenuAction);
    toolbarManager.add (saveMenuAction);
    toolbarManager.add (deleteMenuAction);
  }

  @Override
  public void setFocus ()
  {
    menuTableViewer.getControl ().setFocus ();
  }

  private class MenuViewContentProvider extends MenuMakerContentProvider
  {
    @Override
    public Object[] fetchElements ()
    {
      final DatabaseManager dbManager = MenumakerPluginUI.getDatabase ();
      if (dbManager.IsConnected ())
        try
        {
          return dbManager.getMenuList ();
        } catch (final DatabaseException e)
        {
          MessageDialog.openError (getSite ().getShell (), "Error", e.getMessage ());
          return new Object[0];
        }

      return new Object[0];
    }
  }

  private class MenuViewSorter extends ViewerSorter
  {
    @Override
    public int compare (Viewer viewer, Object object1, Object object2)
    {
      return ((Menu) object1).getDescription ().compareToIgnoreCase (((Menu) object2).getDescription ());
    }
  }

  private class MenuViewLabelProvider extends LabelProvider implements ITableLabelProvider
  {
    @Override
    public Image getColumnImage (Object uElement, int uColumnIndex)
    {
      return null;
    }

    @Override
    public Image getImage (Object uElement)
    {
      return PlatformUI.getWorkbench ().getSharedImages ().getImage (ISharedImages.IMG_OBJ_ELEMENT);
    }

    @Override
    public String getColumnText (Object uElement, int uColumnIndex)
    {
      switch (uColumnIndex)
      {
        case 0:
          return ((Menu) uElement).getDescription ();
        case 1:
          final SimpleDateFormat formatter = new SimpleDateFormat ("dd-MM-yyyy");
          return formatter.format (((Menu) uElement).getCreationDate ());
        default:
          return "";
      }
    }
  }

  private void refreshView ()
  {
    if (menuContentProvider != null)
    {
      menuContentProvider.reload ();
      menuTableViewer.refresh ();
    }
  }

  private void updateActions ()
  {
    if (!MenumakerPluginUI.getDatabase ().IsConnected ())
    {
      disableActions ();
      return;
    }
    refreshAction.setEnabled (true);
    newMenuAction.setEnabled (true);

    if (menuSelection != null && !menuSelection.isEmpty ())
    {
      deleteMenuAction.setEnabled (true);

      if (menuSelection.size () == 1)
        loadMenuAction.setEnabled (true);
      else
        loadMenuAction.setEnabled (false);

    } else
    {
      deleteMenuAction.setEnabled (false);
      loadMenuAction.setEnabled (false);
    }
    updateMenuAction.setEnabled (!handler.getHandlerState ().equals (HandlerState.IDLE));
    closeMenuAction.setEnabled (!handler.getHandlerState ().equals (HandlerState.IDLE));
    saveMenuAction.setEnabled (handler.isDirty ());
  }

  private void disableActions ()
  {
    refreshAction.setEnabled (false);
    loadMenuAction.setEnabled (false);
    saveMenuAction.setEnabled (false);
    deleteMenuAction.setEnabled (false);
    newMenuAction.setEnabled (false);
    closeMenuAction.setEnabled (false);
    updateMenuAction.setEnabled (false);
  }

  @Override
  public void dispose ()
  {
    notificator.removeMenuModelListener (modelListener);
    notificator.removeConnectionListener (connectionListener);
    super.dispose ();
  }
}
