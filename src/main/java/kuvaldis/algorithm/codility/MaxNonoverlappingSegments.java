package kuvaldis.algorithm.codility;

/*

1. MaxNonoverlappingSegments
Find a maximal set of non-overlapping segments.
Task description
Located on a line are N segments, numbered from 0 to N ? 1, whose positions are given in zero-indexed arrays A and B. For each I (0 ? I < N) the position of segment I is from A[I] to B[I] (inclusive). The segments are sorted by their ends, which means that B[K] ? B[K + 1] for K such that 0 ? K < N ? 1.

Two segments I and J, such that I ? J, are overlapping if they share at least one common point. In other words, A[I] ? A[J] ? B[I] or A[J] ? A[I] ? B[J].

We say that the set of segments is non-overlapping if it contains no two overlapping segments. The goal is to find the size of a non-overlapping set containing the maximal number of segments.

For example, consider arrays A, B such that:

    A[0] = 1    B[0] = 5
    A[1] = 3    B[1] = 6
    A[2] = 7    B[2] = 8
    A[3] = 9    B[3] = 9
    A[4] = 9    B[4] = 10
The segments are shown in the figure below.



The size of a non-overlapping set containing a maximal number of segments is 3. For example, possible sets are {0, 2, 3}, {0, 2, 4}, {1, 2, 3} or {1, 2, 4}. There is no non-overlapping set with four segments.

Write a function:

class Solution { public int solution(int[] A, int[] B); }

that, given two zero-indexed arrays A and B consisting of N integers, returns the size of a non-overlapping set containing a maximal number of segments.

For example, given arrays A, B shown above, the function should return 3, as explained above.

Assume that:

N is an integer within the range [0..30,000];
each element of arrays A, B is an integer within the range [0..1,000,000,000];
A[I] ? B[I], for each I (0 ? I < N);
B[K] ? B[K + 1], for each K (0 ? K < N ? 1).
Complexity:

expected worst-case time complexity is O(N);
expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.

 */
public class MaxNonoverlappingSegments {
    public int solution(int[] a, int[] b) {
        // we use greedy approach here
        // since B[i] <= B[i + 1] and overlapping segments has B[i] < A[j],
        // each added to the set segment increases right border in the segment,
        // so only segments having A less than this value may be added to the set.
        // for 1th step we add the 1th segment.
        // so other segments with left border greater than it's right border are not overlapping with it
        // for 2nd step: if 2nd element doesn't overlap the first it should be added to the set
        // if it does, then 2nd element makes another set with the same size of 1.
        // for the 3rd step: if 3rd element fits into the second set, it also fits into the first
        // suppose on ith step we have two sets with the max size
        // 1. if next segment fits both then i + 1 step will be the same case
        // 2. if next segment fits only the set with b max being the minimal of two, then one of the segments gets size more than another with max be the same or greater than another's
        // for the case when there are two sets with size difference equals to 1
        // 1. set with greater max b has less segments. next segment which fits to it also fits to the other.
        // 2. sets has the same max b. next segment fitting to both will increase both sets
        // 3. set with greater max b has more segments. the segment goes either both (same case) or the set with less max be (case when there are two sets with max size).
        // in other words the set built by adding first possible non-overlapping segment to it has the size not less than the sizes of other sets.
        if (a.length == 0) {
            return 0;
        }
        int result = 0;
        int besti = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i] > b[besti]) {
                result++;
                besti = i;
            }
        }
        return result + 1;
    }
}
