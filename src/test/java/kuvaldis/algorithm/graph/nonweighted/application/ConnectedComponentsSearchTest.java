package kuvaldis.algorithm.graph.nonweighted.application;

import kuvaldis.algorithm.graph.GraphUtils;
import kuvaldis.algorithm.graph.domain.Graph;
import kuvaldis.algorithm.graph.domain.Vertex;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class ConnectedComponentsSearchTest {

    @Test
    public void testConnectedSearch() throws Exception {
        final Graph graph = GraphUtils.fromResource("graph.txt");
        final Map<Integer, List<Vertex>> result = new ConnectedComponentsSearch(graph).search().result();
        assertEquals(1, result.size());
        final List<Integer> numbers = result.get(1).stream().map(Vertex::getNumber).collect(Collectors.toList());
        Collections.sort(numbers);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6), numbers);
    }

    @Test
    public void testDisconnectedSearch() throws Exception {
        final Graph graph = GraphUtils.fromResource("disconnected_graph.txt");
        final Map<Integer, List<Vertex>> result = new ConnectedComponentsSearch(graph).search().result();
        assertEquals(2, result.size());
        List<Integer> numbers = result.get(1).stream().map(Vertex::getNumber).collect(Collectors.toList());
        Collections.sort(numbers);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6), numbers);
        numbers = result.get(2).stream().map(Vertex::getNumber).collect(Collectors.toList());
        Collections.sort(numbers);
        assertEquals(Arrays.asList(7, 8, 9), numbers);
    }
}