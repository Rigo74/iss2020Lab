%====================================================================================
% basicrobotusage description   
%====================================================================================
context(ctxrobotusage, "localhost",  "TCP", "8018").
context(ctxrobot, "localhost",  "TCP", "8018" ).
 qactor( robot, ctxrobot, "external").
  qactor( robotcallersimulator, ctxrobotusage, "it.unibo.robotcallersimulator.Robotcallersimulator").
