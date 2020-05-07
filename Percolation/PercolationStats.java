import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final int tr;
    private final double[] fr;
    private static final double coeff = 1.96;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("n <= 0 || trials <= 0");
        }
        tr = trials;
        fr = new double[trials];
        for (int expNum = 0; expNum < tr; expNum++) {
            Percolation pr = new Percolation(n);
            int openedSites = 0;
            while (!pr.percolates()) {
                int i = StdRandom.uniform(1, n + 1);
                int j = StdRandom.uniform(1, n + 1);
                if (!pr.isOpen(i, j)) {
                    pr.open(i, j);
                    openedSites++;
                }
            }
            double fraction = (double) openedSites / (n * n);
            fr[expNum] = fraction;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(fr);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(fr);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - ((coeff * stddev()) / Math.sqrt(tr));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + ((coeff * stddev()) / Math.sqrt(tr));
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, trials);

        String confidence = "[" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]";
        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = " + confidence);
    }
}
