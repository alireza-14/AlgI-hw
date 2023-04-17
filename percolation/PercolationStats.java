import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;;

public class PercolationStats {

    int n;
    int trials;
    double[] trialResults;
    double mean, std;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n < 1 || trials < 1)
            throw new IllegalArgumentException();
        this.n = n;
        this.trials = trials;
        trialResults = new double[trials];
        // StdRandom.setSeed(0);
        runTrials();
    }

    private void runTrials() {
        for (int i = 0; i < trials; i++) {
            trialResults[i] = runTest();
        }
        mean = StdStats.mean(trialResults);
        std = StdStats.stddev(trialResults);
    }

    private double runTest() {
        Percolation ptest = new Percolation(n);
        int[] indices = new int[n*n];
        for (int i = 0; i < n*n; i++) indices[i] = i;
        StdRandom.shuffle(indices);
        int idx = 0;
        int row, col;
        int node;
        while (!ptest.percolates()) {
            node = indices[idx++];
            row = node / n + 1;
            col = node % n + 1;
            ptest.open(row, col);
        }
        return (double)ptest.numberOfOpenSites()/(n*n);
    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return std;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean - 1.96 * std / Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean + 1.96 * std / Math.sqrt(trials);
    }

    // test client (see below)
    public static void main(String[] args) {
        int n, trials;
        n = Integer.parseInt(args[0]);
        trials = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, trials);
        StdOut.printf("%-23s = %f\n", "mean", ps.mean());
        StdOut.printf("%-23s = %f\n", "stddev", ps.stddev());
        StdOut.printf("95%% confidence interval = [%f, %f]\n", ps.confidenceLo(), ps.confidenceHi());

    }

}