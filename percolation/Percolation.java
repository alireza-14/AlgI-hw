import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int n;
    private WeightedQuickUnionUF wqu;
    private boolean[] openStatus;
    private int openSites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException();
        this.n = n;
        wqu = new WeightedQuickUnionUF(n * n + 2);
        openStatus = new boolean[n*n + 2];
        openStatus[0] = true;
        openStatus[n*n + 1] = true;
        openSites = 0;

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isOut(row) || isOut(col))
            throw new IllegalArgumentException();
        int idx = (row - 1) * n + col;
        openStatus[idx] = true;
        openSites += 1;
        for (int b : getNeighbors(row, col)) {
            if (b > 0) {
                if(openStatus[b]) {
                    wqu.union(idx, b);
                }
            }
        }
        if (row == 1) {
            wqu.union(idx, 0);
        }
        if (row == n) {
            wqu.union(idx, n*n+1);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (isOut(row) || isOut(col))
            throw new IllegalArgumentException();
        return openStatus[(row - 1)*n + col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (isOut(row) || isOut(col))
            throw new IllegalArgumentException();
        return wqu.find((row - 1) * n + col) == wqu.find(0);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return wqu.find(0) == wqu.find(n*n + 1);
    }

    private boolean isOut(int i) {
        return i < 1 || i > n;
    }

    private int[] getNeighbors(int row, int col) {
        int[] neighbors = new int[4];
        int[] step = new int[]{-1, 1};
        int r, c;
        r = 1;
        for (int i : step) {
            c = row + i;
            if (isOut(c)) neighbors[r+i] = -1;
            else neighbors[r+i] = (c-1)*n + col;
        }
        r = 2;
        for (int j : step) {
            c = col + j;
            if (isOut(c)) neighbors[r+j] = -1;
            else neighbors[r+j] = (row - 1)*n + c;
        }
        return neighbors;
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation ptest = new Percolation(5);
        int[] rows = new int[]{1, 2, 3, 1, 4, 5, 3};
        int[] cols = new int[]{1, 2, 1, 2, 1, 1, 2};
        int iterLength = rows.length;
        int row, col;
        for (int i = 0; i < iterLength; i++) {
            row = rows[i];
            col = cols[i];
            ptest.open(row, col);
            System.out.println("number of open sites: " + ptest.numberOfOpenSites());
            System.out.println("percolates: " + ptest.percolates());
            System.out.println("is full: " + ptest.isFull(row, col));
        }
    }
}