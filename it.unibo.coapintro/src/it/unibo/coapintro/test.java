package it.unibo.coapintro;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;

public class test {
private static CoapClient client = new CoapClient();	
  	
 	
 	public static void produce(  ) {
 		//String uriStr = "coap://localhost:5683/robot/sonar";
  		String uriStr = "coap://coap.thethings.io/v2/things/";
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
