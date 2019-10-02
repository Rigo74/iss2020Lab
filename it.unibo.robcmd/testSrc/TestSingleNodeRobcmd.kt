package it.unibo.eclipse.qak.robotMinds19

import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlinx.coroutines.launch
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay

class TestSingleNodeRobcmd {
 	
	@Before
	fun systemSetUp() {
  	 		GlobalScope.launch{
 			    println(" %%%%%%% TestRobcmd starts   ")
				it.unibo.ctxRobcmd.main()
 			}
 			delay(4000)		//give the time to execute
 	}
 
	@After
	fun terminate() {
		println(" %%%%%%% TestRobcmd terminate ")
	}
 
	@Test
	fun moveTest() {
		val finalRobotState = itunibo.robcmd.logSimple.getLastLine()
		println(" %%%%%%% TestRobcmd  moveTest ${ finalRobotState }")
 		assertTrue("", finalRobotState==itunibo.robcmd.robotState.stateRepStopped  )		
 	}

	fun delay( time : Long ){
		Thread.sleep( time )
	}
}