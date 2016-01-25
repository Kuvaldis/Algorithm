package kuvaldis.algorithm.careercup;

import java.util.PriorityQueue;

/*

Find the n-th smallest multiple given a set of numbers.
For example, set = {4, 6}, n = 6
The sequence is 4, 6, 8, 12, 16, 18
Answer is 18

 */
public class NSmallestMultiple {
    private static class Val implements Comparable<Val> {
        private int value;
        private final int factor;

        private Val(int value, int factor) {
            this.value = value;
            this.factor = factor;
        }

        @Override
        public int compareTo(@SuppressWarnings("NullableProblems") final Val o) {
            return this.value - o.value;
        }
    }

    // O((n + a.length) * log(a.length)) time
    // O(a.length) space
    public int solution(final int[] a, final int n) {
        if (a == null || a.length == 0 || n == 0) {
            return 0;
        }
        final PriorityQueue<Val> queue = new PriorityQueue<>();
        // O(a.length * log(a.length)) time complexity
        // O(a.length) space complexity
        for (int value : a) {
            // add O(log(a.length))
            queue.add(new Val(value, value));
        }
        // O(n * log(a.length)
        Integer prev = null;
        // create and remove n - 1 multiples
        for (int i = 1; i < n; ) {
            // remove is O(1)
            final Val base = queue.remove();
            if (prev == null || base.value != prev) {
                prev = base.value;
                i++;
            }
            base.value += base.factor;
            // add again O(log(a.length))
            queue.add(base);
        }
        // remove n-th
        return queue.remove().value;
    }
}
