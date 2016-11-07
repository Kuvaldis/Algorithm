package kuvaldis.algorithm.codility;

/*

You are given integers K, M and a non-empty zero-indexed array A consisting of N integers. Every element of the array is not greater than M.

You should divide this array into K blocks of consecutive elements. The size of the block is any integer between 0 and N. Every element of the array should belong to some block.

The sum of the block from X to Y equals A[X] + A[X + 1] + ... + A[Y]. The sum of empty block equals 0.

The large sum is the maximal sum of any block.

For example, you are given integers K = 3, M = 5 and array A such that:

  A[0] = 2
  A[1] = 1
  A[2] = 5
  A[3] = 1
  A[4] = 2
  A[5] = 2
  A[6] = 2
The array can be divided, for example, into the following blocks:

[2, 1, 5, 1, 2, 2, 2], [], [] with a large sum of 15;
[2], [1, 5, 1, 2], [2, 2] with a large sum of 9;
[2, 1, 5], [], [1, 2, 2, 2] with a large sum of 8;
[2, 1], [5, 1], [2, 2, 2] with a large sum of 6.
The goal is to minimize the large sum. In the above example, 6 is the minimal large sum.

Write a function:

class Solution { public int solution(int K, int M, int[] A); }

that, given integers K, M and a non-empty zero-indexed array A consisting of N integers, returns the minimal large sum.

For example, given K = 3, M = 5 and array A such that:

  A[0] = 2
  A[1] = 1
  A[2] = 5
  A[3] = 1
  A[4] = 2
  A[5] = 2
  A[6] = 2
the function should return 6, as explained above.

Assume that:

N and K are integers within the range [1..100,000];
M is an integer within the range [0..10,000];
each element of array A is an integer within the range [0..M].
Complexity:

expected worst-case time complexity is O(N*log(N+M));
expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.

 */
public class MinMaxDivision {

    // O(n * log(m * n)) in total
    // I have no idea how to make it O(n * log(m + n)), but Codility evaluates the solution as O(n * log(m + n)),
    // probably there is some proof...
    public int solution(int k, int m, int[] a) {
        // look for max value. it may be less than m
        // the result cannot be less than m
        // count the sum. the result cannot be more than the sum
        // O(n)
        int max = 0;
        int sum = 0;
        for (final int value : a) {
            if (value > max) {
                max = value;
            }
            sum += value;
        }
        // two trivial cases. if k is 1, then all the elements inside one group
        if (k == 1) {
            return sum;
        }
        // if k is more than number of elements, some of them may be empty
        // but all the elements may be split to the groups of 1 element
        // large sum will be equal to max element value
        if (k >= a.length) {
            return max;
        }
        // the result is somewhere between max(n) and sum(n)
        // use binary search to find the correct one
        // max value of sum is n * m, max(n) can be 0 if all the elements are 0
        // so at most we perform binary search on the interval from 0 to n * m
        // which is O(log(n * m))
        int begin = max;
        int end = sum;
        int result = sum;
        // O(n * log(m * n)) in total
        while (begin <= end) {
            int medium = (begin + end) / 2;
            if (isDivisible(a, medium, k)) {
                result = medium;
                end = medium - 1;
            } else {
                begin = medium + 1;
            }
        }
        return result;
    }

    // O(n)
    private boolean isDivisible(final int[] a, final int size, final int k) {
        int sum = 0;
        int stepsLeft = k - 1; // first is started already
        for (final int value : a) {
            sum += value;
            if (sum > size) {
                sum = value;
                stepsLeft--; // start a new step
            }
        }
        return stepsLeft >= 0;
    }
}
