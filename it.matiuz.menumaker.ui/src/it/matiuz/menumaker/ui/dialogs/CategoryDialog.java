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

public class CategoryDialog extends Dialog
{
  private Text descriptionText;
  private Text priorityText;

  private String description;
  private int priority;

  private Text errorText;

  public CategoryDialog (Shell parentShell)
  {
    super (parentShell);
    description = "";
    priority = 0;
  }

  @Override
  public Control createDialogArea (Composite uParent)
  {
    final Composite container = (Composite) super.createDialogArea (uParent);
    final GridLayout gridLayout = new GridLayout ();
    gridLayout.numColumns = 2;
    container.setLayout (gridLayout);

    final Label desciptionLabel = new Label (container, SWT.NONE);
    final GridData gd_desciptionLabel = new GridData (GridData.END, GridData.CENTER, false, false);
    desciptionLabel.setLayoutData (gd_desciptionLabel);
    desciptionLabel.setText ("Description:");

    descriptionText = new Text (container, SWT.BORDER);
    final GridData gridData = new GridData (GridData.FILL, GridData.CENTER, true, false);
    descriptionText.setLayoutData (gridData);
    descriptionText.addModifyListener (new ModifyListener ()
    {
      @Override
      public void modifyText (ModifyEvent uEvent)
      {
        validateInput ();
      }
    });

    final Label priorityLabel = new Label (container, SWT.NONE);
    final GridData gd_priorityLabel = new GridData (SWT.RIGHT, SWT.CENTER, false, false);
    priorityLabel.setLayoutData (gd_priorityLabel);
    priorityLabel.setText ("Priority:");

    priorityText = new Text (container, SWT.BORDER);
    priorityText.setLayoutData (new GridData (SWT.FILL, SWT.CENTER, false, false));

    priorityText.addModifyListener (new ModifyListener ()
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

    if (priorityText.getText ().length () == 0)
    {
      errorText.setText ("Invalid priority");
      getButton (IDialogConstants.OK_ID).setEnabled (false);
      return;
    }
    try
    {
      Integer.parseInt (priorityText.getText ());
    } catch (final NumberFormatException e)
    {
      errorText.setText ("Invalid priority");
      getButton (IDialogConstants.OK_ID).setEnabled (false);
      return;
    }
  }

  @Override
  public void configureShell (Shell newShell)
  {
    super.configureShell (newShell);
    newShell.setText ("Menu Category");
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
    descriptionText.setText (description);
    priorityText.setText (String.valueOf (priority));
  }

  @Override
  public void okPressed ()
  {
    description = descriptionText.getText ();
    priority = Integer.parseInt (priorityText.getText ());
    super.okPressed ();
  }

  public String getDescription ()
  {
    return description;
  }

  public void setDescription (String uDescription)
  {
    description = uDescription;
  }

  public int getPriority ()
  {
    return priority;
  }

  public void setPriority (int uPriority)
  {
    priority = uPriority;
  }
}
