package kuvaldis.algorithm.codility;

import java.util.Arrays;

/*

CountNonDivisible
Calculate the number of elements of an array that are not divisors of each element.

You are given a non-empty zero-indexed array A consisting of N integers.

For each number A[i] such that 0 ? i < N, we want to count the number of elements of the array that are not the divisors of A[i]. We say that these elements are non-divisors.

For example, consider integer N = 5 and array A such that:

    A[0] = 3
    A[1] = 1
    A[2] = 2
    A[3] = 3
    A[4] = 6
For the following elements:

A[0] = 3, the non-divisors are: 2, 6,
A[1] = 1, the non-divisors are: 3, 2, 3, 6,
A[2] = 2, the non-divisors are: 3, 3, 6,
A[3] = 3, the non-divisors are: 2, 6,
A[6] = 6, there aren't any non-divisors.
Write a function:

class Solution { public int[] solution(int[] A); }

that, given a non-empty zero-indexed array A consisting of N integers, returns a sequence of integers representing the amount of non-divisors.

The sequence should be returned as:

a structure Results (in C), or
a vector of integers (in C++), or
a record Results (in Pascal), or
an array of integers (in any other programming language).
For example, given:

    A[0] = 3
    A[1] = 1
    A[2] = 2
    A[3] = 3
    A[4] = 6
the function should return [2, 4, 3, 2, 0], as explained above.

Assume that:

N is an integer within the range [1..50,000];
each element of array A is an integer within the range [1..2 * N].
Complexity:

expected worst-case time complexity is O(N*log(N));
expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.

 */
public class CountNonDivisible {
    public int[] solution(int[] a) {
        final int[] s = new int[a.length];
        System.arraycopy(a, 0, s, 0, a.length);
        Arrays.sort(s); // O(n * log(n))
        // max value is <= 2 * N
        final int[] n = new int[a.length * 2];
        int c = 0;
        // loops give O(n * log(n)) because the total sum of steps is no more than
        // n/1 + n/2 + n/3 + ... + n/n which is O(n * log(n))
        for (int i = 0; i < s.length; i++) {
            c += 1;
            if (i + 1 < s.length && s[i] == s[i + 1]) {
                continue;
            }
            for (int j = s[i] - 1; j < n.length; j += s[i]) {
                n[j] += c;
            }
            c = 0;
        }
        for (int i = 0; i < a.length; i++) {
            s[i] = a.length - n[a[i] - 1];
        }
        return s;
    }
}
