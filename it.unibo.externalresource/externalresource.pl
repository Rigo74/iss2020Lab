%====================================================================================
% externalresource description   
%====================================================================================
mqttBroker("localhost", "1883").
context(ctxexternalresource, "localhost",  "TCP", "8018").
context(ctxlocaluser, "localhost",  "TCP", "8013").
 qactor( resource, ctxexternalresource, "it.unibo.resource.Resource").
  qactor( localcaller1, ctxlocaluser, "it.unibo.localcaller1.Localcaller1").
