package kuvaldis.algorithm.interview.arraysstrings;

public class Task53 {

    public int nextLargest(final int n) {
        if (n == -1 || n == 0) {
            return 0;
        }
        int tmp = n;
        int i = 0;
        while (true) {
            if ((tmp & 3) == 1 || tmp == 0) {
                return n - (1 << i) + (1 << (i + 1));
            }
            tmp = tmp >>> 1;
            i++;
        }
    }
    public int nextSmallest(final int n) {
        if (n == -1 || n == 0) {
            return 0;
        }
        int tmp = n;
        int i = 0;
        while (true) {
            if ((tmp & 3) == 2) {
                return n - (1 << (i + 1)) + (1 << i);
            }
            if (tmp == 0) {
                return ~(~0 >>> i);
            }
            tmp = tmp >> 1;
            i++;
        }
    }
}
