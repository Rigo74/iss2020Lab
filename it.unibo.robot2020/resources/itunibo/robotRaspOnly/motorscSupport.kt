package itunibo.robotRaspOnly

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import it.unibo.kactor.ActorBasic
import kotlinx.coroutines.delay
import java.io.BufferedOutputStream
import java.io.OutputStreamWriter

object motorscSupport {
	lateinit var writer : OutputStreamWriter

	fun create( actor : ActorBasic, todo : String="" ){
		println("motorscSupport | CREATING")		
		GlobalScope.launch{
			val p = Runtime.getRuntime().exec("sudo ./Motors")			
			println("motorscSupport | CREATED  $p")
			writer = OutputStreamWriter(  p.getOutputStream()  )
		}
		println("motorscSupport | CREATED with writer=$writer")
	}

	fun write( actor : ActorBasic, msg : String="" ){
		writer.write( msg+"\n" )
		writer.flush()
	}

}