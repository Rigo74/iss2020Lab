%====================================================================================
% onestepnaive description   
%====================================================================================
context(ctxrobotmind, "localhost",  "TCP", "8035").
 qactor( onestepahead, ctxrobotmind, "it.unibo.onestepahead.Onestepahead").
  qactor( onestpcaller, ctxrobotmind, "it.unibo.onestpcaller.Onestpcaller").
  qactor( basicrobotinmind, ctxrobotmind, "it.unibo.basicrobotinmind.Basicrobotinmind").
  qactor( sonarhandler, ctxrobotmind, "it.unibo.sonarhandler.Sonarhandler").
