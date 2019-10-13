%====================================================================================
% robotstep description   
%====================================================================================
context(ctxsteprobot, "localhost",  "TCP", "8030").
context(ctxbasicrobot, "192.168.1.33",  "TCP", "8020" ).
 qactor( steprobot, ctxsteprobot, "it.unibo.steprobot.Steprobot").
  qactor( basicrobot, ctxbasicrobot, "external").
