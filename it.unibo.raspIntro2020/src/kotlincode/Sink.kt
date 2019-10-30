package kotlincode

import it.unibo.kactor.*
import kotlinx.coroutines.CoroutineScope
import javacode.ResourceSonarSupport

class Sink(name:String, scope: CoroutineScope) : ActorBasic( name, scope ){
var resourceSupport : ResourceSonarSupport	 
	
	init{
		resourceSupport = ResourceSonarSupport("coap://192.168.1.8:5683", "sonardata")
	}
    override suspend fun actorBody(msg : ApplMessage){
        println("   $name |  receives msg= $msg ")
		//PUT to a CoAP resource
		resourceSupport.updateTheResource( msg.toString() )
    }
}