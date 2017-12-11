package kuvaldis.algorithm.geometry;

import kuvaldis.algorithm.geometry.ClosestPoints.Result;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class ClosestPointsTest {

    @Test
    public void testEmpty() throws Exception {
        // given
        final List<Point> points = Collections.emptyList();

        // when
        final Result result = new ClosestPoints(points).find();

        // then
        assertNull(result);
    }

    @Test
    public void testOnePoint() throws Exception {
        // given
        final List<Point> points = Collections.singletonList(new Point(1, 2));

        // when
        final Result result = new ClosestPoints(points).find();

        // then
        assertNull(result);
    }

    @Test
    public void testTwoPoints() throws Exception {
        // given
        final List<Point> points = Arrays.asList(new Point(1, 1), new Point(5, 4));

        // when
        final Result result = new ClosestPoints(points).find();

        // then
        assertEquals(5.0, result.getDistance(), 0.1);
    }

    @Test
    public void testTwoSame() throws Exception {
        // given
        final List<Point> points = Arrays.asList(new Point(1, 1), new Point(1, 1));

        // when
        final Result result = new ClosestPoints(points).find();

        // then
        assertEquals(0.0, result.getDistance(), 0.1);
    }

    @Test
    public void testMany() throws Exception {
        // given
        final Point[] ps = new Point[]{
                new Point(4, 5),
                new Point(1, 6),
                new Point(7, 4),
                new Point(4, 8),
                new Point(5, 4),
                new Point(7, 8),
                new Point(5, 7),
                new Point(5, 1),
                new Point(2, 2),
                new Point(3, 5)
        };

        final List<Point> points = Arrays.asList(ps);

        // when
        final Result result = new ClosestPoints(points).find();

        // then
        assertEquals(1.0, result.getDistance(), 0.1);
        assertEquals(2, result.getPoints().size());
        assertTrue(result.getPoints().contains(ps[0]));
        assertTrue(result.getPoints().contains(ps[9]));
    }

    @Test
    public void testFourPoints() throws Exception {
        // given
        final Point[] ps = new Point[]{
                new Point(0, 0),
                new Point(1, 1),
                new Point(1, 0),
                new Point(0, 1)
        };

        final List<Point> points = Arrays.asList(ps);

        // when
        final Result result = new ClosestPoints(points).find();

        // then
        assertEquals(1.0, result.getDistance(), 0.1);
        assertEquals(2, result.getPoints().size());
    }

    @Test
    public void testSameThreePoints() throws Exception {
        // given
        final Point[] ps = new Point[]{
                new Point(0, 0),
                new Point(0, 0),
                new Point(0, 0)
        };

        final List<Point> points = Arrays.asList(ps);

        // when
        final Result result = new ClosestPoints(points).find();

        // then
        assertEquals(0.0, result.getDistance(), 0.1);
        assertEquals(2, result.getPoints().size());
    }

    @Test
    public void testSameFourPoints() throws Exception {
        // given
        final Point[] ps = new Point[]{
                new Point(0, 0),
                new Point(0, 0),
                new Point(0, 0),
                new Point(0, 0)
        };

        final List<Point> points = Arrays.asList(ps);

        // when
        final Result result = new ClosestPoints(points).find();

        // then
        assertEquals(0.0, result.getDistance(), 0.1);
        assertEquals(2, result.getPoints().size());
    }

    @Test
    public void testIntermediate() throws Exception {
        // given
        final Point[] ps = new Point[]{
                new Point(0, 0),
                new Point(2, 0),
                new Point(3, 0),
                new Point(5, 0)
        };

        final List<Point> points = Arrays.asList(ps);

        // when
        final Result result = new ClosestPoints(points).find();

        // then
        assertEquals(1.0, result.getDistance(), 0.1);
        assertEquals(2, result.getPoints().size());
        assertTrue(result.getPoints().contains(ps[1]));
        assertTrue(result.getPoints().contains(ps[2]));
    }
}