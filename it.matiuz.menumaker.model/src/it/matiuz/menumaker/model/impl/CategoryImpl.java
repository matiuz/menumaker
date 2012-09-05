/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package it.matiuz.menumaker.model.impl;

import it.matiuz.menumaker.model.Category;
import it.matiuz.menumaker.model.MenuModelPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Category</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link it.matiuz.menumaker.model.impl.CategoryImpl#getId <em>Id</em>}</li>
 *   <li>{@link it.matiuz.menumaker.model.impl.CategoryImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link it.matiuz.menumaker.model.impl.CategoryImpl#getPriority <em>Priority</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CategoryImpl extends EObjectImpl implements Category
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
   * The default value of the '{@link #getPriority() <em>Priority</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPriority()
   * @generated
   * @ordered
   */
  protected static final int PRIORITY_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getPriority() <em>Priority</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPriority()
   * @generated
   * @ordered
   */
  protected int priority = PRIORITY_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected CategoryImpl ()
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
    return MenuModelPackage.Literals.CATEGORY;
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
      eNotify (new ENotificationImpl (this, Notification.SET, MenuModelPackage.CATEGORY__ID, oldId, id));
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
      eNotify (new ENotificationImpl (this, Notification.SET, MenuModelPackage.CATEGORY__DESCRIPTION,
          oldDescription, description));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getPriority ()
  {
    return priority;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPriority (int newPriority)
  {
    int oldPriority = priority;
    priority = newPriority;
    if (eNotificationRequired ())
      eNotify (new ENotificationImpl (this, Notification.SET, MenuModelPackage.CATEGORY__PRIORITY,
          oldPriority, priority));
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
      case MenuModelPackage.CATEGORY__ID:
        return new Integer (getId ());
      case MenuModelPackage.CATEGORY__DESCRIPTION:
        return getDescription ();
      case MenuModelPackage.CATEGORY__PRIORITY:
        return new Integer (getPriority ());
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
      case MenuModelPackage.CATEGORY__ID:
        setId (((Integer) newValue).intValue ());
        return;
      case MenuModelPackage.CATEGORY__DESCRIPTION:
        setDescription ((String) newValue);
        return;
      case MenuModelPackage.CATEGORY__PRIORITY:
        setPriority (((Integer) newValue).intValue ());
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
      case MenuModelPackage.CATEGORY__ID:
        setId (ID_EDEFAULT);
        return;
      case MenuModelPackage.CATEGORY__DESCRIPTION:
        setDescription (DESCRIPTION_EDEFAULT);
        return;
      case MenuModelPackage.CATEGORY__PRIORITY:
        setPriority (PRIORITY_EDEFAULT);
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
      case MenuModelPackage.CATEGORY__ID:
        return id != ID_EDEFAULT;
      case MenuModelPackage.CATEGORY__DESCRIPTION:
        return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals (description);
      case MenuModelPackage.CATEGORY__PRIORITY:
        return priority != PRIORITY_EDEFAULT;
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
    result.append (", priority: ");
    result.append (priority);
    result.append (')');
    return result.toString ();
  }

} //CategoryImpl
