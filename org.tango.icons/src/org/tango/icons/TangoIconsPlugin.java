package org.tango.icons;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class TangoIconsPlugin extends AbstractUIPlugin
{
  private static final String SMALL_ICON_PATH = "icons/16x16";
  private static final String LARGE_ICON_PATH = "icons/22x22";
  private static final String HUGE_ICON_PATH = "icons/32x32";

  private static TangoIconsPlugin plugin;
  private static ImageRegistry imageRegistry;

  @Override
  public void start (BundleContext context) throws Exception
  {
    super.start (context);
    plugin = this;
    imageRegistry = new ImageRegistry ();
    loadImageRegistry ();
  }

  @Override
  public void stop (BundleContext context) throws Exception
  {
    imageRegistry.dispose ();
    plugin = null;
    super.stop (context);
  }

  public static TangoIconsPlugin getDefault ()
  {
    return plugin;
  }

  public static ImageDescriptor getImageDescriptor (String uPath)
  {
    return AbstractUIPlugin.imageDescriptorFromPlugin ("org.tango.icons", uPath);
  }

  public static Image getSmallImage (String uIconPath)
  {
    return imageRegistry.get (SMALL_ICON_PATH + "/" + uIconPath);
  }

  public static Image getLargeImage (String uIconPath)
  {
    return imageRegistry.get (LARGE_ICON_PATH + "/" + uIconPath);
  }

  public static Image getHugeImage (String uIconPath)
  {
    return imageRegistry.get (HUGE_ICON_PATH + "/" + uIconPath);
  }

  public static ImageDescriptor getSmallImageDescriptor (String uIconPath)
  {
    return imageRegistry.getDescriptor (SMALL_ICON_PATH + "/" + uIconPath);
  }

  public static ImageDescriptor getLargeImageDescriptor (String uIconPath)
  {
    return imageRegistry.getDescriptor (LARGE_ICON_PATH + "/" + uIconPath);
  }

  public static ImageDescriptor getHugeImageDescriptor (String uIconPath)
  {
    return imageRegistry.getDescriptor (HUGE_ICON_PATH + "/" + uIconPath);
  }

  private void loadImageRegistry ()
  {
    loadImageDescriptor (ITangoIconsImages.ACTIONS_ADDRESS_BOOK_NEW);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_APPOINTMENT_NEW);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_BOOKMARK_NEW);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_CONTACT_NEW);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_DOCUMENT_NEW);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_DOCUMENT_OPEN);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_DOCUMENT_PRINT);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_DOCUMENT_PRINT_PREVIEW);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_DOCUMENT_PROPERTIES);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_DOCUMENT_SAVE);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_DOCUMENT_SAVE_AS);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_EDIT_CLEAR);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_EDIT_COPY);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_EDIT_CUT);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_EDIT_DELETE);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_EDIT_FIND);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_EDIT_FIND_REPLACE);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_EDIT_PASTE);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_EDIT_REDO);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_EDIT_SELECT_ALL);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_EDIT_UNDO);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_FOLDER_NEW);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_FORMAT_INDENT_LESS);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_FORMAT_INDENT_MORE);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_FORMAT_JUSTIFY_CENTER);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_FORMAT_JUSTIFY_FILL);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_FORMAT_JUSTIFY_LEFT);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_FORMAT_JUSTIFY_RIGHT);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_FORMAT_TEXT_BOLD);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_FORMAT_TEXT_ITALIC);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_FORMAT_TEXT_STRIKETHROUGH);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_FORMAT_TEXT_UNDERLINE);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_GO_BOTTOM);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_GO_DOWN);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_GO_FIRST);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_GO_HOME);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_GO_JUMP);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_GO_LAST);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_GO_NEXT);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_GO_PREVIOUS);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_GO_TOP);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_GO_UP);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_LIST_ADD);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_LIST_REMOVE);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_MAIL_FORWARD);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_MAIL_MARK_JUNK);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_MAIL_MARK_NOT_JUNK);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_MAIL_MESSAGE_NEW);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_MAIL_REPLY_ALL);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_MAIL_REPLY_SENDER);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_MAIL_SEND_RECEIVE);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_MEDIA_EJECT);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_MEDIA_PLAYBACK_PAUSE);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_MEDIA_PLAYBACK_START);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_MEDIA_PLAYBACK_STOP);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_MEDIA_RECORD);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_MEDIA_SEEK_BACKWARD);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_MEDIA_SEEK_FORWARD);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_MEDIA_SKIP_BACKWARD);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_MEDIA_SKIP_FORWARD);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_PROCESS_STOP);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_SYSTEM_LOCK_SCREEN);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_SYSTEM_LOG_OUT);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_SYSTEM_SEARCH);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_SYSTEM_SHUTDOWN);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_TAB_NEW);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_VIEW_FULLSCREEN);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_VIEW_REFRESH);
    loadImageDescriptor (ITangoIconsImages.ACTIONS_WINDOW_NEW);
    loadImageDescriptor (ITangoIconsImages.ANIMATIONS_PROCESS_WORKING);
    loadImageDescriptor (ITangoIconsImages.APPS_ACCESSORIES_CALCULATOR);
    loadImageDescriptor (ITangoIconsImages.APPS_ACCESSORIES_CHARACTER_MAP);
    loadImageDescriptor (ITangoIconsImages.APPS_ACCESSORIES_TEXT_EDITOR);
    loadImageDescriptor (ITangoIconsImages.APPS_HELP_BROWSER);
    loadImageDescriptor (ITangoIconsImages.APPS_INTERNET_GROUP_CHAT);
    loadImageDescriptor (ITangoIconsImages.APPS_INTERNET_MAIL);
    loadImageDescriptor (ITangoIconsImages.APPS_INTERNET_NEWS_READER);
    loadImageDescriptor (ITangoIconsImages.APPS_INTERNET_WEB_BROWSER);
    loadImageDescriptor (ITangoIconsImages.APPS_OFFICE_CALENDAR);
    loadImageDescriptor (ITangoIconsImages.APPS_PREFERENCES_DESKTOP_ACCESSIBILITY);
    loadImageDescriptor (ITangoIconsImages.APPS_PREFERENCES_DESKTOP_ASSISTIVE_TECHNOLOGY);
    loadImageDescriptor (ITangoIconsImages.APPS_PREFERENCES_DESKTOP_FONT);
    loadImageDescriptor (ITangoIconsImages.APPS_PREFERENCES_DESKTOP_KEYBOARD_SHORTCUTS);
    loadImageDescriptor (ITangoIconsImages.APPS_PREFERENCES_DESKTOP_LOCALE);
    loadImageDescriptor (ITangoIconsImages.APPS_PREFERENCES_DESKTOP_MULTIMEDIA);
    loadImageDescriptor (ITangoIconsImages.APPS_PREFERENCES_DESKTOP_REMOTE_DESKTOP);
    loadImageDescriptor (ITangoIconsImages.APPS_PREFERENCES_DESKTOP_SCREENSAVER);
    loadImageDescriptor (ITangoIconsImages.APPS_PREFERENCES_DESKTOP_THEME);
    loadImageDescriptor (ITangoIconsImages.APPS_PREFERENCES_DESKTOP_WALLPAPER);
    loadImageDescriptor (ITangoIconsImages.APPS_PREFERENCES_SYSTEM_NETWORK_PROXY);
    loadImageDescriptor (ITangoIconsImages.APPS_PREFERENCES_SYSTEM_SESSION);
    loadImageDescriptor (ITangoIconsImages.APPS_PREFERENCES_SYSTEM_WINDOWS);
    loadImageDescriptor (ITangoIconsImages.APPS_SYSTEM_FILE_MANAGER);
    loadImageDescriptor (ITangoIconsImages.APPS_SYSTEM_INSTALLER);
    loadImageDescriptor (ITangoIconsImages.APPS_SYSTEM_SOFTWARE_UPDATE);
    loadImageDescriptor (ITangoIconsImages.APPS_SYSTEM_USERS);
    loadImageDescriptor (ITangoIconsImages.APPS_UTILITIES_SYSTEM_MONITOR);
    loadImageDescriptor (ITangoIconsImages.APPS_UTILITIES_TERMINAL);
    loadImageDescriptor (ITangoIconsImages.CATEGORIES_APPLICATIONS_ACCESSORIES);
    loadImageDescriptor (ITangoIconsImages.CATEGORIES_APPLICATIONS_DEVELOPMENT);
    loadImageDescriptor (ITangoIconsImages.CATEGORIES_APPLICATIONS_GAMES);
    loadImageDescriptor (ITangoIconsImages.CATEGORIES_APPLICATIONS_GRAPHICS);
    loadImageDescriptor (ITangoIconsImages.CATEGORIES_APPLICATIONS_INTERNET);
    loadImageDescriptor (ITangoIconsImages.CATEGORIES_APPLICATIONS_MULTIMEDIA);
    loadImageDescriptor (ITangoIconsImages.CATEGORIES_APPLICATIONS_OFFICE);
    loadImageDescriptor (ITangoIconsImages.CATEGORIES_APPLICATIONS_OTHER);
    loadImageDescriptor (ITangoIconsImages.CATEGORIES_APPLICATIONS_SYSTEM);
    loadImageDescriptor (ITangoIconsImages.CATEGORIES_PREFERENCES_DESKTOP);
    loadImageDescriptor (ITangoIconsImages.CATEGORIES_PREFERENCES_DESKTOP_PERIPHERALS);
    loadImageDescriptor (ITangoIconsImages.CATEGORIES_PREFERENCES_SYSTEM);
    loadImageDescriptor (ITangoIconsImages.DEVICES_AUDIO_CARD);
    loadImageDescriptor (ITangoIconsImages.DEVICES_AUDIO_INPUT_MICROPHONE);
    loadImageDescriptor (ITangoIconsImages.DEVICES_BATTERY);
    loadImageDescriptor (ITangoIconsImages.DEVICES_CAMERA_PHOTO);
    loadImageDescriptor (ITangoIconsImages.DEVICES_CAMERA_VIDEO);
    loadImageDescriptor (ITangoIconsImages.DEVICES_COMPUTER);
    loadImageDescriptor (ITangoIconsImages.DEVICES_DRIVE_HARDDISK);
    loadImageDescriptor (ITangoIconsImages.DEVICES_DRIVE_OPTICAL);
    loadImageDescriptor (ITangoIconsImages.DEVICES_DRIVE_REMOVABLE_MEDIA);
    loadImageDescriptor (ITangoIconsImages.DEVICES_INPUT_GAMING);
    loadImageDescriptor (ITangoIconsImages.DEVICES_INPUT_KEYBOARD);
    loadImageDescriptor (ITangoIconsImages.DEVICES_INPUT_MOUSE);
    loadImageDescriptor (ITangoIconsImages.DEVICES_MEDIA_FLASH);
    loadImageDescriptor (ITangoIconsImages.DEVICES_MEDIA_FLOPPY);
    loadImageDescriptor (ITangoIconsImages.DEVICES_MEDIA_OPTICAL);
    loadImageDescriptor (ITangoIconsImages.DEVICES_MULTIMEDIA_PLAYER);
    loadImageDescriptor (ITangoIconsImages.DEVICES_NETWORK_WIRED);
    loadImageDescriptor (ITangoIconsImages.DEVICES_NETWORK_WIRELESS);
    loadImageDescriptor (ITangoIconsImages.DEVICES_PRINTER);
    loadImageDescriptor (ITangoIconsImages.DEVICES_VIDEO_DISPLAY);
    loadImageDescriptor (ITangoIconsImages.EMBLEMS_EMBLEM_FAVORITE);
    loadImageDescriptor (ITangoIconsImages.EMBLEMS_EMBLEM_IMPORTANT);
    loadImageDescriptor (ITangoIconsImages.EMBLEMS_EMBLEM_PHOTOS);
    loadImageDescriptor (ITangoIconsImages.EMBLEMS_EMBLEM_READONLY);
    loadImageDescriptor (ITangoIconsImages.EMBLEMS_EMBLEM_SYMBOLIC_LINK);
    loadImageDescriptor (ITangoIconsImages.EMBLEMS_EMBLEM_SYSTEM);
    loadImageDescriptor (ITangoIconsImages.EMBLEMS_EMBLEM_UNREADABLE);
    loadImageDescriptor (ITangoIconsImages.EMOTES_FACE_ANGEL);
    loadImageDescriptor (ITangoIconsImages.EMOTES_FACE_CRYING);
    loadImageDescriptor (ITangoIconsImages.EMOTES_FACE_DEVILISH);
    loadImageDescriptor (ITangoIconsImages.EMOTES_FACE_GLASSES);
    loadImageDescriptor (ITangoIconsImages.EMOTES_FACE_GRIN);
    loadImageDescriptor (ITangoIconsImages.EMOTES_FACE_KISS);
    loadImageDescriptor (ITangoIconsImages.EMOTES_FACE_MONKEY);
    loadImageDescriptor (ITangoIconsImages.EMOTES_FACE_PLAIN);
    loadImageDescriptor (ITangoIconsImages.EMOTES_FACE_SAD);
    loadImageDescriptor (ITangoIconsImages.EMOTES_FACE_SMILE);
    loadImageDescriptor (ITangoIconsImages.EMOTES_FACE_SMILE_BIG);
    loadImageDescriptor (ITangoIconsImages.EMOTES_FACE_SURPRISE);
    loadImageDescriptor (ITangoIconsImages.EMOTES_FACE_WINK);
    loadImageDescriptor (ITangoIconsImages.MIMETYPES_APPLICATION_CERTIFICATE);
    loadImageDescriptor (ITangoIconsImages.MIMETYPES_APPLICATION_X_EXECUTABLE);
    loadImageDescriptor (ITangoIconsImages.MIMETYPES_AUDIO_X_GENERIC);
    loadImageDescriptor (ITangoIconsImages.MIMETYPES_FONT_X_GENERIC);
    loadImageDescriptor (ITangoIconsImages.MIMETYPES_IMAGE_X_GENERIC);
    loadImageDescriptor (ITangoIconsImages.MIMETYPES_PACKAGE_X_GENERIC);
    loadImageDescriptor (ITangoIconsImages.MIMETYPES_TEXT_HTML);
    loadImageDescriptor (ITangoIconsImages.MIMETYPES_TEXT_X_GENERIC);
    loadImageDescriptor (ITangoIconsImages.MIMETYPES_TEXT_X_GENERIC_TEMPLATE);
    loadImageDescriptor (ITangoIconsImages.MIMETYPES_TEXT_X_SCRIPT);
    loadImageDescriptor (ITangoIconsImages.MIMETYPES_VIDEO_X_GENERIC);
    loadImageDescriptor (ITangoIconsImages.MIMETYPES_X_OFFICE_ADDRESS_BOOK);
    loadImageDescriptor (ITangoIconsImages.MIMETYPES_X_OFFICE_CALENDAR);
    loadImageDescriptor (ITangoIconsImages.MIMETYPES_X_OFFICE_DOCUMENT);
    loadImageDescriptor (ITangoIconsImages.MIMETYPES_X_OFFICE_DOCUMENT_TEMPLATE);
    loadImageDescriptor (ITangoIconsImages.MIMETYPES_X_OFFICE_DRAWING);
    loadImageDescriptor (ITangoIconsImages.MIMETYPES_X_OFFICE_DRAWING_TEMPLATE);
    loadImageDescriptor (ITangoIconsImages.MIMETYPES_X_OFFICE_PRESENTATION);
    loadImageDescriptor (ITangoIconsImages.MIMETYPES_X_OFFICE_PRESENTATION_TEMPLATE);
    loadImageDescriptor (ITangoIconsImages.MIMETYPES_X_OFFICE_SPREADSHEET);
    loadImageDescriptor (ITangoIconsImages.MIMETYPES_X_OFFICE_SPREADSHEET_TEMPLATE);
    loadImageDescriptor (ITangoIconsImages.PLACES_FOLDER);
    loadImageDescriptor (ITangoIconsImages.PLACES_FOLDER_REMOTE);
    loadImageDescriptor (ITangoIconsImages.PLACES_FOLDER_SAVED_SEARCH);
    loadImageDescriptor (ITangoIconsImages.PLACES_NETWORK_SERVER);
    loadImageDescriptor (ITangoIconsImages.PLACES_NETWORK_WORKGROUP);
    loadImageDescriptor (ITangoIconsImages.PLACES_START_HERE);
    loadImageDescriptor (ITangoIconsImages.PLACES_USER_DESKTOP);
    loadImageDescriptor (ITangoIconsImages.PLACES_USER_HOME);
    loadImageDescriptor (ITangoIconsImages.PLACES_USER_TRASH);
    loadImageDescriptor (ITangoIconsImages.STATUS_AUDIO_VOLUME_HIGH);
    loadImageDescriptor (ITangoIconsImages.STATUS_AUDIO_VOLUME_LOW);
    loadImageDescriptor (ITangoIconsImages.STATUS_AUDIO_VOLUME_MEDIUM);
    loadImageDescriptor (ITangoIconsImages.STATUS_AUDIO_VOLUME_MUTED);
    loadImageDescriptor (ITangoIconsImages.STATUS_BATTERY_CAUTION);
    loadImageDescriptor (ITangoIconsImages.STATUS_DIALOG_ERROR);
    loadImageDescriptor (ITangoIconsImages.STATUS_DIALOG_INFORMATION);
    loadImageDescriptor (ITangoIconsImages.STATUS_DIALOG_WARNING);
    loadImageDescriptor (ITangoIconsImages.STATUS_FOLDER_DRAG_ACCEPT);
    loadImageDescriptor (ITangoIconsImages.STATUS_FOLDER_OPEN);
    loadImageDescriptor (ITangoIconsImages.STATUS_FOLDER_VISITING);
    loadImageDescriptor (ITangoIconsImages.STATUS_IMAGE_LOADING);
    loadImageDescriptor (ITangoIconsImages.STATUS_IMAGE_MISSING);
    loadImageDescriptor (ITangoIconsImages.STATUS_MAIL_ATTACHMENT);
    loadImageDescriptor (ITangoIconsImages.STATUS_NETWORK_ERROR);
    loadImageDescriptor (ITangoIconsImages.STATUS_NETWORK_IDLE);
    loadImageDescriptor (ITangoIconsImages.STATUS_NETWORK_OFFLINE);
    loadImageDescriptor (ITangoIconsImages.STATUS_NETWORK_RECEIVE);
    loadImageDescriptor (ITangoIconsImages.STATUS_NETWORK_TRANSMIT);
    loadImageDescriptor (ITangoIconsImages.STATUS_NETWORK_TRANSMIT_RECEIVE);
    loadImageDescriptor (ITangoIconsImages.STATUS_NETWORK_WIRELESS_ENCRYPTED);
    loadImageDescriptor (ITangoIconsImages.STATUS_PRINTER_ERROR);
    loadImageDescriptor (ITangoIconsImages.STATUS_SOFTWARE_UPDATE_AVAILABLE);
    loadImageDescriptor (ITangoIconsImages.STATUS_SOFTWARE_UPDATE_URGENT);
    loadImageDescriptor (ITangoIconsImages.STATUS_USER_TRASH_FULL);
    loadImageDescriptor (ITangoIconsImages.STATUS_WEATHER_CLEAR);
    loadImageDescriptor (ITangoIconsImages.STATUS_WEATHER_CLEAR_NIGHT);
    loadImageDescriptor (ITangoIconsImages.STATUS_WEATHER_FEW_CLOUDS);
    loadImageDescriptor (ITangoIconsImages.STATUS_WEATHER_FEW_CLOUDS_NIGHT);
    loadImageDescriptor (ITangoIconsImages.STATUS_WEATHER_OVERCAST);
    loadImageDescriptor (ITangoIconsImages.STATUS_WEATHER_SEVERE_ALERT);
    loadImageDescriptor (ITangoIconsImages.STATUS_WEATHER_SHOWERS);
    loadImageDescriptor (ITangoIconsImages.STATUS_WEATHER_SHOWERS_SCATTERED);
    loadImageDescriptor (ITangoIconsImages.STATUS_WEATHER_SNOW);
    loadImageDescriptor (ITangoIconsImages.STATUS_WEATHER_STORM);
  }

  private void loadImageDescriptor (String uSymbolicName)
  {
    imageRegistry.put (SMALL_ICON_PATH + "/" + uSymbolicName, getImageDescriptor (SMALL_ICON_PATH + "/" + uSymbolicName));
    imageRegistry.put (LARGE_ICON_PATH + "/" + uSymbolicName, getImageDescriptor (LARGE_ICON_PATH + "/" + uSymbolicName));
    imageRegistry.put (HUGE_ICON_PATH + "/" + uSymbolicName, getImageDescriptor (HUGE_ICON_PATH + "/" + uSymbolicName));
  }
}
