%====================================================================================
% onestep description   
%====================================================================================
context(ctxrobotmind, "localhost",  "TCP", "8035").
context(ctxbasicrobot, "192.168.1.12",  "TCP", "8020" ).
 qactor( basicrobot, ctxbasicrobot, "external").
  qactor( onestepahead, ctxrobotmind, "it.unibo.onestepahead.Onestepahead").
  qactor( onestpcaller, ctxrobotmind, "it.unibo.onestpcaller.Onestpcaller").
