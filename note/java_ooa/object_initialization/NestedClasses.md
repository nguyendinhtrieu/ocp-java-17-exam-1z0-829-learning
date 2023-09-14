If anonymous class is created for interface, it extends Object class and implement that interface, if it is created for a class then it extends that class. Inner classes are allowed to declare static final fields that are compile time constants as members. Inner classes can't have static methods though. Example:
```
public class Outer
{
    class Inner
    {
    static int k = 10;
    }
}
```
Consider the anonymous inner class for catching action events:
```
ActionListener al = new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                }
            };
```
Here, the anonymous class implicitly extends Object and implements the interface ActionListener. Explicit extends or implements clauses are not allowed for such classes. Other inner class (i.e. non anonymous) can have them. Consider the following (although of no use) class:
```
public class TestClass
{
    public TestClass(int i)  {    }
    public void m1()
    {
        TestClass al = new TestClass(10)
        {
            public void actionPerformed(ActionEvent e)
            {
            }
        };
    }
}
```
This illustrates :
1. Inner class can extend the outer class.
2. Anonymous inner class can be created for classes. (Not just for interfaces). They implicitly extend the class.(Here, TestClass)
3. Anonymous inner class can have initialization parameter. (If the class they extend has a corresponding constructor).

---------------------------------------------------------------------------------------------

Note the difference between an inner class and a static nested class. Inner class means a NON STATIC class defined inside a class. Remember:
1. A nested class is any class whose declaration occurs within the body of another class or interface. 
2. A top level class is a class that is not a nested class. 
3. An inner class is a nested class that is not explicitly or implicitly declared static. 
4. A class defined inside an interface is implicitly static.

For example,
```
public class A  // outer class
{
    static public class B //static nested class.
        //It can be used in other places: A.B b = new A.B(); There is no outer instance.
    {
    }
    class C //Inner class. It can only be used like this:
        // A.C c = new A().new C(); Outer instance is needed.
    {
    }
}
```

One can define a class as a static member of any top-level class. Now consider the following contents of a file named I1.java:
```
public interface I1
{
    public void mA();
    public interface InnerI1
    {
        int k = 10;
        public void innerA();
    }
}
```
Here, interface InnerI1 is implicitly STATIC and so is called as static nested interface. 'k' is a static member of this interface. If you do 'javap' on I1 it prints:

Compiled from I1.java
```
public interface I1
    /* ACC_SUPER bit NOT set */
{
    public abstract void mA();
    public static interface I1. InnerI1
    /* ACC_SUPER bit NOT set */
    {
        public static int k;
        public abstract void innerA();
    }
}
```

This interface can be referred to in other places like:
```
class MyClass implements I1.InnerI1
{
...
}
```
This is similar to referring a top level class.
