package src.test.streams_and_lambda.java_stream_api;

import java.util.Arrays;
import java.util.List;

public class TestMax {
    public static void main(String[] args) {
        List<Integer> ls = Arrays.asList(3, 4, 6, 9, 2, 5, 7);
        System.out.println(ls.stream().reduce(Integer.MIN_VALUE, (a, b) -> a > b ? a : b)); // 9
        System.out.println(ls.stream().max((x, y) -> Integer.max(x, y)).get());             // 3
        System.out.println(ls.stream().max((x, y) -> Integer.compare(x, y)).get());         // 9
        System.out.println(ls.stream().max((x, y) -> x > y ? x : y));                       // Optional[3]
    }
}
