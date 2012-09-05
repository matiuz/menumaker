package it.matiuz.menumaker.ui.dialogs;

import it.matiuz.menumaker.ui.tools.SWTImageCanvas;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.tango.icons.ITangoIconsImages;
import org.tango.icons.TangoIconsPlugin;

public class ImagePreviewDialog extends Dialog
{
  private SWTImageCanvas previewCanvas;

  private final Image image;

  private Image zoomInImage;
  private Image zoomOutImage;
  private Image fitCanvasImage;
  private Image originalImage;
  private Image saveImage;

  public ImagePreviewDialog (Shell uParentShell, Image uImage)
  {
    super (uParentShell);
    image = uImage;
  }

  @Override
  protected Control createDialogArea (Composite parent)
  {
    final Composite container = (Composite) super.createDialogArea (parent);
    container.setLayout (new GridLayout ());
    final GridData gridData = new GridData (SWT.FILL, SWT.CENTER, true, false);
    container.setLayoutData (gridData);

    final Composite actionComposite = new Composite (container, SWT.NONE);
    actionComposite.setLayoutData (new GridData (SWT.FILL, SWT.FILL, true, true));
    final GridLayout gridLayout = new GridLayout ();
    gridLayout.numColumns = 5;
    actionComposite.setLayout (gridLayout);

    final Button zoomInButton = new Button (actionComposite, SWT.NONE);
    zoomInButton.setLayoutData (new GridData ());
    zoomInImage = TangoIconsPlugin.getSmallImage (ITangoIconsImages.ACTIONS_LIST_ADD);
    zoomInButton.setImage (zoomInImage);
    zoomInButton.setToolTipText ("Zoom in");
    zoomInButton.addSelectionListener (new SelectionAdapter ()
    {
      @Override
      public void widgetSelected (final SelectionEvent e)
      {
        previewCanvas.zoomIn ();
      }
    });

    final Button zoomOutButton = new Button (actionComposite, SWT.NONE);
    zoomOutButton.setLayoutData (new GridData ());
    zoomOutImage = TangoIconsPlugin.getSmallImage (ITangoIconsImages.ACTIONS_LIST_REMOVE);
    zoomOutButton.setImage (zoomOutImage);
    zoomOutButton.setToolTipText ("Zoom out");
    zoomOutButton.addSelectionListener (new SelectionAdapter ()
    {
      @Override
      public void widgetSelected (final SelectionEvent e)
      {
        previewCanvas.zoomOut ();
      }
    });

    final Button fitCanvasButton = new Button (actionComposite, SWT.NONE);
    fitCanvasButton.setLayoutData (new GridData ());
    fitCanvasImage = TangoIconsPlugin.getSmallImage (ITangoIconsImages.ACTIONS_VIEW_FULLSCREEN);
    fitCanvasButton.setImage (fitCanvasImage);
    fitCanvasButton.setToolTipText ("Fit to window");
    fitCanvasButton.addSelectionListener (new SelectionAdapter ()
    {
      @Override
      public void widgetSelected (final SelectionEvent e)
      {
        previewCanvas.fitCanvas ();
      }
    });

    final Button originalImageButton = new Button (actionComposite, SWT.NONE);
    originalImage = TangoIconsPlugin.getSmallImage (ITangoIconsImages.ACTIONS_VIEW_REFRESH);
    originalImageButton.setImage (originalImage);
    originalImageButton.setToolTipText ("Show original image");
    originalImageButton.addSelectionListener (new SelectionAdapter ()
    {
      @Override
      public void widgetSelected (final SelectionEvent e)
      {
        previewCanvas.showOriginal ();
      }
    });

    final Button saveButton = new Button (actionComposite, SWT.NONE);
    saveButton.setLayoutData (new GridData (SWT.RIGHT, SWT.CENTER, true, false));
    saveImage = TangoIconsPlugin.getSmallImage (ITangoIconsImages.DEVICES_MEDIA_FLOPPY);
    saveButton.setImage (saveImage);
    saveButton.setToolTipText ("Save to file");
    saveButton.addSelectionListener (new SelectionAdapter ()
    {
      @Override
      public void widgetSelected (final SelectionEvent e)
      {
        final FileDialog dialog = new FileDialog (getShell (), SWT.SAVE);

        dialog.setFilterExtensions (new String[] { "*.bmp" });
        dialog.setFilterNames (new String[] { "BMP file (*.bmp)" });

        String filename = dialog.open ();

        if (filename == null)
          return;

        if (filename.indexOf ('.') == -1)
          filename = filename + ".bmp";

        final ImageLoader loader = new ImageLoader ();
        loader.data = new ImageData[] { previewCanvas.getImageData () };
        loader.save (filename, SWT.IMAGE_BMP);
      }
    });

    final Group previewGroup = new Group (container, SWT.NONE);
    previewGroup.setText ("Print preview");
    previewGroup.setLayout (new GridLayout ());

    previewCanvas = new SWTImageCanvas (previewGroup, SWT.BORDER | SWT.NO_BACKGROUND);
    final GridData gd_previewCanvas = new GridData (SWT.FILL, SWT.FILL, true, true);
    gd_previewCanvas.minimumHeight = 500;
    gd_previewCanvas.minimumWidth = 500;
    previewCanvas.setLayoutData (gd_previewCanvas);
    previewCanvas.setImageData (image.getImageData ());

    return container;
  }

  @Override
  protected void createButtonsForButtonBar (Composite parent)
  {
    createButton (parent, IDialogConstants.OK_ID, "Print", true);
    createButton (parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
  }

  @Override
  protected void configureShell (Shell newShell)
  {
    super.configureShell (newShell);
    newShell.setText ("Print preview");
  }

  @Override
  protected void okPressed ()
  {
    super.okPressed ();
  }

  @Override
  public void create ()
  {
    super.create ();
  }
}
