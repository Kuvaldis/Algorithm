package kuvaldis.algorithm.programcreek;

import java.util.*;

/*

Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] find the minimum number of conference rooms required.

 */
public class MeetingRooms {

    public static class Interval implements Comparable<Interval> {
        // inclusive
        private final int from;
        // exclusive
        private final int to;

        public Interval(final int from,
                        final int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public int compareTo(final Interval that) {
            return this.from - that.from;
        }
    }

    public int calculate(final List<Interval> intervals) {
        // lets assume we cannot modify initial list
        final List<Interval> sortedIntervals = new ArrayList<>(intervals);
        // merge sort, sort of =)
        Collections.sort(sortedIntervals);        
        // used to keep track of rooms end times
        // PriorityQueue is based on heap
        final Queue<Integer> endTimes = new PriorityQueue<Integer>();
        for (Interval interval : sortedIntervals) {
            final Integer minEnd = endTimes.peek();
            if (minEnd != null && minEnd <= interval.from) {
                endTimes.poll();
            }
            endTimes.offer(interval.to);
        }
        return endTimes.size();
    }
}
