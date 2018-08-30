package kuvaldis.algorithm.cake;

import kuvaldis.algorithm.cake.RectangularLove.Rectangle;
import org.junit.Test;

import static org.junit.Assert.*;

public class RectangularLoveTest {

    @Test
    public void testIntersection() {
        // given
        final Rectangle r1 = new Rectangle(0, 2, 3, 3);
        final Rectangle r2 = new Rectangle(2, 0, 3, 3);

        // when
        final Rectangle intersection = new RectangularLove().intersection(r1, r2);

        // then
        assertEquals(2, intersection.getLeftX());
        assertEquals(2, intersection.getBottomY());
        assertEquals(1, intersection.getWidth());
        assertEquals(1, intersection.getHeight());
    }

    @Test
    public void testIntersection2() {
        // given
        final Rectangle r1 = new Rectangle(0, 2, 3, 3);
        final Rectangle r2 = new Rectangle(2, 0, 3, 6);

        // when
        final Rectangle intersection = new RectangularLove().intersection(r1, r2);

        // then
        assertEquals(2, intersection.getLeftX());
        assertEquals(2, intersection.getBottomY());
        assertEquals(1, intersection.getWidth());
        assertEquals(3, intersection.getHeight());
    }

    @Test
    public void testNoIntersection() {
        // given
        final Rectangle r1 = new Rectangle(0, 2, 3, 3);
        final Rectangle r2 = new Rectangle(4, 0, 3, 3);

        // when
        final Rectangle intersection = new RectangularLove().intersection(r1, r2);

        // then
        assertNull(intersection);
    }

    @Test
    public void testCommonEdge() {
        // given
        final Rectangle r1 = new Rectangle(0, 2, 3, 3);
        final Rectangle r2 = new Rectangle(3, 0, 3, 3);

        // when
        final Rectangle intersection = new RectangularLove().intersection(r1, r2);

        // then
        assertNull(intersection);
    }

    @Test
    public void testOneInsideAnother() {
        // given
        final Rectangle r1 = new Rectangle(1, 1, 2, 2);
        final Rectangle r2 = new Rectangle(0, 0, 4, 4);

        // when
        final Rectangle intersection = new RectangularLove().intersection(r1, r2);

        // then
        assertEquals(1, intersection.getLeftX());
        assertEquals(1, intersection.getBottomY());
        assertEquals(2, intersection.getWidth());
        assertEquals(2, intersection.getHeight());
    }
}