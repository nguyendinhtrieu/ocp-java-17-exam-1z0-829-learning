package src.test.arrays_and_collections;

import java.util.Arrays;

public class TestCompareArray {
    public static void main(String[] args) {
        System.out.println(Arrays.compare(new int[]{1, 2, 5, 6}, new int[]{1, 2})); //2
        System.out.println(Arrays.compare(new int[]{1, 2, 5, 6}, new int[]{}));     //4
        System.out.println(Arrays.compare(new int[]{}, new int[]{1, 2, 5, 6}));     //-4
        System.out.println(Arrays.compare(new int[]{1, 2}, new int[]{1, 2, 5, 6})); //-2
        System.out.println(Arrays.compare(new int[]{1, 2}, new int[]{2, 1, 5, 6})); //-1
        System.out.println(Arrays.compare(new int[]{2, 1, 5, 6}, new int[]{1, 2})); //1
    }
}
