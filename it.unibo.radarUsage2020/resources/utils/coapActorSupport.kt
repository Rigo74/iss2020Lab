package utils

import it.unibo.kactor.ActorBasic
import org.eclipse.californium.core.CoapClient
import org.eclipse.californium.core.CoapResponse
import org.eclipse.californium.core.coap.MediaTypeRegistry
import org.eclipse.californium.core.CoapHandler
import org.eclipse.californium.core.CoapObserveRelation
import kotlinx.coroutines.launch
import it.unibo.kactor.MsgUtil
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import it.unibo.kactor.ApplMessage

class MyHandler( val actor : ActorBasic ) : CoapHandler{
	/*
 		response.getResponseText() = msg(sonar,event,radarusagecoap,none,sonar(40),8)
 		it can be emitted as an Event or transformed into a Dispatch
 		REMEMBER that an event emitted by an actor A is NOT propagated to A
 	*/
	override fun onLoad( response : CoapResponse ) {
		val data = response.getResponseText()
		val m    = ApplMessage( data )
		val msg  = MsgUtil.buildDispatch(actor.name, m.msgId(), m.msgContent(), actor.name) //self-msg
		//println(" ... $m")
		actor.scope.launch{ actor.emit( m ); MsgUtil.sendMsg( msg, actor ) }
	}
	
	override fun onError(){
		
	}
}

object coapActorSupport{
lateinit var client : CoapClient
var  relation : CoapObserveRelation? = null
				
	fun connect(  actor : ActorBasic, url : String ){
		client = CoapClient( url )
	}
		
	fun readResource( actor : ActorBasic, map : MutableMap<String,String>, key : String  ){
		val respGet : CoapResponse = client.get( )
		val answer = respGet.getResponseText()
		map.put(key, answer )
	}
	
	fun updateResource(  actor : ActorBasic, data : String ){
		 val m = MsgUtil.buildEvent( actor.name, "sonar", "sonar($data)")
		 val resp : CoapResponse = client.put(m.toString(), MediaTypeRegistry.TEXT_PLAIN)
		 //actor.scope.launch{ actor.emit( "", "") }
	}
	
	fun observeResource( actor : ActorBasic ){
		relation = client!!.observe( MyHandler(actor) )
	}
	
	fun removeObserve( actor : ActorBasic ){
		relation = null
	}
}