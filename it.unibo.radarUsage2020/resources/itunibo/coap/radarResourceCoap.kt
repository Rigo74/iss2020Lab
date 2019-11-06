package itunibo.coap
import org.eclipse.californium.core.coap.CoAP.ResponseCode.BAD_REQUEST
import org.eclipse.californium.core.coap.CoAP.ResponseCode.CHANGED
import org.eclipse.californium.core.CoapResource
import org.eclipse.californium.core.coap.CoAP.ResponseCode
import org.eclipse.californium.core.coap.MediaTypeRegistry
import org.eclipse.californium.core.server.resources.CoapExchange
import it.unibo.kactor.MsgUtil
import org.eclipse.californium.core.CoapServer
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import kotlinx.coroutines.GlobalScope
import org.eclipse.californium.core.coap.CoAP.Type
import kotlinx.coroutines.runBlocking

class radarResourceCoap (name : String ) : CoapResource(name) {
	
	companion object {
 		var curmodelval = "unknown"
		lateinit var resourceCoap : radarResourceCoap

 		fun create(   name: String  ){
 			val server   = CoapServer(5683);		//COAP SERVER
			resourceCoap = radarResourceCoap( name )
			server.add( resourceCoap );
			println("--------------------------------------------------")
			println("radarResourceCoap |  started Coap Server |");	
			println("--------------------------------------------------")
			server.start();
			//resourceModelSupport.setCoapResource(resourceCoap)  //Injects a reference
 		}		
	}
	
	init{ 
		println("--------------------------------------------------")
		println("radarResourceCoap |   init")
		println("--------------------------------------------------")
		setObservable(true) 				// enable observing	!!!!!!!!!!!!!!
		setObserveType(Type.CON)			// configure the notification type to CONs
		//getAttributes().setObservable();	// mark observable in the Link-Format			
	}
	
	fun updateState( modelitem : String ){
 		curmodelval = modelitem
		//println("radarResourceCoap | updateState from $curState to $curmodelval" )
		changed()	// notify all CoAp observers		
        	/*
        	 * Notifies all CoAP clients that have established an observe relation with
        	 * this resource that the state has changed by reprocessing their original
        	 * request that has established the relation. The notification is done by
        	 * the executor of this resource or on the executor of its parent or
        	 * transitively ancestor. If no ancestor defines its own executor, the
        	 * thread that has called this method performs the notification.
        	 */
	}
	 
	override fun handleGET(exchange: CoapExchange?) {
 		println("radarResourceCoap | handleGET  curmodelval=$curmodelval  "  )			
		exchange!!.respond(ResponseCode.CONTENT, curmodelval, MediaTypeRegistry.TEXT_PLAIN)
	}

	override fun handlePOST(exchange: CoapExchange?) {
 		println("radarResourceCoap | handlePOST  "  )
		handlePUT( exchange )		
	}
	override fun handlePUT(exchange: CoapExchange?) {
		try {
			val value = exchange!!.getRequestText()//new String(payload, "UTF-8");
			println("radarResourceCoap | handlePUT  value= $value"  )
 			val curState = curmodelval
 			exchange.respond(CHANGED, "radarResourceCoap has done: handlePUT FROM $curState to $value")
			curmodelval = value
 		} catch (e: Exception) {
			exchange!!.respond(BAD_REQUEST, "Invalid String")
		}
	}
	
  
}

/*
*/ 
	fun main() = runBlocking {
		radarResourceCoap.create(  "radarCoapResource" )
	}
