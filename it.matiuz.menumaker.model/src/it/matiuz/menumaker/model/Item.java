/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package it.matiuz.menumaker.model;

import java.util.Date;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Item</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link it.matiuz.menumaker.model.Item#getId <em>Id</em>}</li>
 *   <li>{@link it.matiuz.menumaker.model.Item#getDescription <em>Description</em>}</li>
 *   <li>{@link it.matiuz.menumaker.model.Item#getCreationDate <em>Creation Date</em>}</li>
 *   <li>{@link it.matiuz.menumaker.model.Item#getPrice <em>Price</em>}</li>
 *   <li>{@link it.matiuz.menumaker.model.Item#getPriceNotes <em>Price Notes</em>}</li>
 *   <li>{@link it.matiuz.menumaker.model.Item#getCategoryId <em>Category Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see it.matiuz.menumaker.model.MenuModelPackage#getItem()
 * @model
 * @generated
 */
public interface Item extends EObject
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
   * @see it.matiuz.menumaker.model.MenuModelPackage#getItem_Id()
   * @model
   * @generated
   */
  int getId ();

  /**
   * Sets the value of the '{@link it.matiuz.menumaker.model.Item#getId <em>Id</em>}' attribute.
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
   * @see it.matiuz.menumaker.model.MenuModelPackage#getItem_Description()
   * @model
   * @generated
   */
  String getDescription ();

  /**
   * Sets the value of the '{@link it.matiuz.menumaker.model.Item#getDescription <em>Description</em>}' attribute.
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
   * @see it.matiuz.menumaker.model.MenuModelPackage#getItem_CreationDate()
   * @model
   * @generated
   */
  Date getCreationDate ();

  /**
   * Sets the value of the '{@link it.matiuz.menumaker.model.Item#getCreationDate <em>Creation Date</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Creation Date</em>' attribute.
   * @see #getCreationDate()
   * @generated
   */
  void setCreationDate (Date value);

  /**
   * Returns the value of the '<em><b>Price</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Price</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Price</em>' attribute.
   * @see #setPrice(double)
   * @see it.matiuz.menumaker.model.MenuModelPackage#getItem_Price()
   * @model
   * @generated
   */
  double getPrice ();

  /**
   * Sets the value of the '{@link it.matiuz.menumaker.model.Item#getPrice <em>Price</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Price</em>' attribute.
   * @see #getPrice()
   * @generated
   */
  void setPrice (double value);

  /**
   * Returns the value of the '<em><b>Price Notes</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Price Notes</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Price Notes</em>' attribute.
   * @see #setPriceNotes(String)
   * @see it.matiuz.menumaker.model.MenuModelPackage#getItem_PriceNotes()
   * @model
   * @generated
   */
  String getPriceNotes ();

  /**
   * Sets the value of the '{@link it.matiuz.menumaker.model.Item#getPriceNotes <em>Price Notes</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Price Notes</em>' attribute.
   * @see #getPriceNotes()
   * @generated
   */
  void setPriceNotes (String value);

  /**
   * Returns the value of the '<em><b>Category Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Category Id</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Category Id</em>' attribute.
   * @see #setCategoryId(int)
   * @see it.matiuz.menumaker.model.MenuModelPackage#getItem_CategoryId()
   * @model
   * @generated
   */
  int getCategoryId ();

  /**
   * Sets the value of the '{@link it.matiuz.menumaker.model.Item#getCategoryId <em>Category Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Category Id</em>' attribute.
   * @see #getCategoryId()
   * @generated
   */
  void setCategoryId (int value);

} // Item
