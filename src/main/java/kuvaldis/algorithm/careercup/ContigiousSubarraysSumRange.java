package kuvaldis.algorithm.careercup;

import java.util.NavigableSet;
import java.util.TreeSet;

/*

http://careercup.com/question?id=5200686994161664

Given an array int32 arr[] of size n, return the number of non-empty contigious subarrays whose sum lies in range [a, b]

That is, implement the following naive algorithm faster than O(n^2)


def naive_algorithm(lst, a, b):
    result = 0
    for i in xrange(len(lst)):
        for j in xrange(i, len(lst)):
            if a <= sum(lst[i:j + 1]) <= b:
                result += 1
    return result
Examples:


count([1,2,3], 0, 3) = 3 # [1], [2], [3], [1, 2], [3]
count([-2,5,-1], -2, 2) = 3 # [-2], [-1], [-2, 5, -1]
You may assume that there are no overflows, that is sum(|x_i|) <= MAX_INT - 1

 */
public class ContigiousSubarraysSumRange {
    public int solution(final int[] arr, final int a, final int b) {
        // we use tree set to store all cumulative sums in sorted order
        // the index field is added so we may store not only unique sums
        // (for instance, for the case [1, 2, 1, 0, 0, 0], 0, 3
        final TreeSet<Entry> set = new TreeSet<>();
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            final int value = arr[i];
            sum += value;
            set.add(new Entry(sum, i));
        }
        int prevSum = 0;
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            final int value = arr[i];
            // we need to find all the cumulative sums, which fall into the range, but without previous cumulative sum
            // because the sums in the set include it, while we are searching for the sums without previous values
            // without prevSum the sums would be sum[i] - prevSum, sum[i + 1] - prevSum etc.
            // and they should fall into the range [a, b] => a <= sum[i] - prevSum <= b,
            // which gives us a + prevSum <= sum[i] <= b + sum[i]
            final int from = a + prevSum;
            final int to = b + prevSum;
            // when searching we basically make the indexes out of the possible range
            // so when we have several entries with the same value, they all go to the subset
            // because from's index is always less than left border value, the same for the right border
            final NavigableSet<Entry> subset = set.subSet(new Entry(from, -1), true, new Entry(to, arr.length), true);
            result += subset.size();
            prevSum += value;
            // remove the current cumulative sum entry, so we will not calculate it again
            set.remove(new Entry(prevSum, i));
        }
        return result;
    }

    private static class Entry implements Comparable<Entry> {
        private int value;
        private int index;

        public Entry(final int value, final int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(@SuppressWarnings("NullableProblems") final Entry o) {
            if (this.value != o.value) {
                return this.value - o.value;
            } else {
                return this.index - o.index;
            }
        }
    }
}
