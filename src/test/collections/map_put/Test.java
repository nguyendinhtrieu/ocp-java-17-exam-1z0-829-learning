package src.test.collections.map_put;

import java.util.HashMap;

public class Test {
    public static void main(String[] args) {
        final HashMap<String, String> m = new HashMap<>();
        System.out.println(m.put("1", "2"));
        System.out.println(m.put("1", "3"));
        System.out.println(m.put("2", "5"));
        System.out.println(m.put(null, "6"));
        System.out.println(m.put("7", null));
    }
}
