/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package it.matiuz.menumaker.model;

import java.util.Date;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Menu</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link it.matiuz.menumaker.model.Menu#getId <em>Id</em>}</li>
 *   <li>{@link it.matiuz.menumaker.model.Menu#getDescription <em>Description</em>}</li>
 *   <li>{@link it.matiuz.menumaker.model.Menu#getCreationDate <em>Creation Date</em>}</li>
 *   <li>{@link it.matiuz.menumaker.model.Menu#getItems <em>Items</em>}</li>
 * </ul>
 * </p>
 *
 * @see it.matiuz.menumaker.model.MenuModelPackage#getMenu()
 * @model
 * @generated
 */
public interface Menu extends EObject
{
  /**
   * Returns the value of the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Id</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Id</em>' attribute.
   * @see #setId(int)
   * @see it.matiuz.menumaker.model.MenuModelPackage#getMenu_Id()
   * @model
   * @generated
   */
  int getId ();

  /**
   * Sets the value of the '{@link it.matiuz.menumaker.model.Menu#getId <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Id</em>' attribute.
   * @see #getId()
   * @generated
   */
  void setId (int value);

  /**
   * Returns the value of the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Description</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Description</em>' attribute.
   * @see #setDescription(String)
   * @see it.matiuz.menumaker.model.MenuModelPackage#getMenu_Description()
   * @model
   * @generated
   */
  String getDescription ();

  /**
   * Sets the value of the '{@link it.matiuz.menumaker.model.Menu#getDescription <em>Description</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Description</em>' attribute.
   * @see #getDescription()
   * @generated
   */
  void setDescription (String value);

  /**
   * Returns the value of the '<em><b>Creation Date</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Creation Date</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Creation Date</em>' attribute.
   * @see #setCreationDate(Date)
   * @see it.matiuz.menumaker.model.MenuModelPackage#getMenu_CreationDate()
   * @model
   * @generated
   */
  Date getCreationDate ();

  /**
   * Sets the value of the '{@link it.matiuz.menumaker.model.Menu#getCreationDate <em>Creation Date</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Creation Date</em>' attribute.
   * @see #getCreationDate()
   * @generated
   */
  void setCreationDate (Date value);

  /**
   * Returns the value of the '<em><b>Items</b></em>' containment reference list.
   * The list contents are of type {@link it.matiuz.menumaker.model.Item}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Items</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Items</em>' containment reference list.
   * @see it.matiuz.menumaker.model.MenuModelPackage#getMenu_Items()
   * @model type="model.Item" containment="true"
   * @generated
   */
  EList<Item> getItems ();

} // Menu
