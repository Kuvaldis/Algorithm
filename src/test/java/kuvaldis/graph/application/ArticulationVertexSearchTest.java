package kuvaldis.graph.application;

import kuvaldis.graph.GraphUtils;
import kuvaldis.graph.domain.Graph;
import kuvaldis.graph.domain.Vertex;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class ArticulationVertexSearchTest {
    @Test
    public void testArticulationVertexSearch() throws Exception {
        Graph graph = GraphUtils.fromResource("articulation_vertex_graph_1.txt");
        Map<ArticulationVertexSearch.ArticulationVertexType, Set<Vertex>> result =
                new ArticulationVertexSearch(graph, 1).search().result();
        assertEquals(Arrays.asList(1), getNumbers(result, ArticulationVertexSearch.ArticulationVertexType.ROOT));
        assertEquals(Arrays.asList(2), getNumbers(result, ArticulationVertexSearch.ArticulationVertexType.PARENT));
        assertEquals(Arrays.asList(1, 2), getNumbers(result, ArticulationVertexSearch.ArticulationVertexType.BRIDGE));

        graph = GraphUtils.fromResource("articulation_vertex_graph_2.txt");
        result = new ArticulationVertexSearch(graph, 1).search().result();
        assertEquals(Arrays.asList(1), getNumbers(result, ArticulationVertexSearch.ArticulationVertexType.ROOT));
        assertEquals(Arrays.asList(4, 8), getNumbers(result, ArticulationVertexSearch.ArticulationVertexType.PARENT));
        assertEquals(Arrays.asList(3, 4), getNumbers(result, ArticulationVertexSearch.ArticulationVertexType.BRIDGE));
    }

    private List<Integer> getNumbers(final Map<ArticulationVertexSearch.ArticulationVertexType, Set<Vertex>> result,
                                     final ArticulationVertexSearch.ArticulationVertexType type) {
        return Optional.ofNullable(result.get(type)).orElse(Collections.emptySet()).stream()
                .map(Vertex::getNumber)
                .sorted()
                .collect(Collectors.toList());
    }
}