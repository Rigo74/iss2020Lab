%====================================================================================
% radarusagecoap description   
%====================================================================================
context(ctxradarusagecoap, "localhost",  "TCP", "8044").
 qactor( radarusagecoap, ctxradarusagecoap, "it.unibo.radarusagecoap.Radarusagecoap").
  qactor( sonardataobserver, ctxradarusagecoap, "it.unibo.sonardataobserver.Sonardataobserver").
