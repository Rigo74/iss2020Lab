%====================================================================================
% basicrobotusage description   
%====================================================================================
context(ctxrobotusage, "localhost",  "TCP", "8048").
context(ctxbasicrobot, "10.201.111.204",  "TCP", "8018").
 qactor( basicrobot, ctxbasicrobot, "external").
  qactor( robotusage, ctxrobotusage, "it.unibo.robotusage.Robotusage").
  qactor( callerfriend, ctxrobotusage, "it.unibo.callerfriend.Callerfriend").
tracing.