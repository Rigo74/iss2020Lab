%====================================================================================
% smartrobot description   
%====================================================================================
context(ctxsmartrobot, "localhost",  "TCP", "8020").
context(ctxbasicrobot, "10.201.111.204",  "TCP", "8018").
 qactor( basicrobot, ctxbasicrobot, "external").
  qactor( smartrobot, ctxsmartrobot, "it.unibo.smartrobot.Smartrobot").
