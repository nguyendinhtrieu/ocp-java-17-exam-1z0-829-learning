The default constructor is provided by the compiler only when a class does not define ANY constructor explicitly.

For example,
```
public class A{
    public A()  //This constructor is automatically inserted by the compiler
    //because there is no other constructor defined by the programmer explicitly.
    {
        super();  //Observe that it calls the super class's default no-args constructor.   
    }
}  
```
```
public class A{
    //Compiler will not generate any constructor because the programmer has defined a constructor.
    public A(int i){
        // do something
    }
}
```