/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package it.matiuz.menumaker.model;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Menu Item</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link it.matiuz.menumaker.model.MenuItem#getItemId <em>Item Id</em>}</li>
 *   <li>{@link it.matiuz.menumaker.model.MenuItem#getMenuId <em>Menu Id</em>}</li>
 *   <li>{@link it.matiuz.menumaker.model.MenuItem#getItemPosition <em>Item Position</em>}</li>
 * </ul>
 * </p>
 *
 * @see it.matiuz.menumaker.model.MenuModelPackage#getMenuItem()
 * @model
 * @generated
 */
public interface MenuItem extends EObject
{
  /**
   * Returns the value of the '<em><b>Item Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Item Id</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Item Id</em>' attribute.
   * @see #setItemId(int)
   * @see it.matiuz.menumaker.model.MenuModelPackage#getMenuItem_ItemId()
   * @model
   * @generated
   */
  int getItemId ();

  /**
   * Sets the value of the '{@link it.matiuz.menumaker.model.MenuItem#getItemId <em>Item Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Item Id</em>' attribute.
   * @see #getItemId()
   * @generated
   */
  void setItemId (int value);

  /**
   * Returns the value of the '<em><b>Menu Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Menu Id</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Menu Id</em>' attribute.
   * @see #setMenuId(int)
   * @see it.matiuz.menumaker.model.MenuModelPackage#getMenuItem_MenuId()
   * @model
   * @generated
   */
  int getMenuId ();

  /**
   * Sets the value of the '{@link it.matiuz.menumaker.model.MenuItem#getMenuId <em>Menu Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Menu Id</em>' attribute.
   * @see #getMenuId()
   * @generated
   */
  void setMenuId (int value);

  /**
   * Returns the value of the '<em><b>Item Position</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Item Position</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Item Position</em>' attribute.
   * @see #setItemPosition(int)
   * @see it.matiuz.menumaker.model.MenuModelPackage#getMenuItem_ItemPosition()
   * @model
   * @generated
   */
  int getItemPosition ();

  /**
   * Sets the value of the '{@link it.matiuz.menumaker.model.MenuItem#getItemPosition <em>Item Position</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Item Position</em>' attribute.
   * @see #getItemPosition()
   * @generated
   */
  void setItemPosition (int value);

} // MenuItem
