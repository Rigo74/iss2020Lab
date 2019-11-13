package actors

import it.unibo.kactor.*
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.delay

class RadarGui( name : String ) : ActorBasic( name ){

	init{
		radarPojo.radarSupport.setUpRadarGui()
	}
	
	override suspend fun actorBody(msg : ApplMessage){ //msg-driven
		//polar : D
		println( msg )		
		val DistanceStr = msg.msgContent()
 		radarPojo.radarSupport.update( DistanceStr, "0")
	}
}

class SonarSimulator( name : String ) : ActorBasic( name ){
	override suspend fun actorBody(msg : ApplMessage){ //msg-driven
	}
}

fun main() = runBlocking {
 	println("RadarGui | START")
	val radar = RadarGui("radarGui")
	delay( 2000 )
	MsgUtil.sendMsg( "main", "polar", "40", radar)
	delay( 2000 )
	MsgUtil.sendMsg( "main", "polar", "60", radar)
 }
