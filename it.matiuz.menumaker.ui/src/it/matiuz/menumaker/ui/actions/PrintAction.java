package it.matiuz.menumaker.ui.actions;

import it.matiuz.menumaker.model.Item;
import it.matiuz.menumaker.model.Menu;
import it.matiuz.menumaker.ui.MenumakerPluginUI;
import it.matiuz.menumaker.ui.dialogs.ImagePreviewDialog;
import it.matiuz.menumaker.ui.tools.DatabaseManager;
import it.matiuz.menumaker.ui.tools.PrintConfigurator;
import it.matiuz.menumaker.ui.tools.PrintConfigurator.LogoPosition;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.printing.PrintDialog;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

public class PrintAction extends Action implements IWorkbenchAction
{
  public static final String ID = "it.matiuz.menumaker.ui.actions.print_action";

  private IWorkbenchWindow window;

  private final Display display;
  private Printer printer;
  private double scaleFactor;

  private int itemFontHeight;
  private int categoryFontHeight;

  public PrintAction (IWorkbenchWindow uWindow, String uLabel)
  {
    window = uWindow;
    display = window.getWorkbench ().getDisplay ();

    setText (uLabel);
    setId (ID);
    setActionDefinitionId (ID);
  }

  @Override
  public void run ()
  {
    final PrintDialog dialog = new PrintDialog (window.getShell (), SWT.NONE);
    final PrinterData data = dialog.open ();
    if (data == null)
      return;

    printer = new Printer (Printer.getDefaultPrinterData ());
    scaleFactor = printer.getDPI ().x * 1.0 / display.getDPI ().x;

    final Thread printingThread = new Thread ("Printing")
    {
      @Override
      public void run ()
      {
        // Start the print job
        if (printer.startJob ("Print"))
        {
          GC printerGC = null;

          try
          {
            if (printer.startPage ())
            {
              printerGC = new GC (printer);
              drawMenu (MenumakerPluginUI.getMenuModelHandler ().getMenu (), printer, printerGC, scaleFactor);
            }
          } catch (final Exception uException)
          {
            uException.printStackTrace ();
            PlatformUI.getWorkbench ().getDisplay ().asyncExec (new Runnable ()
            {
              @Override
              public void run ()
              {
                MessageDialog.openError (window.getShell (), "Error", "Print error");
              }
            });
          } finally
          {
            if (printerGC != null)
              printerGC.dispose ();
            printer.endPage ();
          }
        }
        // End the job and dispose the printer
        printer.endJob ();
        printer.dispose ();
      }
    };

    final Image previewImage = createPreview ();
    if (previewImage == null)
      return;

    final ImagePreviewDialog previewDialog = new ImagePreviewDialog (window.getShell (), previewImage);
    if (previewDialog.open () == Window.OK)
      printingThread.start ();

    previewImage.dispose ();
  }

  @Override
  public void dispose ()
  {
    window = null;
  }

  private int getMaxPriceSize (GC uGC)
  {
    final Menu menu = MenumakerPluginUI.getMenuModelHandler ().getMenu ();
    int max = 0;

    for (final Item item : menu.getItems ())
    {
      final String completePrice = String.format ("€ %1$.2f", new Double (item.getPrice ())) + item.getPriceNotes ();
      final int size = uGC.stringExtent (completePrice).x;

      if (size > max)
        max = size;
    }
    return max;
  }

  private Image createPreview ()
  {
    final Menu menu = MenumakerPluginUI.getMenuModelHandler ().getMenu ();

    if (menu == null)
      return null;

    Image previewImage = null;
    GC previewGC = null;

    try
    {
      final Rectangle clientArea = printer.getClientArea ();

      previewImage = new Image (display, (int) (clientArea.width / scaleFactor), (int) (clientArea.height / scaleFactor));

      previewGC = new GC (previewImage);
      drawMenu (menu, display, previewGC, 1.0);

    } catch (final Exception uException)
    {
      uException.printStackTrace ();
      PlatformUI.getWorkbench ().getDisplay ().asyncExec (new Runnable ()
      {
        @Override
        public void run ()
        {
          MessageDialog.openError (window.getShell (), "Error", "Create preview error");
        }
      });
      return null;
    } finally
    {
      if (previewGC != null)
        previewGC.dispose ();
    }
    return previewImage;
  }

