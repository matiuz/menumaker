package it.matiuz.menumaker.ui.dialogs;

import it.matiuz.menumaker.ui.tools.PrintConfigurator.ImageType;
import it.matiuz.menumaker.ui.tools.PrintConfigurator.LogoPosition;

import java.io.File;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class ImageDialog extends Dialog
{
  private Text imageBorderSizeText;
  private Text logoImageText;
  private Button topLeftButton;
  private Button topRightButton;
  private Button bottomLeftButton;
  private Button bottomRightButton;
  private Button centerButton;
  private Button noLogoButton;
  private Button imageBorderEnableButton;

  private int imageBorderSize;
  private String logoImagePath;
  private LogoPosition logoPosition;
  private boolean isImageBorderEnable;

  private Text errorText;

  private final Shell shell;

  public ImageDialog (Shell uParentShell)
  {
    super (uParentShell);
    shell = uParentShell;
  }

  @Override
  public Control createDialogArea (Composite uParent)
  {
    final Composite container = (Composite) super.createDialogArea (uParent);
    final GridLayout gridLayout = new GridLayout ();
    gridLayout.numColumns = 3;
    container.setLayout (gridLayout);

    final Label backgroundImageLabel = new Label (container, SWT.NONE);
    backgroundImageLabel.setLayoutData (new GridData (SWT.RIGHT, SWT.CENTER, false, false));
    backgroundImageLabel.setText ("Border size:");

    imageBorderSizeText = new Text (container, SWT.BORDER);
    imageBorderSizeText.setLayoutData (new GridData (SWT.FILL, SWT.CENTER, true, false));
    imageBorderSizeText.addModifyListener (new ModifyListener ()
    {
      @Override
      public void modifyText (ModifyEvent uEvent)
      {
        validateInput ();
      }
    });
    new Label (container, SWT.NONE);

    final Label enableBorderLabel = new Label (container, SWT.NONE);
    enableBorderLabel.setLayoutData (new GridData (SWT.RIGHT, SWT.CENTER, false, false));
    enableBorderLabel.setText ("Enable border:");

    imageBorderEnableButton = new Button (container, SWT.CHECK);
    new Label (container, SWT.NONE);

    final Label logoImageLabel = new Label (container, SWT.NONE);
    logoImageLabel.setLayoutData (new GridData (SWT.RIGHT, SWT.CENTER, false, false));
    logoImageLabel.setText ("Logo path:");

    logoImageText = new Text (container, SWT.BORDER | SWT.READ_ONLY);
    final GridData gd_logoImageText = new GridData (SWT.FILL, SWT.CENTER, false, false);
    gd_logoImageText.minimumWidth = 300;
    logoImageText.setLayoutData (gd_logoImageText);

    logoImageText.addModifyListener (new ModifyListener ()
    {
      @Override
      public void modifyText (ModifyEvent uEvent)
      {
        validateInput ();
      }
    });

    final Button browseLogo = new Button (container, SWT.NONE);
    browseLogo.setLayoutData (new GridData ());
    browseLogo.setText ("Browse");
    browseLogo.addSelectionListener (new SelectionAdapter ()
    {
      @Override
      public void widgetSelected (SelectionEvent e)
      {
        implementBrowse (ImageType.LOGO_IMAGE);
      }
    });

    final Group logoPostionGroup = new Group (container, SWT.NONE);
    logoPostionGroup.setText ("Logo position");
    final GridData gd_logoPostionGroup = new GridData (SWT.FILL, SWT.CENTER, true, false, 3, 1);
    logoPostionGroup.setLayoutData (gd_logoPostionGroup);
    final GridLayout gridLayout_1 = new GridLayout ();
    gridLayout_1.numColumns = 6;
    logoPostionGroup.setLayout (gridLayout_1);

    topLeftButton = new Button (logoPostionGroup, SWT.RADIO);
    topLeftButton.setText ("Top left");

    topRightButton = new Button (logoPostionGroup, SWT.RADIO);
    topRightButton.setText ("Top right");
    topRightButton.setLayoutData (new GridData ());

    centerButton = new Button (logoPostionGroup, SWT.RADIO);
    centerButton.setText ("Center");
    centerButton.setLayoutData (new GridData ());

    bottomLeftButton = new Button (logoPostionGroup, SWT.RADIO);
    bottomLeftButton.setText ("Bottom Left");
    bottomLeftButton.setLayoutData (new GridData ());

    bottomRightButton = new Button (logoPostionGroup, SWT.RADIO);
    bottomRightButton.setText ("Bottom Right");
    bottomRightButton.setLayoutData (new GridData ());

    noLogoButton = new Button (logoPostionGroup, SWT.RADIO);
    noLogoButton.setText ("Disable");
    final GridData gd_noLogoButton = new GridData ();
    noLogoButton.setLayoutData (gd_noLogoButton);

    errorText = new Text (container, SWT.NONE);
    errorText.setEditable (false);
    errorText.setLayoutData (new GridData (SWT.FILL, SWT.CENTER, true, false, 2, 1));
    new Label (container, SWT.NONE);

    return container;
  }

  private void validateInput ()
  {
    errorText.setText ("");
    getButton (IDialogConstants.OK_ID).setEnabled (true);

    if (imageBorderSizeText.getText ().length () == 0)
    {
      errorText.setText ("Invalid border");
      getButton (IDialogConstants.OK_ID).setEnabled (false);
      return;
    }

    try
    {
      Integer.parseInt (imageBorderSizeText.getText ());
    } catch (final NumberFormatException e)
    {
      errorText.setText ("Invalid border size");
      getButton (IDialogConstants.OK_ID).setEnabled (false);
      return;
    }

    final int value = Integer.parseInt (imageBorderSizeText.getText ());

    if (value < 1 || value > 10)
    {
      errorText.setText ("Border size must be >= 1 and <= 10 ");
      getButton (IDialogConstants.OK_ID).setEnabled (false);
      return;
    }
  }

  @Override
  public void configureShell (Shell newShell)
  {
    super.configureShell (newShell);
    newShell.setText ("Configure images");
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
    imageBorderSizeText.setText (Integer.toString (imageBorderSize));
    logoImageText.setText (logoImagePath);

    if (logoPosition.equals (LogoPosition.TOP_LEFT))
      topLeftButton.setSelection (true);
    else if (logoPosition.equals (LogoPosition.BOTTOM_LEFT))
      bottomLeftButton.setSelection (true);
    else if (logoPosition.equals (LogoPosition.TOP_RIGHT))
      topRightButton.setSelection (true);
    else if (logoPosition.equals (LogoPosition.BOTTOM_RIGHT))
      bottomRightButton.setSelection (true);
    else if (logoPosition.equals (LogoPosition.CENTER))
      centerButton.setSelection (true);
    else
      noLogoButton.setSelection (true);
    imageBorderEnableButton.setSelection (isImageBorderEnable);
  }

  @Override
  public void okPressed ()
  {
    imageBorderSize = Integer.parseInt (imageBorderSizeText.getText ());
    logoImagePath = logoImageText.getText ();

    if (topLeftButton.getSelection ())
      logoPosition = LogoPosition.TOP_LEFT;
    else if (topRightButton.getSelection ())
      logoPosition = LogoPosition.TOP_RIGHT;
    else if (bottomLeftButton.getSelection ())
      logoPosition = LogoPosition.BOTTOM_LEFT;
    else if (bottomRightButton.getSelection ())
      logoPosition = LogoPosition.BOTTOM_RIGHT;
    else if (centerButton.getSelection ())
      logoPosition = LogoPosition.CENTER;
    else
      logoPosition = LogoPosition.NO_LOGO;

    isImageBorderEnable = imageBorderEnableButton.getSelection ();
    super.okPressed ();
  }

  public int getImageBorderSize ()
  {
    return imageBorderSize;
  }

  public void setImageBorderSize (int uImageBorderSize)
  {
    imageBorderSize = uImageBorderSize;
  }

  public String getLogoImagePath ()
  {
    return logoImagePath;
  }

  public void setLogoPosition (LogoPosition uLogoPosition)
  {
    logoPosition = uLogoPosition;
  }

  public LogoPosition getLogoPosition ()
  {
    return logoPosition;
  }

  public void setImageBorderEnable (boolean uBorderEnable)
  {
    isImageBorderEnable = uBorderEnable;
  }

  public boolean isImageBorderEnable ()
  {
    return isImageBorderEnable;
  }

  public void setLogoImagePath (String uLogoImagePath)
  {
    logoImagePath = uLogoImagePath;
  }

  private void implementBrowse (ImageType uImageType)
  {
    final FileDialog dialog = new FileDialog (shell);

    dialog.setFilterExtensions (new String[] { "*.jpg", "*.png", "*.gif" });
    dialog.setFilterNames (new String[] { "Jpg Image (*.jpg)", "Png Image (*.png)", "Gif Image (*.gif)" });

    final String filename = dialog.open ();

    if (filename == null)
      return;

    if (!new File (filename).exists ())
    {
      MessageDialog.openError (shell, "Error", "File does not exist");
      return;
    }

    if (uImageType.equals (ImageType.LOGO_IMAGE))
      logoImageText.setText (filename);
  }
}
