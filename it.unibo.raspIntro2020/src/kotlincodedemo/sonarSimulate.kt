package kotlincodedemo

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.actor 
import java.io.BufferedReader
import java.io.InputStreamReader

fun curThread() : String { 
	return "thread=${Thread.currentThread().name}" 
}

data class SonarData( val distance: Int)

object sonarSimulator{
	val data = listOf(
	    SonarData( 0),  SonarData( 10), SonarData( 20), SonarData( 30), SonarData( 40),
		SonarData( 50), SonarData( 60), SonarData( 70), SonarData( 80) 
	)
	suspend fun produce(){
		var dataCounter = 1
        data.forEach{
              println("data ${dataCounter++} = $it | ${curThread()}" )
              delay(500)
        }	
	}
}



fun testRunFunction() {
	val job = GlobalScope.launch {
        println("In start : ${curThread()}")
        Thread.sleep(200)
        println("In ended : ${curThread()}")
    }
    run {
        println("Out start: ${curThread()}")
        Thread.sleep(300)
        println("Out ended: ${curThread()}")
    }
}

fun testRunFunction0() {
	run {
        println("In start : ${curThread()}")
        Thread.sleep(200)
        println("In ended : ${curThread()}")
    }
    run {	//Calls the specified function block and returns its result.
        println("Out start: ${curThread()}")
        Thread.sleep(300)
        println("Out ended: ${curThread()}")
    }
}

fun testRunFunction1() {
    runBlocking {
        // Start a coroutine
        launch(coroutineContext) {
            println("In start : ${curThread()}")
            Thread.sleep(200)
            println("In ended : ${curThread()}")
        }
        run {
            println("Out start: ${curThread()}")
            Thread.sleep(300)
            println("Out ended: ${curThread()}")
        }
    }
}

fun testRunFunction2() {
    runBlocking {
        // Start a coroutine
        launch(coroutineContext) {
            println("In start : ${curThread()}")
            delay(200)
            println("In ended : ${curThread()}")
        }
        run {
            println("Out start: ${curThread()}")
            delay(300)
            println("Out ended: ${curThread()}")
        }
    }
}

suspend fun runBlockSuspend(){
	    //Calls the specified function block and returns its result.
	    run {
            println("Out start: ${curThread()}")
            delay(1500) 
            println("Out ended: ${curThread()}")
        }
}

fun runBlockThread(){
	    //Calls the specified function block and returns its result.
	    run {
            println("Out start: ${curThread()}")
            Thread.sleep(1500) 
            println("Out ended: ${curThread()}")
        }
}

suspend fun delayedFun(){
	println("delayedFun STARTS ${curThread()}")
	kotlinx.coroutines.delay(500)
	println("delayedFun ENDS ")
}

fun test2(){
 runBlocking {
	GlobalScope.launch{ println(coroutineContext); delayedFun() } //launched in another scope
	runBlockSuspend() 
  }	
}


fun test1(){
 runBlocking {
	 launch{ println(coroutineContext); delayedFun() }  //launched in current scope
	 runBlockSuspend()
 }	
}

fun test0(){
 runBlocking {
	delayedFun()
 }
}

/*
fun main(  ) = runBlocking{ 
	//test0()
	GlobalScope.launch{ runBlockThread() }
//	Thread.sleep(1510)
//	GlobalScope.launch{ runBlockSuspend() }
//	Thread.sleep(2100)
}
 

fun main() = runBlocking {
	GlobalScope.launch{ sonarSimulator.produce() }
	    run {
            println("Out start: ${curThread()}")
            delay(2000) 
            println("Out ended: ${curThread()}")
        }

//	testRunFunction0()
//	val job = launch {
//        println("In start : ${curThread()}")
//        Thread.sleep(200)
//        println("In ended : ${curThread()}")
//    }

}
 		*/