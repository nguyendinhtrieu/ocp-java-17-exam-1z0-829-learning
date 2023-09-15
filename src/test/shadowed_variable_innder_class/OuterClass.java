package src.test.shadowed_variable_innder_class;

public class OuterClass {
    int i = 10;
    class Inner {
        int i = 5;
        public void testMethod() {
            System.out.println(i);
        }
    }
    public static void main(String[] args) {
        new OuterClass().new Inner().testMethod();
    }
}
