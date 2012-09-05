package it.matiuz.menumaker.ui.views;

import it.matiuz.menumaker.model.Category;
import it.matiuz.menumaker.model.Item;
import it.matiuz.menumaker.model.MenuModelFactory;
import it.matiuz.menumaker.ui.MenumakerPluginUI;
import it.matiuz.menumaker.ui.dialogs.ItemDialog;
import it.matiuz.menumaker.ui.listeners.IMenuModelListener;
import it.matiuz.menumaker.ui.listeners.MenuModelAdapter;
import it.matiuz.menumaker.ui.tools.DatabaseException;
import it.matiuz.menumaker.ui.tools.DatabaseManager;
import it.matiuz.menumaker.ui.tools.DialogTools;
import it.matiuz.menumaker.ui.tools.IConnectionListener;
import it.matiuz.menumaker.ui.tools.IMenuModelHandler;
import it.matiuz.menumaker.ui.tools.IMenuModelHandler.HandlerState;
import it.matiuz.menumaker.ui.tools.MenuModelNotificator;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.emf.edit.ui.dnd.ViewerDragAdapter;
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
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.tango.icons.ITangoIconsImages;
import org.tango.icons.TangoIconsPlugin;

public class ItemView extends ViewPart
{
  public static final String ID = "it.matiuz.menumaker.ui.views.ItemView";
  
  private Combo categoryCombo;
  private Map<String, Integer> categoryMap;

  private TableViewer itemTableViewer;
  private Table itemTable;
  private ItemViewContentProvider itemContentProvider;
  private IStructuredSelection itemSelection;

  private Action deleteItemAction;
  private Action newItemAction;
  private Action editItemAction;
  private Action refreshAction;

  private final IMenuModelListener modelListener;
  private final IConnectionListener connectionListener;

  private final IMenuModelHandler handler;
  private final MenuModelNotificator notificator;

  public ItemView ()
  {
    handler = MenumakerPluginUI.getMenuModelHandler ();
    notificator = MenumakerPluginUI.getMenuModelNotificator ();
    modelListener = new MenuModelAdapter ()
    {
      @Override
      public void onUpdateCategory ()
      {
        loadCategoryNames ();
        updateActions ();
        refreshView ();
      }

      @Override
      public void onRemoveCategory ()
      {
        loadCategoryNames ();
        updateActions ();
        refreshView ();
      }

      @Override
      public void onAddCategory ()
      {
        loadCategoryNames ();
        updateActions ();
        refreshView ();
      }

      @Override
      public void onSaveMenu ()
      {
        updateActions ();
        refreshView ();
      }
    };

    connectionListener = new IConnectionListener ()
    {
      @Override
      public void onConnect ()
      {
        loadCategoryNames ();
        updateActions ();
        refreshView ();
      }

      @Override
      public void onDisconnect ()
      {
        categoryCombo.removeAll ();
        updateActions ();
        refreshView ();
      }
    };

    notificator.addConnectionListener (connectionListener);
    notificator.addMenuModelListener (modelListener);
  }

