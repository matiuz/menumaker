/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package it.matiuz.menumaker.model.util;

import it.matiuz.menumaker.model.Category;
import it.matiuz.menumaker.model.Item;
import it.matiuz.menumaker.model.Menu;
import it.matiuz.menumaker.model.MenuItem;
import it.matiuz.menumaker.model.MenuModelPackage;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see model.ModelPackage
 * @generated
 */
public class MenuModelSwitch
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static MenuModelPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MenuModelSwitch ()
  {
    if (modelPackage == null)
    {
      modelPackage = MenuModelPackage.eINSTANCE;
    }
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  public Object doSwitch (EObject theEObject)
  {
    return doSwitch (theEObject.eClass (), theEObject);
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  protected Object doSwitch (EClass theEClass, EObject theEObject)
  {
    if (theEClass.eContainer () == modelPackage)
      return doSwitch (theEClass.getClassifierID (), theEObject);

    List eSuperTypes = theEClass.getESuperTypes ();
    return eSuperTypes.isEmpty () ? defaultCase (theEObject) : doSwitch ((EClass) eSuperTypes.get (0), theEObject);

  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  protected Object doSwitch (int classifierID, EObject theEObject)
  {
    switch (classifierID)
    {
      case MenuModelPackage.MENU:
      {
        Menu menu = (Menu) theEObject;
        Object result = caseMenu (menu);
        if (result == null)
          result = defaultCase (theEObject);
        return result;
      }
      case MenuModelPackage.CATEGORY:
      {
        Category category = (Category) theEObject;
        Object result = caseCategory (category);
        if (result == null)
          result = defaultCase (theEObject);
        return result;
      }
      case MenuModelPackage.ITEM:
      {
        Item item = (Item) theEObject;
        Object result = caseItem (item);
        if (result == null)
          result = defaultCase (theEObject);
        return result;
      }
      case MenuModelPackage.MENU_ITEM:
      {
        MenuItem menuItem = (MenuItem) theEObject;
        Object result = caseMenuItem (menuItem);
        if (result == null)
          result = defaultCase (theEObject);
        return result;
      }
      default:
        return defaultCase (theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Menu</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Menu</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseMenu (Menu object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Category</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Category</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseCategory (Category object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Item</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Item</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseItem (Item object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Menu Item</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Menu Item</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseMenuItem (MenuItem object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch, but this is the last case anyway.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  public Object defaultCase (EObject object)
  {
    return null;
  }

} //ModelSwitch
