%====================================================================================
% sysexample description   
%====================================================================================
context(ctxsysexample, "localhost",  "TCP", "8072").
context(ctxbls, "10.200.103.164",  "TCP", "8075" ).
 qactor( qa0, ctxsysexample, "it.unibo.qa0.Qa0").
  qactor( qacoded, ctxsysexample, "resources.qacoded").
  qactor( led, ctxbls, "external").
