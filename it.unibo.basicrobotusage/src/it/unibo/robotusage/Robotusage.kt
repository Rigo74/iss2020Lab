/* Generated by AN DISI Unibo */ 
package it.unibo.robotusage

import it.unibo.kactor.*
import alice.tuprolog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
	
class Robotusage ( name: String, scope: CoroutineScope ) : ActorBasicFsm( name, scope){
 	
	override fun getInitialState() : String{
		return "s0"
	}
		
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						println("robotusage start")
					}
					 transition( edgeName="goto",targetState="work", cond=doswitch() )
				}	 
				state("work") { //this:State
					action { //it:State
						delay(1000) 
						forward("cmd", "cmd(w)" ,"basicrobot" ) 
						delay(1000) 
						forward("cmd", "cmd(h)" ,"basicrobot" ) 
						delay(1000) 
						forward("cmd", "cmd(a)" ,"basicrobot" ) 
						delay(1000) 
						forward("cmd", "cmd(h)" ,"basicrobot" ) 
						delay(1000) 
						forward("cmd", "cmd(d)" ,"basicrobot" ) 
						delay(1000) 
						forward("cmd", "cmd(h)" ,"basicrobot" ) 
						delay(1000) 
						forward("cmd", "cmd(s)" ,"basicrobot" ) 
						delay(1000) 
						forward("cmd", "cmd(h)" ,"basicrobot" ) 
					}
				}	 
			}
		}
}
