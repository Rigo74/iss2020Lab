package it.unibo.xtextIntro20.generator;

import it.unibo.xtextIntro20.generator.GenUtils;
import it.unibo.xtextIntro20.iot.IotSystem;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.InputOutput;

@SuppressWarnings("all")
public class GenIotSystem {
  public void doGenerate(final IotSystem system) {
    String _name = system.getSpec().getName();
    String _plus = ("doGenerate system with name=" + _name);
    InputOutput.<String>println(_plus);
    GenUtils.setPackageName(system.getSpec().getName());
    final String packageName = GenUtils.packageName;
    GenUtils.genFile(packageName, "sysRules", "pl", this.genCtxRules());
  }
  
  public CharSequence genCtxRules() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("%==============================================");
    _builder.newLine();
    _builder.append("% DEFINED BY THE SYSTEM DESIGNER ");
    _builder.newLine();
    _builder.append("% ");
    _builder.append(GenUtils.logo);
    _builder.newLineIfNotEmpty();
    _builder.append("%==============================================");
    _builder.newLine();
    _builder.append("showSystemConfiguration :-");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("stdout <- println(\"&&&&&&&&&&&&&&&&&&Sys&&&&&&&&&&&&&&&&&&&&\"),");
    _builder.newLine();
    _builder.append("  \t");
    _builder.append("getTheContexts(CTXS),");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("stdout <- println(\'CONTEXTS IN THE SYSTEM:\'),");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("showElements(CTXS),");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("stdout <- println(\'ACTORS   IN THE SYSTEM:\'),");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("getTheActors(ACTORS),");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("showElements(ACTORS),");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("stdout <- println(\"&&&&&&&&&&&&&&&&&&Sys&&&&&&&&&&&&&&&&&&&&\").");
    _builder.newLine();
    _builder.newLine();
    _builder.append("showElements(ElementListString):- ");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("text_term( ElementListString, ElementList ),");
    _builder.newLine();
    _builder.append(" \t");
    _builder.append("showListOfElements(ElementList).");
    _builder.newLine();
    _builder.append("showListOfElements([]).");
    _builder.newLine();
    _builder.append("showListOfElements([C|R]):-");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("stdout <- println( C ),");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("showElements(R).");
    _builder.newLine();
    return _builder;
  }
}
