/* Generated by AN DISI Unibo */ 
package it.unibo.sentinel

import it.unibo.kactor.*
import alice.tuprolog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
	
class Sentinel ( name: String, scope: CoroutineScope ) : ActorBasicFsm( name, scope){
 	
	override fun getInitialState() : String{
		return "s0"
	}
		
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						delay(1000) 
						println("	sentinel | STARTS")
						  
						//CREATE A PIPE for the sonar-data stream
						//WARNING: use myself to denote the sentinel actor, since this refers to the state
						
						val logger   = itunibo.robot.rx.Logger("logger")
						val filter   = itunibo.robot.rx.sonaractorfilter("filter", myself)  //generates obstacle
						val forradar = itunibo.robot.rx.sonarforradar("forradar", myself)  //generates polar
						//itunibo.robot.robotSupport.subscribe( logger ).subscribe( filter )
						itunibo.robot.robotSupport.subscribe( filter ).subscribe( forradar ) 
					}
					 transition( edgeName="goto",targetState="work", cond=doswitch() )
				}	 
				state("work") { //this:State
					action { //it:State
					}
					 transition(edgeName="t03",targetState="showTheMsg",cond=whenEvent("sonarRobot"))
					transition(edgeName="t04",targetState="handleAlarm",cond=whenEvent("alarm"))
				}	 
				state("showTheMsg") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
					}
					 transition( edgeName="goto",targetState="work", cond=doswitch() )
				}	 
				state("handleObstacle") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
						println("	sentinel | handleObstacle: emits alarm(obstacle) ")
						emit("alarm", "alarm(obstacle)" ) 
					}
					 transition( edgeName="goto",targetState="s0", cond=doswitch() )
				}	 
				state("handleAlarm") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
						println("	sentinel | handleAlarm ")
					}
					 transition(edgeName="t05",targetState="handleAlarm",cond=whenEvent("alarm"))
				}	 
			}
		}
}
