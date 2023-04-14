import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String sword = null;
        int index = 1;
        boolean select;
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            select = StdRandom.bernoulli(1.0/index);
            if (select) {
                sword = word;
            }
            index++;
        }
        StdOut.println(sword);
    }
}