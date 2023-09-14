package foundation_test_01;

public class Q12_InterfacesAllowMultipleImplementationInheritanceThroughDefaultMethods {

    interface I1 {
        public default void m1() {
            System.out.println("in I1.m1");
        }
    }

    interface I2 {
        public default void m1() {
            System.out.println("in I2.m1");
        }
    }

    class CI implements I1, I2 { //This class will not compile. 
    }

    //This class will compile because it provides its own implementation of m1. 
    class C2 implements I1, I2 {
        public void m1() {
            System.out.println("in C2.m1");
        }
    }
}