  @Override
  public void createPartControl (Composite parent)
  {
    final Composite container = new Composite (parent, SWT.NONE);
    container.setLayout (new GridLayout ());
    itemContentProvider = new ItemViewContentProvider ();
    final Composite composite = new Composite (container, SWT.NONE);
    composite.setLayoutData (new GridData (SWT.FILL, SWT.CENTER, true, false));
    final GridLayout gridLayout = new GridLayout ();
    gridLayout.numColumns = 2;
    composite.setLayout (gridLayout);

    final Label menuCategoryLabel = new Label (composite, SWT.NONE);
    menuCategoryLabel.setText ("Menu category:");

    categoryCombo = new Combo (composite, SWT.READ_ONLY);
    final GridData gd_categoryCombo = new GridData (SWT.LEFT, SWT.CENTER, true, false);
    gd_categoryCombo.minimumWidth = 100;
    categoryCombo.setLayoutData (gd_categoryCombo);
    categoryCombo.addModifyListener (new ModifyListener ()
    {
      @Override
      public void modifyText (ModifyEvent e)
      {
        if (categoryMap == null)
          return;

        itemContentProvider.setCategoryId (categoryMap.get (categoryCombo.getText ()) != null ? categoryMap.get (categoryCombo.getText ()).intValue () : 0);

        refreshView ();
      }
    });

    final Composite composite_1 = new Composite (container, SWT.NONE);
    composite_1.setLayoutData (new GridData (GridData.FILL, GridData.FILL, true, true));
    composite_1.setLayout (new FillLayout (SWT.VERTICAL));

    itemTableViewer = new TableViewer (composite_1, SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER);

    itemTableViewer.setContentProvider (itemContentProvider);
    itemTableViewer.setLabelProvider (new ItemViewLabelProvider ());
    itemTableViewer.setSorter (new ItemViewSorter ());

    itemTable = itemTableViewer.getTable ();
    itemTable.setHeaderVisible (true);

    final TableColumn descriptionColumn = new TableColumn (itemTable, SWT.NONE);
    descriptionColumn.setWidth (200);
    descriptionColumn.setText ("Description");

    final TableColumn priceColumn = new TableColumn (itemTable, SWT.NONE);
    priceColumn.setWidth (60);
    priceColumn.setText ("Price");

    final TableColumn priceNotesColumn = new TableColumn (itemTable, SWT.NONE);
    priceNotesColumn.setWidth (90);
    priceNotesColumn.setText ("Price notes");

    final TableColumn creationDateColumn = new TableColumn (itemTable, SWT.NONE);
    creationDateColumn.setWidth (150);
    creationDateColumn.setText ("Creation date");

    itemTableViewer.setInput (new Object ());

    itemTableViewer.addSelectionChangedListener (new ISelectionChangedListener ()
    {
      @Override
      public void selectionChanged (SelectionChangedEvent uEvent)
      {
        itemSelection = null;
        if (uEvent.getSelection () instanceof IStructuredSelection)
          itemSelection = (IStructuredSelection) uEvent.getSelection ();
        updateActions ();
      }
    });

    itemTable.addKeyListener (new KeyAdapter ()
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
            if (deleteItemAction.isEnabled ())
              deleteItemAction.run ();
            break;
          default:
            break;
        }
      }
    });

    itemTableViewer.addOpenListener (new IOpenListener ()
    {
      @Override
      public void open (OpenEvent event)
      {
        if (editItemAction.isEnabled ())
          editItemAction.run ();
      }
    });

    itemTableViewer.addDragSupport (DND.DROP_COPY | DND.DROP_MOVE, new Transfer[] { LocalTransfer.getInstance () }, new ViewerDragAdapter (itemTableViewer));

    createActions ();
    inizializeMenu ();
    initializeToolBar ();
    updateActions ();
  }

  private void implementNewItem ()
  {
    final ItemDialog dialog = new ItemDialog (getSite ().getShell ());
    if (dialog.open () == Window.OK)
    {
      final DatabaseManager databaseManager = MenumakerPluginUI.getDatabase ();
      final Item item = MenuModelFactory.eINSTANCE.createItem ();
      DialogTools.retrieveItemDialogData (dialog, item);

      final Connection connection = databaseManager.getConnection ();

      try
      {
        databaseManager.createItem (item);
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
    notificator.notifyAddItem ();
    refreshView ();
    updateActions ();
  }

  private void implementEditItem ()
  {
    if (!handler.getHandlerState ().equals (HandlerState.IDLE))
    {
      MessageDialog.openWarning (getSite ().getShell (), "Warning", "First, close current menu");
      return;
    }

    final ItemDialog dialog = new ItemDialog (getSite ().getShell ());
    final Item item = (Item) itemSelection.getFirstElement ();

    DialogTools.storeItemDialogData (dialog, item);

    if (dialog.open () == Window.OK)
    {
      DialogTools.retrieveItemDialogData (dialog, item);

      final DatabaseManager dbManager = MenumakerPluginUI.getDatabase ();
      final Connection connection = dbManager.getConnection ();

      try
      {
        dbManager.updateItem (item);
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
    notificator.notifyUpdateItem ();

    updateActions ();
    refreshView ();
  }

  @SuppressWarnings("unchecked")
  private void implementDeleteItem ()
  {
    if (!handler.getHandlerState ().equals (HandlerState.IDLE))
    {
      MessageDialog.openWarning (getSite ().getShell (), "Warning", "First, close current menu");
      return;
    }

    if (!MessageDialog.openConfirm (getSite ().getShell (), "Delete", "Remove items?"))
      return;

    final DatabaseManager databaseManager = MenumakerPluginUI.getDatabase ();
    final Iterator<Item> itemIterator = itemSelection.iterator ();

    while (itemIterator.hasNext ())
    {
      final Item currentItem = itemIterator.next ();
      final Connection connection = databaseManager.getConnection ();

      try
      {
        databaseManager.deleteItem (currentItem.getId ());
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
    notificator.notifyRemoveItem ();
    updateActions ();
    refreshView ();
  }

  private void createActions ()
  {
    deleteItemAction = new Action ("Delete", TangoIconsPlugin.getSmallImageDescriptor (ITangoIconsImages.ACTIONS_LIST_REMOVE))
    {
      @Override
      public void run ()
      {
        implementDeleteItem ();
      }
    };
    deleteItemAction.setToolTipText ("Delete selected items");
    deleteItemAction.setEnabled (true);

    newItemAction = new Action ("New", TangoIconsPlugin.getSmallImageDescriptor (ITangoIconsImages.ACTIONS_LIST_ADD))
    {
      @Override
      public void run ()
      {
        implementNewItem ();
      }
    };
    newItemAction.setToolTipText ("Add a new item");
    newItemAction.setEnabled (true);

    editItemAction = new Action ("Edit", TangoIconsPlugin.getSmallImageDescriptor (ITangoIconsImages.APPS_ACCESSORIES_TEXT_EDITOR))
    {
      @Override
      public void run ()
      {
        implementEditItem ();
      }
    };
    editItemAction.setToolTipText ("Edit selected item");
    editItemAction.setEnabled (true);

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
    menuManager.add (newItemAction);
    menuManager.add (editItemAction);
    menuManager.add (deleteItemAction);
  }

  private void initializeToolBar ()
  {
    final IToolBarManager toolbarManager = getViewSite ().getActionBars ().getToolBarManager ();
    toolbarManager.add (newItemAction);
    toolbarManager.add (editItemAction);
    toolbarManager.add (deleteItemAction);
  }

  @Override
  public void setFocus ()
  {
    itemTableViewer.getControl ().setFocus ();
  }

  private class ItemViewContentProvider extends MenuMakerContentProvider
  {
    private int categoryId;

    public ItemViewContentProvider ()
    {
      super ();
      categoryId = 0;
    }

    @Override
    public Object[] fetchElements ()
    {
      final DatabaseManager manager = MenumakerPluginUI.getDatabase ();
      if (manager.IsConnected ())
        try
        {
          return manager.getItemListFromCategoryId (categoryId);
        } catch (final DatabaseException e)
        {
          MessageDialog.openError (getSite ().getShell (), "Error", e.getMessage ());
          return new Object[0];
        }

      return new Object[0];
    }

    public void setCategoryId (int uCategoryId)
    {
      categoryId = uCategoryId;
    }
  }

  private class ItemViewSorter extends ViewerSorter
  {
    @Override
    public int compare (Viewer viewer, Object object1, Object object2)
    {
      return ((Item) object1).getDescription ().compareToIgnoreCase (((Item) object2).getDescription ());
    }
  }

  private class ItemViewLabelProvider extends LabelProvider implements ITableLabelProvider
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
          return ((Item) uElement).getDescription ();
        case 1:
          return String.format ("€ %1$.2f", new Double (((Item) uElement).getPrice ()));
        case 2:
          return ((Item) uElement).getPriceNotes ();
        case 3:
          final SimpleDateFormat formatter = new SimpleDateFormat ("dd-MM-yyyy");
          return formatter.format (((Item) uElement).getCreationDate ());
        default:
          return "";
      }
    }
  }

  private void refreshView ()
  {
    if (itemContentProvider != null)
    {
      itemContentProvider.reload ();
      itemTableViewer.refresh ();
    }
  }

  private void updateActions ()
  {
    if (!MenumakerPluginUI.getDatabase ().IsConnected ())
    {
      disableActions ();
      return;
    }
    newItemAction.setEnabled (true);
    refreshAction.setEnabled (true);

    if (itemSelection != null && !itemSelection.isEmpty ())
    {
      deleteItemAction.setEnabled (true);
      if (itemSelection.size () == 1)
        editItemAction.setEnabled (true);
      else
        editItemAction.setEnabled (false);
    } else
    {
      deleteItemAction.setEnabled (false);
      editItemAction.setEnabled (false);
    }
  }

  private void disableActions ()
  {
    refreshAction.setEnabled (false);
    deleteItemAction.setEnabled (false);
    newItemAction.setEnabled (false);
    editItemAction.setEnabled (false);
  }

  private void loadCategoryNames ()
  {
    if (categoryCombo.getItemCount () > 0)
      categoryCombo.removeAll ();

    categoryCombo.add ("-----");
    categoryCombo.select (0);

    categoryMap = new HashMap<String, Integer> ();
    final DatabaseManager databaseManager = MenumakerPluginUI.getDatabase ();
    if (databaseManager.IsConnected ())
      try
      {
        final Category[] categories = databaseManager.getCategoryList ();

        // Ordering list with Bubble-Sort

        for (int i = categories.length - 1; i >= 0; i--)
          for (int j = 1; j <= i; j++)
            if (categories[j - 1].getDescription ().compareToIgnoreCase (categories[j].getDescription ()) > 0)
            {
              final Category swapCategory = categories[j - 1];
              categories[j - 1] = categories[j];
              categories[j] = swapCategory;
            }
        for (int i = 0; i < categories.length; i++)
        {
          final Category currentCategory = categories[i];
          categoryMap.put (currentCategory.getDescription (), Integer.valueOf (currentCategory.getId ()));
          categoryCombo.add (currentCategory.getDescription ());
        }
      } catch (final DatabaseException e)
      {
        e.printStackTrace ();
      }
  }

  @Override
  public void dispose ()
  {
    notificator.removeMenuModelListener (modelListener);
    notificator.removeConnectionListener (connectionListener);
    super.dispose ();
  }
}
