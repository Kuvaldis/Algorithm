package kuvaldis.algorithm.geometry;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SegmentContainsPointTest {

    @Test
    public void testSimple() throws Exception {
        // given
        final Segment segment = new Segment(new Point(0, 0), new Point(2, 2));
        final Point point = new Point(1, 1);

        // when
        final boolean result = segment.contains(point);

        // then
        assertTrue(result);
    }

    @Test
    public void testSimpleRevert() throws Exception {
        // given
        final Segment segment = new Segment(new Point(2, 2), new Point(0, 0));
        final Point point = new Point(1, 1);

        // when
        final boolean result = segment.contains(point);

        // then
        assertTrue(result);
    }


    @Test
    public void testSimpleOutside() throws Exception {
        // given
        final Segment segment = new Segment(new Point(0, 0), new Point(1, 1));
        final Point point = new Point(2, 2);

        // when
        final boolean result = segment.contains(point);

        // then
        assertFalse(result);
    }

    @Test
    public void testSameStart() throws Exception {
        // given
        final Segment segment = new Segment(new Point(1, 1), new Point(2, 3));
        final Point point = new Point(1, 1);

        // when
        final boolean result = segment.contains(point);

        // then
        assertTrue(result);
    }

    @Test
    public void testSameEnd() throws Exception {
        // given
        final Segment segment = new Segment(new Point(1, 1), new Point(2, 3));
        final Point point = new Point(2, 3);

        // when
        final boolean result = segment.contains(point);

        // then
        assertTrue(result);
    }

    @Test
    public void testABitMoreComplex() throws Exception {
        // given
        final Segment segment = new Segment(new Point(5, 6), new Point(-1, -3));
        final Point point = new Point(3, 3);

        // when
        final boolean result = segment.contains(point);

        // then
        assertTrue(result);

    }

    @Test
    public void testCloseButNoCigar() throws Exception {
        // given
        final Segment segment = new Segment(new Point(5, 6), new Point(-1, -3));
        final Point point = new Point(3, 4);

        // when
        final boolean result = segment.contains(point);

        // then
        assertFalse(result);
    }
}