/*
 MainRadarCoapClient..kt
 */
package itunibo.coap.client

import org.eclipse.californium.core.CoapClient
import org.eclipse.californium.core.CoapResponse
import org.eclipse.californium.core.Utils
import org.eclipse.californium.core.coap.MediaTypeRegistry
import itunibo.coap.observer.AsynchListener

	private lateinit var coapClient: CoapClient
	
	fun createClient(serverAddr: String, port: Int, resourceName: String?) {
		coapClient = CoapClient("coap://$serverAddr:" + port + "/" + resourceName)
		println("Client | started")
	}

	fun synchGet() { //Synchronously send the GET message (blocking call)
		//println("Client | synchGet ")
//		CoapResponse coapResp = coapClient.advanced(request);
		val coapResp = coapClient.get()
//The "CoapResponse" message contains the response.
 		//println(Utils.prettyPrint(coapResp))
		println("Client | GET=" + coapResp.responseText)
	}

	fun put(v: String) {
		val coapResp = coapClient.put(v, MediaTypeRegistry.TEXT_PLAIN)
//The "CoapResponse" message contains the response.
		println("Client | ANSWER put to $v:")
		//println(Utils.prettyPrint(coapResp))
		println("Client | ${coapResp.responseText} code=${coapResp.getCode()}" )
	}

 	fun asynchGet() {
 		coapClient.get( AsynchListener );
	}

	fun main() {
		val hostAddr = "localhost" // "192.168.43.67 3"
 		val resourceName = "radarCoapResource"
 		createClient(hostAddr, 5683, resourceName) 
 		synchGet()
 		put("w")
   		synchGet()		 
 		put("h")
   		synchGet()		 
	}
