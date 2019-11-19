package kotlindemo

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis
import kotlinx.coroutines.delay
import kotlinx.coroutines.async
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.channels.Channel
import kotlin.coroutines.experimental.Continuation
import kotlin.coroutines.experimental.suspendCoroutine
 
val name = "Bob"
val str = "Hello $name"
		
fun curThread() : String { 
	return "thread=${Thread.currentThread().name} / nthreads=${Thread.activeCount()}" 
}

fun runBlockThread(){	    
 run { //Calls a function block; returns its result
    println("Out start: ${curThread()}")
    Thread.sleep(1500) 
    println("Out ended: ${curThread()}")
 }
}
	
@kotlinx.coroutines.ObsoleteCoroutinesApi
@kotlinx.coroutines.ExperimentalCoroutinesApi
fun main(){
    val cpus = Runtime.getRuntime().availableProcessors();
    println("BEGINS CPU=$cpus ${curThread()}")
	 
	GlobalScope.launch{ runBlockThread() }
	println("BYE")
	
	Thread.sleep(1510) //Required to see the output
	
    println("ENDS ${curThread()}")
}