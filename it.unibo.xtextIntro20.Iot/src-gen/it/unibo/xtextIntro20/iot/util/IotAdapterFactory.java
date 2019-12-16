/**
 * generated by Xtext 2.18.0.M3
 */
package it.unibo.xtextIntro20.iot.util;

import it.unibo.xtextIntro20.iot.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see it.unibo.xtextIntro20.iot.IotPackage
 * @generated
 */
public class IotAdapterFactory extends AdapterFactoryImpl
{
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static IotPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IotAdapterFactory()
  {
    if (modelPackage == null)
    {
      modelPackage = IotPackage.eINSTANCE;
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
  @Override
  public boolean isFactoryForType(Object object)
  {
    if (object == modelPackage)
    {
      return true;
    }
    if (object instanceof EObject)
    {
      return ((EObject)object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected IotSwitch<Adapter> modelSwitch =
    new IotSwitch<Adapter>()
    {
      @Override
      public Adapter caseIotSystem(IotSystem object)
      {
        return createIotSystemAdapter();
      }
      @Override
      public Adapter caseIotSystemSpec(IotSystemSpec object)
      {
        return createIotSystemSpecAdapter();
      }
      @Override
      public Adapter caseBrokerSpec(BrokerSpec object)
      {
        return createBrokerSpecAdapter();
      }
      @Override
      public Adapter caseMessage(Message object)
      {
        return createMessageAdapter();
      }
      @Override
      public Adapter caseEvent(Event object)
      {
        return createEventAdapter();
      }
      @Override
      public Adapter caseDispatch(Dispatch object)
      {
        return createDispatchAdapter();
      }
      @Override
      public Adapter caseRequest(Request object)
      {
        return createRequestAdapter();
      }
      @Override
      public Adapter defaultCase(EObject object)
      {
        return createEObjectAdapter();
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
  @Override
  public Adapter createAdapter(Notifier target)
  {
    return modelSwitch.doSwitch((EObject)target);
  }


  /**
   * Creates a new adapter for an object of class '{@link it.unibo.xtextIntro20.iot.IotSystem <em>System</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see it.unibo.xtextIntro20.iot.IotSystem
   * @generated
   */
  public Adapter createIotSystemAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link it.unibo.xtextIntro20.iot.IotSystemSpec <em>System Spec</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see it.unibo.xtextIntro20.iot.IotSystemSpec
   * @generated
   */
  public Adapter createIotSystemSpecAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link it.unibo.xtextIntro20.iot.BrokerSpec <em>Broker Spec</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see it.unibo.xtextIntro20.iot.BrokerSpec
   * @generated
   */
  public Adapter createBrokerSpecAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link it.unibo.xtextIntro20.iot.Message <em>Message</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see it.unibo.xtextIntro20.iot.Message
   * @generated
   */
  public Adapter createMessageAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link it.unibo.xtextIntro20.iot.Event <em>Event</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see it.unibo.xtextIntro20.iot.Event
   * @generated
   */
  public Adapter createEventAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link it.unibo.xtextIntro20.iot.Dispatch <em>Dispatch</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see it.unibo.xtextIntro20.iot.Dispatch
   * @generated
   */
  public Adapter createDispatchAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link it.unibo.xtextIntro20.iot.Request <em>Request</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see it.unibo.xtextIntro20.iot.Request
   * @generated
   */
  public Adapter createRequestAdapter()
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
  public Adapter createEObjectAdapter()
  {
    return null;
  }

} //IotAdapterFactory
