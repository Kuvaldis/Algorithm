package kuvaldis.algorithm.leetcode;

import kuvaldis.algorithm.leetcode.IntervalListIntersections.Interval;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class IntervalListIntersectionsTest {

    @Test
    public void testExample() {
        // given
        final List<Interval> a = Arrays.asList(
                new Interval(0, 2),
                new Interval(5, 10),
                new Interval(13, 23),
                new Interval(24, 25)
        );
        final List<Interval> b = Arrays.asList(
                new Interval(1, 5),
                new Interval(8, 12),
                new Interval(15, 24),
                new Interval(25, 26)
        );
        final List<Interval> expectedIntersections = Arrays.asList(
                new Interval(1, 2),
                new Interval(5, 5),
                new Interval(8, 10),
                new Interval(15, 23),
                new Interval(24, 24),
                new Interval(25, 25)
        );

        // when
        final List<Interval> intersections = new IntervalListIntersections().findIntersections(a, b);

        // then
        assertEquals(expectedIntersections, intersections);
    }

    @Test
    public void testSameInterval() {
        // given
        final List<Interval> a = Arrays.asList(
                new Interval(0, 2),
                new Interval(5, 10),
                new Interval(13, 23),
                new Interval(24, 25)
        );

        // when
        final List<Interval> intersections = new IntervalListIntersections().findIntersections(a, a);

        // then
        assertEquals(a, intersections);
    }
}