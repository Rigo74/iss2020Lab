package it.unibo.xtextIntro20.generator

import it.unibo.xtextIntro20.iot.IotSystem

class GenIotSystem {
	def doGenerate(IotSystem system ) {
		println("doGenerate system with name=" + system.spec.name)
		
		GenUtils.setPackageName( system.spec.name );
		
		val packageName     = GenUtils.packageName ;
		
		GenUtils.genFile(  packageName, "sysRules" , "pl", genCtxRules() )
		
	}
	
	
	def genCtxRules()'''
%==============================================
% DEFINED BY THE SYSTEM DESIGNER 
% «GenUtils.logo»
%==============================================
showSystemConfiguration :-
	stdout <- println("&&&&&&&&&&&&&&&&&&Sys&&&&&&&&&&&&&&&&&&&&"),
  	getTheContexts(CTXS),
	stdout <- println('CONTEXTS IN THE SYSTEM:'),
	showElements(CTXS),
	stdout <- println('ACTORS   IN THE SYSTEM:'),
	getTheActors(ACTORS),
	showElements(ACTORS),
	stdout <- println("&&&&&&&&&&&&&&&&&&Sys&&&&&&&&&&&&&&&&&&&&").

showElements(ElementListString):- 
	text_term( ElementListString, ElementList ),
 	showListOfElements(ElementList).
showListOfElements([]).
showListOfElements([C|R]):-
	stdout <- println( C ),
	showElements(R).
'''	
}