%====================================================================================
% robotradarsys description   
%====================================================================================
mqttBroker("localhost", "1883").
context(ctxradargui, "localhost",  "MQTT", "8038").
 qactor( radargui, ctxradargui, "it.unibo.radargui.Radargui").
  qactor( sonarsimulator, ctxradargui, "it.unibo.sonarsimulator.Sonarsimulator").
