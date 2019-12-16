/**
 * generated by Xtext 2.18.0.M3
 */
package it.unibo.xtextIntro20.iot.impl;

import it.unibo.xtextIntro20.iot.BrokerSpec;
import it.unibo.xtextIntro20.iot.Dispatch;
import it.unibo.xtextIntro20.iot.Event;
import it.unibo.xtextIntro20.iot.IotFactory;
import it.unibo.xtextIntro20.iot.IotPackage;
import it.unibo.xtextIntro20.iot.IotSystem;
import it.unibo.xtextIntro20.iot.IotSystemSpec;
import it.unibo.xtextIntro20.iot.Message;
import it.unibo.xtextIntro20.iot.Request;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class IotPackageImpl extends EPackageImpl implements IotPackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass iotSystemEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass iotSystemSpecEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass brokerSpecEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass messageEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass eventEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass dispatchEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass requestEClass = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see it.unibo.xtextIntro20.iot.IotPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private IotPackageImpl()
  {
    super(eNS_URI, IotFactory.eINSTANCE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
   *
   * <p>This method is used to initialize {@link IotPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static IotPackage init()
  {
    if (isInited) return (IotPackage)EPackage.Registry.INSTANCE.getEPackage(IotPackage.eNS_URI);

    // Obtain or create and register package
    Object registeredIotPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
    IotPackageImpl theIotPackage = registeredIotPackage instanceof IotPackageImpl ? (IotPackageImpl)registeredIotPackage : new IotPackageImpl();

    isInited = true;

    // Create package meta-data objects
    theIotPackage.createPackageContents();

    // Initialize created meta-data
    theIotPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theIotPackage.freeze();

    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(IotPackage.eNS_URI, theIotPackage);
    return theIotPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getIotSystem()
  {
    return iotSystemEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getIotSystem_Spec()
  {
    return (EReference)iotSystemEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getIotSystemSpec()
  {
    return iotSystemSpecEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getIotSystemSpec_Name()
  {
    return (EAttribute)iotSystemSpecEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getIotSystemSpec_MqttBroker()
  {
    return (EReference)iotSystemSpecEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getIotSystemSpec_Message()
  {
    return (EReference)iotSystemSpecEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getBrokerSpec()
  {
    return brokerSpecEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getBrokerSpec_BrokerHost()
  {
    return (EAttribute)brokerSpecEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getBrokerSpec_BrokerPort()
  {
    return (EAttribute)brokerSpecEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getMessage()
  {
    return messageEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getMessage_Name()
  {
    return (EAttribute)messageEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getMessage_Msg()
  {
    return (EAttribute)messageEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getEvent()
  {
    return eventEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getDispatch()
  {
    return dispatchEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getRequest()
  {
    return requestEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public IotFactory getIotFactory()
  {
    return (IotFactory)getEFactoryInstance();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isCreated = false;

  /**
   * Creates the meta-model objects for the package.  This method is
   * guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void createPackageContents()
  {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    iotSystemEClass = createEClass(IOT_SYSTEM);
    createEReference(iotSystemEClass, IOT_SYSTEM__SPEC);

    iotSystemSpecEClass = createEClass(IOT_SYSTEM_SPEC);
    createEAttribute(iotSystemSpecEClass, IOT_SYSTEM_SPEC__NAME);
    createEReference(iotSystemSpecEClass, IOT_SYSTEM_SPEC__MQTT_BROKER);
    createEReference(iotSystemSpecEClass, IOT_SYSTEM_SPEC__MESSAGE);

    brokerSpecEClass = createEClass(BROKER_SPEC);
    createEAttribute(brokerSpecEClass, BROKER_SPEC__BROKER_HOST);
    createEAttribute(brokerSpecEClass, BROKER_SPEC__BROKER_PORT);

    messageEClass = createEClass(MESSAGE);
    createEAttribute(messageEClass, MESSAGE__NAME);
    createEAttribute(messageEClass, MESSAGE__MSG);

    eventEClass = createEClass(EVENT);

    dispatchEClass = createEClass(DISPATCH);

    requestEClass = createEClass(REQUEST);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isInitialized = false;

  /**
   * Complete the initialization of the package and its meta-model.  This
   * method is guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void initializePackageContents()
  {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    eventEClass.getESuperTypes().add(this.getMessage());
    dispatchEClass.getESuperTypes().add(this.getMessage());
    requestEClass.getESuperTypes().add(this.getMessage());

    // Initialize classes and features; add operations and parameters
    initEClass(iotSystemEClass, IotSystem.class, "IotSystem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getIotSystem_Spec(), this.getIotSystemSpec(), null, "spec", null, 0, 1, IotSystem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(iotSystemSpecEClass, IotSystemSpec.class, "IotSystemSpec", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getIotSystemSpec_Name(), ecorePackage.getEString(), "name", null, 0, 1, IotSystemSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getIotSystemSpec_MqttBroker(), this.getBrokerSpec(), null, "mqttBroker", null, 0, 1, IotSystemSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getIotSystemSpec_Message(), this.getMessage(), null, "message", null, 0, -1, IotSystemSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(brokerSpecEClass, BrokerSpec.class, "BrokerSpec", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getBrokerSpec_BrokerHost(), ecorePackage.getEString(), "brokerHost", null, 0, 1, BrokerSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getBrokerSpec_BrokerPort(), ecorePackage.getEInt(), "brokerPort", null, 0, 1, BrokerSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(messageEClass, Message.class, "Message", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getMessage_Name(), ecorePackage.getEString(), "name", null, 0, 1, Message.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getMessage_Msg(), ecorePackage.getEString(), "msg", null, 0, 1, Message.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(eventEClass, Event.class, "Event", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(dispatchEClass, Dispatch.class, "Dispatch", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(requestEClass, Request.class, "Request", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    // Create resource
    createResource(eNS_URI);
  }

} //IotPackageImpl
