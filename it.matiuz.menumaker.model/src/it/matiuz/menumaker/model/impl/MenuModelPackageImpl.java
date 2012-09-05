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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class MenuModelPackageImpl extends EPackageImpl implements MenuModelPackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass menuEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass categoryEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass itemEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass menuItemEClass = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see it.matiuz.menumaker.model.MenuModelPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private MenuModelPackageImpl ()
  {
    super (eNS_URI, MenuModelFactory.eINSTANCE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this
   * model, and for any others upon which it depends.  Simple
   * dependencies are satisfied by calling this method on all
   * dependent packages before doing anything else.  This method drives
   * initialization for interdependent packages directly, in parallel
   * with this package, itself.
   * <p>Of this package and its interdependencies, all packages which
   * have not yet been registered by their URI values are first created
   * and registered.  The packages are then initialized in two steps:
   * meta-model objects for all of the packages are created before any
   * are initialized, since one package's meta-model objects may refer to
   * those of another.
   * <p>Invocation of this method will not affect any packages that have
   * already been initialized.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static MenuModelPackage init ()
  {
    if (isInited)
      return (MenuModelPackage) EPackage.Registry.INSTANCE.getEPackage (MenuModelPackage.eNS_URI);

    // Obtain or create and register package
    MenuModelPackageImpl theModelPackage = (MenuModelPackageImpl) (EPackage.Registry.INSTANCE.getEPackage (eNS_URI) instanceof MenuModelPackageImpl ? EPackage.Registry.INSTANCE.getEPackage (eNS_URI) : new MenuModelPackageImpl ());

    isInited = true;

    // Create package meta-data objects
    theModelPackage.createPackageContents ();

    // Initialize created meta-data
    theModelPackage.initializePackageContents ();

    // Mark meta-data to indicate it can't be changed
    theModelPackage.freeze ();

    return theModelPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMenu ()
  {
    return menuEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMenu_Id ()
  {
    return (EAttribute) menuEClass.getEStructuralFeatures ().get (0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMenu_Description ()
  {
    return (EAttribute) menuEClass.getEStructuralFeatures ().get (1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMenu_CreationDate ()
  {
    return (EAttribute) menuEClass.getEStructuralFeatures ().get (2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMenu_Items ()
  {
    return (EReference) menuEClass.getEStructuralFeatures ().get (3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCategory ()
  {
    return categoryEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getCategory_Id ()
  {
    return (EAttribute) categoryEClass.getEStructuralFeatures ().get (0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getCategory_Description ()
  {
    return (EAttribute) categoryEClass.getEStructuralFeatures ().get (1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getCategory_Priority ()
  {
    return (EAttribute) categoryEClass.getEStructuralFeatures ().get (2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getItem ()
  {
    return itemEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getItem_Id ()
  {
    return (EAttribute) itemEClass.getEStructuralFeatures ().get (0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getItem_Description ()
  {
    return (EAttribute) itemEClass.getEStructuralFeatures ().get (1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getItem_CreationDate ()
  {
    return (EAttribute) itemEClass.getEStructuralFeatures ().get (2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getItem_Price ()
  {
    return (EAttribute) itemEClass.getEStructuralFeatures ().get (3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getItem_PriceNotes ()
  {
    return (EAttribute) itemEClass.getEStructuralFeatures ().get (4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getItem_CategoryId ()
  {
    return (EAttribute) itemEClass.getEStructuralFeatures ().get (5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMenuItem ()
  {
    return menuItemEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMenuItem_ItemId ()
  {
    return (EAttribute) menuItemEClass.getEStructuralFeatures ().get (0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMenuItem_MenuId ()
  {
    return (EAttribute) menuItemEClass.getEStructuralFeatures ().get (1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMenuItem_ItemPosition ()
  {
    return (EAttribute) menuItemEClass.getEStructuralFeatures ().get (2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MenuModelFactory getModelFactory ()
  {
    return (MenuModelFactory) getEFactoryInstance ();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isCreated = false;

  /**
   * Creates the meta-model objects for the package.  This method is
   * guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void createPackageContents ()
  {
    if (isCreated)
      return;
    isCreated = true;

    // Create classes and their features
    menuEClass = createEClass (MENU);
    createEAttribute (menuEClass, MENU__ID);
    createEAttribute (menuEClass, MENU__DESCRIPTION);
    createEAttribute (menuEClass, MENU__CREATION_DATE);
    createEReference (menuEClass, MENU__ITEMS);

    categoryEClass = createEClass (CATEGORY);
    createEAttribute (categoryEClass, CATEGORY__ID);
    createEAttribute (categoryEClass, CATEGORY__DESCRIPTION);
    createEAttribute (categoryEClass, CATEGORY__PRIORITY);

    itemEClass = createEClass (ITEM);
    createEAttribute (itemEClass, ITEM__ID);
    createEAttribute (itemEClass, ITEM__DESCRIPTION);
    createEAttribute (itemEClass, ITEM__CREATION_DATE);
    createEAttribute (itemEClass, ITEM__PRICE);
    createEAttribute (itemEClass, ITEM__PRICE_NOTES);
    createEAttribute (itemEClass, ITEM__CATEGORY_ID);

    menuItemEClass = createEClass (MENU_ITEM);
    createEAttribute (menuItemEClass, MENU_ITEM__ITEM_ID);
    createEAttribute (menuItemEClass, MENU_ITEM__MENU_ID);
    createEAttribute (menuItemEClass, MENU_ITEM__ITEM_POSITION);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isInitialized = false;

  /**
   * Complete the initialization of the package and its meta-model.  This
   * method is guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void initializePackageContents ()
  {
    if (isInitialized)
      return;
    isInitialized = true;

    // Initialize package
    setName (eNAME);
    setNsPrefix (eNS_PREFIX);
    setNsURI (eNS_URI);

    // Add supertypes to classes

    // Initialize classes and features; add operations and parameters
    initEClass (menuEClass, Menu.class, "Menu", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute (getMenu_Id (), ecorePackage.getEInt (), "id", null, 0, 1, Menu.class, !IS_TRANSIENT,
        !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute (getMenu_Description (), ecorePackage.getEString (), "description", null, 0, 1,
        Menu.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);
    initEAttribute (getMenu_CreationDate (), ecorePackage.getEDate (), "creationDate", null, 0, 1,
        Menu.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);
    initEReference (getMenu_Items (), this.getItem (), null, "items", null, 0, -1, Menu.class, !IS_TRANSIENT,
        !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);

    initEClass (categoryEClass, Category.class, "Category", !IS_ABSTRACT, !IS_INTERFACE,
        IS_GENERATED_INSTANCE_CLASS);
    initEAttribute (getCategory_Id (), ecorePackage.getEInt (), "id", null, 0, 1, Category.class,
        !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
        IS_ORDERED);
    initEAttribute (getCategory_Description (), ecorePackage.getEString (), "description", null, 0, 1,
        Category.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);
    initEAttribute (getCategory_Priority (), ecorePackage.getEInt (), "priority", null, 0, 1, Category.class,
        !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
        IS_ORDERED);

    initEClass (itemEClass, Item.class, "Item", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute (getItem_Id (), ecorePackage.getEInt (), "id", null, 0, 1, Item.class, !IS_TRANSIENT,
        !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute (getItem_Description (), ecorePackage.getEString (), "description", null, 0, 1,
        Item.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);
    initEAttribute (getItem_CreationDate (), ecorePackage.getEDate (), "creationDate", null, 0, 1,
        Item.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);
    initEAttribute (getItem_Price (), ecorePackage.getEDouble (), "price", null, 0, 1, Item.class,
        !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
        IS_ORDERED);
    initEAttribute (getItem_PriceNotes (), ecorePackage.getEString (), "priceNotes", null, 0, 1, Item.class,
        !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
        IS_ORDERED);
    initEAttribute (getItem_CategoryId (), ecorePackage.getEInt (), "categoryId", null, 0, 1, Item.class,
        !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
        IS_ORDERED);

    initEClass (menuItemEClass, MenuItem.class, "MenuItem", !IS_ABSTRACT, !IS_INTERFACE,
        IS_GENERATED_INSTANCE_CLASS);
    initEAttribute (getMenuItem_ItemId (), ecorePackage.getEInt (), "itemId", null, 0, 1, MenuItem.class,
        !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
        IS_ORDERED);
    initEAttribute (getMenuItem_MenuId (), ecorePackage.getEInt (), "menuId", null, 0, 1, MenuItem.class,
        !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
        IS_ORDERED);
    initEAttribute (getMenuItem_ItemPosition (), ecorePackage.getEInt (), "itemPosition", null, 0, 1,
        MenuItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);

    // Create resource
    createResource (eNS_URI);
  }

} //ModelPackageImpl
