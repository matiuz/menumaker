package it.matiuz.menumaker.ui.tools;

import it.matiuz.menumaker.ui.MenumakerPluginUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.eclipse.swt.SWT;

public class PrintConfigurator
{
  public static enum FontType {
    CATEGORY_FONT, ITEM_FONT
  };

  public static enum ImageType {
    BACKGROUND_IMAGE, LOGO_IMAGE
  };

  public static enum LogoPosition {
    TOP_RIGHT, TOP_LEFT, BOTTOM_RIGHT, BOTTOM_LEFT, CENTER, NO_LOGO
  };

  private static final String FILENAME_PROPERTIES = "menu_maker.xml";

  private static final String KEY_CATEGORY_FONT_NAME = "CATEGORY_FONT_NAME";
  private static final String KEY_CATEGORY_FONT_SIZE = "CATEGORY_FONT_SIZE";
  private static final String KEY_CATEGORY_FONT_TYPE = "CATEGORY_FONT_TYPE";
  private static final String KEY_CATEGORY_RED = "CATEGORY_RED";
  private static final String KEY_CATEGORY_GREEN = "CATEGORY_GREEN";
  private static final String KEY_CATEGORY_BLUE = "CATEGORY_BLUE";
  private static final String KEY_ITEM_FONT_NAME = "ITEM_FONT_NAME";
  private static final String KEY_ITEM_FONT_SIZE = "ITEM_FONT_SIZE";
  private static final String KEY_ITEM_FONT_TYPE = "ITEM_FONT_TYPE";
  private static final String KEY_ITEM_RED = "ITEM_RED";
  private static final String KEY_ITEM_GREEN = "ITEM_GREEN";
  private static final String KEY_ITEM_BLUE = "ITEM_BLUE";

  private static final String KEY_IMAGE_BORDER_SIZE = "IMAGE_BORDER_SIZE";
  private static final String KEY_IS_IMAGE_BORDER_ENABLE = "IS_IMAGE_BORDER_ENABLE";
  private static final String KEY_LOGO_IMAGE_PATH = "LOGO_IMAGE_PATH";
  private static final String KEY_LOGO_POSITION = "LOGO_POSITION";

  private static final String KEY_HORIZONTAL_MARGIN_SIZE = "HORIZONTAL_MARGIN_SIZE";
  private static final String KEY_VERTICAL_MARGIN_SIZE = "VERTICAL_MARGIN_SIZE";
  private static final String KEY_CATEGORY_BOUNDS_SIZE = "CATEGORY_BOUNDS_SIZE";
  private static final String KEY_ITEM_BOUNDS_SIZE = "ITEM_BOUNDS_SIZE";

  private static final String DEFAULT_ITEM_FONT_NAME = "Arial";
  private static final int DEFAULT_ITEM_FONT_SIZE = 12;
  private static final int DEFAULT_ITEM_FONT_TYPE = SWT.NORMAL;
  private static final int DEFAULT_ITEM_RED = 0x00;
  private static final int DEFAULT_ITEM_GREEN = 0x00;
  private static final int DEFAULT_ITEM_BLUE = 0x00;
  private static final String DEFAULT_CATEGORY_FONT_NAME = "Arial";
  private static final int DEFAULT_CATEGORY_FONT_SIZE = 12;
  private static final int DEFAULT_CATEGORY_FONT_TYPE = SWT.NORMAL;
  private static final int DEFAULT_CATEGORY_RED = 0x00;
  private static final int DEFAULT_CATEGORY_GREEN = 0x00;
  private static final int DEFAULT_CATEGORY_BLUE = 0x00;

  private static final int DEFAULT_IMAGE_BORDER_SIZE = 1;
  private static final boolean DEFAULT_IS_IMAGE_BORDER_ENABLE = false;
  private static final String DEFAULT_LOGO_IMAGE_PATH = "";
  private static final LogoPosition DEFAULT_LOGO_POSITION = LogoPosition.NO_LOGO;

