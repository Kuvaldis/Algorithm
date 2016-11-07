package kuvaldis.algorithm.codility;

import java.util.Arrays;

/*

CountTriangles
Count the number of triangles that can be built from a given set of edges.

A zero-indexed array A consisting of N integers is given. A triplet (P, Q, R) is triangular if it is possible to build a triangle with sides of lengths A[P], A[Q] and A[R]. In other words, triplet (P, Q, R) is triangular if 0 ? P < Q < R < N and:

A[P] + A[Q] > A[R],
A[Q] + A[R] > A[P],
A[R] + A[P] > A[Q].
For example, consider array A such that:

  A[0] = 10    A[1] = 2    A[2] = 5
  A[3] = 1     A[4] = 8    A[5] = 12
There are four triangular triplets that can be constructed from elements of this array, namely (0, 2, 4), (0, 2, 5), (0, 4, 5), and (2, 4, 5).

Write a function:

class Solution { public int solution(int[] A); }

that, given a zero-indexed array A consisting of N integers, returns the number of triangular triplets in this array.

For example, given array A such that:

  A[0] = 10    A[1] = 2    A[2] = 5
  A[3] = 1     A[4] = 8    A[5] = 12
the function should return 4, as explained above.

Assume that:

N is an integer within the range [0..1,000];
each element of array A is an integer within the range [1..1,000,000,000].
Complexity:

expected worst-case time complexity is O(N2);
expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.

 */
public class CountTriangles {
    public int solution(int[] a) {
        if (a.length < 3) {
            return 0;
        }
        Arrays.sort(a);
        int result = 0;
        for (int p = 0; p < a.length - 2; p++) {
            int q = p + 1;
            for (int r = p + 2; r < a.length; r++) {
                while (q < r && a[p] + a[q] <= a[r]) {
                    q++;
                }
                result = result + r - q;
            }
        }
        return result;
    }
}
