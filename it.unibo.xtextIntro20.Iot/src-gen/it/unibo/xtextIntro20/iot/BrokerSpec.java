/**
 * generated by Xtext 2.18.0.M3
 */
package it.unibo.xtextIntro20.iot;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Broker Spec</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link it.unibo.xtextIntro20.iot.BrokerSpec#getBrokerHost <em>Broker Host</em>}</li>
 *   <li>{@link it.unibo.xtextIntro20.iot.BrokerSpec#getBrokerPort <em>Broker Port</em>}</li>
 * </ul>
 *
 * @see it.unibo.xtextIntro20.iot.IotPackage#getBrokerSpec()
 * @model
 * @generated
 */
public interface BrokerSpec extends EObject
{
  /**
   * Returns the value of the '<em><b>Broker Host</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Broker Host</em>' attribute.
   * @see #setBrokerHost(String)
   * @see it.unibo.xtextIntro20.iot.IotPackage#getBrokerSpec_BrokerHost()
   * @model
   * @generated
   */
  String getBrokerHost();

  /**
   * Sets the value of the '{@link it.unibo.xtextIntro20.iot.BrokerSpec#getBrokerHost <em>Broker Host</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Broker Host</em>' attribute.
   * @see #getBrokerHost()
   * @generated
   */
  void setBrokerHost(String value);

  /**
   * Returns the value of the '<em><b>Broker Port</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Broker Port</em>' attribute.
   * @see #setBrokerPort(int)
   * @see it.unibo.xtextIntro20.iot.IotPackage#getBrokerSpec_BrokerPort()
   * @model
   * @generated
   */
  int getBrokerPort();

  /**
   * Sets the value of the '{@link it.unibo.xtextIntro20.iot.BrokerSpec#getBrokerPort <em>Broker Port</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Broker Port</em>' attribute.
   * @see #getBrokerPort()
   * @generated
   */
  void setBrokerPort(int value);

} // BrokerSpec
