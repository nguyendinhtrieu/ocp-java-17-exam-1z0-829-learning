Module options applicable for java as well as javac:
```
--module or -m : used to run or compile only the specified module.
--module-path or -p: used to specify the paths where java or javac will look for module definitions.
```
Module options applicable only for javac:
```
--module-source-path has no shortcut. Used by javac to look for source module definitions.
-d: used to specify output directory where the class files will be created after compilation.  
```
Module options applicable only for java:
```
--list-modules has no shortcut. It lists observable modules and exits.
--show-module-resolution has no shortcut. It shows module resolution output during startup. 
--describe-module or -d: It describes a module and exits.
```
Note that -d works differently in java and javac. Further, -d is very different from -D, which is used while running a java program to specify name-value pairs of properties at the command line.