  private void drawMenu (Menu uMenu, Device uDevice, GC uGC, double uScaleFactor) throws Exception
  {
    final PrintConfigurator configurator = MenumakerPluginUI.getPrintConfigurator ();
    final DatabaseManager manager = MenumakerPluginUI.getDatabase ();

    final Rectangle clientArea = printer.getClientArea ();
    final Rectangle trim = printer.computeTrim (0, 0, 0, 0);

    final Point printerDPI = printer.getDPI ();

    final int leftMargin = (int) ((printerDPI.x + trim.x) / scaleFactor); // one inch from left side of paper
    final int rightMargin = (int) ((clientArea.width - printerDPI.x + trim.x + trim.width) / scaleFactor); // one inch from right side of paper
    final int topMargin = (int) ((printerDPI.y + trim.y) / scaleFactor); // one inch from top edge of paper
    final int bottomMargin = (int) ((clientArea.height - printerDPI.y + trim.y + trim.height) / scaleFactor); // one inch from bottom edge of paper

    int x1 = leftMargin + configurator.getHorizontalMarginSize ();
    int x2 = rightMargin - configurator.getHorizontalMarginSize ();

    int y1 = topMargin + configurator.getVerticalMarginSize ();
    int y2 = bottomMargin - configurator.getVerticalMarginSize ();

    if (configurator.getLogoPosition () != LogoPosition.NO_LOGO)
    {
      final ImageData[] imageData = new ImageLoader ().load (configurator.getLogoImagePath ());
      final Image logoImage = new Image (uDevice, imageData[0]);

      int imageX = x1;
      int imageY = y1;

      if (configurator.getLogoPosition ().equals (LogoPosition.TOP_LEFT))
      {
        imageX = leftMargin;
        imageY = topMargin;
      } else if (configurator.getLogoPosition ().equals (LogoPosition.TOP_RIGHT))
      {
        imageX = rightMargin - imageData[0].width;
        imageY = topMargin;
      } else if (configurator.getLogoPosition ().equals (LogoPosition.BOTTOM_LEFT))
      {
        imageX = leftMargin;
        imageY = bottomMargin - imageData[0].height;
      } else if (configurator.getLogoPosition ().equals (LogoPosition.BOTTOM_RIGHT))
      {
        imageX = rightMargin - imageData[0].width;
        imageY = bottomMargin - imageData[0].height;
      } else if (configurator.getLogoPosition ().equals (LogoPosition.CENTER))
      {
        imageX = (rightMargin - leftMargin - imageData[0].width) / 2 + leftMargin;
        imageY = (bottomMargin - topMargin - imageData[0].height) / 2 + topMargin;
      }

      uGC.drawImage (logoImage, 0, 0, imageData[0].width, imageData[0].height, (int) (imageX * uScaleFactor), (int) (imageY * uScaleFactor), (int) (uScaleFactor * imageData[0].width), (int) (uScaleFactor * imageData[0].height));

      logoImage.dispose ();
    }

    final Color itemColor = new Color (uDevice, configurator.getItemRed (), configurator.getItemGreen (), configurator.getItemBlue ());
    final Font itemFont = new Font (uDevice, configurator.getItemFontName (), configurator.getItemFontSize (), configurator.getItemFontType ());

    final Color categoryColor = new Color (uDevice, configurator.getCategoryRed (), configurator.getCategoryGreen (), configurator.getCategoryBlue ());
    final Font categoryFont = new Font (uDevice, configurator.getCategoryFontName (), configurator.getCategoryFontSize (), configurator.getCategoryFontType ());

    if (uDevice instanceof Display)
    {
      uGC.setFont (categoryFont);
      categoryFontHeight = uGC.getFontMetrics ().getHeight ();

      uGC.setFont (itemFont);
      itemFontHeight = uGC.getFontMetrics ().getHeight ();
    }

    uGC.setFont (itemFont);

    if (configurator.isImageBorderEnable ())
    {
      final int borderSize = configurator.getImageBorderSize ();

      x1 += borderSize;
      x2 -= borderSize;
      y1 += borderSize;
      y2 -= borderSize;

      uGC.setBackground (categoryColor);

      uGC.fillRectangle ((int) (leftMargin * uScaleFactor), (int) (topMargin * uScaleFactor), (int) (borderSize * uScaleFactor), (int) ((bottomMargin - topMargin) * uScaleFactor));
      uGC.fillRectangle ((int) (leftMargin * uScaleFactor), (int) (topMargin * uScaleFactor), (int) ((rightMargin - leftMargin) * uScaleFactor), (int) (borderSize * uScaleFactor));
      uGC.fillRectangle ((int) ((rightMargin - borderSize) * uScaleFactor), (int) (topMargin * uScaleFactor), (int) (borderSize * uScaleFactor), (int) ((bottomMargin - topMargin) * uScaleFactor));
      uGC.fillRectangle ((int) (leftMargin * uScaleFactor), (int) ((bottomMargin - borderSize) * uScaleFactor), (int) ((rightMargin - leftMargin) * uScaleFactor), (int) (borderSize * uScaleFactor));
    }
    uGC.setFont (itemFont);

    int y = y1;
    int categoryId = 0;
    final int maxPriceSize = getMaxPriceSize (uGC);

    for (final Item item : uMenu.getItems ())
    {
      if (item.getCategoryId () != categoryId)
      {
        y += configurator.getCategoryBoundsSize ();

        categoryId = item.getCategoryId ();
        final String category = manager.getCategoryDescription (categoryId);

        uGC.setForeground (categoryColor);
        uGC.setFont (categoryFont);

        if (y + categoryFontHeight > y2)
          break;

        uGC.drawString (category, (int) (((x2 - x1 - uGC.stringExtent (category).x / uScaleFactor) / 2 + x1) * uScaleFactor), (int) (y * uScaleFactor), true);

        y += categoryFontHeight + configurator.getCategoryBoundsSize ();
      }
      final String description = item.getDescription ();
      final String completePrice = String.format ("€ %1$.2f", new Double (item.getPrice ())) + item.getPriceNotes ();

      uGC.setForeground (itemColor);
      uGC.setFont (itemFont);

      if (y + itemFontHeight > y2)
        break;

      y += configurator.getItemBoundsSize ();

      uGC.drawString (description, (int) (x1 * uScaleFactor), (int) (y * uScaleFactor), true);
      uGC.drawString (completePrice, (int) ((x2 - maxPriceSize / uScaleFactor) * uScaleFactor), (int) (y * uScaleFactor), true);

      y += itemFontHeight + configurator.getItemBoundsSize ();
    }
    itemColor.dispose ();
    itemFont.dispose ();
    categoryColor.dispose ();
    categoryFont.dispose ();
  }
}
