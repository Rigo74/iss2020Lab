%====================================================================================
% robot description   
%====================================================================================
context(radarctx, "192.168.1.10",  "TCP", "6789").
context(robotctx, "localhost",  "TCP", "7000").
 qactor( radar, radarctx, "external").
  qactor( sonaractor, robotctx, "it.unibo.sonar.SonarActor").
  qactor( robot, robotctx, "it.unibo.robot.Robot").
