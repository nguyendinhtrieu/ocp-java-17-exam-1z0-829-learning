package foundation_test_01;

public class Q55_Record {
    public record Student(Integer id, String name) {
        public static Integer i = 0;

        public Student() {
            this(1, "");
        }

        public Student(Integer id, String name) { // Canonical constructor
            this.id = 1;
            this.name = "1";
        }

        void method() {

        }
    }

    public static void main(String[] args) {
        var s = new Student();
    }
}
