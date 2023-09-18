### Bottom Up Approach for modularizing an application
While modularizing an app using the bottom-up approach, you basically need to convert lower level libraries into modular jars before you can convert the higher level libraries. For example, if a class in `A.jar` directly uses a class from `B.jar`, and a class in `B.jar` directly uses a class from `C.jar`, you need to first modularize `C.jar` and then `B.jar` before you can modularize `A.jar`.

Thus, bottom up approach is possible only when the dependencies are modularized already. Effectively, when bottom-up migration is complete, every class/package of an application is put on the module-path. Nothing is left on the classpath.

### Top Down Approach for modularzing an application
While modularizing an app in a top-down approach, you need to remember the following points -
1. Any jar file can be converted into an automatic module by simply putting that jar on the module-path instead of the classpath. Java automatically derives the name of this module from the name of the jar file.  
2. Any jar that is put on classpath (instead of module-path) is loaded as a part of the "unnamed" module.  
3. An explicitly named module (which means, a module that has an explicitly defined name in its module-info.java file) can specify dependency on an automatic module just like it does for any other module i.e. by adding a requires <module-name>; clause in its module info but it cannot do so for the unnamed module because there is no way to write a requires clause without a name.  In other words, a named module can access classes present in an automatic module but not in the unnamed module.  
4. Automatic modules are given access to classes in the unnamed module (even though there is no explicitly defined module-info and requires clause in an automatic module). In other words, a class from an automatic module will be able to read a class in the unnamed module without doing anything special.  
5. An automatic module exports all its packages and is allowed to read all packages exported by other modules. Thus, an automatic module can access: all packages of all other automatic modules + all packages exported by all explicitly named modules + all packages of the unnamed module (i.e. classes loaded from the classpath).
   
Thus, if your application jar `A` directly uses a class from another jar `B`, then you would have to convert `B` into a module (either named or automatic). If `B` uses another jar `C`, then you can leave `C` on the class path if `B` hasn't yet been migrated into a named module. Otherwise, you would have to convert `C` into an automatic module as well.

### Note:
There are two possible ways for an automatic module to get its name: 
1. When an Automatic-Module-Name entry is available in the manifest, its value is the name of the automatic module. 
2. Otherwise, a name is derived from the JAR filename (see the ModuleFinder JavaDoc for the derivation algorithm) - Basically, hyphens are converted into dots and the version number part is ignored. So, for example, if you put mysql-connector-java-8.0.11.jar on module path, its module name would be mysql.connector.java


### Another reference
https://www3.ntu.edu.sg/home/ehchua/programming/java/JDK9_Module.html#:~:text=A%20CLASSPATH%20is%20a%20sequence,modules%20used%20in%20the%20application.

------------------------------------------------------------------------------
-jdkinternals or --jdk-internals<br>
Finds class-level dependencies in the JDK internal APIs. By default, this option analyzes all classes specified in the --classpath option and input files unless you specified the -include option. You can’t use this option with the -p, -e, and -s options.

For example, if you have the following class (you need to compile it with JDK 8 or less because sun.misc.BASE64Encoder was removed in Java 9):

```
public class TestClass{
   public static void main(String[] args) throws Exception {
        sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
        String s = encoder.encode("hello".getBytes());
   }
}
```
and if you run jdeps -jdkinternals TestClass.class, you will see the following output:
```
TestClass.class -> JDK removed internal API
    TestClass          -> sun.misc.BASE64Encoder     JDK internal API (JDK removed internal API)
```
Warning: JDK internal APIs are unsupported and private to JDK implementation that are subject to be removed or changed incompatibly and could break your application. Please modify your code to eliminate dependence on any JDK internal APIs. For the most recent update on JDK internal API replacements, please check: https://wiki.openjdk.java.net/display/JDK8/Java+Dependency+Analysis+Tool
```
JDK Internal API                         Suggested Replacement
----------------                         ---------------------
sun.misc.BASE64Encoder                   Use java.util.Base64 @since 1.8
```
