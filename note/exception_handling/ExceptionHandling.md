You must remember the hierarchy of exception classes:  

The base class of all exceptions is java.lang.Throwable. java.lang.Error and java.lang.Exception are the only two subclasses of Throwable.  

Error is used by the JVM to throw exception that have nothing to do with the program code as such but occur because of environment. For example, OutOfMemoryError. It indicates serious problems that a reasonable application should not try to catch. Most such errors are abnormal conditions. Error and its subclasses are regarded as unchecked exceptions for the purposes of compile-time checking of exceptions.   

Exception is used by the programmer as well as the JVM when it encounters exceptional situation in the program. Exception and its subclasses (except RuntimeException) are called Checked Exceptions. Checked exceptions need to be declared in a method or constructor's throws clause if they can be thrown by the execution of the method or constructor and propagate outside the method or constructor boundary. For example, java.io.IOException.  

RuntimeException extends Exception, which is used to report exceptional situations that cannot be predetermined at compile time. For example, IndexOutOfBoundsException or NullPointerException. RuntimeException and its subclasses are unchecked exceptions. Unchecked exceptions do not need to be declared in a method or constructor's throws clause.