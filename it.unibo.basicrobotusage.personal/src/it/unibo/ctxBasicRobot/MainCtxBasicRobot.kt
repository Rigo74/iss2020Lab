/* Generated by AN DISI Unibo */ 
package it.unibo.ctxBasicRobot
import it.unibo.kactor.QakContext
import it.unibo.kactor.sysUtil
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
	QakContext.createContexts(
	        "10.201.111.204", this, "basicrobotusage.pl", "sysRules.pl"
	)
}

