import java.lang.IllegalArgumentException;
import edu.princeton.cs.algs4.StdOut;


public class Test {

    public void testMethod() {
        throw new IllegalArgumentException();
    }
    public static void main(String[] args) {
        StdOut.printf("%-23s = %f\n", "myword", 0.1111011);
        StdOut.printf("95%% confidence interval = [%f, %f]\n", 0.1111011, 0.1212233);
    }
}