  private static final int DEFAULT_HORIZONTAL_MARGIN_SIZE = 0;
  private static final int DEFAULT_VERTICAL_MARGIN_SIZE = 0;
  private static final int DEFAULT_CATEGORY_BOUNDS_SIZE = 0;
  private static final int DEFAULT_ITEM_BOUNDS_SIZE = 0;

  private String itemFontName;
  private int itemFontSize;
  private int itemFontType;
  private int itemRed;
  private int itemGreen;
  private int itemBlue;
  private String categoryFontName;
  private int categoryFontSize;
  private int categoryFontType;
  private int categoryRed;
  private int categoryGreen;
  private int categoryBlue;
  private int imageBorderSize;
  private boolean isImageBorderEnable;
  private String logoImagePath;
  private LogoPosition logoPosition;
  private int horizontalMarginSize;
  private int verticalMarginSize;
  private int categoryBoundsSize;
  private int itemBoundsSize;

  public PrintConfigurator ()
  {
    itemFontName = DEFAULT_ITEM_FONT_NAME;
    itemFontSize = DEFAULT_ITEM_FONT_SIZE;
    itemFontType = DEFAULT_ITEM_FONT_TYPE;
    itemRed = DEFAULT_ITEM_RED;
    itemBlue = DEFAULT_ITEM_BLUE;
    itemGreen = DEFAULT_ITEM_GREEN;
    categoryFontName = DEFAULT_CATEGORY_FONT_NAME;
    categoryFontSize = DEFAULT_CATEGORY_FONT_SIZE;
    categoryFontType = DEFAULT_CATEGORY_FONT_TYPE;
    categoryRed = DEFAULT_CATEGORY_RED;
    categoryBlue = DEFAULT_CATEGORY_BLUE;
    categoryGreen = DEFAULT_CATEGORY_GREEN;

    imageBorderSize = DEFAULT_IMAGE_BORDER_SIZE;
    logoImagePath = DEFAULT_LOGO_IMAGE_PATH;
    logoPosition = DEFAULT_LOGO_POSITION;
    isImageBorderEnable = DEFAULT_IS_IMAGE_BORDER_ENABLE;

    horizontalMarginSize = DEFAULT_HORIZONTAL_MARGIN_SIZE;
    verticalMarginSize = DEFAULT_VERTICAL_MARGIN_SIZE;
    categoryBoundsSize = DEFAULT_CATEGORY_BOUNDS_SIZE;
    itemBoundsSize = DEFAULT_ITEM_BOUNDS_SIZE;
  }

  public String getItemFontName ()
  {
    return itemFontName;
  }

  public void setItemFontName (String uItemFontName)
  {
    itemFontName = uItemFontName;
  }

  public int getItemFontSize ()
  {
    return itemFontSize;
  }

  public void setItemFontSize (int uItemFontSize)
  {
    itemFontSize = uItemFontSize;
  }

  public int getItemFontType ()
  {
    return itemFontType;
  }

  public void setItemFontType (int uItemFontType)
  {
    itemFontType = uItemFontType;
  }

  public int getItemRed ()
  {
    return itemRed;
  }

  public void setItemRed (int uItemRed)
  {
    itemRed = uItemRed;
  }

  public int getItemGreen ()
  {
    return itemGreen;
  }

  public void setItemGreen (int uItemGreen)
  {
    itemGreen = uItemGreen;
  }

  public int getItemBlue ()
  {
    return itemBlue;
  }

  public void setItemBlue (int uItemBlue)
  {
    itemBlue = uItemBlue;
  }

  public String getCategoryFontName ()
  {
    return categoryFontName;
  }

  public void setCategoryFontName (String uCategoryFontName)
  {
    categoryFontName = uCategoryFontName;
  }

  public int getCategoryFontSize ()
  {
    return categoryFontSize;
  }

  public void setCategoryFontSize (int uCategoryFontSize)
  {
    categoryFontSize = uCategoryFontSize;
  }

  public int getCategoryFontType ()
  {
    return categoryFontType;
  }

  public void setCategoryFontType (int uCategoryFontType)
  {
    categoryFontType = uCategoryFontType;
  }

  public int getCategoryRed ()
  {
    return categoryRed;
  }

