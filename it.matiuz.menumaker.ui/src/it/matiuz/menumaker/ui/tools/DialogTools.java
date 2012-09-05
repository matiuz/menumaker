package it.matiuz.menumaker.ui.tools;

import it.matiuz.menumaker.model.Category;
import it.matiuz.menumaker.model.Item;
import it.matiuz.menumaker.ui.MenumakerPluginUI;
import it.matiuz.menumaker.ui.dialogs.CategoryDialog;
import it.matiuz.menumaker.ui.dialogs.FontDialog;
import it.matiuz.menumaker.ui.dialogs.ImageDialog;
import it.matiuz.menumaker.ui.dialogs.ItemDialog;
import it.matiuz.menumaker.ui.dialogs.PageDialog;
import it.matiuz.menumaker.ui.tools.PrintConfigurator.FontType;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;

public class DialogTools
{
  public static void storeItemDialogData (ItemDialog uItemDialog, Item uItem)
  {
    uItemDialog.setDescription (uItem.getDescription ());
    uItemDialog.setPrice (uItem.getPrice ());
    uItemDialog.setPriceNotes (uItem.getPriceNotes ());
    uItemDialog.setCategoryId (uItem.getCategoryId ());
    
    final DatabaseManager manager = MenumakerPluginUI.getDatabase ();
    try
    {
      uItemDialog.setCategoryName (manager.getCategoryDescription (uItem.getCategoryId ()));
    } catch (final DatabaseException e)
    {
      MessageDialog.openError (PlatformUI.getWorkbench ().getDisplay ().getActiveShell (), "Error", e.getMessage ());
    }
  }

  public static void retrieveItemDialogData (ItemDialog uItemDialog, Item uItem)
  {
    uItem.setDescription (uItemDialog.getDescription ());
    uItem.setPrice (uItemDialog.getPrice ());
    uItem.setPriceNotes (uItemDialog.getPriceNotes ());
    uItem.setCategoryId (uItemDialog.getCategoryId ());
  }

  public static void retrieveCategoryDialogData (CategoryDialog uCategoryDialog, Category uCategory)
  {
    uCategory.setDescription (uCategoryDialog.getDescription ());
    uCategory.setPriority (uCategoryDialog.getPriority ());
  }

  public static void storeCategoryDialogData (CategoryDialog uCategoryDialog, Category uCategory)
  {
    uCategoryDialog.setDescription (uCategory.getDescription ());
    uCategoryDialog.setPriority (uCategory.getPriority ());
  }

  public static void retrieveFontDialogData (FontDialog uFontDialog, FontType uFontType)
  {
    final PrintConfigurator configurator = MenumakerPluginUI.getPrintConfigurator ();

    if (uFontType.equals (FontType.CATEGORY_FONT))
    {
      configurator.setCategoryFontName (uFontDialog.getFontName ());
      configurator.setCategoryFontSize (uFontDialog.getFontSize ());
      configurator.setCategoryFontType (uFontDialog.getFontType ());
      configurator.setCategoryGreen (uFontDialog.getGreen ());
      configurator.setCategoryRed (uFontDialog.getRed ());
      configurator.setCategoryBlue (uFontDialog.getBlue ());
    } else
    {
      configurator.setItemFontName (uFontDialog.getFontName ());
      configurator.setItemFontSize (uFontDialog.getFontSize ());
      configurator.setItemFontType (uFontDialog.getFontType ());
      configurator.setItemGreen (uFontDialog.getGreen ());
      configurator.setItemRed (uFontDialog.getRed ());
      configurator.setItemBlue (uFontDialog.getBlue ());
    }
  }

  public static void storeFontDialogData (FontDialog uFontDialog, FontType uFontType)
  {
    final PrintConfigurator configurator = MenumakerPluginUI.getPrintConfigurator ();

    if (uFontType.equals (FontType.CATEGORY_FONT))
    {
      uFontDialog.setFontName (configurator.getCategoryFontName ());
      uFontDialog.setFontSize (configurator.getCategoryFontSize ());
      uFontDialog.setFontType (configurator.getCategoryFontType ());
      uFontDialog.setGreen (configurator.getCategoryGreen ());
      uFontDialog.setRed (configurator.getCategoryRed ());
      uFontDialog.setBlue (configurator.getCategoryBlue ());
    } else
    {
      uFontDialog.setFontName (configurator.getItemFontName ());
      uFontDialog.setFontSize (configurator.getItemFontSize ());
      uFontDialog.setFontType (configurator.getItemFontType ());
      uFontDialog.setGreen (configurator.getItemGreen ());
      uFontDialog.setRed (configurator.getItemRed ());
      uFontDialog.setBlue (configurator.getItemBlue ());
    }
  }

  public static void retrieveImageDialogData (ImageDialog uImageDialog)
  {
    final PrintConfigurator configurator = MenumakerPluginUI.getPrintConfigurator ();

    configurator.setImageBorderSize (uImageDialog.getImageBorderSize ());
    configurator.setImageBorderEnable (uImageDialog.isImageBorderEnable ());
    configurator.setLogoImagePath (uImageDialog.getLogoImagePath ());
    configurator.setLogoPosition (uImageDialog.getLogoPosition ());
  }

  public static void storeImageDialogData (ImageDialog uImageDialog)
  {
    final PrintConfigurator configurator = MenumakerPluginUI.getPrintConfigurator ();

    uImageDialog.setImageBorderSize (configurator.getImageBorderSize ());
    uImageDialog.setImageBorderEnable (configurator.isImageBorderEnable ());
    uImageDialog.setLogoImagePath (configurator.getLogoImagePath ());
    uImageDialog.setLogoPosition (configurator.getLogoPosition ());
  }

  public static void retrievePageDialogData (PageDialog uPageDialog)
  {
    final PrintConfigurator configurator = MenumakerPluginUI.getPrintConfigurator ();

    configurator.setHorizontalMarginSize (uPageDialog.getHorizontalMarginSize ());
    configurator.setVerticalMarginSize (uPageDialog.getVerticalMarginSize ());
    configurator.setCategoryBoundsSize (uPageDialog.getCategoryBoundsSize ());
    configurator.setItemBoundsSize (uPageDialog.getItemBoundsSize ());
  }

  public static void storePageDialogData (PageDialog uPageDialog)
  {
    final PrintConfigurator configurator = MenumakerPluginUI.getPrintConfigurator ();

    uPageDialog.setHorizontalMarginSize (configurator.getHorizontalMarginSize ());
    uPageDialog.setVerticalMarginSize (configurator.getVerticalMarginSize ());
    uPageDialog.setCategoryBoundsSize (configurator.getCategoryBoundsSize ());
    uPageDialog.setItemBoundsSize (configurator.getItemBoundsSize ());
  }
}
