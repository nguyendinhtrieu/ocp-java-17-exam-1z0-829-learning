For example, if an abc.print module implements an org.printing.Print service interface defined in PrintServiceAPI module using com.abc.PrintImpl class, then this is how its module-info should look:

```
module abc.print{  
    requires PrintServiceAPI; //required  
    //because this module defines the service interface org.printing.Print  
    provides org.printing.Print with com.abc.PrintImpl;
}
```
A module named customer that uses Print service may look like this:
```
module customer{ 
    requires PrintServiceAPI; //required 
    //because this module defines the service interface org.printing.Print 
    uses org.printing.Print; //specifies that this module uses this service  
    //observe that abc.print module is not required. 
}
```

------------------------------------------
Here are the rules for a service provider:
1. If a service provider explicitly declares a public constructor with no formal parameters, or implicitly declares a public default constructor, then that constructor is called the provider constructor.
2. If a service provider explicitly declares a public static method called provider with no formal parameters, then that method is called the provider method.
3. If a service provider has a provider method, then its return type must (i) either be declared in the current module, or be declared in another module and be accessible to code in the current module; and (ii) be a subtype of the service specified in the provides directive; or a compile-time error occurs.
4. While a service provider that is specified by a provides directive must be declared in the current module, its provider method may have a return type that is declared in another module. Also, note that when a service provider declares a provider method, the service provider itself need not be a subtype of the service.
5. If a service provider does not have a provider method, then that service provider must have a provider constructor and must be a subtype of the service specified in the provides directive, or a compile-time error occurs.
