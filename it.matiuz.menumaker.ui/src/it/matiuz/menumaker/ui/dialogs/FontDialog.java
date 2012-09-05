package it.matiuz.menumaker.ui.dialogs;

import java.awt.GraphicsEnvironment;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;

public class FontDialog extends Dialog
{
  private static final String PREVIEW_STRING = "AaBbYyZz";

  private String fontName;
  private int fontSize;
  private int fontType;
  private int red;
  private int green;
  private int blue;

  private Combo fontCombo;
  private Spinner fontSizeSpinner;
  private Scale redScale;
  private Scale greenScale;
  private Scale blueScale;

  private Canvas previewCanvas;
  private Canvas redCanvas;
  private Canvas greenCanvas;
  private Canvas blueCanvas;

  private String title;
  private final Shell shell;

  private Button normalFontButton;

  private Button italicFontButton;

  private Button boldFontButton;

  public FontDialog (Shell uParentShell)
  {
    super (uParentShell);
    shell = uParentShell;
  }

  public void setTitle (String uTitle)
  {
    title = uTitle;
  }

  public String getFontName ()
  {
    return fontName;
  }

  public void setFontName (String uFontName)
  {
    fontName = uFontName;
  }

  public int getFontSize ()
  {
    return fontSize;
  }

  public void setFontSize (int uFontSize)
  {
    fontSize = uFontSize;
  }

  public int getFontType ()
  {
    return fontType;
  }

  public void setFontType (int uFontType)
  {
    fontType = uFontType;
  }

  public int getRed ()
  {
    return red;
  }

  public void setRed (int uRed)
  {
    red = uRed;
  }

  public int getGreen ()
  {
    return green;
  }

  public void setGreen (int uGreen)
  {
    green = uGreen;
  }

  public int getBlue ()
  {
    return blue;
  }

  public void setBlue (int uBlue)
  {
    blue = uBlue;
  }

