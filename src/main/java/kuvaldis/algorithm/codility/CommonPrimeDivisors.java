package kuvaldis.algorithm.codility;

/*

CommonPrimeDivisors
Check whether two numbers have the same prime divisors.

A prime is a positive integer X that has exactly two distinct divisors: 1 and X. The first few prime integers are 2, 3, 5, 7, 11 and 13.

A prime D is called a prime divisor of a positive integer P if there exists a positive integer K such that D * K = P. For example, 2 and 5 are prime divisors of 20.

You are given two positive integers N and M. The goal is to check whether the sets of prime divisors of integers N and M are exactly the same.

For example, given:

N = 15 and M = 75, the prime divisors are the same: {3, 5};
N = 10 and M = 30, the prime divisors aren't the same: {2, 5} is not equal to {2, 3, 5};
N = 9 and M = 5, the prime divisors aren't the same: {3} is not equal to {5}.
Write a function:

class Solution { public int solution(int[] A, int[] B); }

that, given two non-empty zero-indexed arrays A and B of Z integers, returns the number of positions K for which the prime divisors of A[K] and B[K] are exactly the same.

For example, given:

    A[0] = 15   B[0] = 75
    A[1] = 10   B[1] = 30
    A[2] = 3    B[2] = 5
the function should return 1, because only one pair (15, 75) has the same set of prime divisors.

Assume that:

Z is an integer within the range [1..6,000];
each element of arrays A, B is an integer within the range [1..2,147,483,647].
Complexity:

expected worst-case time complexity is O(Z*log(max(A)+max(B))2);
expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.

 */
public class CommonPrimeDivisors {
    public int solution(int[] a, int[] b) {
        int c = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == 1 && b[i] == 1) {
                c++;
                continue;
            }
            // two numbers n and m may have the same set of prime factors only if
            // n / gcm and m / gcm contain only factors included into gcm
            // n / gcm and m / gcm don't contain the same factors or they'd be included to gcm
            final int g = gcm(a[i], b[i]); // no more than max(a[i]) or max(b[i])
            int m = a[i] / g; // no more than max(a[i])
            int gcm = g;
            boolean containsA = false;
            // the loop is performed no more than original gcm, so it's no more than max(a[i]) or max(b[i]) as well as max(a[i]) + max(b[i])
            while (true) {
                if (gcm % m == 0) {
                    containsA = true;
                    break;
                } else {
                    final int newG = gcm(gcm, m); // each one O(max(a[i]) + max(b[i]))
                    if (newG == 1) {
                        break;
                    }
                    m = m / newG;
                    gcm = newG;
                }
            }
            m = b[i] / g;
            gcm = g;
            boolean containsB = false;
            while (true) {
                if (gcm % m == 0) {
                    containsB = true;
                    break;
                } else {
                    final int newG = gcm(gcm, m);
                    if (newG == 1) {
                        break;
                    }
                    m = m / newG;
                    gcm = newG;
                }
            }
            if (containsA && containsB) {
                c++;
            }
        }
        return c;
    }

    private int gcm(final int a, final int b) {
        if (a % b == 0) {
            return b;
        } else {
            return gcm(b, a % b);
        }
    }
}
