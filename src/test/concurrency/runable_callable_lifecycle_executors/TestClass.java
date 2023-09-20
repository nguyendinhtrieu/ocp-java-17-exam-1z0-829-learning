package test.concurrency.runable_callable_lifecycle_executors;

class A extends Thread {
    boolean flag = true;

    public void run() {
        System.out.println("Starting loop");
        while (flag) {
            System.out.println("loop"); // Adding this line will help logic end 
        }//1
        System.out.println("Ending loop");
    }
}

public class TestClass {
    public static void main(String args[]) throws Exception {
        A a = new A();
        a.start();
        Thread.sleep(1000);        //2
        a.flag = false;
    }
}
