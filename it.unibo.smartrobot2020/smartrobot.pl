%====================================================================================
% smartrobot description   
%====================================================================================
context(ctxsmartrobot, "localhost",  "TCP", "8020").
context(ctxbasicrobot, "192.168.43.171",  "TCP", "8018").
 qactor( basicrobot, ctxbasicrobot, "external").
  qactor( smartrobot, ctxsmartrobot, "it.unibo.smartrobot.Smartrobot").
