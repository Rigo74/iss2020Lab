%====================================================================================
% radargui description   
%====================================================================================
mqttBroker("localhost", "1883").
context(ctxradargui, "localhost",  "TCP", "8038").
 qactor( radargui, ctxradargui, "it.unibo.radargui.Radargui").
  qactor( demo, ctxradargui, "it.unibo.demo.Demo").
