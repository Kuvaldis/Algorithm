package kuvaldis.algorithm.geometry;

import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class GrahamConvexHullTest {

    @Test
    public void testThreePoints() throws Exception {
        // given
        final GrahamConvexHull hull = new GrahamConvexHull(new Point(3, 4), new Point(2, 1), new Point(5, 2));

        // when
        final Set<Point> result = hull.build();

        // then
        final Set<Point> expected = Stream.of(new Point(3, 4), new Point(2, 1), new Point(5, 2))
                .collect(Collectors.toSet());
        assertEquals(expected, result);
    }

    @Test
    public void testFourPoints() throws Exception {
        // given
        final GrahamConvexHull hull = new GrahamConvexHull(new Point(3, 4),
                new Point(2, 1), new Point(5, 2), new Point(4, 6));

        // when
        final Set<Point> result = hull.build();

        // then
        final Set<Point> expected = Stream.of(new Point(3, 4),
                new Point(2, 1), new Point(5, 2), new Point(4, 6))
                .collect(Collectors.toSet());
        assertEquals(expected, result);
    }

    @Test
    public void testFivePointsOneOutsidePoints() throws Exception {
        // given
        final GrahamConvexHull hull = new GrahamConvexHull(new Point(3, 4),
                new Point(2, 1), new Point(5, 2), new Point(4, 6),
                new Point(4, 2));

        // when
        final Set<Point> result = hull.build();

        // then
        final Set<Point> expected = Stream.of(new Point(3, 4),
                new Point(2, 1), new Point(5, 2), new Point(4, 6))
                .collect(Collectors.toSet());
        assertEquals(expected, result);
    }

    @Test
    public void testComplex() throws Exception {
        // given
        final Point[] p = new Point[13];
        p[0] = new Point(5, 2);
        p[1] = new Point(17, 4);
        p[2] = new Point(17, 6);
        p[3] = new Point(19, 8);
        p[4] = new Point(16, 8);
        p[5] = new Point(15, 9);
        p[6] = new Point(11, 10);
        p[7] = new Point(9, 9);
        p[8] = new Point(8, 7);
        p[9] = new Point(7, 9);
        p[10] = new Point(7, 13);
        p[11] = new Point(5, 8);
        p[12] = new Point(2, 6);

        // when
        final Set<Point> result = new GrahamConvexHull(p).build();

        // then
        final Set<Point> expected = Stream.of(p[0], p[1], p[3], p[10], p[12]).collect(Collectors.toSet());
        assertEquals(expected, result);
    }
}