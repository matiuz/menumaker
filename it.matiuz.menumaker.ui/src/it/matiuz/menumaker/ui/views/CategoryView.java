package it.matiuz.menumaker.ui.views;

import it.matiuz.menumaker.model.Category;
import it.matiuz.menumaker.model.MenuModelFactory;
import it.matiuz.menumaker.ui.MenumakerPluginUI;
import it.matiuz.menumaker.ui.dialogs.CategoryDialog;
import it.matiuz.menumaker.ui.tools.DatabaseException;
import it.matiuz.menumaker.ui.tools.DatabaseManager;
import it.matiuz.menumaker.ui.tools.DialogTools;
import it.matiuz.menumaker.ui.tools.IConnectionListener;
import it.matiuz.menumaker.ui.tools.IMenuModelHandler;
import it.matiuz.menumaker.ui.tools.IMenuModelHandler.HandlerState;
import it.matiuz.menumaker.ui.tools.MenuModelNotificator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IOpenListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.OpenEvent;
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

public class CategoryView extends ViewPart
{
  public static String ID = "it.matiuz.menumaker.ui.views.CategoryView";

  private TableViewer categoryTableViewer;
  private Table categoryTable;
  private CategoryViewContentProvider categoryContentProvider;
  private IStructuredSelection categorySelection;

  private Action deleteCategoryAction;
  private Action newCategoryAction;
  private Action editCategoryAction;
  private Action refreshAction;

  private final IMenuModelHandler handler;
  private final MenuModelNotificator notificator;

  private final IConnectionListener connectionListener;

  public CategoryView ()
  {
    handler = MenumakerPluginUI.getMenuModelHandler ();
    notificator = MenumakerPluginUI.getMenuModelNotificator ();
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

    notificator.addConnectionListener (connectionListener);
  }

