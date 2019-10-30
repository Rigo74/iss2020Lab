package testSrc

import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlinx.coroutines.launch
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay

class TestManyNodesRobcmd {
 	
	@Before
	fun systemSetUp() {
//  	 		GlobalScope.launch{
// 			    println(" %%%%%%% Robot starts   ")
//				it.unibo.ctxRobot.main()
// 			}
// 			delay(1000)		//give the time to start
  	 		GlobalScope.launch{
 			    println(" %%%%%%% Console starts   ")
				it.unibo.ctxConsole.main()
 			}
 			delay(5000)		//give the time to start
 	}
 
	@After
	fun terminate() {
		println(" %%%%%%% TestRobcmd terminate ")
	}
 
	@Test
	fun moveTest() {
		val finalRobotState = itunibo.robcmd.robotState.stateVal
		println(" %%%%%%% TestRobcmd  moveTest ${ finalRobotState }")
		//assertTrue("", finalRobotState==itunibo.robcmd.robotState.stateRepForward )
		assertTrue("", finalRobotState==itunibo.robcmd.logSimple.getFirstLine() )
		
 	}


	fun delay( time : Long ){
		Thread.sleep( time )
	}


}