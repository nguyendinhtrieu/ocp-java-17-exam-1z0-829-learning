package src.test.java_io_api.nio2.path_interface;

import java.nio.file.Paths;

public class TestPathResolve {

    public static void main(String[] args) {
        System.out.println(Paths.get("/home/a/b/c.txt").resolve(Paths.get("a/b/c.txt")));  // /home/a/b/c.txt/a/b/c.txt
        System.out.println(Paths.get("/home/a/b/c.txt").resolve(Paths.get("/a/b/c.txt"))); // /a/b/c.txt
        System.out.println(Paths.get("/home/a/b/c.txt").resolve(Paths.get("/c.txt")));     // /c.txt
        System.out.println(Paths.get("c.txt").resolve(Paths.get("a/b/c.txt")));            // c.txt/a/b/c.txt
        System.out.println(Paths.get("/home/a/b/c.txt").resolve(Paths.get("")));           // /home/a/b/c.txt
        System.out.println(Paths.get("home/a/b/c.txt").resolve(Paths.get("/t/t.txt")));    // /t/t.txt
        System.out.println(Paths.get("home/a/b/c.txt").resolve(Paths.get("t/t.txt")));     // home/a/b/c.txt/t/t.txt
    }
}
