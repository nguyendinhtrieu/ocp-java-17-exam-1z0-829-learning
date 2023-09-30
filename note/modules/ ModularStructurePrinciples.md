Remember that the modular structure of the JDK implements the following principles:
- Standard modules, whose specifications are governed by the JCP, have names starting with the string "java.".
- All other modules are merely part of the JDK, and have names starting with the string "jdk.".
- A standard module may contain both standard and non-standard API packages.  If a standard module exports a standard API package then the export may be qualified; if a standard module exports a non-standard API package then the export must be qualified.
- A standard module may depend upon one or more non-standard modules. It must not grant implied readability to any non-standard module. If it is a Java SE module then it must not grant implied readability to any non-SE module.
- A non-standard module must not export any standard API packages. A non-standard module may grant implied readability to a standard module.
