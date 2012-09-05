/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package it.matiuz.menumaker.model.impl;

import it.matiuz.menumaker.model.Category;
import it.matiuz.menumaker.model.Item;
import it.matiuz.menumaker.model.Menu;
import it.matiuz.menumaker.model.MenuItem;
import it.matiuz.menumaker.model.MenuModelFactory;
import it.matiuz.menumaker.model.MenuModelPackage;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class MenuModelFactoryImpl extends EFactoryImpl implements MenuModelFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static MenuModelFactory init ()
  {
    try
    {
      MenuModelFactory theModelFactory = (MenuModelFactory) EPackage.Registry.INSTANCE.getEFactory ("http://www.matiuz.it/menu_maker");
      if (theModelFactory != null)
      {
        return theModelFactory;
      }
    } catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log (exception);
    }
    return new MenuModelFactoryImpl ();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MenuModelFactoryImpl ()
  {
    super ();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EObject create (EClass eClass)
  {
    switch (eClass.getClassifierID ())
    {
      case MenuModelPackage.MENU:
        return createMenu ();
      case MenuModelPackage.CATEGORY:
        return createCategory ();
      case MenuModelPackage.ITEM:
        return createItem ();
      case MenuModelPackage.MENU_ITEM:
        return createMenuItem ();
      default:
        throw new IllegalArgumentException ("The class '" + eClass.getName () + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Menu createMenu ()
  {
    MenuImpl menu = new MenuImpl ();
    return menu;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Category createCategory ()
  {
    CategoryImpl category = new CategoryImpl ();
    return category;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Item createItem ()
  {
    ItemImpl item = new ItemImpl ();
    return item;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MenuItem createMenuItem ()
  {
    MenuItemImpl menuItem = new MenuItemImpl ();
    return menuItem;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MenuModelPackage getModelPackage ()
  {
    return (MenuModelPackage) getEPackage ();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  public static MenuModelPackage getPackage ()
  {
    return MenuModelPackage.eINSTANCE;
  }

} //ModelFactoryImpl
