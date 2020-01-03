package it.unibo.coapintro;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;

public class testWebiopi {
private static CoapClient client = new CoapClient();	
  	
 	
 	public static void produce(  ) {
 		String uriStr = "coap://192.168.1.7:5683/GPIO/17/value";
  		client.setURI( uriStr );
 		String arg = "{ \"activationCode\"  :  \"ACTIVATION_CODE\" }";
 		System.out.println("USING: " + uriStr);
 		CoapResponse respPut = client.put(arg, MediaTypeRegistry.TEXT_PLAIN);
 		System.out.println("PUT RESPONSE CODE=" + respPut.getResponseText() );
	}
	
	
 	
	public static void main(String[] args) {
		produce();
   	}

}
