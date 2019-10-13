%====================================================================================
% smartrobot description   
%====================================================================================
context(ctxsmartrobot, "localhost",  "TCP", "8020").
context(ctxbasicrobot, "192.168.1.12",  "TCP", "8018").
 qactor( basicrobot, ctxbasicrobot, "external").
  qactor( smartrobot, ctxsmartrobot, "it.unibo.smartrobot.Smartrobot").
  qactor( smartrobotcaller, ctxsmartrobot, "it.unibo.smartrobotcaller.Smartrobotcaller").
