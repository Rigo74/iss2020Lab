package kotlincode

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.actor 
import java.io.BufferedReader
import java.io.InputStreamReader
import javacode.ResourceSonarSupport
import it.unibo.kactor.ApplMessage
import it.unibo.kactor.MsgUtil


class sonarOnRaspShow( val name : String, val scope: CoroutineScope = GlobalScope )  {

	val actor = scope.actor<ApplMessage>{
 			    for (msg in channel) { // iterate over incoming messages
			        when ( msg.msgId()  ) {
						"start" -> scope.launch{ readInputData() }
			            else -> throw Exception( "unknown" )
			        }
			    }
			}
	
	var resourceSupport : ResourceSonarSupport	 

	
	init{
		resourceSupport = ResourceSonarSupport("coap://192.168.1.8:5683", "sonardata")
	}

    suspend fun readInputData(){
        val numData = 8
        var dataCounter = 1
        val p : Process = Runtime.getRuntime().exec("sudo ./SonarAlone")  //machineExec("sudo ./SonarAlone")
        val reader = BufferedReader(InputStreamReader(p.getInputStream()))
		
        while( true ){
             var data = reader.readLine()    //blocking
             println("data ${dataCounter++} = $data " )
             if( dataCounter % numData == 0 ) { //every numData ...
                val m = MsgUtil.buildEvent(name, "sonar", "sonar($dataCounter, $data)")
                println("EMIT to CoAP: $m"  )
				resourceSupport.updateTheResource( m.toString() )
             }
        }

    }

}

	
fun main() = runBlocking {

	val a = sonarOnRaspShow("sonarOnRasp")
	val m = MsgUtil.buildEvent("", "start", "start(1)")
    a.actor.send( m )
	kotlinx.coroutines.delay( 60000 )
}