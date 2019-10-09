/* Generated by AN DISI Unibo */ 
package it.unibo.caller2

import it.unibo.kactor.*
import alice.tuprolog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
	
class Caller2 ( name: String, scope: CoroutineScope ) : ActorBasicFsm( name, scope){
 	
	override fun getInitialState() : String{
		return "s0"
	}
		
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						println("caller2 request cmd ")
						request("cmd", "cmd(caller2)" ,"resource" )  
					}
					 transition(edgeName="t01",targetState="handleReply",cond=whenReply("replytocmd"))
				}	 
				state("handleReply") { //this:State
					action { //it:State
						println("       --- caller2 handleReply ")
						println("$name in ${currentState.stateName} | $currentMsg")
						println("       --- caller2 handleReply ")
					}
				}	 
			}
		}
}
