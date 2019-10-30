package it.unibo.coapintro;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;

public class ResourceRobotClient {
	public static void main(String[] args) {
 		CoapClient client = new CoapClient("coap://localhost:5683/robot");

		System.out.println("SYNCHRONOUS GET");		
 		String content1 = client.get().getResponseText();
		System.out.println("RESPONSE 1: " + content1);
 
		System.out.println("SYNCHRONOUS PUT");		
		CoapResponse resp2 = client.put("inc", MediaTypeRegistry.TEXT_PLAIN);
		System.out.println("RESPONSE 2 CODE: " + resp2.getCode());

 		System.out.println("ASYNCHRONOUS GET");		
		client.get( new CoapHandler() {
			@Override public void onLoad(CoapResponse response) {
				String content = response.getResponseText();
				System.out.println("RESPONSE 3: " + content);
			}			
			@Override public void onError() {
				System.err.println("FAILED");
			}
		});
		
		//Give time to acquire asynch GET
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
 			e.printStackTrace();
		}
		
  	}

}
