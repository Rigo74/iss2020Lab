%====================================================================================
% basicrobot description   
%====================================================================================
context(ctxbasicrobot, "localhost",  "TCP", "8018").
 qactor( basicrobot, ctxbasicrobot, "it.unibo.basicrobot.Basicrobot").
  qactor( sonarhandler, ctxbasicrobot, "it.unibo.sonarhandler.Sonarhandler").
  qactor( robotcaller, ctxbasicrobot, "it.unibo.robotcaller.Robotcaller").
