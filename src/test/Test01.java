import java.util.ArrayList;
import java.util.List;

public class Test01 {
    public static void main(String[] args) {
        List<? super Booby> bV = null;
        List<? extends Tooby> tV = null;

        int i =5;
        byte b = I;
        short x = 3; x += 4.6;
    }
    public static final int I = 127;
    class Booby{ } class Dooby extends Booby{ } class Tooby extends Dooby{ }
}

class Book {
}

class TextBook extends Book {
}

class BookList extends ArrayList<Book> {
    public int count = 0;
    public static final int count2;
    static {
        count2 =9;
    }

    public static void main(String[] args) {
        C1 c1 = new C1();
        C2 c2 = (C2) c1.c1();
    }
}

interface I1 {
    static void m(){};
}
interface I2 {
    default void m(){};
}
interface I3 {
    void m();
}

abstract class C implements I1, I2, I3 {
    public abstract void m();
}


class C1 {
    Object c1(){return null;};
}
class C2 extends C1 {
    public <T extends String> void method(){
    };
    void c2(){};
}
