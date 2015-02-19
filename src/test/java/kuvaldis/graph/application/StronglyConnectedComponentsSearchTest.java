package kuvaldis.graph.application;

import kuvaldis.graph.GraphUtils;
import kuvaldis.graph.domain.Graph;
import kuvaldis.graph.domain.Vertex;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.*;

public class StronglyConnectedComponentsSearchTest {

    @Test
    public void testSearch() throws Exception {
        final Graph graph = GraphUtils.fromResource("strongly_connected_components_graph.txt");
        final Map<Integer, List<Vertex>> result = new StronglyConnectedComponentsSearch(graph).search().result();
        assertEquals(3, result.size());
        List<List<Integer>> componentsVerticesNumbers = result.values().stream()
                .map(it -> it.stream().map(Vertex::getNumber).collect(toList())).collect(toList());
        assertTrue(componentsVerticesNumbers.contains(Arrays.asList(1, 2, 3, 4)));
        assertTrue(componentsVerticesNumbers.contains(Arrays.asList(5, 6, 7)));
        assertTrue(componentsVerticesNumbers.contains(Arrays.asList(8)));
    }

    private List<Integer> collectVertexNumbers(List<Vertex> vertices) {
        return vertices.stream().map(Vertex::getNumber).sorted().collect(toList());
    }
}