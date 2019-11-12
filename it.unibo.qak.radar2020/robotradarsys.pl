%====================================================================================
% robotradarsys description   
%====================================================================================
mqttBroker("localhost", "1883").
context(ctxradargui, "localhost",  "MQTT", "0").
 qactor( radargui, ctxradargui, "it.unibo.radargui.Radargui").
  qactor( sonarsimulator, ctxradargui, "it.unibo.sonarsimulator.Sonarsimulator").
