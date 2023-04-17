import java.lang.IllegalArgumentException;


public class Test {

    public void testMethod() {
        throw new IllegalArgumentException();
    }
    public static void main(String[] args) {
        Test t = new Test();
        t.testMethod();
    }
}