/* Generated by AN DISI Unibo */ 
package it.unibo.smartrobotcaller

import it.unibo.kactor.*
import alice.tuprolog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
	
class Smartrobotcaller ( name: String, scope: CoroutineScope ) : ActorBasicFsm( name, scope){
 	
	override fun getInitialState() : String{
		return "s0"
	}
		
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						delay(1000) 
						println("smartrobotcaller | doing a step")
						request("step", "step(1000)" ,"smartrobot" )  
					}
					 transition( edgeName="goto",targetState="work", cond=doswitch() )
				}	 
				state("work") { //this:State
					action { //it:State
					}
					 transition(edgeName="t00",targetState="anotherStep",cond=whenReply("stepdone"))
					transition(edgeName="t01",targetState="stepFailed",cond=whenReply("stepfail"))
					transition(edgeName="t02",targetState="perceiveObstacle",cond=whenEvent("obstacle"))
					transition(edgeName="t03",targetState="handleAlarm",cond=whenEvent("alarm"))
				}	 
				state("anotherStep") { //this:State
					action { //it:State
						println("smartrobotcaller | doing another step")
						request("step", "step(1000)" ,"smartrobot" )  
					}
					 transition(edgeName="t04",targetState="endOfStep",cond=whenReply("stepdone"))
					transition(edgeName="t05",targetState="stepFailed",cond=whenReply("stepfail"))
				}	 
				state("endOfStep") { //this:State
					action { //it:State
						println("smartrobotcaller |  step DONE")
					}
					 transition( edgeName="goto",targetState="work", cond=doswitch() )
				}	 
				state("stepFailed") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("stepfail(DURATION,CAUSE)"), Term.createTerm("stepfail(DURATION,CAUSE)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								println("smartrobotcaller | step request failed after Duration=${payloadArg(0)} Cause=${payloadArg(1)}")
						}
						emit("alarm", "alarm(stepcallerfails)" ) 
					}
					 transition( edgeName="goto",targetState="work", cond=doswitch() )
				}	 
				state("perceiveObstacle") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("obstacle(DISTANCE)"), Term.createTerm("obstacle(D)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								println("smartrobotcaller | perceives obstacle at Distance=${payloadArg(0)} ")
						}
					}
					 transition( edgeName="goto",targetState="work", cond=doswitch() )
				}	 
				state("handleAlarm") { //this:State
					action { //it:State
						println("smartrobotcaller | handle alarm ")
						println("$name in ${currentState.stateName} | $currentMsg")
					}
					 transition( edgeName="goto",targetState="work", cond=doswitch() )
				}	 
			}
		}
}
