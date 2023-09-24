1. The compiler always tries to choose the most specific method available with least number of modifications to the arguments.
```
public class OverloadingExample {
    public void print(int num) {
        System.out.println("Printing integer: " + num);
    }

    public void print(double num) {
        System.out.println("Printing double: " + num);
    }

    public static void main(String[] args) {
        OverloadingExample example = new OverloadingExample();
        int intValue = 42;
        double doubleValue = 3.14;
        
        example.print(intValue);    // Calls print(int num)
        example.print(doubleValue); // Calls print(double num)
    }
}
```
In this example, there are two overloaded methods, print(int num) and print(double num). When calling these methods, the compiler selects the most specific method based on the argument type. It chooses the method that requires the least modification to the argument's type.
2. Java designers have decided that old code should work exactly as it used to work before boxing-unboxing functionality became available.
```
public class BoxingUnboxingExample {
    public void display(Integer num) {
        System.out.println("Displaying Integer: " + num);
    }

    public void display(int num) {
        System.out.println("Displaying int: " + num);
    }

    public static void main(String[] args) {
        BoxingUnboxingExample example = new BoxingUnboxingExample();
        int intValue = 42;
        
        example.display(intValue); // Calls display(int num)
    }
}
```
In this example, we have two overloaded methods, `display(Integer num)` and `display(int num)`. Despite the availability of boxing and unboxing in Java (conversion between primitive types and their corresponding wrapper classes), the compiler chooses the method that doesn't require boxing because it's designed to maintain backward compatibility with older code that predates boxing and unboxing.
3. Widening is preferred to boxing/unboxing (because of rule 2), which in turn, is preferred over var-args.
```
public class PreferenceExample {
    public void process(long num) {
        System.out.println("Processing long: " + num);
    }

    public void process(Integer num) {
        System.out.println("Processing Integer: " + num);
    }

    public void process(int... nums) {
        System.out.println("Processing var-args: " + Arrays.toString(nums));
    }

    public static void main(String[] args) {
        PreferenceExample example = new PreferenceExample();
        int intValue = 42;
        long longValue = 100L;
        
        example.process(intValue);     // Calls process(long num)
        example.process((Integer) 42); // Calls process(Integer num)
        example.process(1, 2, 3);      // Calls process(int... nums)
    }
}
```
In this example, there are three overloaded methods, `process(long num)`, `process(Integer num)`, and `process(int... nums)`. The compiler prefers widening conversions (e.g., from int to long) over boxing/unboxing (e.g., from int to Integer), and it prefers boxing/unboxing over var-args.

- When you pass an int value, it chooses process(long num) because it's a widening conversion.
- When you pass an Integer value, it chooses process(Integer num) because it involves boxing.
- When you pass multiple int values, it chooses process(int... nums) because it's the least specific and requires var-args.