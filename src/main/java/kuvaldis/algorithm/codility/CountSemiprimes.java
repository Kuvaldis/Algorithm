package kuvaldis.algorithm.codility;

/*

CountSemiprimes
Count the semiprime numbers in the given range [a..b]

A prime is a positive integer X that has exactly two distinct divisors: 1 and X. The first few prime integers are 2, 3, 5, 7, 11 and 13.

A semiprime is a natural number that is the product of two (not necessarily distinct) prime numbers. The first few semiprimes are 4, 6, 9, 10, 14, 15, 21, 22, 25, 26.

You are given two non-empty zero-indexed arrays P and Q, each consisting of M integers. These arrays represent queries about the number of semiprimes within specified ranges.

Query K requires you to find the number of semiprimes within the range (P[K], Q[K]), where 1 ? P[K] ? Q[K] ? N.

For example, consider an integer N = 26 and arrays P, Q such that:

    P[0] = 1    Q[0] = 26
    P[1] = 4    Q[1] = 10
    P[2] = 16   Q[2] = 20
The number of semiprimes within each of these ranges is as follows:

(1, 26) is 10,
(4, 10) is 4,
(16, 20) is 0.
Write a function:

class Solution { public int[] solution(int N, int[] P, int[] Q); }

that, given an integer N and two non-empty zero-indexed arrays P and Q consisting of M integers, returns an array consisting of M elements specifying the consecutive answers to all the queries.

For example, given an integer N = 26 and arrays P, Q such that:

    P[0] = 1    Q[0] = 26
    P[1] = 4    Q[1] = 10
    P[2] = 16   Q[2] = 20
the function should return the values [10, 4, 0], as explained above.

Assume that:

N is an integer within the range [1..50,000];
M is an integer within the range [1..30,000];
each element of arrays P, Q is an integer within the range [1..N];
P[i] ? Q[i].
Complexity:

expected worst-case time complexity is O(N*log(log(N))+M);
expected worst-case space complexity is O(N+M), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.

 */
public class CountSemiprimes {
    public int[] solution(int n, int[] p, int[] q) {
        final int[] m = new int[n];
        int i = 2;
        // mark all non-prime numbers with minimal prime factor
        while ((long) i * (long) i <= n) {
            int k = i * i;
            while (k <= n) {
                if (m[k - 1] == 0) {
                    m[k - 1] = i;
                }
                k += i;
            }
            i++;
        }
        // if number / it's minimal prime factor is not a prime number, unmark
        for (i = m.length - 1; i >= 0; i--) {
            if (m[i] > 0 && m[(i + 1) / m[i] - 1] != 0) {
                m[i] = 0;
            }
        }
        // memorize number semiprimes from 0 to i
        int c = 0;
        for (i = 0; i < m.length; i++) {
            if (m[i] > 0) {
                c++;
            }
            m[i] = c;
        }
        // calculate result for ranges
        final int[] result = new int[p.length];
        for (i = 0; i < p.length; i++) {
            final int from = p[i] - 1;
            final int to = q[i] - 1;
            if (from == 0) {
                result[i] = m[to];
            } else {
                result[i] = m[to] - m[from - 1];
            }
        }
        return result;
    }
}
