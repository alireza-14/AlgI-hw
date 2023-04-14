import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {

    private int N;
    private int T;
    private double[] thresholds;
    private Percolation percolation;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        this.N = n;
        this.T = trials;
        thresholds = new double[trials];
        int[] indices = new int[n*n];
        for (int i = 0; i < n*n; i++) {
            indices[i] = i;
        }
        for (int i = 0; i < trials; i++) {
            percolation = new Percolation(n);
            StdRandom.shuffle(indices);
            // printArray(indices);
            int idx = 0;
            int x, y;
            while (!percolation.percolates()) {
                x = this.getX(indices[idx]);
                y = this.getY(indices[idx]);
                percolation.open(x, y);
                idx++;
            }
            thresholds[i] = (float)percolation.numberOfOpenSites() / (n * n);
            // StdOut.println(percolation.numberOfOpenSites());
        }

    }

    // private void printArray(int[] arr) {
    //     for (int a:arr) {
    //         StdOut.print(a + ", ");
    //     }
    //     StdOut.println();
    // }

    private int getX(int index) {
        return index / this.N + 1;
    }

    private int getY(int index) {
        return index % this.N + 1;
    }
    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(this.thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.mean() - 1.96 * this.stddev() / this.T;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.mean() + 1.96 * this.stddev() / this.T;
    }

   // test client (see below)
    public static void main(String[] args) {
        int n, trials;
        n = Integer.parseInt(args[0]);
        trials = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, trials);
        StdOut.printf("mean\t= %f\n", ps.mean());
        StdOut.printf("stddev\t= %f\n", ps.stddev());
        StdOut.println("95% confidence interval\t= [" + ps.confidenceLo() +"," + ps.confidenceHi() + "]");
    }

}
