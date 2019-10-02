package itunibo.robcmd

object robotState {
	val stateRepStopped     = "robot_stopped"
	val stateRepForward     = "robot_moving_forward"
	val stateRepBackward    = "robot_moving_backward"
	val stateRepRotateLeft  = "robot_rotating_left"
	val stateRepRotateRight = "robot_rotating_right"
	
	var stateVal = stateRepStopped
	
	//fun mySetValue( v : String ){ value = v }
	
	fun robotGoingForward( ){
		stateVal = stateRepForward
		itunibo.robcmd.logSimple.saveData( stateVal )
		println("robot state updated to $stateVal")
	}
	
	fun robotMove(  move : String ){
		when (move ){
			"w" -> stateVal = stateRepForward
			"s" -> stateVal = stateRepBackward
			"a" -> stateVal = stateRepRotateLeft
			"d" -> stateVal = stateRepRotateRight
			"h" -> stateVal = stateRepStopped
		}
 		itunibo.robcmd.logSimple.saveData( stateVal )
		println("robot state updated to $stateVal")
	}
	 
}