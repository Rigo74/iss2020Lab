package it.unibo.coapintro;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapResponse;
 
public class ResourceClientReader {
	
	public static void delay(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
 			e.printStackTrace();
		}		
	}
	
	
	public static void doGet( CoapClient client ) {
 		CoapResponse respGet = client.get();
 			System.out.println("SYNCH GET  RESPONSE CODE= " + respGet.getCode()  + 
					" content:" + respGet.getResponseText() );
//ASYNCHRONOUS 
		client.get( new CoapHandler() {
			@Override public void onLoad(CoapResponse resp) {
				System.out.println("ASYNCH GET RESPONSE CODE= " + resp.getCode()  + 
 					" content:" + resp.getResponseText() );
			}			
			@Override public void onError() {
				System.err.println("FAILED");
			}
		});		
		//Give time to acquire asynch GET
		delay( 500 );		
	}
	
	public static void usingRoot(  CoapClient client ) {
  		String uriStr = "coap://localhost:5683/robot";
 		System.out.println("USING: " + uriStr);
 		client.setURI( uriStr );
 		doGet( client );
 	}
	
	public static void usingSonar( CoapClient client ) {
 		String uriStr = "coap://localhost:5683/robot/sonar";
 		client.setURI( uriStr );
 		System.out.println("USING: " + uriStr);
 		doGet( client );
	}
	
	public static void main(String[] args) {
 		CoapClient client = new CoapClient();	
 		usingRoot( client );
 		System.out.println("-------------------------");	
 		usingSonar( client );
  	}
	//coapCli: coap get coap://coap.localhost/robot
	//coapCli: coap get coap://localhost:5683/robot
}
