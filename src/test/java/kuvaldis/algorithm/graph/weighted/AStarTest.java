package kuvaldis.algorithm.graph.weighted;

import org.junit.Test;

import java.util.List;

import static kuvaldis.algorithm.graph.weighted.AStar.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AStarTest {

    @Test
    public void testSimple() throws Exception {
        final AStar a = init(
                x(c(), c(), c(), c(), c(), c(), c(), c()),
                x(c(), c(), c(), c(), o(), c(), c(), c()),
                x(c(), c(), s(), c(), o(), c(), e(), c()),
                x(c(), c(), c(), c(), o(), c(), c(), c()),
                x(c(), c(), c(), c(), c(), c(), c(), c())
        );
        final List<Coordinates> result = a.buildPath();
        System.out.println(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testAnotherSimple() throws Exception {
        final AStar a = init(
                x(c(), c(), c(), c(), e(), c(), c(), c()),
                x(c(), c(), o(), o(), c(), c(), c(), c()),
                x(c(), c(), s(), o(), c(), c(), c(), c()),
                x(c(), c(), c(), c(), o(), c(), c(), c()),
                x(c(), c(), c(), c(), c(), c(), c(), c())
        );
        final List<Coordinates> result = a.buildPath();
        System.out.println(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testDeadEnd() throws Exception {
        final AStar a = init(
                x(c(), c(), c(), c(), e(), c(), c(), c()),
                x(c(), c(), o(), c(), c(), c(), c(), c()),
                x(c(), o(), s(), o(), c(), c(), c(), c()),
                x(c(), c(), o(), c(), c(), c(), c(), c()),
                x(c(), c(), c(), c(), c(), c(), c(), c())
        );
        final List<Coordinates> result = a.buildPath();
        System.out.println(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testNoPath() throws Exception {
        final AStar a = init(
                x(o(), o(), o(), o(), o(), c(), c(), c()),
                x(o(), c(), c(), c(), o(), c(), c(), c()),
                x(o(), c(), s(), c(), o(), c(), e(), c()),
                x(o(), c(), c(), c(), o(), c(), c(), c()),
                x(o(), o(), o(), o(), o(), c(), c(), c())
        );
        final List<Coordinates> result = a.buildPath();
        System.out.println(result);
        assertTrue(result.isEmpty());
    }
}