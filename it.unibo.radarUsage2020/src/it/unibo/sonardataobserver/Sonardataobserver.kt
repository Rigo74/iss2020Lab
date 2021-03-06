/* Generated by AN DISI Unibo */ 
package it.unibo.sonardataobserver

import it.unibo.kactor.*
import alice.tuprolog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
	
class Sonardataobserver ( name: String, scope: CoroutineScope ) : ActorBasicFsm( name, scope){
 	
	override fun getInitialState() : String{
		return "s0"
	}
		
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						println("sonardataobserver | start")
					}
					 transition(edgeName="t02",targetState="handleSonarData",cond=whenEvent("sonar"))
				}	 
				state("handleSonarData") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
					}
					 transition(edgeName="t03",targetState="handleSonarData",cond=whenEvent("sonar"))
				}	 
			}
		}
}
