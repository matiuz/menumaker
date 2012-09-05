/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package it.matiuz.menumaker.model;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see it.matiuz.menumaker.model.MenuModelFactory
 * @model kind="package"
 * @generated
 */
public interface MenuModelPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "model";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.matiuz.it/menu_maker";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "it.matiuz.menumaker.model";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  MenuModelPackage eINSTANCE = it.matiuz.menumaker.model.impl.MenuModelPackageImpl.init ();

  /**
   * The meta object id for the '{@link model.impl.MenuImpl <em>Menu</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see model.impl.MenuImpl
   * @see it.matiuz.menumaker.model.impl.MenuModelPackageImpl#getMenu()
   * @generated
   */
  int MENU = 0;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MENU__ID = 0;

  /**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MENU__DESCRIPTION = 1;

  /**
   * The feature id for the '<em><b>Creation Date</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MENU__CREATION_DATE = 2;

  /**
   * The feature id for the '<em><b>Items</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MENU__ITEMS = 3;

  /**
   * The number of structural features of the '<em>Menu</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MENU_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link model.impl.CategoryImpl <em>Category</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see model.impl.CategoryImpl
   * @see it.matiuz.menumaker.model.impl.MenuModelPackageImpl#getCategory()
   * @generated
   */
  int CATEGORY = 1;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CATEGORY__ID = 0;

  /**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CATEGORY__DESCRIPTION = 1;

  /**
   * The feature id for the '<em><b>Priority</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CATEGORY__PRIORITY = 2;

  /**
   * The number of structural features of the '<em>Category</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CATEGORY_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link model.impl.ItemImpl <em>Item</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see model.impl.ItemImpl
   * @see it.matiuz.menumaker.model.impl.MenuModelPackageImpl#getItem()
   * @generated
   */
  int ITEM = 2;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ITEM__ID = 0;

  /**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ITEM__DESCRIPTION = 1;

  /**
   * The feature id for the '<em><b>Creation Date</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ITEM__CREATION_DATE = 2;

  /**
   * The feature id for the '<em><b>Price</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ITEM__PRICE = 3;

  /**
   * The feature id for the '<em><b>Price Notes</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ITEM__PRICE_NOTES = 4;

  /**
   * The feature id for the '<em><b>Category Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ITEM__CATEGORY_ID = 5;

  /**
   * The number of structural features of the '<em>Item</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ITEM_FEATURE_COUNT = 6;

  /**
   * The meta object id for the '{@link it.matiuz.menumaker.model.impl.MenuItemImpl <em>Menu Item</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see it.matiuz.menumaker.model.impl.MenuItemImpl
   * @see it.matiuz.menumaker.model.impl.MenuModelPackageImpl#getMenuItem()
   * @generated
   */
  int MENU_ITEM = 3;

  /**
   * The feature id for the '<em><b>Item Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MENU_ITEM__ITEM_ID = 0;

  /**
   * The feature id for the '<em><b>Menu Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MENU_ITEM__MENU_ID = 1;

  /**
   * The feature id for the '<em><b>Item Position</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MENU_ITEM__ITEM_POSITION = 2;

  /**
   * The number of structural features of the '<em>Menu Item</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MENU_ITEM_FEATURE_COUNT = 3;

  /**
   * Returns the meta object for class '{@link model.Menu <em>Menu</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Menu</em>'.
   * @see model.Menu
   * @generated
   */
  EClass getMenu ();

  /**
   * Returns the meta object for the attribute '{@link model.Menu#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see model.Menu#getId()
   * @see #getMenu()
   * @generated
   */
  EAttribute getMenu_Id ();

  /**
   * Returns the meta object for the attribute '{@link model.Menu#getDescription <em>Description</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Description</em>'.
   * @see model.Menu#getDescription()
   * @see #getMenu()
   * @generated
   */
  EAttribute getMenu_Description ();

  /**
   * Returns the meta object for the attribute '{@link model.Menu#getCreationDate <em>Creation Date</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Creation Date</em>'.
   * @see model.Menu#getCreationDate()
   * @see #getMenu()
   * @generated
   */
  EAttribute getMenu_CreationDate ();

  /**
   * Returns the meta object for the containment reference list '{@link model.Menu#getItems <em>Items</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Items</em>'.
   * @see model.Menu#getItems()
   * @see #getMenu()
   * @generated
   */
  EReference getMenu_Items ();

  /**
   * Returns the meta object for class '{@link model.Category <em>Category</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Category</em>'.
   * @see model.Category
   * @generated
   */
  EClass getCategory ();

  /**
   * Returns the meta object for the attribute '{@link model.Category#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see model.Category#getId()
   * @see #getCategory()
   * @generated
   */
  EAttribute getCategory_Id ();

  /**
   * Returns the meta object for the attribute '{@link model.Category#getDescription <em>Description</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Description</em>'.
   * @see model.Category#getDescription()
   * @see #getCategory()
   * @generated
   */
  EAttribute getCategory_Description ();

  /**
   * Returns the meta object for the attribute '{@link model.Category#getPriority <em>Priority</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Priority</em>'.
   * @see model.Category#getPriority()
   * @see #getCategory()
   * @generated
   */
  EAttribute getCategory_Priority ();

  /**
   * Returns the meta object for class '{@link model.Item <em>Item</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Item</em>'.
   * @see model.Item
   * @generated
   */
  EClass getItem ();

  /**
   * Returns the meta object for the attribute '{@link model.Item#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see model.Item#getId()
   * @see #getItem()
   * @generated
   */
  EAttribute getItem_Id ();

  /**
   * Returns the meta object for the attribute '{@link model.Item#getDescription <em>Description</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Description</em>'.
   * @see model.Item#getDescription()
   * @see #getItem()
   * @generated
   */
  EAttribute getItem_Description ();

  /**
   * Returns the meta object for the attribute '{@link model.Item#getCreationDate <em>Creation Date</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Creation Date</em>'.
   * @see model.Item#getCreationDate()
   * @see #getItem()
   * @generated
   */
  EAttribute getItem_CreationDate ();

  /**
   * Returns the meta object for the attribute '{@link model.Item#getPrice <em>Price</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Price</em>'.
   * @see model.Item#getPrice()
   * @see #getItem()
   * @generated
   */
  EAttribute getItem_Price ();

  /**
   * Returns the meta object for the attribute '{@link model.Item#getPriceNotes <em>Price Notes</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Price Notes</em>'.
   * @see model.Item#getPriceNotes()
   * @see #getItem()
   * @generated
   */
  EAttribute getItem_PriceNotes ();

  /**
   * Returns the meta object for the attribute '{@link model.Item#getCategoryId <em>Category Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Category Id</em>'.
   * @see model.Item#getCategoryId()
   * @see #getItem()
   * @generated
   */
  EAttribute getItem_CategoryId ();

  /**
   * Returns the meta object for class '{@link it.matiuz.menumaker.model.MenuItem <em>Menu Item</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Menu Item</em>'.
   * @see it.matiuz.menumaker.model.MenuItem
   * @generated
   */
  EClass getMenuItem ();

  /**
   * Returns the meta object for the attribute '{@link it.matiuz.menumaker.model.MenuItem#getItemId <em>Item Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Item Id</em>'.
   * @see it.matiuz.menumaker.model.MenuItem#getItemId()
   * @see #getMenuItem()
   * @generated
   */
  EAttribute getMenuItem_ItemId ();

  /**
   * Returns the meta object for the attribute '{@link it.matiuz.menumaker.model.MenuItem#getMenuId <em>Menu Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Menu Id</em>'.
   * @see it.matiuz.menumaker.model.MenuItem#getMenuId()
   * @see #getMenuItem()
   * @generated
   */
  EAttribute getMenuItem_MenuId ();

  /**
   * Returns the meta object for the attribute '{@link it.matiuz.menumaker.model.MenuItem#getItemPosition <em>Item Position</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Item Position</em>'.
   * @see it.matiuz.menumaker.model.MenuItem#getItemPosition()
   * @see #getMenuItem()
   * @generated
   */
  EAttribute getMenuItem_ItemPosition ();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  MenuModelFactory getModelFactory ();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link model.impl.MenuImpl <em>Menu</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see model.impl.MenuImpl
     * @see it.matiuz.menumaker.model.impl.MenuModelPackageImpl#getMenu()
     * @generated
     */
    EClass MENU = eINSTANCE.getMenu ();

    /**
     * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MENU__ID = eINSTANCE.getMenu_Id ();

    /**
     * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MENU__DESCRIPTION = eINSTANCE.getMenu_Description ();

    /**
     * The meta object literal for the '<em><b>Creation Date</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MENU__CREATION_DATE = eINSTANCE.getMenu_CreationDate ();

    /**
     * The meta object literal for the '<em><b>Items</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MENU__ITEMS = eINSTANCE.getMenu_Items ();

    /**
     * The meta object literal for the '{@link model.impl.CategoryImpl <em>Category</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see model.impl.CategoryImpl
     * @see it.matiuz.menumaker.model.impl.MenuModelPackageImpl#getCategory()
     * @generated
     */
    EClass CATEGORY = eINSTANCE.getCategory ();

    /**
     * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CATEGORY__ID = eINSTANCE.getCategory_Id ();

    /**
     * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CATEGORY__DESCRIPTION = eINSTANCE.getCategory_Description ();

    /**
     * The meta object literal for the '<em><b>Priority</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CATEGORY__PRIORITY = eINSTANCE.getCategory_Priority ();

    /**
     * The meta object literal for the '{@link model.impl.ItemImpl <em>Item</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see model.impl.ItemImpl
     * @see it.matiuz.menumaker.model.impl.MenuModelPackageImpl#getItem()
     * @generated
     */
    EClass ITEM = eINSTANCE.getItem ();

    /**
     * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ITEM__ID = eINSTANCE.getItem_Id ();

    /**
     * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ITEM__DESCRIPTION = eINSTANCE.getItem_Description ();

    /**
     * The meta object literal for the '<em><b>Creation Date</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ITEM__CREATION_DATE = eINSTANCE.getItem_CreationDate ();

    /**
     * The meta object literal for the '<em><b>Price</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ITEM__PRICE = eINSTANCE.getItem_Price ();

    /**
     * The meta object literal for the '<em><b>Price Notes</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ITEM__PRICE_NOTES = eINSTANCE.getItem_PriceNotes ();

    /**
     * The meta object literal for the '<em><b>Category Id</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ITEM__CATEGORY_ID = eINSTANCE.getItem_CategoryId ();

    /**
     * The meta object literal for the '{@link it.matiuz.menumaker.model.impl.MenuItemImpl <em>Menu Item</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see it.matiuz.menumaker.model.impl.MenuItemImpl
     * @see it.matiuz.menumaker.model.impl.MenuModelPackageImpl#getMenuItem()
     * @generated
     */
    EClass MENU_ITEM = eINSTANCE.getMenuItem ();

    /**
     * The meta object literal for the '<em><b>Item Id</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MENU_ITEM__ITEM_ID = eINSTANCE.getMenuItem_ItemId ();

    /**
     * The meta object literal for the '<em><b>Menu Id</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MENU_ITEM__MENU_ID = eINSTANCE.getMenuItem_MenuId ();

    /**
     * The meta object literal for the '<em><b>Item Position</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MENU_ITEM__ITEM_POSITION = eINSTANCE.getMenuItem_ItemPosition ();

  }

} //ModelPackage
