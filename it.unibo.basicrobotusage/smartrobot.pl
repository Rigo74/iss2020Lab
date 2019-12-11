%====================================================================================
% smartrobot description   
%====================================================================================
mqttBroker("localhost", "1883").
context(ctxsmartrobot, "localhost",  "MQTT", "8020").
context(ctxbasicrobot, "192.168.43.171",  "MQTT", "8018").
 qactor( basicrobot, ctxbasicrobot, "external").
  qactor( smartrobot, ctxsmartrobot, "it.unibo.smartrobot.Smartrobot").
  qactor( walker, ctxsmartrobot, "it.unibo.walker.Walker").
