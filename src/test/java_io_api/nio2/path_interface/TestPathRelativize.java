package test.java_io_api.nio2.path_interface;

import java.nio.file.Paths;

public class TestPathRelativize {
    public static void main(String[] args) {
        System.out.println(Paths.get("/photos/goa").relativize(Paths.get("/index.html"))); //: ../../index.html
        System.out.println(Paths.get("/photos/goa").relativize(Paths.get("/photos/index.html"))); //: ../index.html

        // throw java.lang.IllegalArgumentException
        System.out.println(Paths.get("photos/goa").relativize(Paths.get("/index.html")));          
        System.out.println(Paths.get("/photos/goa").relativize(Paths.get("index.html")));
        System.out.println(Paths.get("/photos/goa").relativize(Paths.get("photos/index.html")));
    }
}
