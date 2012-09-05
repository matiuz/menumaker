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
 * A representation of the model object '<em><b>Category</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link it.matiuz.menumaker.model.Category#getId <em>Id</em>}</li>
 *   <li>{@link it.matiuz.menumaker.model.Category#getDescription <em>Description</em>}</li>
 *   <li>{@link it.matiuz.menumaker.model.Category#getPriority <em>Priority</em>}</li>
 * </ul>
 * </p>
 *
 * @see it.matiuz.menumaker.model.MenuModelPackage#getCategory()
 * @model
 * @generated
 */
public interface Category extends EObject
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
   * @see it.matiuz.menumaker.model.MenuModelPackage#getCategory_Id()
   * @model
   * @generated
   */
  int getId ();

  /**
   * Sets the value of the '{@link it.matiuz.menumaker.model.Category#getId <em>Id</em>}' attribute.
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
   * @see it.matiuz.menumaker.model.MenuModelPackage#getCategory_Description()
   * @model
   * @generated
   */
  String getDescription ();

  /**
   * Sets the value of the '{@link it.matiuz.menumaker.model.Category#getDescription <em>Description</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Description</em>' attribute.
   * @see #getDescription()
   * @generated
   */
  void setDescription (String value);

  /**
   * Returns the value of the '<em><b>Priority</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Priority</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Priority</em>' attribute.
   * @see #setPriority(int)
   * @see it.matiuz.menumaker.model.MenuModelPackage#getCategory_Priority()
   * @model
   * @generated
   */
  int getPriority ();

  /**
   * Sets the value of the '{@link it.matiuz.menumaker.model.Category#getPriority <em>Priority</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Priority</em>' attribute.
   * @see #getPriority()
   * @generated
   */
  void setPriority (int value);

} // Category
