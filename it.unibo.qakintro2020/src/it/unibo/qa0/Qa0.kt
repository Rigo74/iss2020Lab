/* Generated by AN DISI Unibo */ 
package it.unibo.qa0

import it.unibo.kactor.*
import alice.tuprolog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
	
class Qa0 ( name: String, scope: CoroutineScope ) : ActorBasicFsm( name, scope){
 	
	override fun getInitialState() : String{
		return "s0"
	}
		
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						println("qa0 sends w to qacoded")
						forward("cmd", "cmd(w)" ,"qacoded" ) 
						delay(500) 
						println("qa0 sends  ledCmd : ledCmd( on )  to the external led")
						forward("ledCmd", "ledCmd(on)" ,"led" ) 
						delay(1000) 
						println("qa0 sends  ledCmd : ledCmd( off ) to the external led")
						forward("ledCmd", "ledCmd(off)" ,"led" ) 
					}
					 transition( edgeName="goto",targetState="s1", cond=doswitch() )
				}	 
				state("s1") { //this:State
					action { //it:State
						println("qa0 waiting ... ")
					}
					 transition(edgeName="t00",targetState="handleAlarm",cond=whenEvent("alarm"))
				}	 
				state("handleAlarm") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
					}
					 transition( edgeName="goto",targetState="s1", cond=doswitch() )
				}	 
			}
		}
}
