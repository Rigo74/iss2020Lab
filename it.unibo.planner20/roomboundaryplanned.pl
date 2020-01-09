%====================================================================================
% roomboundaryplanned description   
%====================================================================================
mqttBroker("localhost", "1883").
context(ctxboundaryplanned, "localhost",  "MQTT", "8068").
context(ctxsmartrobot, "127.0.0.1",  "MQTT", "8020").
 qactor( smartrobot, ctxsmartrobot, "external").
  qactor( roomboudaryexplorer, ctxboundaryplanned, "it.unibo.roomboudaryexplorer.Roomboudaryexplorer").
