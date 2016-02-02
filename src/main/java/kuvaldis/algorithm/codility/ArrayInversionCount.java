package kuvaldis.algorithm.codility;

/*

1. ArrayInversionCount
Compute number of inversion in an array.
Task description
A zero-indexed array A consisting of N integers is given. An inversion is a pair of indexes (P, Q) such that P < Q and A[Q] < A[P].

Write a function:

class Solution { public int solution(int[] A); }

that computes the number of inversions in A, or returns ?1 if it exceeds 1,000,000,000.

Assume that:

N is an integer within the range [0..100,000];
each element of array A is an integer within the range [?2,147,483,648..2,147,483,647].
For example, in the following array:

A[0] = -1 A[1] = 6 A[2] = 3
A[3] =  4 A[4] = 7 A[5] = 4
there are four inversions:

  (1,2)  (1,3)  (1,5)  (4,5)
so the function should return 4.

Complexity:

expected worst-case time complexity is O(N*log(N));
expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.

 */
public class ArrayInversionCount {
    private long s = 0;

    public int solution(int[] a) {
        sort(a, 0, a.length);
        if (s > 1000000000) {
            return -1;
        }
        return (int) s;
    }

    private int[] sort(final int[] a, final int from, final int to) {
        if (to == from) {
            return new int[0];
        }
        if (to - from == 1) {
            return new int[] { a[from] };
        }
        final int[] left = sort(a, from, (from + to) / 2);
        final int[] right = sort(a, (from + to) / 2, to);
        return merge(left, right);
    }

    private int[] merge(final int[] a, final int b[]) {
        final int[] out = new int[a.length + b.length];
        int i = 0;
        int j = 0;
        int k = 0;
        int count = 0;
        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) {
                out[k++] = a[i++];
                this.s += count;
            } else {
                out[k++] = b[j++];
                count++;
            }
        }
        while (i < a.length) {
            out[k++] = a[i++];
            this.s += count;
        }
        while (j < b.length) {
            out[k++] = b[j++];
        }
        return out;
    }
}
