/* Generated by AN DISI Unibo */ 
package it.unibo.callerfriend

import it.unibo.kactor.*
import alice.tuprolog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
	
class Callerfriend ( name: String, scope: CoroutineScope ) : ActorBasicFsm( name, scope){
 	
	override fun getInitialState() : String{
		return "s0"
	}
		
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						println("callerfriend start")
					}
					 transition(edgeName="t01",targetState="handleObstacle",cond=whenEvent("obstacle"))
				}	 
				state("handleObstacle") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
						println("callerfriend handleObstacle ")
						emit("alarm", "alarm(obstacle)" ) 
					}
					 transition(edgeName="t02",targetState="handleObstacle",cond=whenEvent("obstacle"))
				}	 
			}
		}
}
