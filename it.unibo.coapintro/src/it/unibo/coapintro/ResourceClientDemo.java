package it.unibo.coapintro;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;

public class ResourceClientDemo {
	
	
	public static void delay(int time) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
 			e.printStackTrace();
		}		
	}
	
	public static void usingRoot( CoapClient client ) {
 		String uriStr = "coap://localhost:5683/robot";
 		System.out.println("USING: " + uriStr);
 		client.setURI( uriStr );
 		CoapResponse respGet = client.get();
		System.out.println("GET RESPONSE CODE= " + respGet.getCode()  + " content:" + respGet.getResponseText() );

		CoapResponse respPut = client.put("inc", MediaTypeRegistry.TEXT_PLAIN);
		System.out.println("PUT inc RESPONSE CODE=" + respPut.getCode());

		client.get( new CoapHandler() {
			@Override public void onLoad(CoapResponse response) {
 				System.out.println("ASYNCHRONOUS GET RESPONSE CODE= " + response.getCode()  + " content:" + response.getResponseText() );
			}			
			@Override public void onError() {
				System.err.println("FAILED");
			}
		});		
		//Give time to acquire asynch GET
		delay( 1000 );
	}
	
	public static void usingSonar( CoapClient client ) {
 		String uriStr = "coap://localhost:5683/robot/sonar";
 		client.setURI( uriStr );
 		System.out.println("USING: " + uriStr);
 		CoapResponse respGet = client.get();
		System.out.println("GET RESPONSE CODE= " + respGet.getCode()  + " content:" + respGet.getResponseText() );

		CoapResponse respPut = client.put("inc", MediaTypeRegistry.TEXT_PLAIN);
		System.out.println("PUT inc RESPONSE CODE=" + respPut.getCode());

		client.get( new CoapHandler() {
			@Override public void onLoad(CoapResponse response) {
 				System.out.println("ASYNCHRONOUS GET RESPONSE CODE= " + response.getCode()  + " content:" + response.getResponseText() );
			}			
			@Override public void onError() {
				System.err.println("FAILED");
			}
		});		
		//Give time to acquire asynch GET
		delay( 1000 );
	}
	
	public static void main(String[] args) {
 		CoapClient client = new CoapClient();	
 		usingRoot( client );
 		System.out.println("-------------------------");	
 		usingSonar( client );
  	}

}
