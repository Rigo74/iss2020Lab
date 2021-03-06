package it.unibo.kactor

import alice.tuprolog.*
import kotlinx.coroutines.*
import java.util.NoSuchElementException

/*
================================================================
 STATE
================================================================
 */
class State(val stateName : String, val scope: CoroutineScope ) {
    private val edgeList          = mutableListOf<Transition>()
    private val stateEnterAction  = mutableListOf< suspend (State) -> Unit>()
    private val myself : State    = this

    fun transition(edgeName: String, targetState: String, cond: Transition.() -> Unit) {
        val trans = Transition(edgeName, targetState)
        //println("      trans $name $targetState")
        trans.cond() //set eventHandler (given by user) See fireIf
        edgeList.add(trans)
    }
    //Add an action which will be called when the state is entered
    fun action(  a:  suspend (State) -> Unit) {
        //println("State $stateName    | add action  $a")
        stateEnterAction.add( a )
    }
    /*
    fun addAction (action:  suspend (State) -> Unit) {
        stateEnterAction.add(action)
    }
    */
    suspend fun enterState() {
        val myself = this
        scope.launch {
            //println(" --- | State $stateName    | enterState ${myself.stateName} ")
            stateEnterAction.get(0)( myself )
        }.join()
        //println(" --- | State $stateName    | enterState DONE ")
        //stateEnterAction.forEach {  it(this) }
    }

    //Get the appropriate Edge for the Message
    fun getTransitionForMessage(msg: ApplMessage): Transition? {
        //println("State $name       | getTransitionForMessage  $msg  list=${edgeList.size} ")
        val first = edgeList.firstOrNull { it.canHandleMessage(msg) }
        return first
    }
}

/*
================================================================
 EDGE
================================================================
 */
class Transition(val edgeName: String, val targetState: String) {

    lateinit var edgeEventHandler: (String) -> Boolean  //MsgId
    private val actionList = mutableListOf<(Transition) -> Unit>()

    fun action(action: (Transition) -> Unit) { //MEALY?
        //println("Transition  | add ACTION:  $action")
        actionList.add(action)
    }

    //Invoke when you go down the transition to another state
    fun enterTransition(retrieveState: (String) -> State): State {
        //println("Transition  | enterEdge  retrieveState: ${retrieveState} actionList=$actionList")
        actionList.forEach { it(this) }         //MEALY?
        return retrieveState(targetState)
    }

    fun canHandleMessage(msg: ApplMessage): Boolean {
        //println("Transition  | canHandleMessage: ${msg}  ${msg is Message.Event}" )
        return edgeEventHandler(msg.msgId())
    }
}

/*
================================================================
 ActorBasicFsm
================================================================
 */
