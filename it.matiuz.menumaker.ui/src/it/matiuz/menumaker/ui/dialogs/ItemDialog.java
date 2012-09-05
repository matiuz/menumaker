package it.matiuz.menumaker.ui.dialogs;

import it.matiuz.menumaker.model.Category;
import it.matiuz.menumaker.ui.MenumakerPluginUI;
import it.matiuz.menumaker.ui.tools.DatabaseException;
import it.matiuz.menumaker.ui.tools.DatabaseManager;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class ItemDialog extends Dialog
{
  private Combo categoryCombo;
  private Text priceNotesText;
  private Text priceText;
  private Text descriptionText;

  private String description;
  private double price;
  private String priceNotes;
  private String categoryName;
  private int categoryId;
  private Map<String, Integer> categoryMap;

  private Text errorText;

  public ItemDialog (Shell parentShell)
  {
    super (parentShell);
    description = "";
    price = 0;
    priceNotes = "";
    categoryId = 0;
    categoryName = "";
  }

  @Override
  public Control createDialogArea (Composite uParent)
  {
    final Composite container = (Composite) super.createDialogArea (uParent);
    final GridLayout gridLayout = new GridLayout ();
    gridLayout.numColumns = 2;
    container.setLayout (gridLayout);

    final Label desciptionLabel = new Label (container, SWT.NONE);
    desciptionLabel.setLayoutData (new GridData (GridData.END, GridData.CENTER, false, false));
    desciptionLabel.setText ("Description:");

    descriptionText = new Text (container, SWT.BORDER);
    final GridData gd_descriptionText = new GridData (GridData.FILL, GridData.CENTER, true, false);
    gd_descriptionText.minimumWidth = 150;
    descriptionText.setLayoutData (gd_descriptionText);
    descriptionText.addModifyListener (new ModifyListener ()
    {
      @Override
      public void modifyText (ModifyEvent uEvent)
      {
        validateInput ();
      }
    });

    final Label priceLabel = new Label (container, SWT.NONE);
    priceLabel.setLayoutData (new GridData (SWT.RIGHT, SWT.CENTER, false, false));
    priceLabel.setText ("Price (\u20AC):");

    priceText = new Text (container, SWT.BORDER);
    priceText.setLayoutData (new GridData (SWT.FILL, SWT.CENTER, true, false));
    priceText.addModifyListener (new ModifyListener ()
    {
      @Override
      public void modifyText (ModifyEvent uEvent)
      {
        validateInput ();
      }
    });

    final Label priceNotesLabel = new Label (container, SWT.NONE);
    final GridData gd_priceNotesLabel = new GridData (SWT.RIGHT, SWT.CENTER, false, false);
    priceNotesLabel.setLayoutData (gd_priceNotesLabel);
    priceNotesLabel.setText ("Price notes:");

    priceNotesText = new Text (container, SWT.BORDER);
    priceNotesText.setLayoutData (new GridData (SWT.FILL, SWT.CENTER, true, false));
    priceNotesText.addModifyListener (new ModifyListener ()
    {
      @Override
      public void modifyText (ModifyEvent uEvent)
      {
        validateInput ();
      }
    });

    final Label menuCategoryLabel = new Label (container, SWT.NONE);
    final GridData gd_menuCategoryLabel = new GridData (SWT.RIGHT, SWT.CENTER, false, false);
    menuCategoryLabel.setLayoutData (gd_menuCategoryLabel);
    menuCategoryLabel.setText ("Menu category:");

    categoryCombo = new Combo (container, SWT.READ_ONLY);
    final GridData gd_menuCategoryCombo = new GridData (SWT.FILL, SWT.CENTER, true, false);
    categoryCombo.setLayoutData (gd_menuCategoryCombo);

    errorText = new Text (container, SWT.NONE);
    errorText.setEditable (false);
    final GridData gd_errorText = new GridData (SWT.FILL, SWT.CENTER, true, false, 2, 1);
    errorText.setLayoutData (gd_errorText);

    return container;
  }

  private void validateInput ()
  {
    errorText.setText ("");
    getButton (IDialogConstants.OK_ID).setEnabled (true);

    if (descriptionText.getText ().length () == 0)
    {
      errorText.setText ("Invalid description");
      getButton (IDialogConstants.OK_ID).setEnabled (false);
      return;
    }

    if (priceText.getText ().length () == 0)
    {
      errorText.setText ("Invalid price");
      getButton (IDialogConstants.OK_ID).setEnabled (false);
      return;
    }
    try
    {
      Double.parseDouble (priceText.getText ());
    } catch (final NumberFormatException e)
    {
      errorText.setText ("Invalid price");
      getButton (IDialogConstants.OK_ID).setEnabled (false);
      return;
    }

    if (priceNotesText.getText ().length () > 10)
    {
      errorText.setText ("Invalid price notes");
      getButton (IDialogConstants.OK_ID).setEnabled (false);
      return;
    }
  }

  @Override
  public void configureShell (Shell newShell)
  {
    super.configureShell (newShell);
    newShell.setText ("Menu Item");
  }

  @Override
  public void createButtonsForButtonBar (Composite parent)
  {
    createButton (parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
    createButton (parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
  }

  @Override
  public void create ()
  {
    super.create ();
    loadCategoryNames ();
    descriptionText.setText (description);
    priceText.setText (String.valueOf (price));
    priceNotesText.setText (priceNotes);
    if (categoryName == null)
      categoryName = "";
    categoryCombo.setText (categoryName);
  }

  @Override
  public void okPressed ()
  {
    if (categoryCombo.getText () != null)
      if (categoryMap.get (categoryCombo.getText ()) != null)
        categoryId = categoryMap.get (categoryCombo.getText ()).intValue ();

    if (categoryId != 0)
    {
      description = descriptionText.getText ();
      price = Double.valueOf (priceText.getText ()).doubleValue ();
      priceNotes = priceNotesText.getText ();
      super.okPressed ();
    } else
      MessageDialog.openError (getShell (), "Error", "Menu category not selected");
  }

  private void loadCategoryNames ()
  {
    if (categoryCombo.getItemCount () > 0)
      categoryCombo.removeAll ();

    categoryMap = new HashMap<String, Integer> ();

    final DatabaseManager databaseManager = MenumakerPluginUI.getDatabase ();

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
        categoryMap.put (currentCategory.getDescription (), new Integer (currentCategory.getId ()));
        categoryCombo.add (currentCategory.getDescription ());
      }
    } catch (final DatabaseException e)
    {
      e.printStackTrace ();
    }
  }

  public String getDescription ()
  {
    return description;
  }

  public void setDescription (String uDescription)
  {
    description = uDescription;
  }

  public double getPrice ()
  {
    return price;
  }

  public void setPrice (double uPrice)
  {
    price = uPrice;
  }

  public String getPriceNotes ()
  {
    return priceNotes;
  }

  public void setPriceNotes (String uPriceNotes)
  {
    priceNotes = uPriceNotes;
  }

  public int getCategoryId ()
  {
    return categoryId;
  }

  public void setCategoryId (int uCategoryId)
  {
    categoryId = uCategoryId;
  }

  public String getCategoryName ()
  {
    return categoryName;
  }

  public void setCategoryName (String uCategoryName)
  {
    categoryName = uCategoryName;
  }
}
