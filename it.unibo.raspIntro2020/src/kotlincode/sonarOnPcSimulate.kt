package kotlincode

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.actor 
import java.io.BufferedReader
import java.io.InputStreamReader
import javacode.ResourceSonarSupport
import it.unibo.kactor.ApplMessage
import it.unibo.kactor.MsgUtil


class sonarOnPcSimulate( val name : String, val scope: CoroutineScope = GlobalScope )  {
	var resourceSupport : ResourceSonarSupport	 

	val actor = scope.actor<ApplMessage>{
 			    for (msg in channel) { // iterate over incoming messages
			        when ( msg.msgId()  ) {
						"start" -> scope.launch{ readInputData() }
			            else -> throw Exception( "unknown" )
			        }
			    }
			}
	

	init{
		resourceSupport = ResourceSonarSupport( "coap://localhost:5683" )
	}

    suspend fun readInputData(){
        var dataCounter = 1
        while( true ){
 			 var data = readLine()
             println("data ${dataCounter++} = $data " )
             val m = MsgUtil.buildEvent(name, "sonar", "sonar($dataCounter, $data)")
             println("EMIT to CoAP: $m"  )
             resourceSupport.updateTheResource( m.toString() )
        }
    }

}

	
fun main() = runBlocking {

	val a = sonarOnPcSimulate("sonaronpcsimulated")
	val m = MsgUtil.buildEvent("", "start", "start(1)")
    a.actor.send( m )
	kotlinx.coroutines.delay( 600000 )
}