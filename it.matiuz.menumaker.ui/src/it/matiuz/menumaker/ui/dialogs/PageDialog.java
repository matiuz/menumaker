package it.matiuz.menumaker.ui.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class PageDialog extends Dialog
{
  private Text itemBoundsText;
  private Text verticalMarginSizeText;
  private Text horizontalMarginSizeText;
  private Text categoryBoundsText;

  private int horizontalMarginSize;
  private int verticalMarginSize;
  private int categoryBoundsSize;
  private int itemBoundsSize;

  private Text errorText;

  public PageDialog (Shell uParentShell)
  {
    super (uParentShell);
  }

  @Override
  public Control createDialogArea (Composite uParent)
  {
    final Composite container = (Composite) super.createDialogArea (uParent);
    final GridLayout gridLayout = new GridLayout ();
    gridLayout.numColumns = 2;
    container.setLayout (gridLayout);

    final Label marginLabel = new Label (container, SWT.NONE);
    marginLabel.setLayoutData (new GridData (GridData.END, GridData.CENTER, false, false));
    marginLabel.setText ("Horizontal margin size:");

    horizontalMarginSizeText = new Text (container, SWT.BORDER);
    horizontalMarginSizeText.setLayoutData (new GridData (GridData.FILL, GridData.CENTER, true, false));
    horizontalMarginSizeText.addModifyListener (new ModifyListener ()
    {
      @Override
      public void modifyText (ModifyEvent uEvent)
      {
        validateInput ();
      }
    });

    final Label verticalMarginSizeLabel = new Label (container, SWT.NONE);
    verticalMarginSizeLabel.setLayoutData (new GridData (SWT.RIGHT, SWT.CENTER, false, false));
    verticalMarginSizeLabel.setText ("Vertical margin size:");

    verticalMarginSizeText = new Text (container, SWT.BORDER);
    verticalMarginSizeText.setLayoutData (new GridData (SWT.FILL, SWT.CENTER, true, false));
    verticalMarginSizeText.addModifyListener (new ModifyListener ()
    {
      @Override
      public void modifyText (ModifyEvent uEvent)
      {
        validateInput ();
      }
    });

    final Label categoryBoundsLabel = new Label (container, SWT.NONE);
    categoryBoundsLabel.setLayoutData (new GridData (SWT.RIGHT, SWT.CENTER, false, false));
    categoryBoundsLabel.setText ("Category bounds size:");

    categoryBoundsText = new Text (container, SWT.BORDER);
    categoryBoundsText.setLayoutData (new GridData (SWT.FILL, SWT.CENTER, false, false));
    categoryBoundsText.addModifyListener (new ModifyListener ()
    {
      @Override
      public void modifyText (ModifyEvent uEvent)
      {
        validateInput ();
      }
    });

    final Label itemBoundsSizeLabel = new Label (container, SWT.NONE);
    itemBoundsSizeLabel.setLayoutData (new GridData (SWT.RIGHT, SWT.CENTER, false, false));
    itemBoundsSizeLabel.setText ("Item bounds size:");

    itemBoundsText = new Text (container, SWT.BORDER);
    itemBoundsText.setLayoutData (new GridData (SWT.FILL, SWT.CENTER, true, false));
    itemBoundsText.addModifyListener (new ModifyListener ()
    {
      @Override
      public void modifyText (ModifyEvent uEvent)
      {
        validateInput ();
      }
    });

    errorText = new Text (container, SWT.NONE);
    errorText.setEditable (false);
    final GridData gd_errorText = new GridData (SWT.FILL, SWT.CENTER, true, false, 2, 1);
    gd_errorText.minimumWidth = 300;
    errorText.setLayoutData (gd_errorText);

    return container;
  }

  @Override
  public void configureShell (Shell newShell)
  {
    super.configureShell (newShell);
    newShell.setText ("Configure page");
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
    horizontalMarginSizeText.setText (Integer.toString (horizontalMarginSize));
    verticalMarginSizeText.setText (Integer.toString (verticalMarginSize));
    categoryBoundsText.setText (Integer.toString (categoryBoundsSize));
    itemBoundsText.setText (Integer.toString (itemBoundsSize));
  }

  @Override
  public void okPressed ()
  {
    horizontalMarginSize = Integer.parseInt (horizontalMarginSizeText.getText ());
    verticalMarginSize = Integer.parseInt (verticalMarginSizeText.getText ());
    categoryBoundsSize = Integer.parseInt (categoryBoundsText.getText ());
    itemBoundsSize = Integer.parseInt (itemBoundsText.getText ());

    super.okPressed ();
  }

  public int getHorizontalMarginSize ()
  {
    return horizontalMarginSize;
  }

  public void setVerticalMarginSize (int uVerticalMarginSize)
  {
    verticalMarginSize = uVerticalMarginSize;
  }

  public int getVerticalMarginSize ()
  {
    return verticalMarginSize;
  }

  public void setHorizontalMarginSize (int uHorizontalMarginSize)
  {
    horizontalMarginSize = uHorizontalMarginSize;
  }

  public int getCategoryBoundsSize ()
  {
    return categoryBoundsSize;
  }

  public void setCategoryBoundsSize (int uCategoryBoundsSize)
  {
    categoryBoundsSize = uCategoryBoundsSize;
  }

  public int getItemBoundsSize ()
  {
    return itemBoundsSize;
  }

  public void setItemBoundsSize (int uItemBoundsSize)
  {
    itemBoundsSize = uItemBoundsSize;
  }

  private void validateInput ()
  {
    errorText.setText ("");
    getButton (IDialogConstants.OK_ID).setEnabled (true);

    if (horizontalMarginSizeText.getText ().length () == 0)
    {
      errorText.setText ("Invalid horizontal margin");
      getButton (IDialogConstants.OK_ID).setEnabled (false);
      return;
    }
    try
    {
      Integer.parseInt (horizontalMarginSizeText.getText ());
    } catch (final NumberFormatException e)
    {
      errorText.setText ("Invalid horizontal margin");
      getButton (IDialogConstants.OK_ID).setEnabled (false);
      return;
    }

    int value = Integer.parseInt (horizontalMarginSizeText.getText ());

    if (value < 0 || value > 30)
    {
      errorText.setText ("Horizontal margin must be >= 0 and <= 30");
      getButton (IDialogConstants.OK_ID).setEnabled (false);
      return;
    }

    if (verticalMarginSizeText.getText ().length () == 0)
    {
      errorText.setText ("Invalid vertical margin");
      getButton (IDialogConstants.OK_ID).setEnabled (false);
      return;
    }
    try
    {
      Integer.parseInt (verticalMarginSizeText.getText ());
    } catch (final NumberFormatException e)
    {
      errorText.setText ("Invalid vertical margin");
      getButton (IDialogConstants.OK_ID).setEnabled (false);
      return;
    }

    value = Integer.parseInt (verticalMarginSizeText.getText ());

    if (value < 0 || value > 30)
    {
      errorText.setText ("Vertical margin must be >= 0 and <= 30");
      getButton (IDialogConstants.OK_ID).setEnabled (false);
      return;
    }

    if (categoryBoundsText.getText ().length () == 0)
    {
      errorText.setText ("Invalid category bounds");
      getButton (IDialogConstants.OK_ID).setEnabled (false);
      return;
    }
    try
    {
      Integer.parseInt (categoryBoundsText.getText ());
    } catch (final NumberFormatException e)
    {
      errorText.setText ("Invalid category bounds");
      getButton (IDialogConstants.OK_ID).setEnabled (false);
      return;
    }

    value = Integer.parseInt (categoryBoundsText.getText ());

    if (value < 0 || value > 20)
    {
      errorText.setText ("Category bounds must be >= 0 and <= 20");
      getButton (IDialogConstants.OK_ID).setEnabled (false);
      return;
    }

    if (itemBoundsText.getText ().length () == 0)
    {
      errorText.setText ("Invalid item bounds");
      getButton (IDialogConstants.OK_ID).setEnabled (false);
      return;
    }
    try
    {
      Integer.parseInt (itemBoundsText.getText ());
    } catch (final NumberFormatException e)
    {
      errorText.setText ("Invalid item bounds");
      getButton (IDialogConstants.OK_ID).setEnabled (false);
      return;
    }

    value = Integer.parseInt (itemBoundsText.getText ());

    if (value < 0 || value > 20)
    {
      errorText.setText ("Item bounds must be >= 0 and <= 20");
      getButton (IDialogConstants.OK_ID).setEnabled (false);
      return;
    }
  }
}
