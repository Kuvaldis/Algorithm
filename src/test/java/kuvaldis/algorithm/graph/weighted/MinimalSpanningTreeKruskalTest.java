package kuvaldis.algorithm.graph.weighted;

import kuvaldis.algorithm.graph.weighted.domain.WeightedEdge;
import kuvaldis.algorithm.graph.weighted.domain.WeightedGraph;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class MinimalSpanningTreeKruskalTest extends SpanningTreeTest {

    @Test
    public void testBuild() throws Exception {
        final WeightedGraph graph = WeightedGraphUtils.fromSource("graph_kruskal.txt", false);
        final Set<WeightedEdge> edges = new MinimalSpanningTreeKruskal(graph).build();
        assertNotNull(edges);
        assertEquals(graph.size() - 1, edges.size());
        assertTrue(contains(edges, 1, 2, 1));
        assertTrue(contains(edges, 1, 5, 2));
        assertTrue(contains(edges, 1, 6, 1));
        assertTrue(contains(edges, 4, 5, 1));
        assertTrue(contains(edges, 3, 4, 3));
    }
}