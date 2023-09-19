package src.test.streams_and_lambda.java_stream_api;

import java.util.OptionalDouble;
import java.util.stream.IntStream;

public class TestIntStream {
    public static void main(String[] args) {
        IntStream is1 = IntStream.of(1, 3, 5);
        OptionalDouble x = is1.filter(i -> i % 2 == 0).average();
        System.out.println(x);

        IntStream is2 = IntStream.of(2,4,6);
        int y = is2.filter(i -> i % 2 != 0).sum();
    }
}
