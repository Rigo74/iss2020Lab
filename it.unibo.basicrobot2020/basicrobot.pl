%====================================================================================
% basicrobot description   
%====================================================================================
mqttBroker("192.168.1.14", "1883").
context(ctxradargui, "localhost",  "MQTT", "0").
context(ctxbasicrobot, "localhost",  "MQTT", "0").
 qactor( robotadapter, ctxbasicrobot, "itunibo.robot.robotAdapterQaStream").
  qactor( basicrobot, ctxbasicrobot, "it.unibo.basicrobot.Basicrobot").
  qactor( sentinel, ctxbasicrobot, "it.unibo.sentinel.Sentinel").
