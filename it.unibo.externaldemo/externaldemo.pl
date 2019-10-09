%====================================================================================
% externaldemo description   
%====================================================================================
context(ctxexternaluser, "localhost",  "TCP", "8015" ).
context(ctxexternalresource, "192.168.1.12",  "TCP", "8018" ).
 qactor( user, ctxexternaluser, "it.unibo.user.User").
