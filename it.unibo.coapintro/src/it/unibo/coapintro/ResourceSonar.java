package it.unibo.coapintro;

import static org.eclipse.californium.core.coap.CoAP.ResponseCode.CHANGED;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.server.resources.CoapExchange;

public class ResourceSonar extends ResourceSimple {

	private int sonar = 0;
	
	public ResourceSonar(String name) {
		super(name);
 	}
	
	@Override
	public void handleGET(CoapExchange exchange) {
		System.out.println("ResourceSonar | handleGET from:" + exchange.getSourceAddress() + " arg:" + exchange.getRequestText()  );
		exchange.respond("ResourceSonar | sonar = " + sonar);
	}
  
	@Override
	public void handlePUT(CoapExchange exchange) {
		System.out.println("ResourceSonar | PUT arg=" + exchange.getRequestText() );
		sonar++;
 		changed();	// notify all CoAp observers		
 		exchange.respond(CHANGED);
	}

 
	public static void main(String[] args) {
		CoapServer server = new CoapServer();
		server.add( 
			new ResourceSimple("robot").add( 
				new ResourceSonar("sonar")
			) 
		);
 		server.start();
	}

}
