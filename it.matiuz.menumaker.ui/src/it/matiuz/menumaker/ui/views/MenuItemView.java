package it.matiuz.menumaker.ui.views;

import it.matiuz.menumaker.model.Category;
import it.matiuz.menumaker.model.Item;
import it.matiuz.menumaker.model.Menu;
import it.matiuz.menumaker.ui.MenumakerPluginUI;
import it.matiuz.menumaker.ui.dialogs.ItemDialog;
import it.matiuz.menumaker.ui.listeners.IMenuModelListener;
import it.matiuz.menumaker.ui.listeners.MenuModelAdapter;
import it.matiuz.menumaker.ui.tools.DatabaseException;
import it.matiuz.menumaker.ui.tools.DatabaseManager;
import it.matiuz.menumaker.ui.tools.DialogTools;
import it.matiuz.menumaker.ui.tools.IMenuModelHandler;
import it.matiuz.menumaker.ui.tools.MenuModelNotificator;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
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
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.tango.icons.ITangoIconsImages;
import org.tango.icons.TangoIconsPlugin;

public class MenuItemView extends ViewPart
{
  public static final String ID = "it.matiuz.menumaker.ui.views.MenuItemView";
  
  private Text creationDateText;
  private Text decriptionText;

  private TableViewer menuItemTableViewer;
  private Table menuItemTable;
  private MenuItemViewContentProvider itemViewContentProvider;

  private IStructuredSelection menuItemSelection;

  private final IMenuModelListener modelListener;

  private Action deleteItemAction;
  private Action editItemAction;
  private Action refreshAction;

  private Font font;
  private Color backgroundColor;
  private Color backgroundIsDirtyColor;

  private Font tableFont;
  private Color tableForegroundColor;

  private final IMenuModelHandler handler;
  private final MenuModelNotificator notificator;

  private Button upButton;
  private Button downButton;

  private Image upImage;
  private Image downImage;

  public MenuItemView ()
  {
    handler = MenumakerPluginUI.getMenuModelHandler ();
    notificator = MenumakerPluginUI.getMenuModelNotificator ();

    modelListener = new MenuModelAdapter ()
    {
      @Override
      public void onLoadMenu ()
      {
        menuItemTable.setEnabled (true);

        updateActions ();
        refreshView ();
      }

      @Override
      public void onSaveMenu ()
      {
        updateActions ();
        refreshView ();
      }

      @Override
      public void onRemoveMenu ()
      {
        updateActions ();
        refreshView ();
      }

      @Override
      public void onCloseMenu ()
      {
        updateActions ();
        refreshView ();
      }

      @Override
      public void onUpdateMenu ()
      {
        updateActions ();
        refreshView ();
      }

      @Override
      public void onAddMenu ()
      {
        updateActions ();
        refreshView ();
      }
    };

    notificator.addMenuModelListener (modelListener);
  }

