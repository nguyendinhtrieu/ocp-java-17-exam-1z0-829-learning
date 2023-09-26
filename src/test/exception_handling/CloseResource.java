package test.exception_handling;

import java.io.IOException;

public class CloseResource {
}

class Device implements AutoCloseable {
    String header = null;

    public Device(String name) throws IOException {
        header = name;
        if ("D2".equals(name)) throw new IOException("Unknown");
        System.out.println(header + " Opened");
    }

    public String read() throws IOException {
        return "";
    }

    public void close() {
        System.out.println("Closing device " + header);
        throw new RuntimeException("RTE while closing " + header);
    }

    public static void main(String[] args) throws Exception {
        try (Device d1 = new Device("D1"); Device d2 = new Device("D2")) {
            throw new Exception("test");
        }
//      D1 Opened
//      Closing device D1
//      Exception in thread "main" java.io.IOException: Unknown
//          at test.exception_handling.Device.<init>(CloseResource.java:13)
//          at test.exception_handling.Device.main(CloseResource.java:27)
//              Suppressed: java.lang.RuntimeException: RTE while closing D1
//                  at test.exception_handling.Device.close(CloseResource.java:23)
//		            ... 1 more
    }
}