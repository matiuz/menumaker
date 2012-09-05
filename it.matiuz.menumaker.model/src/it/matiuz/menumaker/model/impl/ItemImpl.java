/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package it.matiuz.menumaker.model.impl;

import it.matiuz.menumaker.model.Item;
import it.matiuz.menumaker.model.MenuModelPackage;

import java.util.Date;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Item</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link it.matiuz.menumaker.model.impl.ItemImpl#getId <em>Id</em>}</li>
 *   <li>{@link it.matiuz.menumaker.model.impl.ItemImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link it.matiuz.menumaker.model.impl.ItemImpl#getCreationDate <em>Creation Date</em>}</li>
 *   <li>{@link it.matiuz.menumaker.model.impl.ItemImpl#getPrice <em>Price</em>}</li>
 *   <li>{@link it.matiuz.menumaker.model.impl.ItemImpl#getPriceNotes <em>Price Notes</em>}</li>
 *   <li>{@link it.matiuz.menumaker.model.impl.ItemImpl#getCategoryId <em>Category Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ItemImpl extends EObjectImpl implements Item
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
  protected static final Date CREATION_DATE_EDEFAULT = null;

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
   * The default value of the '{@link #getPrice() <em>Price</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPrice()
   * @generated
   * @ordered
   */
  protected static final double PRICE_EDEFAULT = 0.0;

  /**
   * The cached value of the '{@link #getPrice() <em>Price</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPrice()
   * @generated
   * @ordered
   */
  protected double price = PRICE_EDEFAULT;

  /**
   * The default value of the '{@link #getPriceNotes() <em>Price Notes</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPriceNotes()
   * @generated
   * @ordered
   */
  protected static final String PRICE_NOTES_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getPriceNotes() <em>Price Notes</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPriceNotes()
   * @generated
   * @ordered
   */
  protected String priceNotes = PRICE_NOTES_EDEFAULT;

  /**
   * The default value of the '{@link #getCategoryId() <em>Category Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCategoryId()
   * @generated
   * @ordered
   */
  protected static final int CATEGORY_ID_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getCategoryId() <em>Category Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCategoryId()
   * @generated
   * @ordered
   */
  protected int categoryId = CATEGORY_ID_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ItemImpl ()
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
    return MenuModelPackage.Literals.ITEM;
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
      eNotify (new ENotificationImpl (this, Notification.SET, MenuModelPackage.ITEM__ID, oldId, id));
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
      eNotify (new ENotificationImpl (this, Notification.SET, MenuModelPackage.ITEM__DESCRIPTION,
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
      eNotify (new ENotificationImpl (this, Notification.SET, MenuModelPackage.ITEM__CREATION_DATE,
          oldCreationDate, creationDate));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public double getPrice ()
  {
    return price;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPrice (double newPrice)
  {
    double oldPrice = price;
    price = newPrice;
    if (eNotificationRequired ())
      eNotify (new ENotificationImpl (this, Notification.SET, MenuModelPackage.ITEM__PRICE, oldPrice, price));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getPriceNotes ()
  {
    return priceNotes;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPriceNotes (String newPriceNotes)
  {
    String oldPriceNotes = priceNotes;
    priceNotes = newPriceNotes;
    if (eNotificationRequired ())
      eNotify (new ENotificationImpl (this, Notification.SET, MenuModelPackage.ITEM__PRICE_NOTES,
          oldPriceNotes, priceNotes));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getCategoryId ()
  {
    return categoryId;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCategoryId (int newCategoryId)
  {
    int oldCategoryId = categoryId;
    categoryId = newCategoryId;
    if (eNotificationRequired ())
      eNotify (new ENotificationImpl (this, Notification.SET, MenuModelPackage.ITEM__CATEGORY_ID,
          oldCategoryId, categoryId));
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
      case MenuModelPackage.ITEM__ID:
        return new Integer (getId ());
      case MenuModelPackage.ITEM__DESCRIPTION:
        return getDescription ();
      case MenuModelPackage.ITEM__CREATION_DATE:
        return getCreationDate ();
      case MenuModelPackage.ITEM__PRICE:
        return new Double (getPrice ());
      case MenuModelPackage.ITEM__PRICE_NOTES:
        return getPriceNotes ();
      case MenuModelPackage.ITEM__CATEGORY_ID:
        return new Integer (getCategoryId ());
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
      case MenuModelPackage.ITEM__ID:
        setId (((Integer) newValue).intValue ());
        return;
      case MenuModelPackage.ITEM__DESCRIPTION:
        setDescription ((String) newValue);
        return;
      case MenuModelPackage.ITEM__CREATION_DATE:
        setCreationDate ((Date) newValue);
        return;
      case MenuModelPackage.ITEM__PRICE:
        setPrice (((Double) newValue).doubleValue ());
        return;
      case MenuModelPackage.ITEM__PRICE_NOTES:
        setPriceNotes ((String) newValue);
        return;
      case MenuModelPackage.ITEM__CATEGORY_ID:
        setCategoryId (((Integer) newValue).intValue ());
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
      case MenuModelPackage.ITEM__ID:
        setId (ID_EDEFAULT);
        return;
      case MenuModelPackage.ITEM__DESCRIPTION:
        setDescription (DESCRIPTION_EDEFAULT);
        return;
      case MenuModelPackage.ITEM__CREATION_DATE:
        setCreationDate (CREATION_DATE_EDEFAULT);
        return;
      case MenuModelPackage.ITEM__PRICE:
        setPrice (PRICE_EDEFAULT);
        return;
      case MenuModelPackage.ITEM__PRICE_NOTES:
        setPriceNotes (PRICE_NOTES_EDEFAULT);
        return;
      case MenuModelPackage.ITEM__CATEGORY_ID:
        setCategoryId (CATEGORY_ID_EDEFAULT);
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
      case MenuModelPackage.ITEM__ID:
        return id != ID_EDEFAULT;
      case MenuModelPackage.ITEM__DESCRIPTION:
        return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals (description);
      case MenuModelPackage.ITEM__CREATION_DATE:
        return CREATION_DATE_EDEFAULT == null ? creationDate != null : !CREATION_DATE_EDEFAULT.equals (creationDate);
      case MenuModelPackage.ITEM__PRICE:
        return price != PRICE_EDEFAULT;
      case MenuModelPackage.ITEM__PRICE_NOTES:
        return PRICE_NOTES_EDEFAULT == null ? priceNotes != null : !PRICE_NOTES_EDEFAULT.equals (priceNotes);
      case MenuModelPackage.ITEM__CATEGORY_ID:
        return categoryId != CATEGORY_ID_EDEFAULT;
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
    result.append (", price: ");
    result.append (price);
    result.append (", priceNotes: ");
    result.append (priceNotes);
    result.append (", categoryId: ");
    result.append (categoryId);
    result.append (')');
    return result.toString ();
  }

} //ItemImpl
