/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package it.matiuz.menumaker.model;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see it.matiuz.menumaker.model.MenuModelPackage
 * @generated
 */
public interface MenuModelFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  MenuModelFactory eINSTANCE = it.matiuz.menumaker.model.impl.MenuModelFactoryImpl.init ();

  /**
   * Returns a new object of class '<em>Menu</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Menu</em>'.
   * @generated
   */
  Menu createMenu ();

  /**
   * Returns a new object of class '<em>Category</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Category</em>'.
   * @generated
   */
  Category createCategory ();

  /**
   * Returns a new object of class '<em>Item</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Item</em>'.
   * @generated
   */
  Item createItem ();

  /**
   * Returns a new object of class '<em>Menu Item</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Menu Item</em>'.
   * @generated
   */
  MenuItem createMenuItem ();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  MenuModelPackage getModelPackage ();

} //ModelFactory
