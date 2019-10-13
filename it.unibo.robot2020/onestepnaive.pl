%====================================================================================
% onestepnaive description   
%====================================================================================
context(ctxrobotmind, "localhost",  "TCP", "8035").
context(ctxbasicrobot, "localhost",  "TCP", "8020").
 qactor( onestepahead, ctxrobotmind, "it.unibo.onestepahead.Onestepahead").
  qactor( onestpcaller, ctxrobotmind, "it.unibo.onestpcaller.Onestpcaller").
  qactor( basicrobot, ctxbasicrobot, "it.unibo.basicrobot.Basicrobot").
  qactor( sonarhandler, ctxbasicrobot, "it.unibo.sonarhandler.Sonarhandler").
