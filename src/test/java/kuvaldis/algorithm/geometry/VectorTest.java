package kuvaldis.algorithm.geometry;

import org.junit.Test;

import static org.junit.Assert.*;

public class VectorTest {

    @Test
    public void testTurn() throws Exception {
        // given
        final Point p1 = new Point(1, 1);
        final Point p2 = new Point(2, 4);
        final Point p3 = new Point(3, 2);

        final Vector v12 = new Vector(p1, p2);
        final Vector v13 = new Vector(p1, p3);
        final Vector v31 = new Vector(p3, p1);
        final Vector v32 = new Vector(p3, p2);

        // when
        final long v12tov13 = v12.multiply(v13);
        final long v13tov12 = v13.multiply(v12);
        final long v31tov32 = v31.multiply(v32);
        final long v32tov31 = v32.multiply(v31);

        // then
        System.out.println(v12tov13);
        System.out.println(v13tov12);
        System.out.println(v31tov32);
        System.out.println(v32tov31);
        assertTrue(v12tov13 < 0);
        assertTrue(v13tov12 > 0);
        assertTrue(v31tov32 < 0);
        assertTrue(v32tov31 > 0);
    }

    @Test
    public void testAngle() throws Exception {
        // given
        final Point p0 = new Point(1, 1);
        final Point p1 = new Point(3, 2);
        final Point p2 = new Point(2, 4);
        final Point p3 = new Point(1, 2);
        final Point p4 = new Point(0, 3);

        final Vector hv = new Vector(p0, new Point(3, 1));

        final Vector v1 = new Vector(p0, p1);
        final Vector v2 = new Vector(p0, p2);
        final Vector v3 = new Vector(p0, p3);
        final Vector v4 = new Vector(p0, p4);

        // when
        final double cos0 = hv.cos(hv);
        final double cos1 = hv.cos(v1);
        final double cos2 = hv.cos(v2);
        final double cos3 = hv.cos(v3);
        final double cos4 = hv.cos(v4);

        assertEquals(cos0, 1.00, 0.1);
        assertEquals(cos1, 0.89, 0.1);
        assertEquals(cos2, 0.31, 0.1);
        assertEquals(cos3, 0.00, 0.1);
        assertEquals(cos4, -0.44, 0.1);

        System.out.println(cos0);
        System.out.println(cos1);
        System.out.println(cos2);
        System.out.println(cos3);
        System.out.println(cos4);
    }
}