  public void setCategoryRed (int uCategoryRed)
  {
    categoryRed = uCategoryRed;
  }

  public int getCategoryGreen ()
  {
    return categoryGreen;
  }

  public void setCategoryGreen (int uCategoryGreen)
  {
    categoryGreen = uCategoryGreen;
  }

  public int getCategoryBlue ()
  {
    return categoryBlue;
  }

  public void setCategoryBlue (int uCategoryBlue)
  {
    categoryBlue = uCategoryBlue;
  }

  public void setLogoPosition (LogoPosition uLogoPosition)
  {
    logoPosition = uLogoPosition;
  }

  public LogoPosition getLogoPosition ()
  {
    return logoPosition;
  }

  public boolean isImageBorderEnable ()
  {
    return isImageBorderEnable;
  }

  public void setImageBorderEnable (boolean uImageBorderEnable)
  {
    isImageBorderEnable = uImageBorderEnable;
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

  public void setLogoImagePath (String uLogoImagePath)
  {
    logoImagePath = uLogoImagePath;
  }

  public int getHorizontalMarginSize ()
  {
    return horizontalMarginSize;
  }

  public void setHorizontalMarginSize (int uHorizontalMarginSize)
  {
    horizontalMarginSize = uHorizontalMarginSize;
  }

  public int getVerticalMarginSize ()
  {
    return verticalMarginSize;
  }

  public void setVerticalMarginSize (int uVerticalMarginSize)
  {
    verticalMarginSize = uVerticalMarginSize;
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

  public void loadProperties ()
  {
    final Properties properties = new Properties ();

    try
    {
      properties.loadFromXML (new FileInputStream (new File (getPrintConfigurationPath ())));
    } catch (final FileNotFoundException e)
    {
    } catch (final IOException e)
    {
      e.printStackTrace ();
    }

    itemFontName = properties.getProperty (KEY_ITEM_FONT_NAME, DEFAULT_ITEM_FONT_NAME);
    itemFontSize = Integer.parseInt (properties.getProperty (KEY_ITEM_FONT_SIZE, Integer.toString (DEFAULT_ITEM_FONT_SIZE)));
    itemFontType = Integer.parseInt (properties.getProperty (KEY_ITEM_FONT_TYPE, Integer.toString (DEFAULT_ITEM_FONT_TYPE)));
    itemRed = Integer.parseInt (properties.getProperty (KEY_ITEM_RED, Integer.toString (DEFAULT_ITEM_RED)));
    itemGreen = Integer.parseInt (properties.getProperty (KEY_ITEM_GREEN, Integer.toString (DEFAULT_ITEM_GREEN)));
    itemBlue = Integer.parseInt (properties.getProperty (KEY_ITEM_BLUE, Integer.toString (DEFAULT_ITEM_BLUE)));
    categoryFontName = properties.getProperty (KEY_CATEGORY_FONT_NAME, DEFAULT_CATEGORY_FONT_NAME);
    categoryFontSize = Integer.parseInt (properties.getProperty (KEY_CATEGORY_FONT_SIZE, Integer.toString (DEFAULT_CATEGORY_FONT_SIZE)));
    categoryFontType = Integer.parseInt (properties.getProperty (KEY_CATEGORY_FONT_TYPE, Integer.toString (DEFAULT_CATEGORY_FONT_TYPE)));
    categoryRed = Integer.parseInt (properties.getProperty (KEY_CATEGORY_RED, Integer.toString (DEFAULT_CATEGORY_RED)));
    categoryGreen = Integer.parseInt (properties.getProperty (KEY_CATEGORY_GREEN, Integer.toString (DEFAULT_CATEGORY_GREEN)));
    categoryBlue = Integer.parseInt (properties.getProperty (KEY_CATEGORY_BLUE, Integer.toString (DEFAULT_CATEGORY_BLUE)));

    imageBorderSize = Integer.parseInt (properties.getProperty (KEY_IMAGE_BORDER_SIZE, Integer.toString (DEFAULT_IMAGE_BORDER_SIZE)));
    isImageBorderEnable = Boolean.parseBoolean (properties.getProperty (KEY_IS_IMAGE_BORDER_ENABLE, Boolean.toString (DEFAULT_IS_IMAGE_BORDER_ENABLE)));
    logoImagePath = properties.getProperty (KEY_LOGO_IMAGE_PATH, DEFAULT_LOGO_IMAGE_PATH);
    logoPosition = LogoPosition.valueOf (properties.getProperty (KEY_LOGO_POSITION, DEFAULT_LOGO_POSITION.name ()));

    horizontalMarginSize = Integer.parseInt (properties.getProperty (KEY_HORIZONTAL_MARGIN_SIZE, Integer.toString (DEFAULT_HORIZONTAL_MARGIN_SIZE)));
    verticalMarginSize = Integer.parseInt (properties.getProperty (KEY_VERTICAL_MARGIN_SIZE, Integer.toString (DEFAULT_VERTICAL_MARGIN_SIZE)));
    categoryBoundsSize = Integer.parseInt (properties.getProperty (KEY_CATEGORY_BOUNDS_SIZE, Integer.toString (DEFAULT_CATEGORY_BOUNDS_SIZE)));
    itemBoundsSize = Integer.parseInt (properties.getProperty (KEY_ITEM_BOUNDS_SIZE, Integer.toString (DEFAULT_ITEM_BOUNDS_SIZE)));
  }

  public void saveProperties ()
  {
    final Properties properties = new Properties ();

    properties.setProperty (KEY_ITEM_FONT_NAME, itemFontName);
    properties.setProperty (KEY_ITEM_FONT_SIZE, Integer.toString (itemFontSize));
    properties.setProperty (KEY_ITEM_FONT_TYPE, Integer.toString (itemFontType));
    properties.setProperty (KEY_ITEM_RED, Integer.toString (itemRed));
    properties.setProperty (KEY_ITEM_GREEN, Integer.toString (itemGreen));
    properties.setProperty (KEY_ITEM_BLUE, Integer.toString (itemBlue));
    properties.setProperty (KEY_CATEGORY_FONT_NAME, categoryFontName);
    properties.setProperty (KEY_CATEGORY_FONT_SIZE, Integer.toString (categoryFontSize));
    properties.setProperty (KEY_CATEGORY_FONT_TYPE, Integer.toString (categoryFontType));
    properties.setProperty (KEY_CATEGORY_RED, Integer.toString (categoryRed));
    properties.setProperty (KEY_CATEGORY_GREEN, Integer.toString (categoryGreen));
    properties.setProperty (KEY_CATEGORY_BLUE, Integer.toString (categoryBlue));

    properties.setProperty (KEY_IMAGE_BORDER_SIZE, Integer.toString (imageBorderSize));
    properties.setProperty (KEY_IS_IMAGE_BORDER_ENABLE, Boolean.toString (isImageBorderEnable));
    properties.setProperty (KEY_LOGO_IMAGE_PATH, logoImagePath);
    properties.setProperty (KEY_LOGO_POSITION, logoPosition.name ());

    properties.setProperty (KEY_HORIZONTAL_MARGIN_SIZE, Integer.toString (horizontalMarginSize));
    properties.setProperty (KEY_VERTICAL_MARGIN_SIZE, Integer.toString (verticalMarginSize));
    properties.setProperty (KEY_CATEGORY_BOUNDS_SIZE, Integer.toString (categoryBoundsSize));
    properties.setProperty (KEY_ITEM_BOUNDS_SIZE, Integer.toString (itemBoundsSize));

    FileOutputStream os = null;
    try
    {
      os = new FileOutputStream (new File (getPrintConfigurationPath ()));
      properties.storeToXML (os, "Menu Maker print configuration");
    } catch (final FileNotFoundException e)
    {
      e.printStackTrace ();
    } catch (final IOException e)
    {
      e.printStackTrace ();
    } finally
    {
      try
      {
        if (os != null)
          os.close ();
      } catch (final IOException e)
      {
        e.printStackTrace ();
      }
    }
  }

  private String getPrintConfigurationPath ()
  {
    return MenumakerPluginUI.getDefault ().getStateLocation ().append (FILENAME_PROPERTIES).toString ();
  }
}
