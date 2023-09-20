package test.collections.test_method_reference;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        List<Test> list = new ArrayList<>();
        list.stream().filter(x -> Test.test1(x)).collect(Collectors.toList());
        list.stream().filter(Test::test1).collect(Collectors.toList()); // Same as above
        
        list.stream().filter(x -> x.test2()).collect(Collectors.toList());
        list.stream().filter(Test::test2).collect(Collectors.toList()); // Same as above

        Test test = new Test();
        Function<Test, Boolean> f = x -> test.test2();
        
        Function<Integer, Boolean> f2 = x -> test.test2(x);
        Function<Integer, Boolean> f3 = test::test2; // Same as above
    }

    public static boolean test1(Test t) {
        return true;
    }

    public boolean test2() {
        return true;
    }

    public boolean test2(Integer i) {
        return true;
    }
}
