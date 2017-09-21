package kuvaldis.algorithm.graph.weighted;

import org.junit.Test;

import static kuvaldis.algorithm.graph.weighted.AStar.*;

public class AStarTest {

    @Test
    public void testSimple() throws Exception {
        final AStar a = init(
                x(c(), c(), c(), c(), c(), c(), c()),
                x(c(), c(), c(), o(), c(), c(), c()),
                x(c(), s(), c(), o(), c(), e(), c()),
                x(c(), c(), c(), o(), c(), c(), c()),
                x(c(), c(), c(), c(), c(), c(), c())
        );
        a.buildPath();

    }
}