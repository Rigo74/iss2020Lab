%====================================================================================
% steprobot description   
%====================================================================================
context(ctxbasicrobot, "localhost",  "TCP", "8018").
context(ctxsteprobot, "localhost",  "TCP", "8016").
 qactor( basicrobot, ctxbasicrobot, "external").
  qactor( robot, ctxsteprobot, "it.unibo.robot.Robot").
