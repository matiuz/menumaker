/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package it.matiuz.menumaker.model.impl;

import it.matiuz.menumaker.model.MenuItem;
import it.matiuz.menumaker.model.MenuModelPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Menu Item</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link it.matiuz.menumaker.model.impl.MenuItemImpl#getItemId <em>Item Id</em>}</li>
 *   <li>{@link it.matiuz.menumaker.model.impl.MenuItemImpl#getMenuId <em>Menu Id</em>}</li>
 *   <li>{@link it.matiuz.menumaker.model.impl.MenuItemImpl#getItemPosition <em>Item Position</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MenuItemImpl extends EObjectImpl implements MenuItem
{
  /**
   * The default value of the '{@link #getItemId() <em>Item Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getItemId()
   * @generated
   * @ordered
   */
  protected static final int ITEM_ID_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getItemId() <em>Item Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getItemId()
   * @generated
   * @ordered
   */
  protected int itemId = ITEM_ID_EDEFAULT;

  /**
   * The default value of the '{@link #getMenuId() <em>Menu Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMenuId()
   * @generated
   * @ordered
   */
  protected static final int MENU_ID_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getMenuId() <em>Menu Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMenuId()
   * @generated
   * @ordered
   */
  protected int menuId = MENU_ID_EDEFAULT;

  /**
   * The default value of the '{@link #getItemPosition() <em>Item Position</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getItemPosition()
   * @generated
   * @ordered
   */
  protected static final int ITEM_POSITION_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getItemPosition() <em>Item Position</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getItemPosition()
   * @generated
   * @ordered
   */
  protected int itemPosition = ITEM_POSITION_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected MenuItemImpl ()
  {
    super ();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EClass eStaticClass ()
  {
    return MenuModelPackage.Literals.MENU_ITEM;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getItemId ()
  {
    return itemId;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setItemId (int newItemId)
  {
    int oldItemId = itemId;
    itemId = newItemId;
    if (eNotificationRequired ())
      eNotify (new ENotificationImpl (this, Notification.SET, MenuModelPackage.MENU_ITEM__ITEM_ID, oldItemId,
          itemId));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getMenuId ()
  {
    return menuId;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMenuId (int newMenuId)
  {
    int oldMenuId = menuId;
    menuId = newMenuId;
    if (eNotificationRequired ())
      eNotify (new ENotificationImpl (this, Notification.SET, MenuModelPackage.MENU_ITEM__MENU_ID, oldMenuId,
          menuId));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getItemPosition ()
  {
    return itemPosition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setItemPosition (int newItemPosition)
  {
    int oldItemPosition = itemPosition;
    itemPosition = newItemPosition;
    if (eNotificationRequired ())
      eNotify (new ENotificationImpl (this, Notification.SET, MenuModelPackage.MENU_ITEM__ITEM_POSITION,
          oldItemPosition, itemPosition));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object eGet (int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case MenuModelPackage.MENU_ITEM__ITEM_ID:
        return new Integer (getItemId ());
      case MenuModelPackage.MENU_ITEM__MENU_ID:
        return new Integer (getMenuId ());
      case MenuModelPackage.MENU_ITEM__ITEM_POSITION:
        return new Integer (getItemPosition ());
    }
    return super.eGet (featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eSet (int featureID, Object newValue)
  {
    switch (featureID)
    {
      case MenuModelPackage.MENU_ITEM__ITEM_ID:
        setItemId (((Integer) newValue).intValue ());
        return;
      case MenuModelPackage.MENU_ITEM__MENU_ID:
        setMenuId (((Integer) newValue).intValue ());
        return;
      case MenuModelPackage.MENU_ITEM__ITEM_POSITION:
        setItemPosition (((Integer) newValue).intValue ());
        return;
    }
    super.eSet (featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eUnset (int featureID)
  {
    switch (featureID)
    {
      case MenuModelPackage.MENU_ITEM__ITEM_ID:
        setItemId (ITEM_ID_EDEFAULT);
        return;
      case MenuModelPackage.MENU_ITEM__MENU_ID:
        setMenuId (MENU_ID_EDEFAULT);
        return;
      case MenuModelPackage.MENU_ITEM__ITEM_POSITION:
        setItemPosition (ITEM_POSITION_EDEFAULT);
        return;
    }
    super.eUnset (featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean eIsSet (int featureID)
  {
    switch (featureID)
    {
      case MenuModelPackage.MENU_ITEM__ITEM_ID:
        return itemId != ITEM_ID_EDEFAULT;
      case MenuModelPackage.MENU_ITEM__MENU_ID:
        return menuId != MENU_ID_EDEFAULT;
      case MenuModelPackage.MENU_ITEM__ITEM_POSITION:
        return itemPosition != ITEM_POSITION_EDEFAULT;
    }
    return super.eIsSet (featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String toString ()
  {
    if (eIsProxy ())
      return super.toString ();

    StringBuffer result = new StringBuffer (super.toString ());
    result.append (" (itemId: ");
    result.append (itemId);
    result.append (", menuId: ");
    result.append (menuId);
    result.append (", itemPosition: ");
    result.append (itemPosition);
    result.append (')');
    return result.toString ();
  }

} //MenuItemImpl
