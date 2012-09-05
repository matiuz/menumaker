/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package it.matiuz.menumaker.model.impl;

import it.matiuz.menumaker.model.Item;
import it.matiuz.menumaker.model.Menu;
import it.matiuz.menumaker.model.MenuModelPackage;

import java.util.Collection;
import java.util.Date;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Menu</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link it.matiuz.menumaker.model.impl.MenuImpl#getId <em>Id</em>}</li>
 *   <li>{@link it.matiuz.menumaker.model.impl.MenuImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link it.matiuz.menumaker.model.impl.MenuImpl#getCreationDate <em>Creation Date</em>}</li>
 *   <li>{@link it.matiuz.menumaker.model.impl.MenuImpl#getItems <em>Items</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MenuImpl extends EObjectImpl implements Menu
{
  /**
   * The default value of the '{@link #getId() <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getId()
   * @generated
   * @ordered
   */
  protected static final int ID_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getId()
   * @generated
   * @ordered
   */
  protected int id = ID_EDEFAULT;

  /**
   * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDescription()
   * @generated
   * @ordered
   */
  protected static final String DESCRIPTION_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDescription()
   * @generated
   * @ordered
   */
  protected String description = DESCRIPTION_EDEFAULT;

  /**
   * The default value of the '{@link #getCreationDate() <em>Creation Date</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCreationDate()
   * @generated
   * @ordered
   */
  protected static final Date CREATION_DATE_EDEFAULT = new Date ();

  /**
   * The cached value of the '{@link #getCreationDate() <em>Creation Date</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCreationDate()
   * @generated
   * @ordered
   */
  protected Date creationDate = CREATION_DATE_EDEFAULT;

  /**
   * The cached value of the '{@link #getItems() <em>Items</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getItems()
   * @generated
   * @ordered
   */
  protected EList<Item> items;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected MenuImpl ()
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
    return MenuModelPackage.Literals.MENU;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getId ()
  {
    return id;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setId (int newId)
  {
    int oldId = id;
    id = newId;
    if (eNotificationRequired ())
      eNotify (new ENotificationImpl (this, Notification.SET, MenuModelPackage.MENU__ID, oldId, id));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getDescription ()
  {
    return description;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDescription (String newDescription)
  {
    String oldDescription = description;
    description = newDescription;
    if (eNotificationRequired ())
      eNotify (new ENotificationImpl (this, Notification.SET, MenuModelPackage.MENU__DESCRIPTION,
          oldDescription, description));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Date getCreationDate ()
  {
    return creationDate;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCreationDate (Date newCreationDate)
  {
    Date oldCreationDate = creationDate;
    creationDate = newCreationDate;
    if (eNotificationRequired ())
      eNotify (new ENotificationImpl (this, Notification.SET, MenuModelPackage.MENU__CREATION_DATE,
          oldCreationDate, creationDate));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Item> getItems ()
  {
    if (items == null)
    {
      items = new EObjectContainmentEList<Item> (Item.class, this, MenuModelPackage.MENU__ITEMS);
    }
    return items;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eInverseRemove (InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case MenuModelPackage.MENU__ITEMS:
        return ((InternalEList<Item>) getItems ()).basicRemove (otherEnd, msgs);
    }
    return super.eInverseRemove (otherEnd, featureID, msgs);
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
      case MenuModelPackage.MENU__ID:
        return new Integer (getId ());
      case MenuModelPackage.MENU__DESCRIPTION:
        return getDescription ();
      case MenuModelPackage.MENU__CREATION_DATE:
        return getCreationDate ();
      case MenuModelPackage.MENU__ITEMS:
        return getItems ();
    }
    return super.eGet (featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  public void eSet (int featureID, Object newValue)
  {
    switch (featureID)
    {
      case MenuModelPackage.MENU__ID:
        setId (((Integer) newValue).intValue ());
        return;
      case MenuModelPackage.MENU__DESCRIPTION:
        setDescription ((String) newValue);
        return;
      case MenuModelPackage.MENU__CREATION_DATE:
        setCreationDate ((Date) newValue);
        return;
      case MenuModelPackage.MENU__ITEMS:
        getItems ().clear ();
        getItems ().addAll ((Collection<Item>) newValue);
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
      case MenuModelPackage.MENU__ID:
        setId (ID_EDEFAULT);
        return;
      case MenuModelPackage.MENU__DESCRIPTION:
        setDescription (DESCRIPTION_EDEFAULT);
        return;
      case MenuModelPackage.MENU__CREATION_DATE:
        setCreationDate (CREATION_DATE_EDEFAULT);
        return;
      case MenuModelPackage.MENU__ITEMS:
        getItems ().clear ();
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
      case MenuModelPackage.MENU__ID:
        return id != ID_EDEFAULT;
      case MenuModelPackage.MENU__DESCRIPTION:
        return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals (description);
      case MenuModelPackage.MENU__CREATION_DATE:
        return CREATION_DATE_EDEFAULT == null ? creationDate != null : !CREATION_DATE_EDEFAULT.equals (creationDate);
      case MenuModelPackage.MENU__ITEMS:
        return items != null && !items.isEmpty ();
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
    result.append (" (id: ");
    result.append (id);
    result.append (", description: ");
    result.append (description);
    result.append (", creationDate: ");
    result.append (creationDate);
    result.append (')');
    return result.toString ();
  }

} //MenuImpl
