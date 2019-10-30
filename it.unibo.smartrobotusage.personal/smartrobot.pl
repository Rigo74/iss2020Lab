%====================================================================================
% smartrobot description   
%====================================================================================
context(ctxsmartrobotcaller, "localhost",  "TCP", "8023").
context(ctxsmartrobot, "10.201.111.204",  "TCP", "8020").
 qactor( smartrobot, ctxsmartrobot, "external").
  qactor( smartrobotcaller, ctxsmartrobotcaller, "it.unibo.smartrobotcaller.Smartrobotcaller").
