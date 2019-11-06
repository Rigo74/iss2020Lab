package it.unibo.coapintro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;

public class ResourceClientProducer {
private static CoapClient client = new CoapClient();	
private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
 	
	public static void doPut( String op  ) {
 		CoapResponse respGet = client.get();
		System.out.println("CURRENT content:" + respGet.getResponseText() );

		CoapResponse respPut = client.put(op, MediaTypeRegistry.TEXT_PLAIN);
		System.out.println("PUT inc RESPONSE CODE=" + respPut.getCode());
 	}
	
	public static void usingRobot(   ) throws IOException {
 		String uriStr = "coap://localhost:5683/robot";
 		System.out.println("USING: " + uriStr);
 		client.setURI( uriStr );
		System.out.print("	inc or dec ? > ");
 		String input = br.readLine();
 		doPut( input  );
 	}
	
	public static void usingSonar(  ) {
 		String uriStr = "coap://localhost:5683/robot/sonar";
 		client.setURI( uriStr );
 		System.out.println("USING: " + uriStr);
 		doPut( "inc"  );
	}
	
	
	public static void produce() {
		
		while(true)
		try { 
			System.out.print("	robot or sonar? (x->exit)> ");
			String input = br.readLine(); 
			if( input.equals("x") ) break;
			if( input.equals("sonar")) usingSonar();
			else usingRobot();
		} catch (IOException e) { }
		
	}
	
	public static void main(String[] args) {
		produce();
// 		usingRobot( client );
// 		System.out.println("-------------------------");	
// 		usingSonar( client );
  	}

}
