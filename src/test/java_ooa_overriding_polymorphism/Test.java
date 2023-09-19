package src.test.java_ooa_overriding_polymorphism;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Test {
    class Base {
        public <T extends Number, Z extends Number> Map<T, Z> getMap(T t, Z z) {
            return new HashMap<>();
        }
    }

    class Derived extends Base {
//        public <T, Z> TreeMap<T, Z> getMap(T t, Z z) {
//            return new TreeMap<>();
//        } //1

//        public Map<Number, Number> getMap(Number t, Number z) {
//            return new TreeMap<Number, Number>();
//        }  //2

        public Map<Integer, Integer> getMap(Number t, Number z) {
            return new HashMap<Integer, Integer>();
        }//3
    }
}
