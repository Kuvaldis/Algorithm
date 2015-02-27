package kuvaldis.algorithm.graph.weighted;

import kuvaldis.algorithm.graph.weighted.domain.WeightedEdge;
import kuvaldis.algorithm.graph.weighted.domain.WeightedGraph;
import kuvaldis.algorithm.graph.weighted.domain.WeightedVertex;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class MinimalSpanningTreePrimTest {

    @Test
    public void testBuild() throws Exception {
        final WeightedGraph graph = WeightedGraphUtils.fromSource("graph.txt", false);
        final Set<WeightedEdge> edges = new MinimalSpanningTreePrim(graph).build();
        assertNotNull(edges);
        assertEquals(graph.size() - 1, edges.size());
        assertTrue(edges.contains(edge(1, 2, 1)));
        assertTrue(edges.contains(edge(1, 6, 1)));
        assertTrue(edges.contains(edge(1, 5, 2)));
        assertTrue(edges.contains(edge(2, 3, 3)));
        assertTrue(edges.contains(edge(3, 4, 3)));
    }

    private WeightedEdge edge(final Integer tailNumber, final Integer headNumber, final Integer weight) {
        return new WeightedEdge(new WeightedVertex(tailNumber), new WeightedVertex(headNumber), weight);
    }
}