  @Override
  public void createPartControl (Composite parent)
  {
    final Composite container = new Composite (parent, SWT.NONE);
    container.setLayout (new GridLayout ());
    categoryContentProvider = new CategoryViewContentProvider ();
    final Composite composite = new Composite (container, SWT.NONE);
    composite.setLayoutData (new GridData (SWT.FILL, SWT.CENTER, true, false));
    final GridLayout gridLayout = new GridLayout ();
    gridLayout.numColumns = 3;
    composite.setLayout (gridLayout);

    final Composite composite_1 = new Composite (container, SWT.NONE);
    composite_1.setLayoutData (new GridData (GridData.FILL, GridData.FILL, true, true));
    composite_1.setLayout (new FillLayout (SWT.VERTICAL));

    categoryTableViewer = new TableViewer (composite_1, SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER);

    categoryTableViewer.setContentProvider (categoryContentProvider);
    categoryTableViewer.setLabelProvider (new CategoryViewLabelProvider ());
    categoryTableViewer.setSorter (new CategoryViewSorter ());

    categoryTable = categoryTableViewer.getTable ();
    categoryTable.setHeaderVisible (true);

    final TableColumn descriptionColumn = new TableColumn (categoryTable, SWT.NONE);
    descriptionColumn.setWidth (150);
    descriptionColumn.setText ("Description");

    final TableColumn printOrderColumn = new TableColumn (categoryTable, SWT.NONE);
    printOrderColumn.setWidth (150);
    printOrderColumn.setText ("Priority");

    categoryTableViewer.setInput (new Object ());
    categoryTableViewer.addSelectionChangedListener (new ISelectionChangedListener ()
    {
      @Override
      public void selectionChanged (SelectionChangedEvent uEvent)
      {
        categorySelection = null;
        if (uEvent.getSelection () instanceof IStructuredSelection)
          categorySelection = (IStructuredSelection) uEvent.getSelection ();
        updateActions ();
      }
    });

    categoryTableViewer.addOpenListener (new IOpenListener ()
    {

      @Override
      public void open (OpenEvent event)
      {
        if (editCategoryAction.isEnabled ())
          editCategoryAction.run ();
      }
    });
    categoryTable.addKeyListener (new KeyAdapter ()
    {
      @Override
      public void keyPressed (KeyEvent uEvent)
      {
        switch (uEvent.keyCode)
        {
          case SWT.F5:
            if (refreshAction.isEnabled ())
              refreshAction.run ();
            refreshView ();
            break;
          case SWT.DEL:
            if (deleteCategoryAction.isEnabled ())
              deleteCategoryAction.run ();
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

  private void implementNewCategory ()
  {
    final CategoryDialog dialog = new CategoryDialog (getSite ().getShell ());
    if (dialog.open () == Window.OK)
    {
      final DatabaseManager databaseManager = MenumakerPluginUI.getDatabase ();

      final Category category = MenuModelFactory.eINSTANCE.createCategory ();
      DialogTools.retrieveCategoryDialogData (dialog, category);

      final Connection connection = databaseManager.getConnection ();
      try
      {
        databaseManager.createCategory (category);
        try
        {
          connection.commit ();
        } catch (final SQLException e)
        {
          MessageDialog.openError (getSite ().getShell (), "Error", "Commit error");
        }

      } catch (final DatabaseException e)
      {
        try
        {
          connection.rollback ();
        } catch (final SQLException e1)
        {
          MessageDialog.openError (getSite ().getShell (), "Error", "Rollback error");
        }
        MessageDialog.openError (getSite ().getShell (), "Error", e.getMessage ());
        return;
      }
    }
    notificator.notifyAddCategory ();
    refreshView ();
    updateActions ();
  }

  private void implementEditCategory ()
  {
    if (!handler.getHandlerState ().equals (HandlerState.IDLE))
    {
      MessageDialog.openWarning (getSite ().getShell (), "Warning", "First, close current menu");
      return;
    }

    final CategoryDialog dialog = new CategoryDialog (getSite ().getShell ());
    final Category category = (Category) categorySelection.getFirstElement ();
    DialogTools.storeCategoryDialogData (dialog, category);

    if (dialog.open () == Window.OK)
    {
      final DatabaseManager databaseManager = MenumakerPluginUI.getDatabase ();

      DialogTools.retrieveCategoryDialogData (dialog, category);

      final Connection connection = databaseManager.getConnection ();
      try
      {
        databaseManager.updateCategory (category);
        try
        {
          connection.commit ();
        } catch (final SQLException e)
        {
          MessageDialog.openError (getSite ().getShell (), "Error", "Commit error");
        }

      } catch (final DatabaseException e)
      {
        try
        {
          connection.rollback ();
        } catch (final SQLException e1)
        {
          MessageDialog.openError (getSite ().getShell (), "Error", "Rollback error");
        }
        MessageDialog.openError (getSite ().getShell (), "Error", e.getMessage ());
        return;
      }
    }
    notificator.notifyUpdateCategory ();
    refreshView ();
    updateActions ();
  }

  @SuppressWarnings("unchecked")
  private void implementDeleteCategory ()
  {
    if (!handler.getHandlerState ().equals (HandlerState.IDLE))
    {
      MessageDialog.openWarning (getSite ().getShell (), "Warning", "First, close current menu");
      return;
    }

    if (!MessageDialog.openConfirm (getSite ().getShell (), "Delete", "Remove categories?"))
      return;

    final DatabaseManager databaseManager = MenumakerPluginUI.getDatabase ();
    final Iterator<Category> itemIterator = categorySelection.iterator ();

    while (itemIterator.hasNext ())
    {
      final Category currentCategory = itemIterator.next ();
      final Connection connection = databaseManager.getConnection ();

      try
      {
        databaseManager.deleteCategory (currentCategory.getId ());
        try
        {
          connection.commit ();
        } catch (final SQLException e)
        {
          MessageDialog.openError (getSite ().getShell (), "Error", "Commit error");
        }
      } catch (final DatabaseException e)
      {
        try
        {
          connection.rollback ();
        } catch (final SQLException e1)
        {
          MessageDialog.openError (getSite ().getShell (), "Error", "Rollback error");
        }
        MessageDialog.openError (getSite ().getShell (), "Error", e.getMessage ());
        return;
      }
    }
    notificator.notifyRemoveCategory ();
    refreshView ();
    updateActions ();
  }

  private void createActions ()
  {
    deleteCategoryAction = new Action ("Delete", TangoIconsPlugin.getSmallImageDescriptor (ITangoIconsImages.ACTIONS_LIST_REMOVE))
    {
      @Override
      public void run ()
      {
        implementDeleteCategory ();
      }
    };
    deleteCategoryAction.setToolTipText ("Delete selected categories");
    deleteCategoryAction.setEnabled (true);

    newCategoryAction = new Action ("New", TangoIconsPlugin.getSmallImageDescriptor (ITangoIconsImages.ACTIONS_LIST_ADD))
    {
      @Override
      public void run ()
      {
        implementNewCategory ();
      }
    };
    newCategoryAction.setToolTipText ("Add a new category");
    newCategoryAction.setEnabled (true);

    editCategoryAction = new Action ("Edit", TangoIconsPlugin.getSmallImageDescriptor (ITangoIconsImages.APPS_ACCESSORIES_TEXT_EDITOR))
    {
      @Override
      public void run ()
      {
        implementEditCategory ();
      }
    };
    editCategoryAction.setToolTipText ("Edit selected category");
    editCategoryAction.setEnabled (true);

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
    menuManager.add (newCategoryAction);
    menuManager.add (editCategoryAction);
    menuManager.add (deleteCategoryAction);
  }

  private void initializeToolBar ()
  {
    final IToolBarManager toolbarManager = getViewSite ().getActionBars ().getToolBarManager ();
    toolbarManager.add (newCategoryAction);
    toolbarManager.add (editCategoryAction);
    toolbarManager.add (deleteCategoryAction);
  }

  @Override
  public void setFocus ()
  {
    categoryTableViewer.getControl ().setFocus ();
  }

  private class CategoryViewContentProvider extends MenuMakerContentProvider
  {

    @Override
    public Object[] fetchElements ()
    {
      final DatabaseManager dbManager = MenumakerPluginUI.getDatabase ();
      if (dbManager.IsConnected ())
        try
        {
          return dbManager.getCategoryList ();
        } catch (final DatabaseException e)
        {
          MessageDialog.openError (getSite ().getShell (), "Error", e.getMessage ());
        }

      return new Object[0];
    }
  }

  private class CategoryViewSorter extends ViewerSorter
  {
    @Override
    public int compare (Viewer viewer, Object object1, Object object2)
    {
      return ((Category) object1).getDescription ().compareToIgnoreCase (((Category) object2).getDescription ());
    }
  }

  private class CategoryViewLabelProvider extends LabelProvider implements ITableLabelProvider
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
          return ((Category) uElement).getDescription ();
        case 1:
          return Integer.toString (((Category) uElement).getPriority ());
        default:
          return "";
      }
    }
  }

  private void refreshView ()
  {
    if (categoryContentProvider != null)
    {
      categoryContentProvider.reload ();
      categoryTableViewer.refresh ();
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
    newCategoryAction.setEnabled (true);

    if (categorySelection != null && !categorySelection.isEmpty ())
    {
      deleteCategoryAction.setEnabled (true);
      if (categorySelection.size () == 1)
        editCategoryAction.setEnabled (true);
      else
        editCategoryAction.setEnabled (false);
    } else
    {
      deleteCategoryAction.setEnabled (false);
      editCategoryAction.setEnabled (false);
    }
  }

  private void disableActions ()
  {
    editCategoryAction.setEnabled (false);
    newCategoryAction.setEnabled (false);
    deleteCategoryAction.setEnabled (false);
    refreshAction.setEnabled (false);
  }

  @Override
  public void dispose ()
  {
    notificator.removeConnectionListener (connectionListener);
    super.dispose ();
  }
}