abstract class ActorBasicFsm(  qafsmname:  String,
                      val fsmscope: CoroutineScope = GlobalScope,
                      confined :    Boolean = false,
                      ioBound :     Boolean = false,
                      channelSize : Int = 50
                    ): ActorBasic(  qafsmname, fsmscope, confined, ioBound, channelSize ) {

    val autoStartMsg = MsgUtil.buildDispatch(name, "autoStartSysMsg", "start", name)
    private var isStarted = false
    protected var myself : ActorBasicFsm
    protected lateinit var currentState: State
    protected var currentMsg       = NoMsg
    protected var msgToReply       = NoMsg
    lateinit protected var mybody: ActorBasicFsm.() -> Unit
    var stateTimer : TimerActor?   = null

    private val stateList = mutableListOf<State>()
    private val msgQueueStore = mutableListOf<ApplMessage>()

    //================================== STRUCTURAL =======================================
    fun state(stateName: String, build: State.() -> Unit) {
        val state = State(stateName, fsmscope)
        state.build()
        stateList.add(state)
    }

    private fun getStateByName(name: String): State {
        return stateList.firstOrNull { it.stateName == name }
            ?: throw NoSuchElementException(name)
    }
    //===========================================================================================

    init {
        //println("ActorBasicFsm INIT")private val
        myself  = this
        setBody(getBody(), getInitialState())
    }

    abstract fun getBody(): (ActorBasicFsm.() -> Unit)
    abstract fun getInitialState(): String

    fun setBody( buildbody: ActorBasicFsm.() -> Unit, initialStateName: String ) {
        buildbody()            //Build the structural part
        currentState = getStateByName(initialStateName)
        //println("ActorBasicFsm $name |  initialize currentState=${currentState.stateName}")
        scope.launch { autoMsg(autoStartMsg) }  //auto-start
    }

    override suspend fun actorBody(msg: ApplMessage) {
        //println(" --- | ActorBasicFsm $name | msg=$msg")
        if (msg.msgId() == autoStartMsg.msgId() && !isStarted) {
            //scope.launch{ fsmwork() } //The actot must continue to receive msgs
            fsmStartWork()
            //println("ActorBasicFsm $name | BACK TO MAIN ACTOR AFTER INIT")
        } else {
            fsmwork(msg)
            //println("ActorBasicFsm $name | BACK TO MAIN ACTOR")
        }
    }

    suspend fun fsmStartWork() {
        isStarted = true
        //println("ActorBasicFsm $name | fsmStartWork in STATE ${currentState.stateName}")
        currentState.enterState()
        checkDoEmptyMove()
    }

    suspend fun fsmwork(msg: ApplMessage) {
        //sysUtil.traceprintln("$tt ActorBasicFsm $name | fsmwork in ${currentState.stateName} $msg")
        var nextState = checkTransition(msg)
        var b         = hanldeCurrentMessage(msg, nextState)
        while (b) { //handle previous messages
            currentState.enterState()
            checkDoEmptyMove()
            val nextState = lookAtMsgQueueStore()
            b = hanldeCurrentMessage(currentMsg, nextState, memo = false)
        }
    }

    fun hanldeCurrentMessage(msg: ApplMessage, nextState: State?, memo: Boolean = true): Boolean {
        //sysUtil.traceprintln("$tt ActorBasicFsm $name | hanldeCurrentMessage in ${currentState.stateName} msg=${msg.msgId()}")
        if (nextState is State) {
            currentMsg   = msg
            if( currentMsg.isRequest() ){ requestMap.put(currentMsg.msgId(), currentMsg) }  //Request
            var msgBody = currentMsg.msgContent()
            val endTheTimer = currentMsg.msgId() != "local_noMsg" &&
                            ( ! msgBody.startsWith("local_tout_")
                                    ||
                                //msgBody.startsWith("local_tout_") &&
                                    ( msgBody.contains(currentState.stateName) &&
                                      msgBody.contains(this.name) )
                            )
            currentState = nextState

            //sysUtil.traceprintln("               %%% ActorBasicFsm $name | hanldeCurrentMessage currentState= ${currentState.stateName}  ")
            //sysUtil.traceprintln("               %%% ActorBasicFsm $name | hanldeCurrentMessage currentMsg= $currentMsg  ")
            /*
            if( currentMsg.msgId() != "local_noMsg" &&
                ! currentMsg.msgContent().startsWith("local_tout_")  &&
                currentMsg.msgContent().contains(currentState.stateName)
                && (stateTimer !== null) ) {
            */
            if( endTheTimer && (stateTimer !== null) ){
                stateTimer!!.endTimer() //terminate TimerActor
                //stateTimer = null
            }

            return true
        } else { //EXCLUDE EVENTS FROM msgQueueStore
            if (!memo) return false
            if (!(msg.isEvent())) {
                msgQueueStore.add(msg)
                println("ActorBasicFsm $name |  added $msg in msgQueueStore")
            }
            //else sysUtil.traceprintln("$tt ActorBasicFsm $name | DISCARDING THE EVENT: ${msg.msgId()}")
            return false
        }
    }

    suspend fun checkDoEmptyMove() {
        var nextState = checkTransition(NoMsg) //EMPTY MOVE
        while (nextState is State) {
            currentMsg = NoMsg
            currentState = nextState
            currentState.enterState()
            nextState = checkTransition(NoMsg) //EMPTY MOVE
        }
    }

    private fun lookAtMsgQueueStore(): State? {
        //sysUtil.traceprintln("$tt ActorBasicFsm $name | lookAtMsgQueueStore :${msgQueueStore.size}")
        msgQueueStore.forEach {
            val state = checkTransition(it)
            if (state is State) {
                currentMsg = msgQueueStore.get( msgQueueStore.indexOf(it) )
                //sysUtil.traceprintln("               %%% ActorBasicFsm $name | lookAtMsgQueueStore FOUND $currentMsg")
                msgQueueStore.remove(it)
                return state
            }
        }
        return null
    }

    private fun checkTransition(msg: ApplMessage): State? {
        val trans = currentState.getTransitionForMessage(msg)
        //sysUtil.traceprintln("$tt ActorBasicFsm $name | checkTransition ENTRY $msg , currentState=${currentState.stateName} ")
        return if (trans != null) {
           trans.enterTransition { getStateByName(it) }
        } else {
            //println("sysUtil.traceprintln("               %%% ActorBasicFsm $name | checkTransition in ${currentState.stateName} NO next State for $msg !!!")
            null
        }
    }

    fun doswitch(): Transition.() -> Unit {
        return { edgeEventHandler = { true } }
    }
    fun doswitchGuarded( guard:()->Boolean ): Transition.() -> Unit {
        return { edgeEventHandler = { guard() } }
    }

    /*
    fun fireIf(cond: (ApplMessage) -> Boolean): Transition.() -> Unit {
        return {
            edgeEventHandler = cond }
    }
    */
    fun whenEvent(evName: String): Transition.() -> Unit {
        return {
            edgeEventHandler = {
                //println("whenEvent $it - $evName");
                it == evName } //it.isEvent() && it.msgId() == evName }
        }
    }
    fun whenEventGuarded(evName: String, guard:()->Boolean ): Transition.() -> Unit {
        return {
            edgeEventHandler = {
                //println("whenEventGuarded $it - $evName");
                it == evName && guard()  } //it.isEvent() && it.msgId() == evName }
        }
    }

    fun whenDispatch(msgName: String): Transition.() -> Unit {
            return {
                edgeEventHandler = {
                    //println("ActorBasicFsm $name | ${currentState.stateName} whenDispatch $it  $msgName")
                    it == msgName }  //it.isDispatch() && it.msgId() == msgName }
            }
    }
    fun whenDispatchGuarded(msgName: String, guard:()->Boolean ): Transition.() -> Unit {
        return {
            edgeEventHandler = {
                //println("whenDispatchGuarded $it - $evName");
                it == msgName && guard() } //it.isDispatch() && it.msgId() == msgName }
        }
    }

    fun whenRequest(msgName: String): Transition.() -> Unit {
        //sysUtil.traceprintln("$tt ActorBasicFsm $name | whenRequest $currentMsg" )
        return {
            edgeEventHandler = {
                //sysUtil.traceprintln("$tt ActorBasicFsm $name | ${currentState.stateName} whenRequest $it  $msgName")
                it == msgName   }  //it.isRequest() && it.msgId() == msgName }
        }
    }
    fun whenRequestGuarded(msgName: String, guard:()->Boolean): Transition.() -> Unit {
        return {
            edgeEventHandler = {
                //sysUtil.traceprintln("$tt ActorBasicFsm $name | ${currentState.stateName} whenRequestGuarded $it, $msgName")
                it == msgName   && guard() }  //it.isRequest() && it.msgId() == msgName }
        }
    }
    fun whenReply(msgName: String): Transition.() -> Unit {
        return {
            edgeEventHandler = {
                //sysUtil.traceprintln("$tt ActorBasicFsm $name | ${currentState.stateName} whenReply $it  $msgName")
                it == msgName   }  //it.isReply() && it.msgId() == msgName }
        }
    }
    fun whenReplyGuarded(msgName: String, guard:()->Boolean): Transition.() -> Unit {
        return {
            edgeEventHandler = {
                //sysUtil.traceprintln("$tt ActorBasicFsm $name | ${currentState.stateName} whenReplyGuarded $it  $msgName")
                it == msgName  && guard() }  //it.isReply() && it.msgId() == msgName }
        }
    }

    fun whenTimeout( timerEventName : String ): Transition.() -> Unit {
                return {
                    edgeEventHandler = {
                        //println("whenTimeoutt $it")
                        it == timerEventName
                    } //it.isEvent() && it.msgId() == timerEventName }
                }
    }

/*
    fun ignoreCurrentCaller() {
        currentMsg = NoMsg
    }
*/

    fun storeCurrentMessageForReply() {
        msgToReply = currentMsg
        //println(getName() + " 			msgToReply:" +msgToReply );
    }


    suspend fun replyToCaller(msgId: String, msg: String) {
        //sysUtil.traceprintln("$tt ActorBasicFsm $name | replyToCaller msgToReply=" + msgToReply);
        val caller = msgToReply.msgSender()
        //println( " replyToCaller  $msgId : $msg  to $caller" );
        forward(msgId, msg, caller)
    }
 /*
    suspend fun replyToCaller(msgId: String, msg: String) {
        if (currentMsg != null) {
            val caller = currentMsg.msgSender()
            println( " replyToCaller  $msgId : $msg  to $caller" );
            forward(msgId, caller, msg)
        }
        else if (msgToReply != NoMsg) { //Aug4
            println( " replyToCaller msgToReply=" + msgToReply);
            currentMsg = msgToReply
            replyToCaller(msgId, msg)
            currentMsg = NoMsg
        }
    }
*/
/*
UTILITIES TO HANDLE MSG CONTENT
 */
    private var msgArgList = mutableListOf<String>()

    fun checkMsgContent(template : Term, curT : Term,  content : String ) : Boolean{
        msgArgList = mutableListOf<String>()
        if( pengine.unify(curT, template ) && pengine.unify(curT, Term.createTerm(content) ) ){
            val tt   = Term.createTerm( curT.toString() )  as Struct
            val ttar = tt.arity
            for( i in 0..ttar-1 ) msgArgList.add( tt.getArg(i).toString().replace("'","") )
            return true
        }
        return false
    }
    fun  payloadArg( n : Int  ) : String{
        return msgArgList.elementAt(n)
    }

}