  @Override
  public void createPartControl (Composite parent)
  {
    font = new Font (null, "Microsoft Sans Serif", 10, SWT.NORMAL);
    backgroundIsDirtyColor = new Color (null, 255, 255, 150);
    backgroundColor = new Color (null, 150, 255, 150);

    tableFont = new Font (null, "Verdana", 9, SWT.NORMAL);
    tableForegroundColor = new Color (null, 30, 30, 30);

    final Composite container = new Composite (parent, SWT.NONE);
    final GridLayout gridLayout_1 = new GridLayout ();
    gridLayout_1.numColumns = 2;
    container.setLayout (gridLayout_1);
    itemViewContentProvider = new MenuItemViewContentProvider ();
    final Composite composite = new Composite (container, SWT.NONE);
    GridData gd_composite = new GridData (SWT.FILL, SWT.CENTER, true, false);
    gd_composite.horizontalSpan = 2;
    composite.setLayoutData (gd_composite);
    final GridLayout gridLayout = new GridLayout ();
    gridLayout.numColumns = 2;
    composite.setLayout (gridLayout);

    final Label menuDescriptionLabel = new Label (composite, SWT.NONE);
    menuDescriptionLabel.setFont (font);
    menuDescriptionLabel.setLayoutData (new GridData (SWT.RIGHT, SWT.CENTER, false, false));
    menuDescriptionLabel.setText ("Menu description:");

    decriptionText = new Text (composite, SWT.READ_ONLY | SWT.BORDER);
    decriptionText.setFont (font);
    decriptionText.setBackground (backgroundColor);
    decriptionText.setLayoutData (new GridData (SWT.FILL, SWT.CENTER, true, false));

    final Label creationDateLabel = new Label (composite, SWT.NONE);
    creationDateLabel.setFont (font);
    creationDateLabel.setLayoutData (new GridData (SWT.RIGHT, SWT.CENTER, false, false));
    creationDateLabel.setText ("Creation date:");

    creationDateText = new Text (composite, SWT.READ_ONLY | SWT.BORDER);
    creationDateText.setBackground (backgroundColor);
    creationDateText.setFont (font);
    creationDateText.setLayoutData (new GridData (SWT.FILL, SWT.CENTER, true, false));

    final Composite composite_1 = new Composite (container, SWT.NONE);
    composite_1.setLayoutData (new GridData (GridData.FILL, GridData.FILL, true, true));
    composite_1.setLayout (new FillLayout (SWT.VERTICAL));

    menuItemTableViewer = new TableViewer (composite_1, SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER);

    menuItemTableViewer.setContentProvider (itemViewContentProvider);
    menuItemTableViewer.setLabelProvider (new MenuItemViewLabelProvider ());

    menuItemTable = menuItemTableViewer.getTable ();
    menuItemTable.setHeaderVisible (true);
    menuItemTable.setEnabled (false);

    final TableColumn descriptionColumn = new TableColumn (menuItemTable, SWT.NONE);
    descriptionColumn.setWidth (250);
    descriptionColumn.setText ("Description");

    final TableColumn priceColumn = new TableColumn (menuItemTable, SWT.NONE);
    priceColumn.setWidth (100);
    priceColumn.setText ("Price");

    final TableColumn categoryColumn = new TableColumn (menuItemTable, SWT.NONE);
    categoryColumn.setWidth (100);
    categoryColumn.setText ("Category");

    menuItemTableViewer.setInput (new Object ());
    menuItemTableViewer.addSelectionChangedListener (new ISelectionChangedListener ()
    {
      @Override
      public void selectionChanged (SelectionChangedEvent uEvent)
      {
        menuItemSelection = null;
        if (uEvent.getSelection () instanceof IStructuredSelection)
          menuItemSelection = (IStructuredSelection) uEvent.getSelection ();
        updateActions ();
      }
    });

    final int TEXT_MARGIN = 7;

    menuItemTable.addListener (SWT.MeasureItem, new Listener ()
    {
      @Override
      public void handleEvent (Event event)
      {
        final Point size = event.gc.textExtent ("M");
        event.width = size.x * 40 + 2 * TEXT_MARGIN;
        event.height = size.y + 2 * TEXT_MARGIN;
      }
    });

    menuItemTable.addListener (SWT.EraseItem, new Listener ()
    {
      @Override
      public void handleEvent (Event event)
      {
        event.detail &= ~SWT.FOREGROUND;
      }
    });

    menuItemTable.addListener (SWT.PaintItem, new Listener ()
    {
      @Override
      public void handleEvent (Event event)
      {
        final TableItem item = (TableItem) event.item;

        final String text = item.getText (event.index);

        int yOffset = 0;

        switch (event.index)
        {
          case 0:
          case 1:
          case 2:
            final Point size = event.gc.textExtent (text);
            yOffset = Math.max (0, (event.height - size.y - 2 * TEXT_MARGIN) / 2);
        }
        event.gc.setFont (tableFont);
        event.gc.setForeground (tableForegroundColor);
        event.gc.drawText (text, event.x + TEXT_MARGIN, event.y + TEXT_MARGIN + yOffset, false);
      }
    });

    menuItemTableViewer.addOpenListener (new IOpenListener ()
    {
      @Override
      public void open (OpenEvent event)
      {
        if (editItemAction.isEnabled ())
          editItemAction.run ();
      }
    });
    menuItemTable.addKeyListener (new KeyAdapter ()
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
            if (deleteItemAction.isEnabled ())
              deleteItemAction.run ();
            break;
          default:
            break;
        }
      }
    });

    menuItemTableViewer.addDropSupport (DND.DROP_COPY | DND.DROP_MOVE, new Transfer[] { LocalTransfer.getInstance () }, new ViewerDropAdapter (menuItemTableViewer)
    {
      @Override
      public boolean performDrop (Object uData)
      {
        if (uData instanceof IStructuredSelection)
        {
          final Iterator itemIterator = ((IStructuredSelection) uData).iterator ();

          while (itemIterator.hasNext ())
          {
            final Item currentItem = (Item) itemIterator.next ();
            if (currentItem.getCategoryId () != 0)
            {
              handler.addItem (currentItem);
              notificator.notifyUpdateMenu ();
            }
          }

          updateActions ();
          refreshView ();
          return true;
        }
        return false;
      }

      @Override
      public boolean validateDrop (Object uTarget, int uOperation, TransferData uTransferType)
      {
        return LocalTransfer.getInstance ().isSupportedType (uTransferType);
      }
    });

    final Composite composite_2 = new Composite (container, SWT.NONE);
    composite_2.setLayoutData (new GridData (SWT.FILL, SWT.FILL, false, false));
    composite_2.setLayout (new GridLayout ());

    upButton = new Button (composite_2, SWT.PUSH);
    GridData gd_upButton = new GridData (SWT.FILL, SWT.CENTER, true, false);
    gd_upButton.widthHint = 30;
    upButton.setLayoutData (gd_upButton);
    upImage = TangoIconsPlugin.getSmallImage (ITangoIconsImages.ACTIONS_GO_UP);
    upButton.setImage (upImage);
    upButton.setToolTipText ("Move up");
    upButton.addSelectionListener (new SelectionAdapter ()
    {
      @Override
      public void widgetSelected (SelectionEvent uEvent)
      {
        handler.moveItem ((Item) menuItemSelection.getFirstElement (), IMenuModelHandler.ItemMovingDirection.UP);

        notificator.notifyUpdateMenu ();

        refreshView ();
        updateActions ();
      }
    });

    downButton = new Button (composite_2, SWT.PUSH);
    GridData gd_downButton = new GridData (SWT.FILL, SWT.CENTER, true, false);
    gd_downButton.widthHint = 30;
    downButton.setLayoutData (gd_downButton);
    downImage = TangoIconsPlugin.getSmallImage (ITangoIconsImages.ACTIONS_GO_DOWN);
    downButton.setImage (downImage);
    downButton.setToolTipText ("Move down");
    downButton.addSelectionListener (new SelectionAdapter ()
    {
      @Override
      public void widgetSelected (SelectionEvent uEvent)
      {
        handler.moveItem ((Item) menuItemSelection.getFirstElement (), IMenuModelHandler.ItemMovingDirection.DOWN);

        notificator.notifyUpdateMenu ();

        refreshView ();
        updateActions ();
      }
    });

    createActions ();
    inizializeMenu ();
    initializeToolBar ();
    updateActions ();
  }

  private void implementEditMenuItem ()
  {
    final ItemDialog dialog = new ItemDialog (getSite ().getShell ());
    final Item item = (Item) menuItemSelection.getFirstElement ();

    DialogTools.storeItemDialogData (dialog, item);

    if (dialog.open () == Window.OK)
    {
      DialogTools.retrieveItemDialogData (dialog, item);
      handler.updateItem (item);
      notificator.notifyUpdateMenu ();
    }
    updateActions ();
    refreshView ();
  }

  private void implementDeleteMenuItem ()
  {
    if (!MessageDialog.openConfirm (getSite ().getShell (), "Delete", "Remove menu items?"))
      return;

    final Iterator itemIterator = menuItemSelection.iterator ();

    while (itemIterator.hasNext ())
    {
      handler.removeItem ((Item) itemIterator.next ());
      notificator.notifyUpdateMenu ();
    }

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
        implementDeleteMenuItem ();
      }
    };
    deleteItemAction.setToolTipText ("Delete selected items");
    deleteItemAction.setEnabled (true);

    editItemAction = new Action ("Edit", TangoIconsPlugin.getSmallImageDescriptor (ITangoIconsImages.APPS_ACCESSORIES_TEXT_EDITOR))
    {
      @Override
      public void run ()
      {
        implementEditMenuItem ();
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
    menuManager.add (editItemAction);
    menuManager.add (deleteItemAction);
  }

  private void initializeToolBar ()
  {
    final IToolBarManager toolbarManager = getViewSite ().getActionBars ().getToolBarManager ();
    toolbarManager.add (editItemAction);
    toolbarManager.add (deleteItemAction);
  }

  @Override
  public void setFocus ()
  {
    menuItemTableViewer.getControl ().setFocus ();
  }

  private class MenuItemViewContentProvider extends MenuMakerContentProvider
  {

    @Override
    public Object[] fetchElements ()
    {
      if (handler.getHandlerState ().equals (IMenuModelHandler.HandlerState.IDLE))
        return new Item[0];

      final List<Item> items = handler.getMenu ().getItems ();
      return items.toArray (new Item[items.size ()]);
    }
  }

  private class MenuItemViewLabelProvider extends LabelProvider implements ITableLabelProvider
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
      final DatabaseManager databaseManager = MenumakerPluginUI.getDatabase ();
      final Item item = (Item) uElement;
      switch (uColumnIndex)
      {
        case 0:
          return ((Item) uElement).getDescription ();
        case 1:
          return String.format ("€ %1$.2f%2$s", new Double (item.getPrice ()), item.getPriceNotes ());
        case 2:
          Category category;
          try
          {
            category = databaseManager.getCategoryFromId (item.getCategoryId ());
          } catch (final DatabaseException e)
          {
            return "-----";
          }
          if (category != null)
            return category.getDescription ();
          return "-----";

        default:
          return "";
      }
    }
  }

  private void refreshView ()
  {
    final Color background = handler.isDirty () ? backgroundIsDirtyColor : backgroundColor;

    decriptionText.setBackground (background);
    creationDateText.setBackground (background);

    if (handler.getHandlerState ().equals (IMenuModelHandler.HandlerState.IDLE))
    {
      menuItemTable.setEnabled (false);
      decriptionText.setText ("");
      creationDateText.setText ("");

    } else
    {
      menuItemTable.setEnabled (true);

      final Menu menu = handler.getMenu ();
      decriptionText.setText (menu.getDescription ());
      creationDateText.setText (new SimpleDateFormat ("dd-MM-yyyy").format (menu.getCreationDate ()));

    }
    if (itemViewContentProvider != null)
    {
      itemViewContentProvider.reload ();
      menuItemTableViewer.refresh ();
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

    if (menuItemSelection != null && !menuItemSelection.isEmpty ())
    {
      deleteItemAction.setEnabled (true);
      if (menuItemSelection.size () == 1)
      {
        editItemAction.setEnabled (true);
        upButton.setEnabled (true);
        downButton.setEnabled (true);
      } else
      {
        editItemAction.setEnabled (false);
        upButton.setEnabled (false);
        downButton.setEnabled (false);
      }
    } else
    {
      deleteItemAction.setEnabled (false);
      editItemAction.setEnabled (false);
      upButton.setEnabled (false);
      downButton.setEnabled (false);
    }
  }

  private void disableActions ()
  {
    refreshAction.setEnabled (false);
    deleteItemAction.setEnabled (false);
    editItemAction.setEnabled (false);
    upButton.setEnabled (false);
    downButton.setEnabled (false);
  }

  @Override
  public void dispose ()
  {
    notificator.removeMenuModelListener (modelListener);

    font.dispose ();
    backgroundIsDirtyColor.dispose ();
    backgroundColor.dispose ();

    tableFont.dispose ();
    tableForegroundColor.dispose ();

    upImage.dispose ();
    downImage.dispose ();

    super.dispose ();
  }
}
