package kuvaldis.algorithm.graph.nonweighted.application;

import kuvaldis.algorithm.graph.nonweighted.GraphUtils;
import kuvaldis.algorithm.graph.nonweighted.domain.Graph;
import kuvaldis.algorithm.graph.nonweighted.domain.Vertex;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class ShortestPathSearchTest {

    @Test
    public void testSearch() throws Exception {
        Graph graph = GraphUtils.fromResource("graph.txt");
        List<Vertex> result = new ShortestPathSearch(graph, 1, 4).search().result();
        assertEquals(Arrays.asList(1, 5, 4), result.stream().map(Vertex::getNumber).collect(Collectors.toList()));

        graph = GraphUtils.fromResource("graph.txt");
        result = new ShortestPathSearch(graph, 2, 4).search().result();
        assertEquals(Arrays.asList(2, 3, 4), result.stream().map(Vertex::getNumber).collect(Collectors.toList()));

        graph = GraphUtils.fromResource("graph.txt");
        result = new ShortestPathSearch(graph, 3, 6).search().result();
        assertEquals(Arrays.asList(3, 2, 1, 6), result.stream().map(Vertex::getNumber).collect(Collectors.toList()));
    }
}