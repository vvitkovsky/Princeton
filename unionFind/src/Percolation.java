import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] opened;
    private final static int top = 0;
    private final int bottom;
    private final int size;
    private int openedSites = 0;
    private final WeightedQuickUnionUF qf;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n <= 0");
        }
        size = n;
        bottom = size * size + 1;
        qf = new WeightedQuickUnionUF(size * size + 2);
        opened = new boolean[size][size];
    }

    private void check(int row, int col) {
        if (row <= 0 || row > size) {
            throw new IllegalArgumentException("row <= 0 || row > _size");
        }
        if (col <= 0 || col > size) {
            throw new IllegalArgumentException("col <= 0 || col > _size");
        }
    }

    private int index(int row, int col) {
        return size * (row - 1) + col;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        check(row, col);

        opened[row - 1][col - 1] = true;
        if (row == 1) {
            qf.union(index(row, col), top);
        }
        if (row == size) {
            qf.union(index(row, col), bottom);
        }

        if (col > 1 && isOpen(row, col - 1)) {
            qf.union(index(row, col), index(row, col - 1));
        }
        if (col < size && isOpen(row, col + 1)) {
            qf.union(index(row, col), index(row, col + 1));
        }
        if (row > 1 && isOpen(row - 1, col)) {
            qf.union(index(row, col), index(row - 1, col));
        }
        if (row < size && isOpen(row + 1, col)) {
            qf.union(index(row, col), index(row + 1, col));
        }
        openedSites++;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        check(row, col);
        return opened[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        check(row, col);
        if (0 < row && row <= size && 0 < col && col <= size) {
            return qf.find(top) == qf.find(index(row, col));
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openedSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return qf.find(top) == qf.find(bottom);
    }

    // test client (optional)
    public static void main(String[] args) {
        // Place anything here
    }
}
