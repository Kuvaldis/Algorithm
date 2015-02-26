package kuvaldis.algorithm.graph.nonweighted.application;

import kuvaldis.algorithm.graph.nonweighted.GraphUtils;
import kuvaldis.algorithm.graph.nonweighted.domain.Graph;
import org.junit.Test;

import static org.junit.Assert.*;

public class BipartiteCheckTest {

    @Test
    public void testNonBipartite() throws Exception {
        final Graph graph = GraphUtils.fromResource("graph.txt");
        assertFalse(new BipartiteCheck(graph).search().result());
    }

    @Test
    public void testBipartite() throws Exception {
        final Graph graph = GraphUtils.fromResource("bipartite_graph.txt");
        assertTrue(new BipartiteCheck(graph).search().result());
    }
}