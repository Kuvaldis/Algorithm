package kuvaldis.algorithm.codility;

/*

MaxSliceSum
Find a maximum sum of a compact subsequence of array elements.

A non-empty zero-indexed array A consisting of N integers is given. A pair of integers (P, Q), such that 0 ? P ? Q < N, is called a slice of array A. The sum of a slice (P, Q) is the total of A[P] + A[P+1] + ... + A[Q].

Write a function:

class Solution { public int solution(int[] A); }

that, given an array A consisting of N integers, returns the maximum sum of any slice of A.

For example, given array A such that:

A[0] = 3  A[1] = 2  A[2] = -6
A[3] = 4  A[4] = 0
the function should return 5 because:

(3, 4) is a slice of A that has sum 4,
(2, 2) is a slice of A that has sum ?6,
(0, 1) is a slice of A that has sum 5,
no other slice of A has sum greater than (0, 1).
Assume that:

N is an integer within the range [1..1,000,000];
each element of array A is an integer within the range [?1,000,000..1,000,000];
the result will be an integer within the range [?2,147,483,648..2,147,483,647].
Complexity:

expected worst-case time complexity is O(N);
expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.

 */
public class MaxSliceSum {

    public int solution(int[] a) {
        final long[] lms = new long[a.length];
        final long[] rms = new long[a.length];
        long minSum = 0;
        long sum = 0;
        for (int i = 0; i < a.length; i++) {
            lms[i] = minSum;
            sum += (long) a[i];
            if (sum < minSum) {
                minSum = sum;
            }
        }
        final long total = sum;
        minSum = 0;
        sum = 0;
        for (int i = a. length - 1; i >= 0; i--) {
            rms[i] = minSum;
            sum += (long) a[i];
            if (sum < minSum) {
                minSum = sum;
            }
        }
        long result = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            sum = total - lms[i] - rms[i];
            if (sum > result) {
                result = sum;
            }
        }
        return (int) result;
    }
}
