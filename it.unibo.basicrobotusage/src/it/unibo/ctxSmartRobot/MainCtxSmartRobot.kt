/* Generated by AN DISI Unibo */ 
package it.unibo.ctxSmartRobot
import it.unibo.kactor.QakContext
import it.unibo.kactor.sysUtil
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
	QakContext.createContexts(
	        "192.168.1.6", this, "smartrobot.pl", "sysRules.pl"
	)
}

