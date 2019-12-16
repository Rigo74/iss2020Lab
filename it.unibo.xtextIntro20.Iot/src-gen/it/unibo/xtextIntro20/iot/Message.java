/**
 * generated by Xtext 2.18.0.M3
 */
package it.unibo.xtextIntro20.iot;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Message</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link it.unibo.xtextIntro20.iot.Message#getName <em>Name</em>}</li>
 *   <li>{@link it.unibo.xtextIntro20.iot.Message#getMsg <em>Msg</em>}</li>
 * </ul>
 *
 * @see it.unibo.xtextIntro20.iot.IotPackage#getMessage()
 * @model
 * @generated
 */
public interface Message extends EObject
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see it.unibo.xtextIntro20.iot.IotPackage#getMessage_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link it.unibo.xtextIntro20.iot.Message#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Msg</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Msg</em>' attribute.
   * @see #setMsg(String)
   * @see it.unibo.xtextIntro20.iot.IotPackage#getMessage_Msg()
   * @model
   * @generated
   */
  String getMsg();

  /**
   * Sets the value of the '{@link it.unibo.xtextIntro20.iot.Message#getMsg <em>Msg</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Msg</em>' attribute.
   * @see #getMsg()
   * @generated
   */
  void setMsg(String value);

} // Message
