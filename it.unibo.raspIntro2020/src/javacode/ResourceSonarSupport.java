package javacode;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;

public class ResourceSonarSupport {
private CoapClient client;
	  

	public ResourceSonarSupport( String address, String path) { //"coap://localhost:5683/" + path
		String url = address + "/" + path;
		client = new CoapClient( url );
		System.out.println("ResourceSonarClient | STARTS for: " +  url);
	}
	public ResourceSonarSupport( String address ) {  
		this(address, ResourceSonar.path);
	}
	
	public void updateTheResource( String msg ) {
		CoapResponse resp = client.put(msg, MediaTypeRegistry.TEXT_PLAIN);
		System.out.println("ResourceSonarClient | RESPONSE CODE: " + resp.getCode());		
	}
}
