You must remember the hierarchy of exception classes:  

The base class of all exceptions is java.lang.Throwable. java.lang.Error and java.lang.Exception are the only two subclasses of Throwable.  

Error is used by the JVM to throw exception that have nothing to do with the program code as such but occur because of environment. For example, OutOfMemoryError. It indicates serious problems that a reasonable application should not try to catch. Most such errors are abnormal conditions. Error and its subclasses are regarded as unchecked exceptions for the purposes of compile-time checking of exceptions.   

Exception is used by the programmer as well as the JVM when it encounters exceptional situation in the program. Exception and its subclasses (except RuntimeException) are called Checked Exceptions. Checked exceptions need to be declared in a method or constructor's throws clause if they can be thrown by the execution of the method or constructor and propagate outside the method or constructor boundary. For example, java.io.IOException.  

RuntimeException extends Exception, which is used to report exceptional situations that cannot be predetermined at compile time. For example, IndexOutOfBoundsException or NullPointerException. RuntimeException and its subclasses are unchecked exceptions. Unchecked exceptions do not need to be declared in a method or constructor's throws clause.

-------------------------------------------------------------------------
You need to know the following points regarding try-with-resources statement for the exam:

1. The resource class must implement java.lang.AutoCloseable interface. Many standard JDK classes such as java.io.FileInputStream implement the older (introduced in Java 1.5), java.io.Closeable interface, which was made to extend java.lang.AutoCloseable in Java 1.7. 
2. AutoCloseable has only one method - public void close() throws Exception.
3. Resources are closed at the end of the try block and before any catch or finally block.
4. Resources are not even accessible in the catch or finally block. For example:
```
try(Device d = new Device())
{
   d.read();
}finally{
   d.close(); //This will not compile because d is not accessible here.
}
```
Note that the try-with-resource was enhanced in Java 9 and it now allows you to use a variable declared before the try statement in the try-with-resource block. In this case, of course, the variable is accessible after the try block but the object referred to by it has been closed. For example, the following is valid since Java 9:
```
Device d = new Device();
try(d){ //valid since Java 9
  ...
}finally{
   d.close(); //this will compile but may not work correctly because the object referred to by d has already been closed.
}
```
5. Resources are closed in the reverse order of their declaration (or creation) in the try clause.
6. Resources are closed even if the code in the try block throws an exception.
7. java.lang.AutoCloseable's close() throws Exception but java.io.Closeable's close() throws IOException.
8. If the code in the try block throws an exception and an exception is also thrown while closing a resource, the exception thrown while closing the resource is **suppressed**. The caller gets the exception thrown in the try block.
9. Auto closeable resources that you want the try clause to manage **must be final or effectively final**.
