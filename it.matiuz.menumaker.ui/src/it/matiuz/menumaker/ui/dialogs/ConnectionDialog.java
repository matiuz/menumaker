package it.matiuz.menumaker.ui.dialogs;

import it.matiuz.menumaker.ui.MenumakerPluginUI;

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

public class ConnectionDialog extends Dialog
{
  private static final String KEY_ADDRESS = "postgres.db_address";
  private static final String KEY_PORT = "postgres.db_port";
  private static final String KEY_NAME = "postgres.db_name";
  private static final String KEY_LOGIN = "postgres.db_login";
  private static final String KEY_PASSWORD = "postgres.db_password";

  private static final int DEFAULT_PORT = 5432;

  private Text addressText;
  private Text nameText;
  private Text portText;
  private Text loginText;
  private Text passwordText;

  private String address;
  private int port;
  private String name;
  private String login;
  private String password;

  private Text errorText;

  public ConnectionDialog (Shell uParentShell)
  {
    super (uParentShell);
  }

  public String getAddress ()
  {
    return address;
  }

  public int getPort ()
  {
    return port;
  }

  public String getName ()
  {
    return name;
  }

  public String getLogin ()
  {
    return login;
  }

  public String getPassword ()
  {
    return password;
  }

  @Override
  public Control createDialogArea (Composite uParent)
  {
    final Composite container = (Composite) super.createDialogArea (uParent);
    final GridLayout gridLayout = new GridLayout ();
    gridLayout.numColumns = 2;
    container.setLayout (gridLayout);

    final Label addressLabel = new Label (container, SWT.NONE);
    addressLabel.setLayoutData (new GridData (GridData.END, GridData.CENTER, false, false));
    addressLabel.setText ("Address:");

    addressText = new Text (container, SWT.BORDER);
    addressText.setLayoutData (new GridData (GridData.FILL, GridData.CENTER, true, false));
    addressText.addModifyListener (new ModifyListener ()
    {
      @Override
      public void modifyText (ModifyEvent uEvent)
      {
        validateInput ();
      }
    });

    final Label portLabel = new Label (container, SWT.NONE);
    final GridData gd_portLabel = new GridData (SWT.RIGHT, SWT.CENTER, false, false);
    portLabel.setLayoutData (gd_portLabel);
    portLabel.setText ("Port:");

    portText = new Text (container, SWT.BORDER);
    final GridData gd_portText = new GridData (SWT.FILL, SWT.CENTER, true, false);
    portText.setLayoutData (gd_portText);
    portText.addModifyListener (new ModifyListener ()
    {
      @Override
      public void modifyText (ModifyEvent uEvent)
      {
        validateInput ();
      }
    });
    final Label nameLabel = new Label (container, SWT.NONE);
    final GridData gd_nameLabel = new GridData (SWT.RIGHT, SWT.CENTER, false, false);
    nameLabel.setLayoutData (gd_nameLabel);
    nameLabel.setText ("Name:");

    nameText = new Text (container, SWT.BORDER);
    final GridData gd_nameText = new GridData (SWT.FILL, SWT.CENTER, true, false);
    nameText.setLayoutData (gd_nameText);
    nameText.addModifyListener (new ModifyListener ()
    {
      @Override
      public void modifyText (ModifyEvent uEvent)
      {
        validateInput ();
      }
    });

    final Label loginLabel = new Label (container, SWT.NONE);
    loginLabel.setLayoutData (new GridData (SWT.RIGHT, SWT.CENTER, false, false));
    loginLabel.setText ("Login:");

    loginText = new Text (container, SWT.BORDER);
    loginText.setLayoutData (new GridData (SWT.FILL, SWT.CENTER, true, false));
    loginText.addModifyListener (new ModifyListener ()
    {
      @Override
      public void modifyText (ModifyEvent uEvent)
      {
        validateInput ();
      }
    });

    final Label passwordLabel = new Label (container, SWT.NONE);
    passwordLabel.setLayoutData (new GridData (SWT.RIGHT, SWT.CENTER, false, false));
    passwordLabel.setText ("Password:");

    passwordText = new Text (container, SWT.BORDER | SWT.PASSWORD);
    passwordText.setLayoutData (new GridData (SWT.FILL, SWT.CENTER, false, false));
    passwordText.addModifyListener (new ModifyListener ()
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

  @Override
  public void configureShell (Shell newShell)
  {
    super.configureShell (newShell);
    newShell.setText ("Database connection");
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

    address = MenumakerPluginUI.getDefault ().getDialogSettings ().get (KEY_ADDRESS);
    addressText.setText (address != null ? address : "");

    final String portString = MenumakerPluginUI.getDefault ().getDialogSettings ().get (KEY_PORT);
    port = portString != null ? Integer.parseInt (portString) : DEFAULT_PORT;
    portText.setText (String.valueOf (port));

    name = MenumakerPluginUI.getDefault ().getDialogSettings ().get (KEY_NAME);
    nameText.setText (name != null ? name : "");

    login = MenumakerPluginUI.getDefault ().getDialogSettings ().get (KEY_LOGIN);
    loginText.setText (login != null ? login : "");

    password = MenumakerPluginUI.getDefault ().getDialogSettings ().get (KEY_PASSWORD);
    passwordText.setText (password != null ? password : "");
  }

  @Override
  public void okPressed ()
  {
    address = addressText.getText ();
    port = Integer.parseInt (portText.getText ());
    name = nameText.getText ();
    login = loginText.getText ();
    password = passwordText.getText ();

    MenumakerPluginUI.getDefault ().getDialogSettings ().put (KEY_ADDRESS, address);
    MenumakerPluginUI.getDefault ().getDialogSettings ().put (KEY_PORT, String.valueOf (port));
    MenumakerPluginUI.getDefault ().getDialogSettings ().put (KEY_NAME, name);
    MenumakerPluginUI.getDefault ().getDialogSettings ().put (KEY_LOGIN, login);
    MenumakerPluginUI.getDefault ().getDialogSettings ().put (KEY_PASSWORD, password);

    super.okPressed ();
  }

  private void validateInput ()
  {
    errorText.setText ("");
    getButton (IDialogConstants.OK_ID).setEnabled (true);

    if (addressText.getText ().length () == 0)
    {
      errorText.setText ("Invalid address");
      getButton (IDialogConstants.OK_ID).setEnabled (false);
      return;
    }

    if (portText.getText ().length () == 0)
    {
      errorText.setText ("Invalid port");
      getButton (IDialogConstants.OK_ID).setEnabled (false);
      return;
    }
    try
    {
      Integer.parseInt (portText.getText ());
    } catch (final NumberFormatException e)
    {
      errorText.setText ("Invalid port");
      getButton (IDialogConstants.OK_ID).setEnabled (false);
      return;
    }

    final int value = Integer.parseInt (portText.getText ());

    if (value < 0 || value > 10000)
    {
      errorText.setText ("Invalid port");
      getButton (IDialogConstants.OK_ID).setEnabled (false);
      return;
    }

    if (nameText.getText ().length () == 0)
    {
      errorText.setText ("Invalid name");
      getButton (IDialogConstants.OK_ID).setEnabled (false);
      return;
    }

    if (loginText.getText ().length () == 0)
    {
      errorText.setText ("Invalid login");
      getButton (IDialogConstants.OK_ID).setEnabled (false);
      return;
    }

    if (passwordText.getText ().length () == 0)
    {
      errorText.setText ("Invalid password");
      getButton (IDialogConstants.OK_ID).setEnabled (false);
      return;
    }
  }
}
