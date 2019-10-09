%====================================================================================
% externalcallers description   
%====================================================================================
mqttBroker("localhost", "1883").
context(ctxexternalcallers, "localhost",  "TCP", "8013").
context(ctxexternalresource, "192.168.1.12",  "TCP", "8018" ).
 qactor( resource, ctxexternalresource, "external").
  qactor( caller1, ctxexternalcallers, "it.unibo.caller1.Caller1").
  qactor( caller2, ctxexternalcallers, "it.unibo.caller2.Caller2").
