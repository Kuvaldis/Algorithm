package kuvaldis.algorithm.graph.nonweighted.application;

import kuvaldis.algorithm.graph.nonweighted.GraphUtils;
import kuvaldis.algorithm.graph.nonweighted.domain.Graph;
import kuvaldis.algorithm.graph.nonweighted.domain.Vertex;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class ArticulationVertexSearchTest {
    @Test
    public void testArticulationVertexSearch() throws Exception {
        Graph graph = GraphUtils.fromResource("articulation_vertex_graph_1.txt");
        Map<ArticulationVertexSearch.ArticulationVertexType, Set<Vertex>> result =
                new ArticulationVertexSearch(graph).search().result();
        assertEquals(Collections.singletonList(1), getNumbers(result, ArticulationVertexSearch.ArticulationVertexType.ROOT));
        assertEquals(Collections.singletonList(2), getNumbers(result, ArticulationVertexSearch.ArticulationVertexType.PARENT));
        assertEquals(Collections.singletonList(2), getNumbers(result, ArticulationVertexSearch.ArticulationVertexType.BRIDGE));

        graph = GraphUtils.fromResource("articulation_vertex_graph_2.txt");
        result = new ArticulationVertexSearch(graph).search().result();
        assertEquals(Collections.singletonList(1), getNumbers(result, ArticulationVertexSearch.ArticulationVertexType.ROOT));
        assertEquals(Arrays.asList(4, 8), getNumbers(result, ArticulationVertexSearch.ArticulationVertexType.PARENT));
        assertEquals(Arrays.asList(3, 4, 11, 12), getNumbers(result, ArticulationVertexSearch.ArticulationVertexType.BRIDGE));

        graph = GraphUtils.fromResource("articulation_vertex_graph_3.txt");
        result = new ArticulationVertexSearch(graph).search().result();
        assertEquals(Collections.singletonList(1), getNumbers(result, ArticulationVertexSearch.ArticulationVertexType.ROOT));
        assertEquals(Collections.singletonList(3), getNumbers(result, ArticulationVertexSearch.ArticulationVertexType.PARENT));
        assertEquals(Arrays.asList(2, 3), getNumbers(result, ArticulationVertexSearch.ArticulationVertexType.BRIDGE));
    }

    private List<Integer> getNumbers(final Map<ArticulationVertexSearch.ArticulationVertexType, Set<Vertex>> result,
                                     final ArticulationVertexSearch.ArticulationVertexType type) {
        return Optional.ofNullable(result.get(type)).orElse(Collections.emptySet()).stream()
                .map(Vertex::getNumber)
                .sorted()
                .collect(Collectors.toList());
    }
}