package it.unibo.coapintro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapObserveRelation;
import org.eclipse.californium.core.CoapResponse;
 

public class ResourceClientObserver {
	private CoapObserveRelation relation = null;
	private CoapClient client = null;
	
	public ResourceClientObserver(){
		client = new CoapClient("coap://localhost:5683/robot");
	}
	
	public void  observe( ) {
		relation = client.observe(
				new CoapHandler() {
					@Override public void onLoad(CoapResponse response) {
						String content = response.getResponseText();
						System.out.println("NOTIFICATION: " + content);
					}					
					@Override public void onError() {
						System.err.println("OBSERVING FAILED (press enter to exit)");
					}
				});		
	}
	
	public void waitUserEnd() {
 		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 		System.out.println("press enter to end ...");		
		try { br.readLine(); } catch (IOException e) { }		
		System.out.println("CANCELLATION");		
		relation.proactiveCancel();		
	}
	
	public static void main(String[] args) {
  		ResourceClientObserver rco = new ResourceClientObserver();
		rco.observe( );
		rco.waitUserEnd();
	}

}
