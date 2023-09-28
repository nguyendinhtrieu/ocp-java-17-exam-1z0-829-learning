package test.streams_and_lambda.java_stream_api;

import java.util.Arrays;

public class TestTakeWhileAndDropWhile {

    enum Card {
        HEART, DIAMOND, CLUB, SPADE;
//        HEART, DIAMOND, CLUB, SPADE;    //-> takeWhile: HEART, DIAMOND
//        HEART, CLUB, DIAMOND, SPADE;    //-> takeWhile: HEART
//        CLUB, HEART, DIAMOND, SPADE;    //-> takeWhile: empty
//        HEART, DIAMOND, CLUB, SPADE;    //-> dropWhile: CLUB, SPADE
//        HEART, CLUB, DIAMOND, SPADE;    //-> dropWhile: CLUB, DIAMOND, SPADE
//        CLUB, HEART, DIAMOND, SPADE;    //-> dropWhile: CLUB, HEART, DIAMOND, SPADE

        public boolean isRed() {
            return switch (this) {
                case HEART, DIAMOND -> true;
                default -> false;
            };
        }
    }

    public static void main(String[] args) {
        Arrays.stream(Card.values()).takeWhile(Card::isRed).forEach(System.out::print);
        System.out.println();
        Arrays.stream(Card.values()).dropWhile(Card::isRed).forEach(System.out::print);
    }
}
