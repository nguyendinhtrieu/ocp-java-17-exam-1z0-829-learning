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
