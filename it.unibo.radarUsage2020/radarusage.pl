%====================================================================================
% radarusage description   
%====================================================================================
mqttBroker("localhost", "1883").
context(ctxradar, "192.168.1.8",  "MQTT", "0").
context(ctxradarusage, "localhost",  "MQTT", "0").
 qactor( radargui, ctxradar, "external").
  qactor( demousage, ctxradarusage, "it.unibo.demousage.Demousage").
