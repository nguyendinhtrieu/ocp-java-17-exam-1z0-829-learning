package src.test.switch_test;

public class SwitchTest {
    public static void main(String[] args) {
        switch (1) {
            default -> System.out.println();
        }
        switch (1) {
            default -> { // remove {} will not compile
                break;
            }
        }
        if (true) {
//            break; Not allow
        }
    }
}
