/*
 * generated by Xtext 2.18.0.M3
 */
package it.unibo.xtextIntro20.tests

import com.google.inject.Inject
import it.unibo.xtextIntro20.iot.IotSystem
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.extensions.InjectionExtension
import org.eclipse.xtext.testing.util.ParseHelper
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.^extension.ExtendWith

@ExtendWith(InjectionExtension)
@InjectWith(IotInjectorProvider)
class IotParsingTest {
	@Inject
	ParseHelper<IotSystem> parseHelper
	
	@Test
	def void loadModel() {
		val result = parseHelper.parse('''
			Hello Xtext!
		''')
		Assertions.assertNotNull(result)
		val errors = result.eResource.errors
		Assertions.assertTrue(errors.isEmpty, '''Unexpected errors: �errors.join(", ")�''')
	}
}
