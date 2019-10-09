%====================================================================================
% robcmd description   
%====================================================================================
context(ctxrobot, "localhost",  "TCP", "8020" ).
context(ctxconsole, "localhost",  "TCP", "8030" ).
 qactor( console, ctxconsole, "it.unibo.console.Console").
  qactor( robot, ctxrobot, "it.unibo.robot.Robot").
