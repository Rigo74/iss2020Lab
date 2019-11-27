/* Generated by AN DISI Unibo */ 
package it.unibo.robot

import it.unibo.kactor.*
import alice.tuprolog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
	
class Robot ( name: String, scope: CoroutineScope ) : ActorBasicFsm( name, scope){
 	
	override fun getInitialState() : String{
		return "init"
	}
		
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		return { //this:ActionBasciFsm
				state("init") { //this:State
					action { //it:State
					}
					 transition( edgeName="goto",targetState="work", cond=doswitch() )
				}	 
				state("work") { //this:State
					action { //it:State
						println("[Robot] Waiting data...")
					}
					 transition(edgeName="dataReceived0",targetState="sendData",cond=whenDispatch("sonar"))
				}	 
				state("sendData") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("sonar(Distance,Theta)"), Term.createTerm("sonar(Distance,Theta)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								forward("sonar", "sonar(${payloadArg(0)},${payloadArg(1)})" ,"radar" ) 
						}
					}
					 transition( edgeName="goto",targetState="work", cond=doswitch() )
				}	 
			}
		}
}
