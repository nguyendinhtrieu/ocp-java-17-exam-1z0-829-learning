A functional interface is an interface that contains exactly one abstract method. It may contain zero or more default methods and/or static methods. Because a functional interface contains exactly one abstract method, you can omit the name of that method when you implement it using a lambda expression. For example, consider the following interface - 
```
interface Predicate<T> {
    boolean test(T t); 
}
```
The purpose of this interface is to provide a method that operates on an object of class T and return a boolean.

You could have a method that takes an instance of class that implements this interface defined like this -
```
public void printImportantData(ArrayList<Data> dataList, Predicate<Data> p){
    for(Data d: dataList){
       if(p.test(d)) System.out.println(d);
   }
}
```
where Data class could be as simple as `public class Data{ public int value; }`
Now, you can call the above method as follows:
```
printImportantData(al, (Data d)->{ return d.value>1; } ); 
```
Notice the lack of method name here. This is possible because the interface has only one abstract method so the compiler can figure out the name. This can be shortened to:
```
printImportantData(al, (Data d)->d.value>1);
```
Notice the lack of curly braces, the return keyword, and the semicolon. This is possible because the method returns a boolean and the expression d.value>1 also returns a boolean. The compiler is therefore able to figure out that the value of this expression is to be returned from the method. This can be shortened even more to:
```
printImportantData(al, d->d.value>1); 
```
Notice that there is no declaration of d! The compiler can figure out all information it needs because the interface has only one abstract method and that method has only one parameter. So you don't need to write all those things in your code.

Compare the above approach to the old style using an inner class that does the same thing -

printImportantData(al,  new Predicate<Data>(){
    public boolean test(Data d){
        return d.value>1;
    }
});

The Predicate interface described above can be used anywhere there is a need to "do something with an object and return a boolean" and is actually provided by the standard java library in java.util.function package. This package provides a few other useful functional interfaces.

- Predicate<T>: Represents a predicate (boolean-valued function) of one argument of type T. 
- Consumer<T>: Represents an operation that accepts a single input argument of type T and returns no result.
- Function<T,R>: Represents a function that accepts one argument of type T and produces a result of type R 
- Supplier<T>: Represents a supplier of results of type T.
 
Please see http://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html for learning Lambda expressions in Java.
