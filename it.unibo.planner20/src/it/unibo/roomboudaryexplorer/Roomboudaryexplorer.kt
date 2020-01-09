/* Generated by AN DISI Unibo */ 
package it.unibo.roomboudaryexplorer

import it.unibo.kactor.*
import alice.tuprolog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
	
class Roomboudaryexplorer ( name: String, scope: CoroutineScope ) : ActorBasicFsm( name, scope){
 	
	override fun getInitialState() : String{
		return "s0"
	}
		
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		
		var mapEmpty    = false
		val mapname     = "roomMbot3"  //"roomBoundary"		// 
		var Tback       = 0
		var NumStep     = 0
		 
		//REAL ROBOT
		//var StepTime   = 1000	 
		//var PauseTime  = 500 
		
		//VIRTUAL ROBOT
		var StepTime   = 330	 
		//var PauseTime  = 250
		
		//var PauseTimeL  = PauseTime.toLong()
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						itunibo.planner.plannerUtil.initAI(  )
						itunibo.planner.moveUtils.showCurrentRobotState(  )
					}
					 transition( edgeName="goto",targetState="detectBoundary", cond=doswitch() )
				}	 
				state("detectBoundary") { //this:State
					action { //it:State
						NumStep++
						itunibo.planner.plannerUtil.showMap(  )
					}
					 transition( edgeName="goto",targetState="doAheadMove", cond=doswitchGuarded({(NumStep<5)}) )
					transition( edgeName="goto",targetState="boundaryFound", cond=doswitchGuarded({! (NumStep<5)}) )
				}	 
				state("doAheadMove") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
						itunibo.planner.moveUtils.attemptTomoveAhead(myself ,StepTime )
					}
					 transition(edgeName="t00",targetState="stepDone",cond=whenReply("stepdone"))
					transition(edgeName="t01",targetState="stepFailed",cond=whenReply("stepfail"))
				}	 
				state("stepDone") { //this:State
					action { //it:State
						itunibo.planner.moveUtils.updateMapAfterAheadOk(myself)
						delay(500) 
					}
					 transition( edgeName="goto",targetState="doAheadMove", cond=doswitch() )
				}	 
				state("stepFailed") { //this:State
					action { //it:State
						println("&&&  FOUND WALL")
						
						val MapStr =  itunibo.planner.plannerUtil.getMapOneLine()  
						//println( MapStr ) 
						if( checkMsgContent( Term.createTerm("stepfail(DURATION,CAUSE)"), Term.createTerm("stepfail(Time,Cause)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								Tback=payloadArg(0).toString().toInt() / 4
								println("stepFailed ${payloadArg(0).toString()}")
						}
						itunibo.planner.moveUtils.backToCompensate(myself ,Tback, Tback )
						itunibo.planner.plannerUtil.wallFound(  )
						itunibo.planner.moveUtils.rotateLeft90(myself)
						 println("		PLEASE TUNE THE ROTATION LEFT" ); readLine() 
					}
					 transition( edgeName="goto",targetState="detectBoundary", cond=doswitch() )
				}	 
				state("boundaryFound") { //this:State
					action { //it:State
						itunibo.planner.plannerUtil.saveMap( mapname  )
						println("FINAL MAP")
						itunibo.planner.moveUtils.showCurrentRobotState(  )
					}
				}	 
			}
		}
}
