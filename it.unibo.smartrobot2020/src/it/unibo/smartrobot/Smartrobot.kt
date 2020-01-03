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
		 
		var foundObstacle = false
		var StepTime      = 0L
		var Duration      = 0 
		
		var TimeToEndStep = 0L  
		var IsBottle      = false
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						println("smartrobot starts")
						forward("cmd", "cmd(l)" ,"basicrobot" ) 
						delay(1000) 
						forward("cmd", "cmd(h)" ,"basicrobot" ) 
						forward("cmd", "cmd(r)" ,"basicrobot" ) 
						delay(1000) 
						forward("cmd", "cmd(h)" ,"basicrobot" ) 
						println("smartrobot started")
					}
					 transition( edgeName="goto",targetState="activateResource", cond=doswitch() )
				}	 
				state("activateResource") { //this:State
					action { //it:State
						kotlincode.resServer.init(myself)
						kotlincode.coapSupport.init( "coap://localhost:5683"  )
						kotlincode.resourceObserver.init( "coap://127.0.0.1:5683", "robot/pos"  )
						consolegui.consoleGui.create(  )
					}
					 transition( edgeName="goto",targetState="work", cond=doswitch() )
				}	 
				state("work") { //this:State
					action { //it:State
					}
					 transition(edgeName="s00",targetState="handleCmd",cond=whenDispatch("cmd"))
					transition(edgeName="s01",targetState="doStep",cond=whenRequest("onestep"))
					transition(edgeName="s02",targetState="handleStopNotExpected",cond=whenDispatch("stop"))
					transition(edgeName="s03",targetState="updateDistanceResource",cond=whenEvent("polar"))
				}	 
				state("updateDistanceResource") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("polar(D,Angle)"), Term.createTerm("polar(D,A)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								kotlincode.coapSupport.updateResource(myself ,"robot/sonar", "distance(${payloadArg(0)})" )
						}
					}
					 transition( edgeName="goto",targetState="work", cond=doswitch() )
				}	 
				state("handleStopNotExpected") { //this:State
					action { //it:State
						println("smartrobot | WARNING: stop command should not be sent here")
					}
					 transition( edgeName="goto",targetState="work", cond=doswitch() )
				}	 
				state("handleCmd") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("cmd(X)"), Term.createTerm("cmd(X)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								var Move = payloadArg(0)
								forward("cmd", "cmd($Move)" ,"basicrobot" ) 
								kotlincode.coapSupport.updateResource(myself ,"robot/pos", "u$Move" )
						}
					}
					 transition( edgeName="goto",targetState="work", cond=doswitch() )
				}	 
				state("doStep") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("onestep(DURATION)"), Term.createTerm("onestep(T)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								StepTime = payloadArg(0).toLong() 
								 			  startTimer()
								forward("cmd", "cmd(w)" ,"basicrobot" ) 
						}
						stateTimer = TimerActor("timer_doStep", 
							scope, context!!, "local_tout_smartrobot_doStep", StepTime )
					}
					 transition(edgeName="t04",targetState="stepDone",cond=whenTimeout("local_tout_smartrobot_doStep"))   
					transition(edgeName="t05",targetState="stepStop",cond=whenDispatch("stop"))
					transition(edgeName="t06",targetState="stepFailVirtual",cond=whenEvent("virtualobstacle"))
					transition(edgeName="t07",targetState="stepFail",cond=whenEvent("obstacle"))
				}	 
				state("stepDone") { //this:State
					action { //it:State
						forward("cmd", "cmd(h)" ,"basicrobot" ) 
						kotlincode.coapSupport.updateResource(myself ,"robot/pos", "up" )
						answer("onestep", "stepdone", "stepdone(ok)"   )  
					}
					 transition( edgeName="goto",targetState="work", cond=doswitch() )
				}	 
				state("stepStop") { //this:State
					action { //it:State
						Duration=getDuration()
						forward("cmd", "cmd(h)" ,"basicrobot" ) 
						answer("onestep", "stepfail", "stepfail($Duration,stopped)"   )  
					}
					 transition( edgeName="goto",targetState="work", cond=doswitch() )
				}	 
				state("stepFail") { //this:State
					action { //it:State
						Duration=getDuration()
						answer("onestep", "stepfail", "stepfail($Duration,obstacle)"   )  
					}
					 transition( edgeName="goto",targetState="work", cond=doswitch() )
				}	 
				state("stepFailVirtual") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("virtualobstacle(OBJNAME)"), Term.createTerm("virtualobstacle(OBJNAME)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								 Duration=getDuration()  
											  IsBottle = payloadArg(0).startsWith("bottle")
											  TimeToEndStep = StepTime - Duration   //100L is the backtime of basicrobot
								println("smartrobot | !!!stepFailVirtual dt=$Duration, $TimeToEndStep, obj=${payloadArg(0)}")
								if(IsBottle){ forward("cmd", "cmd(${payloadArg(0)})" ,"basicrobot" ) 
								delay(100) 
								forward("cmd", "cmd(w)" ,"basicrobot" ) 
								delay(TimeToEndStep)
								forward("cmd", "cmd(h)" ,"basicrobot" ) 
								answer("onestep", "stepdone", "stepdone(ok)"   )  
								 }
								else
								 { answer("onestep", "stepfail", "stepfail($Duration,${payloadArg(0)})"   )  
								  }
						}
					}
					 transition( edgeName="goto",targetState="work", cond=doswitch() )
				}	 
			}
		}
}
