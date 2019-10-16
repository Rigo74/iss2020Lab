%====================================================================================
% basicrobotusage description   
%====================================================================================
context(ctxrobotusage, "localhost",  "TCP", "8048").
context(ctxbasicrobot, "192.168.1.6",  "TCP", "8018").
context(ctxsmartrobot, "192.168.1.6",  "TCP", "8020").
 qactor( basicrobot, ctxbasicrobot, "external").
  qactor( smartrobot, ctxsmartrobot, "external").
  qactor( robotusage, ctxrobotusage, "it.unibo.robotusage.Robotusage").
  qactor( callerfriend, ctxrobotusage, "it.unibo.callerfriend.Callerfriend").
tracing.
