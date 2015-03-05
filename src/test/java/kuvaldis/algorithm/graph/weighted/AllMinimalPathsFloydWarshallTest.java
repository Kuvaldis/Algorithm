package kuvaldis.algorithm.graph.weighted;

import kuvaldis.algorithm.graph.weighted.domain.WeightedGraph;
import kuvaldis.algorithm.graph.weighted.domain.WeightedVertex;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class AllMinimalPathsFloydWarshallTest {

    @Test
    public void testBuild() throws Exception {
        final WeightedGraph graph = WeightedGraphUtils.fromSource("graph_dijkstra.txt", false);
        final AllMinimalPathsFloydWarshall.Result result = new AllMinimalPathsFloydWarshall(graph).build();
        assertNotNull(result);
        assertEquals(1, result.minimalDistance(1, 2).intValue());
        assertEquals(3, result.minimalDistance(1, 3).intValue());
        assertEquals(6, result.minimalDistance(1, 4).intValue());
        assertEquals(1, result.minimalDistance(2, 1).intValue());
        assertEquals(2, result.minimalDistance(2, 3).intValue());
        assertEquals(5, result.minimalDistance(2, 4).intValue());
        assertEquals(3, result.minimalDistance(3, 1).intValue());
        assertEquals(2, result.minimalDistance(3, 2).intValue());
        assertEquals(3, result.minimalDistance(3, 4).intValue());
        assertEquals(6, result.minimalDistance(4, 1).intValue());
        assertEquals(5, result.minimalDistance(4, 2).intValue());
        assertEquals(3, result.minimalDistance(4, 3).intValue());

        assertEquals(Arrays.asList(1, 2, 3, 4), toNumbers(result.minimalPath(1, 4)));
        assertEquals(Arrays.asList(1, 2, 3), toNumbers(result.minimalPath(1, 3)));
        assertEquals(Arrays.asList(1, 2), toNumbers(result.minimalPath(1, 2)));
        assertEquals(Arrays.asList(2, 1), toNumbers(result.minimalPath(2, 1)));
        assertEquals(Arrays.asList(2, 3), toNumbers(result.minimalPath(2, 3)));
        assertEquals(Arrays.asList(2, 3, 4), toNumbers(result.minimalPath(2, 4)));
        assertEquals(Arrays.asList(3, 2, 1), toNumbers(result.minimalPath(3, 1)));
        assertEquals(Arrays.asList(3, 2), toNumbers(result.minimalPath(3, 2)));
        assertEquals(Arrays.asList(3, 4), toNumbers(result.minimalPath(3, 4)));
        assertEquals(Arrays.asList(4, 3, 2, 1), toNumbers(result.minimalPath(4, 1)));
        assertEquals(Arrays.asList(4, 3, 2), toNumbers(result.minimalPath(4, 2)));
        assertEquals(Arrays.asList(4, 3), toNumbers(result.minimalPath(4, 3)));
    }

    private List<Integer> toNumbers(final List<WeightedVertex> weightedVertexes) {
        return weightedVertexes.stream().map(WeightedVertex::getNumber).collect(Collectors.toList());
    }
}