  @Override
  protected Control createDialogArea (Composite parent)
  {
    final Composite container = (Composite) super.createDialogArea (parent);
    final GridData gridData = new GridData (SWT.FILL, SWT.CENTER, true, false);
    gridData.minimumWidth = 250;
    container.setLayoutData (gridData);
    final GridLayout gridLayout = new GridLayout ();
    gridLayout.numColumns = 3;
    container.setLayout (gridLayout);

    final Label fontLabel = new Label (container, SWT.NONE);
    final GridData gd_fontLabel = new GridData (SWT.RIGHT, SWT.CENTER, false, false);
    fontLabel.setLayoutData (gd_fontLabel);
    fontLabel.setText ("Font:");

    fontCombo = new Combo (container, SWT.READ_ONLY);
    fontCombo.setLayoutData (new GridData (SWT.FILL, SWT.CENTER, true, false, 2, 1));
    fontCombo.addModifyListener (new ModifyListener ()
    {
      @Override
      public void modifyText (ModifyEvent uEvent)
      {
        previewCanvas.redraw ();
      }
    });

    final Label sizeLabel = new Label (container, SWT.NONE);
    final GridData gd_sizeLabel = new GridData (SWT.RIGHT, SWT.CENTER, false, false);
    sizeLabel.setLayoutData (gd_sizeLabel);
    sizeLabel.setText ("Size:");

    fontSizeSpinner = new Spinner (container, SWT.BORDER | SWT.READ_ONLY);
    fontSizeSpinner.setPageIncrement (1);
    fontSizeSpinner.setMaximum (20);
    fontSizeSpinner.setMinimum (8);
    fontSizeSpinner.addModifyListener (new ModifyListener ()
    {
      @Override
      public void modifyText (ModifyEvent uEvent)
      {
        previewCanvas.redraw ();
      }
    });
    new Label (container, SWT.NONE);

    final Group typeGroup = new Group (container, SWT.NONE);
    typeGroup.setText ("Type");
    final GridLayout gridLayout_1 = new GridLayout ();
    gridLayout_1.horizontalSpacing = 3;
    gridLayout_1.numColumns = 3;
    typeGroup.setLayout (gridLayout_1);
    final GridData gd_typeGroup = new GridData (SWT.FILL, SWT.FILL, true, true, 3, 1);
    typeGroup.setLayoutData (gd_typeGroup);

    normalFontButton = new Button (typeGroup, SWT.RADIO);
    normalFontButton.setLayoutData (new GridData (SWT.CENTER, SWT.CENTER, true, false));
    normalFontButton.setText ("Normal");
    normalFontButton.addSelectionListener (new SelectionAdapter ()
    {
      @Override
      public void widgetSelected (SelectionEvent e)
      {
        previewCanvas.redraw ();
      }
    });

    italicFontButton = new Button (typeGroup, SWT.RADIO);
    italicFontButton.setLayoutData (new GridData (SWT.CENTER, SWT.CENTER, true, false));
    italicFontButton.setText ("Italic");
    italicFontButton.addSelectionListener (new SelectionAdapter ()
    {
      @Override
      public void widgetSelected (SelectionEvent e)
      {
        previewCanvas.redraw ();
      }
    });

    boldFontButton = new Button (typeGroup, SWT.RADIO);
    boldFontButton.setLayoutData (new GridData (SWT.CENTER, SWT.CENTER, true, false));
    boldFontButton.setText ("Bold");
    boldFontButton.addSelectionListener (new SelectionAdapter ()
    {
      @Override
      public void widgetSelected (SelectionEvent e)
      {
        previewCanvas.redraw ();
      }
    });

    final Label redLabel = new Label (container, SWT.NONE);
    redLabel.setLayoutData (new GridData (SWT.RIGHT, SWT.CENTER, false, false));
    redLabel.setText ("Red:");

    redScale = new Scale (container, SWT.NONE);
    redScale.setMaximum (255);
    redScale.addSelectionListener (new SelectionAdapter ()
    {
      @Override
      public void widgetSelected (SelectionEvent e)
      {
        previewCanvas.redraw ();
      }
    });

    redCanvas = new Canvas (container, SWT.BORDER);
    final GridData gd_redCanvas = new GridData (20, 20);
    redCanvas.setLayoutData (gd_redCanvas);
    redCanvas.setBackground (shell.getDisplay ().getSystemColor (SWT.COLOR_RED));

    final Label greenLabel = new Label (container, SWT.NONE);
    greenLabel.setLayoutData (new GridData (SWT.RIGHT, SWT.CENTER, false, false));
    greenLabel.setText ("Green");

    greenScale = new Scale (container, SWT.NONE);
    greenScale.setMaximum (255);
    greenScale.addSelectionListener (new SelectionAdapter ()
    {
      @Override
      public void widgetSelected (SelectionEvent e)
      {
        previewCanvas.redraw ();
      }
    });

    greenCanvas = new Canvas (container, SWT.BORDER);
    final GridData gd_greenCanvas = new GridData (20, 20);
    greenCanvas.setLayoutData (gd_greenCanvas);
    greenCanvas.setBackground (shell.getDisplay ().getSystemColor (SWT.COLOR_GREEN));

    final Label blueLabel = new Label (container, SWT.NONE);
    blueLabel.setLayoutData (new GridData (SWT.RIGHT, SWT.CENTER, false, false));
    blueLabel.setText ("Blue:");

    blueScale = new Scale (container, SWT.NONE);
    blueScale.setMaximum (255);
    blueScale.addSelectionListener (new SelectionAdapter ()
    {
      @Override
      public void widgetSelected (SelectionEvent e)
      {
        previewCanvas.redraw ();
      }
    });

    blueCanvas = new Canvas (container, SWT.BORDER);
    final GridData gd_blueCanvas = new GridData (20, 20);
    blueCanvas.setLayoutData (gd_blueCanvas);
    blueCanvas.setBackground (shell.getDisplay ().getSystemColor (SWT.COLOR_BLUE));

    final Group previewGroup = new Group (container, SWT.NONE);
    previewGroup.setText ("Preview");
    final GridData gd_previewGroup = new GridData (SWT.FILL, SWT.CENTER, true, false, 3, 1);
    previewGroup.setLayoutData (gd_previewGroup);
    previewGroup.setLayout (new GridLayout ());

    previewCanvas = new Canvas (previewGroup, SWT.BORDER | SWT.NO_BACKGROUND);
    previewCanvas.setLayoutData (new GridData (SWT.FILL, SWT.FILL, true, true));
    previewCanvas.addPaintListener (new PaintListener ()
    {
      @Override
      public void paintControl (PaintEvent uPaintEvent)
      {
        final String osName = System.getProperty ("os.name");

        if (osName.equals ("FreeBSD"))
          implementPaint (uPaintEvent.gc);
        else if (osName.equals ("Linux"))
          implementPaint (uPaintEvent.gc);
        else
        {
          final Image image = new Image (shell.getDisplay (), previewCanvas.getSize ().x, previewCanvas.getSize ().y);
          final GC imageGC = new GC (image);
          implementPaint (imageGC);
          uPaintEvent.gc.drawImage (image, 0, 0);
          imageGC.dispose ();
          image.dispose ();
        }
      }
    });

    return container;
  }

