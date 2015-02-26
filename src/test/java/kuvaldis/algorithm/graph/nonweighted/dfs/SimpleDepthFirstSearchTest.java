package kuvaldis.algorithm.graph.nonweighted.dfs;

import kuvaldis.algorithm.graph.GraphUtils;
import kuvaldis.algorithm.graph.nonweighted.domain.Graph;
import kuvaldis.algorithm.graph.nonweighted.domain.Vertex;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class SimpleDepthFirstSearchTest {

    @Test
    public void testSearch() throws IOException {
        final Graph graph = GraphUtils.fromResource("graph.txt");
        final List<Vertex> result = new SimpleDepthFirstSearch(graph).search().result();
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6), result.stream().map(Vertex::getNumber).collect(Collectors.toList()));
    }
}