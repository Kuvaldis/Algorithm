package kuvaldis.algorithm.codility;

/*

ChocolatesByNumbers
There are N chocolates in a circle. Count the number of chocolates you will eat.

Two positive integers N and M are given. Integer N represents the number of chocolates arranged in a circle, numbered from 0 to N ? 1.

You start to eat the chocolates. After eating a chocolate you leave only a wrapper.

You begin with eating chocolate number 0. Then you omit the next M ? 1 chocolates or wrappers on the circle, and eat the following one.

More precisely, if you ate chocolate number X, then you will next eat the chocolate with number (X + M) modulo N (remainder of division).

You stop eating when you encounter an empty wrapper.

For example, given integers N = 10 and M = 4. You will eat the following chocolates: 0, 4, 8, 2, 6.

The goal is to count the number of chocolates that you will eat, following the above rules.

Write a function:

int solution(int N, int M);

that, given two positive integers N and M, returns the number of chocolates that you will eat.

For example, given integers N = 10 and M = 4. the function should return 5, as explained above.

Assume that:

N and M are integers within the range [1..1,000,000,000].
Complexity:

expected worst-case time complexity is O(log(N+M));
expected worst-case space complexity is O(log(N+M)).

 */
public class ChocolatesByNumbers {
    public int solution(int n, int m) {
        // we have to find less common multiple.
        // first time we meet a wrapper may happen only at the beginning of the circle.
        // it may happen after at least one circle is finished.
        // they meet at the 0th position the next time
        // it cannot be earlier since it's may be the only first time when the visited positions are repeated
        // so k*m = q*n in this case
        // the first time it happens when k * m is the less common multiple.
        // lcm(n, m) = n * m / gcm(n, m)
        // k = lcm(n, m) / m = n / gcm(n, m)
        return n / gcm(n, m);
    }

    private int gcm(final int n, final int m) {
        if (n % m == 0) {
            return m;
        } else {
            return gcm(m, n % m);
        }
    }
}
