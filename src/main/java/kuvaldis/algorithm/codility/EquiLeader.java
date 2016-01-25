package kuvaldis.algorithm.codility;

/*

EquiLeader
Find the index S such that the leaders of the sequences A[0], A[1], ..., A[S] and A[S + 1], A[S + 2], ..., A[N - 1] are the same.

A non-empty zero-indexed array A consisting of N integers is given.

The leader of this array is the value that occurs in more than half of the elements of A.

An equi leader is an index S such that 0 ? S < N ? 1 and two sequences A[0], A[1], ..., A[S] and A[S + 1], A[S + 2], ..., A[N ? 1] have leaders of the same value.

For example, given array A such that:

    A[0] = 4
    A[1] = 3
    A[2] = 4
    A[3] = 4
    A[4] = 4
    A[5] = 2
we can find two equi leaders:

0, because sequences: (4) and (3, 4, 4, 4, 2) have the same leader, whose value is 4.
2, because sequences: (4, 3, 4) and (4, 4, 2) have the same leader, whose value is 4.
The goal is to count the number of equi leaders.

Write a function:

int solution(int A[], int N);

that, given a non-empty zero-indexed array A consisting of N integers, returns the number of equi leaders.

For example, given:

    A[0] = 4
    A[1] = 3
    A[2] = 4
    A[3] = 4
    A[4] = 4
    A[5] = 2
the function should return 2, as explained above.

Assume that:

N is an integer within the range [1..100,000];
each element of array A is an integer within the range [?1,000,000,000..1,000,000,000].
Complexity:

expected worst-case time complexity is O(N);
expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.

 */
public class EquiLeader {
    public int solution(int[] a) {
        int v = 0;
        int s = 0;
        for (final int value : a) {
            if (s == 0) {
                v = value;
                s = 1;
            } else {
                if (v != value) {
                    s--;
                } else {
                    s++;
                }
            }
        }
        if (s == 0) {
            return 0;
        }
        int total = 0;
        for (final int value : a) {
            if (value == v) {
                total++;
            }
        }
        if (total < (a.length / 2 + 1)) {
            return 0;
        }
        int n = 0;
        int r = 0;
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] == v) {
                n++;
            }
            if ((n > (i + 1) / 2) && (total - n) > (a.length - i - 1) / 2) {
                r++;
            }
        }
        return r;
    }
}
