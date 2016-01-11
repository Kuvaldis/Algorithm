package kuvaldis.algorithm.codility;

import java.util.Arrays;

/*
We draw N discs on a plane. The discs are numbered from 0 to N ? 1. A zero-indexed array A of N non-negative integers, specifying the radiuses of the discs, is given. The J-th disc is drawn with its center at (J, 0) and radius A[J].

We say that the J-th disc and K-th disc intersect if J ? K and the J-th and K-th discs have at least one common point (assuming that the discs contain their borders).

The figure below shows discs drawn for N = 6 and A as follows:

  A[0] = 1
  A[1] = 5
  A[2] = 2
  A[3] = 1
  A[4] = 4
  A[5] = 0


There are eleven (unordered) pairs of discs that intersect, namely:

discs 1 and 4 intersect, and both intersect with all the other discs;
disc 2 also intersects with discs 0 and 3.
Write a function:

class Solution { public int solution(int[] A); }

that, given an array A describing N discs as explained above, returns the number of (unordered) pairs of intersecting discs. The function should return ?1 if the number of intersecting pairs exceeds 10,000,000.

Given array A shown above, the function should return 11, as explained above.

Assume that:

N is an integer within the range [0..100,000];
each element of array A is an integer within the range [0..2,147,483,647].
Complexity:

expected worst-case time complexity is O(N*log(N));
expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.
 */
public class NumberOfDiscIntersections {
    public int solution(int[] a) {
        final long[] lefts = new long[a.length];
        final long[] rights = new long[a.length];
        for (int i = 0; i < a.length; i++) {
            lefts[i] = (long) i - (long) a[i];
            rights[i] = (long) i + (long) a[i];
        }
        Arrays.sort(lefts);
        Arrays.sort(rights);
        final int[] lm = new int[lefts.length];
        int v = 0;
        for (int i = lefts.length - 2; i >= 0; i--) {
            if (lefts[i] != lefts[i + 1]) {
                v = lefts.length - i - 1;
            }
            lm[i] = v;
        }
        v = 0;
        final int[] rl = new int[rights.length];
        for (int i = 1; i < rights.length; i++) {
            if (rights[i - 1] != rights[i]) {
                v = i;
            }
            rl[i] = v;
        }
        int c = 0;
        for (int i = 0; i < a.length; i++) {
            final long ar = (long) i + (long) a[i];
            int idx = Arrays.binarySearch(lefts, ar);
            int e;
            if (idx < 0) {
                idx = -1 - idx;
                e = a.length - idx;
            } else {
                e = lm[idx];
            }
            final long al = (long) i - (long) a[i];
            idx = Arrays.binarySearch(rights, al);
            if (idx < 0) {
                idx = -1 - idx;
                e += idx;
            } else {
                e += rl[idx];
            }
            c = c + (a.length - e - 1);
            if (c > 20000000) {
                return -1;
            }
        }
        return c / 2;
    }
}