  private void implementPaint (GC uGC)
  {
    final int width = previewCanvas.getSize ().x;
    final int height = previewCanvas.getSize ().y;

    final Color backgroundColor = new Color (null, 0xff, 0xff, 0xff);
    final Color foregroundColor = new Color (null, redScale.getSelection (), greenScale.getSelection (), blueScale.getSelection ());
    uGC.setForeground (foregroundColor);
    uGC.setBackground (backgroundColor);

    int type = SWT.NORMAL;

    if (normalFontButton.getSelection ())
      type = SWT.NORMAL;
    else if (italicFontButton.getSelection ())
      type = SWT.ITALIC;
    else if (boldFontButton.getSelection ())
      type = SWT.BOLD;

    final Font font = new Font (null, fontCombo.getText (), fontSizeSpinner.getSelection (), type);
    uGC.setFont (font);

    uGC.fillRectangle (0, 0, width, height);

    uGC.drawString (PREVIEW_STRING, (width - uGC.stringExtent (PREVIEW_STRING).x) / 2, (height - uGC.stringExtent (PREVIEW_STRING).y) / 2);

    font.dispose ();
    foregroundColor.dispose ();
    backgroundColor.dispose ();
  }

  @Override
  protected void createButtonsForButtonBar (Composite parent)
  {
    createButton (parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
    createButton (parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
  }

  @Override
  protected void configureShell (Shell newShell)
  {
    super.configureShell (newShell);
    newShell.setText (title);
  }

  @Override
  protected void okPressed ()
  {
    fontName = fontCombo.getText ();
    fontSize = fontSizeSpinner.getSelection ();
    red = redScale.getSelection ();
    green = greenScale.getSelection ();
    blue = blueScale.getSelection ();

    if (normalFontButton.getSelection ())
      fontType = SWT.NORMAL;
    else if (italicFontButton.getSelection ())
      fontType = SWT.ITALIC;
    else if (boldFontButton.getSelection ())
      fontType = SWT.BOLD;

    super.okPressed ();
  }

  @Override
  public void create ()
  {
    super.create ();

    for (final String element : GraphicsEnvironment.getLocalGraphicsEnvironment ().getAvailableFontFamilyNames ())
      fontCombo.add (element);

    fontCombo.setText (fontName);
    fontSizeSpinner.setSelection (fontSize);
    redScale.setSelection (red);
    greenScale.setSelection (green);
    blueScale.setSelection (blue);

    switch (fontType)
    {
      case SWT.NORMAL:
        normalFontButton.setSelection (true);
        break;
      case SWT.ITALIC:
        italicFontButton.setSelection (true);
        break;
      case SWT.BOLD:
        boldFontButton.setSelection (true);
        break;
      default:
        normalFontButton.setSelection (true);
        break;
    }

    previewCanvas.redraw ();
  }
}
