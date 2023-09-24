package test.java_ooa.overloading;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Point {
    int x, y;
}

class ColoredPoint extends Point {
    int color;
}

class Test {
    static void test(ColoredPoint p, Point q) {
        System.out.println("(ColoredPoint, Point)");
    }

    static void test(Point p, ColoredPoint q) {
        System.out.println("(Point, ColoredPoint)");
    }

    static void test(ColoredPoint p, ColoredPoint q) { // test(cp, cp); will compile if have this method declaration
        System.out.println("(Point, ColoredPoint)");
    }

    public static int i = 0;

    public void t() {
        this.i = 9;
    }

    public static void main(String[] args) {
        int i, i1 = 0;
        char a = 'a', b = 98;
        ColoredPoint cp = new ColoredPoint();
        test(cp, cp);
    }

}

class A {
}

class B extends A {
}

class C extends B {
}

class Base {
    public List<B> transform(List<B> list) {
        return null;
    }
}

class Derived extends Base {
    public ArrayList<B> transform(List<B> list) {
        return null;
    }

    public static void main(String[] args) {
        Runnable r = () -> System.out.println("In Runnable");
        Callable<Integer> c = () -> {
            System.out.println("In Callable");
            return 0;
        };
        var es = Executors.newCachedThreadPool();          //INSERT CODE HERE        
        Future<?> submit = es.submit(r);
        Future<Integer> submit1 = es.submit(c);
        es.shutdown();
    }

}


abstract interface i {
    public final static int i = 0;
    private static void m() {}
    public default void m1() {}
    public void m2();
}

abstract class AA {
    protected final static int i = 0;
    private static void m() {}
    private void m1() {}
    protected abstract void m2();
}

interface I1 {
    public default void method() {
        System.out.println("I1");
    }
}

interface I2 extends I1 {
    public default void method() {
        System.out.println("I2");
    }
}

class C11 implements I1, I2 {

    //    @Override
//    public void method() {
//        I2.super.method();
//    }
    public static void main(String[] args) {
        new C11().method();
    }

}

class XX {
    public int m(int i){return 1;}
}
class XXX extends XX {
    public int m(Integer i) {
        return 2;
    }
}

class X {
    public static void main(String[] args) throws Exception {
        try (var fis = new FileInputStream("/.../t.txt"); var isr = new InputStreamReader(fis)) {
            while (isr.ready()) {
                isr.skip(1);
                int i = isr.read();
                char c = (char) i;
                System.out.println(i);
//                System.out.println((int)c);
            }
        }
    }
}