package test.java_ooa.overriding_polymorphism;

class Parent {
    {
        System.out.println("Instant Parent");
    }

    static {
        System.out.println("Static Parent");
    }

    public Parent() {
        System.out.print("On Parent: ");
        print();
    }

    public void print() {
        System.out.println("print: Parent");
    }
}

public class Sub extends Parent {
    int i1 = 2;
    static int i2 = 3;

    {
        System.out.println("Instant Sub");
        System.out.println(i1 + "-" + i2);
    }

    static {
        System.out.println("Static Sub");
    }

    public Sub() {
        super();
        System.out.print("Constructor on Sub: ");
        System.out.println(i1 + "-" + i2);
        print();
    }

    public void print() {
        System.out.println("print: " + i1 + "-" + i2);
    }

    public static void main(String[] args) {
        new Sub().print();
        
//        Here is the output
//        Static Parent               -> Init static for parent class first
//        Static Sub                  -> then, Init static for sub class
//        Instant Parent              -> then, create instance of parent
//        On Parent: print: 0-3       -> print on parent: note that i1 of Sub is not init yet, so it's value is 0
//        Instant Sub                 -> then create instance of sub 
//        2-3                         -> i1 now set to 2 
//        Constructor on Sub: 2-3     -> running constructor for sub 
//        print: 2-3                  -> print i1 is 2 at constructor 
//        print: 2-3                  -> print i1 is 2 at main method
    }
}
