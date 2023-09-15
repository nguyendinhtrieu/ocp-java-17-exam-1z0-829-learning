package src.test.invoke_inner_class;

public class TopClass {
    public Inner inner1 = new Inner() {
        private static int value;

        public void doA() {
            System.out.println("doing A");
        }
    };

    public void doA() {
//        inner1.doA(); cannot invoke
    }
}

class Inner {
    int value;
}