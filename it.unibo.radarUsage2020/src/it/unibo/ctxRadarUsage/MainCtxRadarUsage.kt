/* Generated by AN DISI Unibo */ 
package it.unibo.ctxRadarUsage
import it.unibo.kactor.QakContext
import it.unibo.kactor.sysUtil
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
	QakContext.createContexts(
	        "localhost", this, "radarusage.pl", "sysRules.pl"
	)
}

