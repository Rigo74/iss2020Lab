/*
 * generated by Xtext 2.18.0.M3
 */
package it.unibo.xtextIntro20.ui.tests;

import com.google.inject.Injector;
import it.unibo.xtextIntro20.Iot.ui.internal.IotActivator;
import org.eclipse.xtext.testing.IInjectorProvider;

public class IotUiInjectorProvider implements IInjectorProvider {

	@Override
	public Injector getInjector() {
		return IotActivator.getInstance().getInjector("it.unibo.xtextIntro20.Iot");
	}

}
