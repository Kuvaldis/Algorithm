package kuvaldis.algorithm.geometry;

import org.junit.Test;

import static org.junit.Assert.*;

public class SegmentSetTest {

    @Test
    public void testHasIntersection() {
        // given
        final Segment segment1 = new Segment(new Point(1, 2), new Point(7, 6));
        final Segment segment2 = new Segment(new Point(2, 1), new Point(5, 4));
        final Segment segment3 = new Segment(new Point(5, 2), new Point(7, 7));
        final SegmentSet segmentSet = new SegmentSet(segment1, segment2, segment3);

        // when
        final boolean result = segmentSet.hasIntersection();

        // then
        assertTrue(result);
    }

    @Test
    public void testNoIntersections() {
        // given
        final Segment segment1 = new Segment(new Point(3, 3), new Point(5, 6));
        final Segment segment2 = new Segment(new Point(4, 1), new Point(1, 5));
        final SegmentSet segmentSet = new SegmentSet(segment1, segment2);

        // when
        final boolean result = segmentSet.hasIntersection();

        // then
        assertFalse(result);
    }
}