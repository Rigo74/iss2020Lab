import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.actor

val name = "Bob"
val str = "Hello $name"
/* 
fun main() = runBlocking{
	println( str )
}
 */
  
fun curThread() : String { 
	return "thread=${Thread.currentThread().name}" 
}

class CounterMsg(
  val cmd:String,
  val response:CompletableDeferred<Int>?=null){
}

 
fun CoroutineScope.counterActor() = GlobalScope.actor<CounterMsg> {
     var localCounter = 0 // actor state
    for (msg in channel) { // iterate over incoming messages
        when ( msg.cmd ) {
            "INC" -> localCounter++
            "DEC" -> localCounter--
            "GET" -> msg.response?.complete(localCounter) 
            else -> throw Exception( "unknown" )
        }    }
}
	
public inline fun measureTimeMillis(block: () -> Unit): Long {
    val start = System.currentTimeMillis()
    block()
    return System.currentTimeMillis() - start
}

suspend fun CoroutineScope.massiveRun( action: suspend () -> Unit) {
    val n=100  //number of coroutines to launch
    val k=1000 //times an action is repeated by each coroutine
    val time = measureTimeMillis {
        val jobs = List(n) {
            launch {
                repeat(k) { action() }
            }
        }
        jobs.forEach { it.join() }
    }
    println("Completed ${n * k} actions in $time ms")    
}

fun main() = runBlocking{
    val cpus = Runtime.getRuntime().availableProcessors();
    println("BEGINS with $cpus  cores")
    val counter = counterActor() // create the actor
    val initVal = CompletableDeferred<Int>()
    counter.send(CounterMsg("GET", initVal))
    println("Counter INITIAL VALUE=${initVal.await()}")
	counter.send(CounterMsg("INC") )
     GlobalScope.massiveRun {
         counter.send(CounterMsg("INC") )
     }
     val finalVal = CompletableDeferred<Int>()
     counter.send(CounterMsg("GET", finalVal))
     println("Counter FINAL   VALUE= ${finalVal.await()}")
    counter.close() // shutdown the actor
    println("ENDS ")
}
 

