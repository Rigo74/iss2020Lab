/* Generated by AN DISI Unibo */ 
package it.unibo.onestpcaller

import it.unibo.kactor.*
import alice.tuprolog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
	
class Onestpcaller ( name: String, scope: CoroutineScope ) : ActorBasicFsm( name, scope){
 	
	override fun getInitialState() : String{
		return "s0"
	}
		
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						delay(500) 
						println("onestpcaller STARTED")
						forward("onestep", "onestep(1000)" ,"onestepahead" ) 
					}
				}	 
			}
		}
}
