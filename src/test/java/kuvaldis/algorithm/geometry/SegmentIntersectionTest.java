package kuvaldis.algorithm.geometry;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SegmentIntersectionTest {

    @Test
    public void testSimpleIntersection() throws Exception {
        // given
        final Segment s1 = new Segment(new Point(1, 1), new Point(2, 2));
        final Segment s2 = new Segment(new Point(1, 2), new Point(2, 1));

        // when
        final boolean result = s1.intersects(s2);

        // then
        assertTrue(result);
    }

    @Test
    public void testSimpleIntersectionReversedCoordinates() throws Exception {
        // given
        final Segment s1 = new Segment(new Point(1, 1), new Point(2, 2));
        final Segment s2 = new Segment(new Point(2, 1), new Point(1, 2));

        // when
        final boolean result = s1.intersects(s2);

        // then
        assertTrue(result);
    }

    @Test
    public void testOneCommonPoint() throws Exception {
        // given
        final Segment s1 = new Segment(new Point(1, 1), new Point(5, 4));
        final Segment s2 = new Segment(new Point(3, 3), new Point(5, 4));

        // when
        final boolean result = s1.intersects(s2);

        // then
        assertTrue(result);
    }

    @Test
    public void testOneSegmentPointOnOtherSegment() throws Exception {
        // given
        final Segment s1 = new Segment(new Point(1, 1), new Point(5, 4));
        final Segment s2 = new Segment(new Point(3, 3), new Point(7, 5));

        // when
        final boolean result = s1.intersects(s2);

        // then
        assertTrue(result);
    }

    @Test
    public void testSameLineCommonPart() throws Exception {
        // given
        final Segment s1 = new Segment(new Point(2, 1), new Point(8, 4));
        final Segment s2 = new Segment(new Point(4, 2), new Point(10, 5));

        // when
        final boolean result = s1.intersects(s2);

        // then
        assertTrue(result);
    }

    @Test
    public void testSameLineOneWithinTheOther() throws Exception {
        // given
        final Segment s1 = new Segment(new Point(2, 6), new Point(4, 12));
        final Segment s2 = new Segment(new Point(1, 3), new Point(5, 15));

        // when
        final boolean result = s1.intersects(s2);

        // then
        assertTrue(result);
    }

    @Test
    public void testCoincidingSegments() throws Exception {
        // given
        final Segment s1 = new Segment(new Point(1, 2), new Point(3, 4));
        final Segment s2 = new Segment(new Point(1, 2), new Point(3, 4));

        // when
        final boolean result = s1.intersects(s2);

        // then
        assertTrue(result);
    }

    @Test
    public void testParallelSegments() throws Exception {
        // given
        final Segment s1 = new Segment(new Point(1, 1), new Point(2, 1));
        final Segment s2 = new Segment(new Point(1, 2), new Point(2, 2));

        // when
        final boolean result = s1.intersects(s2);

        // then
        assertFalse(result);
    }

    @Test
    public void testCloseButNotIntersected() throws Exception {
        // given
        final Segment s1 = new Segment(new Point(1, 1), new Point(5, 4));
        final Segment s2 = new Segment(new Point(3, 3), new Point(7, 6));

        // when
        final boolean result = s1.intersects(s2);

        // then
        assertFalse(result);
    }

    @Test
    public void testSameLineNotIntersectedFrames() throws Exception {
        // given
        final Segment s1 = new Segment(new Point(1, 1), new Point(2, 2));
        final Segment s2 = new Segment(new Point(4, 4), new Point(6, 6));

        // when
        final boolean result = s1.intersects(s2);

        // then
        assertFalse(result);
    }
}