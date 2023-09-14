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
