%====================================================================================
% steprobot description   
%====================================================================================
context(ctxbasicrobot, "localhost",  "TCP", "8018").
context(ctxsteprobot, "10.201.111.204",  "TCP", "8016").
 qactor( basicrobot, ctxbasicrobot, "external").
  qactor( robot, ctxsteprobot, "it.unibo.robot.Robot").
