/* Generated by AN DISI Unibo */ 
package it.unibo.smartrobot

import it.unibo.kactor.*
import alice.tuprolog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
	
class Smartrobot ( name: String, scope: CoroutineScope ) : ActorBasicFsm( name, scope){
 	
	override fun getInitialState() : String{
		return "s0"
	}
		
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		 
				var StepTime = 1000L;  
				var Duration=0 
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						println("smartrobot started")
					}
					 transition( edgeName="goto",targetState="work", cond=doswitch() )
				}	 
				state("work") { //this:State
					action { //it:State
					}
					 transition(edgeName="s00",targetState="handleCmd",cond=whenDispatch("cmd"))
					transition(edgeName="s01",targetState="doStep",cond=whenRequest("step"))
					transition(edgeName="s02",targetState="handleStopNotExpected",cond=whenDispatch("stop"))
					transition(edgeName="s03",targetState="ignoreObstacle",cond=whenEvent("obstacle"))
				}	 
				state("handleStopNotExpected") { //this:State
					action { //it:State
						println("smartrobot | WARNING: the stop command should not be sent here")
					}
					 transition( edgeName="goto",targetState="work", cond=doswitch() )
				}	 
				state("ignoreObstacle") { //this:State
					action { //it:State
						println("smartrobot | IGNORE obstacle event in normal work ")
					}
					 transition( edgeName="goto",targetState="work", cond=doswitch() )
				}	 
				state("handleCmd") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
						if( checkMsgContent( Term.createTerm("cmd(X)"), Term.createTerm("cmd(X)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								forward("cmd", "cmd(${payloadArg(0)})" ,"basicrobot" ) 
						}
					}
					 transition( edgeName="goto",targetState="work", cond=doswitch() )
				}	 
				state("doStep") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
						if( checkMsgContent( Term.createTerm("step(DURATION)"), Term.createTerm("step(T)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								StepTime = payloadArg(0).toLong()
								startTimer()
								forward("cmd", "cmd(w)" ,"basicrobot" ) 
						}
						stateTimer = TimerActor("timer_doStep", 
							scope, context!!, "local_tout_smartrobot_doStep", StepTime )
					}
					 transition(edgeName="t04",targetState="endStep",cond=whenTimeout("local_tout_smartrobot_doStep"))   
					transition(edgeName="t05",targetState="stepStop",cond=whenDispatch("stop"))
					transition(edgeName="t06",targetState="stepFail",cond=whenEvent("obstacle"))
				}	 
				state("endStep") { //this:State
					action { //it:State
						forward("cmd", "cmd(h)" ,"basicrobot" ) 
						println("smartrobot | step DONE")
						answer("step", "stepdone", "stepdone(ok)"   )  
					}
					 transition( edgeName="goto",targetState="work", cond=doswitch() )
				}	 
				state("stepStop") { //this:State
					action { //it:State
						Duration=getDuration()
						forward("cmd", "cmd(h)" ,"basicrobot" ) 
						answer("step", "stepfail", "stepfail($Duration,stopped)"   )  
						println("smartrobot | stepStop Duration=$Duration ")
					}
					 transition( edgeName="goto",targetState="work", cond=doswitch() )
				}	 
				state("stepFail") { //this:State
					action { //it:State
						Duration=getDuration()
						answer("step", "stepfail", "stepfail($Duration,obstacle)"   )  
						println("smartrobot | stepFail Duration=$Duration ")
						emit("alarm", "alarm(stepobstacle)" ) 
					}
					 transition( edgeName="goto",targetState="work", cond=doswitch() )
				}	 
			}
		}
}
