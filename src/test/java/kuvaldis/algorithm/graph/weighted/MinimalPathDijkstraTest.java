package kuvaldis.algorithm.graph.weighted;

import kuvaldis.algorithm.graph.weighted.domain.WeightedEdge;
import kuvaldis.algorithm.graph.weighted.domain.WeightedGraph;
import kuvaldis.algorithm.graph.weighted.domain.WeightedVertex;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class MinimalPathDijkstraTest {

    @Test
    public void testBuild() throws Exception {
        final WeightedGraph graph = WeightedGraphUtils.fromSource("graph_dijkstra.txt", false);
        final List<WeightedVertex> result = new MinimalPathDijkstra(graph, 1, 4).build();
        assertEquals(Arrays.asList(1, 2, 3, 4), result.stream().map(WeightedVertex::getNumber).collect(Collectors.toList()));
    }
}