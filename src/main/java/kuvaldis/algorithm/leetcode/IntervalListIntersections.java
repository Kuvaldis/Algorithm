package kuvaldis.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.

Return the intersection of these two interval lists.

(Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.  The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval.  For example, the intersection of [1, 3] and [2, 4] is [2, 3].)



Example 1:

Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
Reminder: The inputs and the desired output are lists of Interval objects, and not arrays or lists.

 */
public class IntervalListIntersections {

    public List<Interval> findIntersections(final List<Interval> a, final List<Interval> b) {
        final List<Interval> result = new ArrayList<>();
        int ia = 0;
        int ib = 0;
        while (ia < a.size() && ib < b.size()) {
            final Interval intervalFromA = a.get(ia);
            final Interval intervalFromB = b.get(ib);
            final int intersectionStart = Math.max(intervalFromA.from, intervalFromB.from);
            final int intersectionEnd = Math.min(intervalFromA.to, intervalFromB.to);
            if (intersectionStart <= intersectionEnd) {
                result.add(new Interval(intersectionStart, intersectionEnd));
            }
            if (intervalFromA.to < intervalFromB.to) {
                ia++;
            } else if (intervalFromA.to > intervalFromB.to) {
                ib++;
            } else {
                ia++;
                ib++;
            }
        }
        return result;
    }

    public static class Interval {
        public final int from;
        public final int to;

        public Interval(final int from, final int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final Interval interval = (Interval) o;
            return from == interval.from &&
                    to == interval.to;
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }

        @Override
        public String toString() {
            return "[" + from + " : " + to + "]";
        }
    }
}
