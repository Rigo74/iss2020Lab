package itunibo.robotMbot

import it.unibo.kactor.ActorBasicFsm
import kotlinx.coroutines.launch
import java.io.BufferedReader
import it.unibo.kactor.ActorBasic
import it.unibo.kactor.MsgUtil
import it.unibo.kactor.ApplMessage
import alice.tuprolog.Term
import alice.tuprolog.Struct


class  robotDataSourceArduino( name : String, val owner : ActorBasic , val conn : SerialPortConnSupport,
							   val filter: ActorBasic?  
		  ) : ActorBasic(name, owner.scope){
		
	init{
		scope.launch{  autoMsg("start","start(1)") }
		//println("   	%%% $name |  starts conn=$conn")	 
	}

	override suspend fun actorBody(msg: ApplMessage) {
        //println("   	%%% $name |  handles msg= $msg  ")
		val vStr  = (Term.createTerm( msg.msgContent()) as Struct).getArg(0).toString()
		//println("   	%%% $name |  handles msg= $msg  vStr=$vStr")
		elabData( vStr )
	}

	suspend fun elabData(data : String ){
 		var obstacleEventEmitted  = false
                while (true) {
 						try {
							var curDataFromArduino = conn.receiveALine()
							//globalTimer.startTimer()  //TIMER ....
 	 						//println("   	%%% $name | getDataFromArduino received: $curDataFromArduino"    )
 							var v = curDataFromArduino.toDouble() 
							//handle too fast change ?? NOT HERE
  							var dataSonar = v.toInt();													
 							if( dataSonar < 350 ){ //WARNING: it generates  many events
 								//println("   	%%% $name | mbotSupport sonar: ${ dataSonar }"   );						
 								val event = MsgUtil.buildEvent( name,"sonarRobot","sonar( $dataSonar )")								
 								owner.emit(  event )
								if( filter != null ) owner.emitLocalStreamEvent( event )
							}
						    //JUNE 2019 (streaming)
							//owner.scope.launch{ owner.emitLocalStreamEvent(event) }
 
							//Oct2019 : emit the event obstacle
							
							if( dataSonar < 7  ){ //WARNING: it generates  many events
								if( ! obstacleEventEmitted ){ //Math.abs(dataSonar - oldSonarValue) > 3
									//println("   	%%% $name | mbotSupport sonar: ${ dataSonar } r"   );
									val obstacle = MsgUtil.buildEvent( name,"obstacle","obstacle($dataSonar)")
	 								owner.emit(  obstacle )
									obstacleEventEmitted = true
								}												
							}else obstacleEventEmitted = false	
						} catch ( e : Exception) {
 							println("   	%%% $name | getDataFromArduino | ERROR $e   ")
                    }
				}
	}
	
 
}