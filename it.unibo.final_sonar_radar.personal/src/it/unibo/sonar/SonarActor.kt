package it.unibo.sonar

import it.unibo.kactor.ActorBasicFsm
import kotlinx.coroutines.CoroutineScope
import alice.tuprolog.Term
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.Random

class SonarActor(name: String, scope: CoroutineScope) : ActorBasicFsm(name, scope) {
	
	val EXEC_CMD: String = "java -cp bin/default it.unibo.sonar.DataGenerator"
	var sonarData: BufferedReader? = null;
	var distance: String = ""
	var theta: Int = 0

	override fun getInitialState(): String {
		return "init"
	}

	override fun getBody(): (ActorBasicFsm.() -> Unit) {
		return {
			state("init") {
				action {
					val process: Process = Runtime.getRuntime().exec(EXEC_CMD)
					sonarData = BufferedReader(InputStreamReader(process.getInputStream()))
				}
				transition(edgeName = "goto", targetState = "work", cond = doswitch())
			}
			state("work") {
				action {
					Thread.sleep(2000)
					distance = sonarData!!.readLine()
					theta = Random().nextInt(360)
				}
				transition(edgeName = "data obtained", targetState = "sendData", cond = doswitch())
			}
			state("sendData") {
				action {
					forward("sonar", "sonar(${distance},${theta})", "robot")
				}
				transition(edgeName = "data sent", targetState = "work", cond = doswitch())
			}
		}
	}
}
