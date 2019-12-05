package prodCons

import kotlindemo.cpus
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

@kotlinx.coroutines.ExperimentalCoroutinesApi
@kotlinx.coroutines.ObsoleteCoroutinesApi
val simpleProducer : ReceiveChannel<Int> = GlobalScope.produce{
    for( i in 1..3 ){
        println( "simpleProducer produces $i in ${curThread()}")
        send( i )
    }
}

@kotlinx.coroutines.ExperimentalCoroutinesApi
@kotlinx.coroutines.ObsoleteCoroutinesApi
suspend fun consume(){
    val v = simpleProducer.receive()
    println( "consume receives ${v} in ${curThread()}" )
    /*
    simpleProducer.consumeEach {
        println( "consume receives $it in ${curThread()}" )
    }*/
}

@kotlinx.coroutines.ExperimentalCoroutinesApi
@kotlinx.coroutines.ObsoleteCoroutinesApi
fun main() = runBlocking{
    println("BEGINS CPU=$cpus ${kotlindemo.curThread()}")
    consume()
    println("ENDS ${kotlindemo.curThread()}")
}
