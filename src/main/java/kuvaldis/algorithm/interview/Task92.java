package kuvaldis.algorithm.interview;

public class Task92 {

    public int search(final int[] a, final int x) {
        int f = 0;
        int t = a.length - 1;
        while (f <= t) {
            final int m = (f + t) / 2;
            if (a[m] == x) {
                return m;
            }
            if (a[f] <= a[m]) {
                if (x <= a[m]) {
                    t = m - 1;
                } else {
                    f = m + 1;
                }
            } else if (x >= a[f] || x < a[m]) {
                t = m - 1;
            } else {
                f = m + 1;
            }
        }
        return -1;
    }
}
