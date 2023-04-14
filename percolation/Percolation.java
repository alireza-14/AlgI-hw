// import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {

    private int N;
    private int openSites;
    private WeightedQuickUnionUF uf;
    private boolean[][] openFlag;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) throws IllegalArgumentException {
        if (n > 0) {
            this.N = n;
            this.openSites = 0;
            this.uf = new WeightedQuickUnionUF(n * n + 2);
            this.openFlag = new boolean[n][n];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    this.openFlag[i][j] = false;
                }
            }
        }
        else
            throw new IllegalArgumentException("array size must be positive");
    }

    private int getIndex(int row, int col) throws IllegalArgumentException{
        if (!((row <= this.N && row > 0) && (col <= this.N && col > 0)))
            throw new IllegalArgumentException("array index not correct");
        int idx = (row - 1) * this.N + (col - 1);
        return idx;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) throws IllegalArgumentException{
        if (!((row <= this.N && row > 0) && (col <= this.N && col > 0)))
            throw new IllegalArgumentException("array index not correct");
        if (! this.openFlag[row - 1][col - 1]) {
            this.openSites++;
            this.openFlag[row - 1][col - 1] = true;
            int idx = this.getIndex(row, col);
            int[] neighbors = {row, col - 1, row, col + 1, row - 1, col, row + 1, col};
            for (int i = 0; i < 4; i++) {
                int x, y;
                x = neighbors[2 * i];
                y = neighbors[2 * i + 1];
                try {
                    int idxN = this.getIndex(x, y);
                    if (isOpen(x, y)) {
                        this.uf.union(idx, idxN);
                    }
                }catch(IllegalArgumentException e) {
                    if (row == 1) {
                        this.uf.union(0, idx);
                    }
                    if (row == N) {
                        this.uf.union(idx, this.N*this.N+1);
                    }
                }
            }


        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) throws IllegalArgumentException {
        if (!((row <= this.N && row > 0) && (col <= this.N && col > 0)))
            throw new IllegalArgumentException("array index not correct");
        return this.openFlag[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) throws IllegalArgumentException{
        if (!((row <= this.N && row > 0) && (col <= this.N && col > 0)))
            throw new IllegalArgumentException("array index not correct");
        return !this.openFlag[row - 1][col - 1];
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        // if (this.uf.find(0) == uf.find(this.N*this.N + 1)) {
        //     StdOut.println(this.uf.find(0));
        //     StdOut.println(this.uf.find(this.N * this.N + 1));
        // }
        // StdOut.println(this.uf.find(0));
        // StdOut.println(this.uf.find(this.N * this.N + 1));
        return this.uf.find(0) == uf.find(this.N*this.N + 1);
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}