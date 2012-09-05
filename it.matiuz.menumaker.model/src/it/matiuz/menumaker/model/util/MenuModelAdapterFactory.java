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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see model.ModelPackage
 * @generated
 */
public class MenuModelAdapterFactory extends AdapterFactoryImpl
{
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static MenuModelPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MenuModelAdapterFactory ()
  {
    if (modelPackage == null)
    {
      modelPackage = MenuModelPackage.eINSTANCE;
    }
  }

  /**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc -->
   * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
   * <!-- end-user-doc -->
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
  public boolean isFactoryForType (Object object)
  {
    if (object == modelPackage)
    {
      return true;
    }
    if (object instanceof EObject)
    {
      return ((EObject) object).eClass ().getEPackage () == modelPackage;
    }
    return false;
  }

  /**
   * The switch the delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected MenuModelSwitch modelSwitch = new MenuModelSwitch ()
  {
    public Object caseMenu (Menu object)
    {
      return createMenuAdapter ();
    }

    public Object caseCategory (Category object)
    {
      return createCategoryAdapter ();
    }

    public Object caseItem (Item object)
    {
      return createItemAdapter ();
    }

    public Object caseMenuItem (MenuItem object)
    {
      return createMenuItemAdapter ();
    }

    public Object defaultCase (EObject object)
    {
      return createEObjectAdapter ();
    }
  };

  /**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
  public Adapter createAdapter (Notifier target)
  {
    return (Adapter) modelSwitch.doSwitch ((EObject) target);
  }

  /**
   * Creates a new adapter for an object of class '{@link model.Menu <em>Menu</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see model.Menu
   * @generated
   */
  public Adapter createMenuAdapter ()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link model.Category <em>Category</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see model.Category
   * @generated
   */
  public Adapter createCategoryAdapter ()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link model.Item <em>Item</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see model.Item
   * @generated
   */
  public Adapter createItemAdapter ()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link model.MenuItem <em>Menu Item</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see model.MenuItem
   * @generated
   */
  public Adapter createMenuItemAdapter ()
  {
    return null;
  }

  /**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
   * This default implementation returns null.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @generated
   */
  public Adapter createEObjectAdapter ()
  {
    return null;
  }

} //ModelAdapterFactory
