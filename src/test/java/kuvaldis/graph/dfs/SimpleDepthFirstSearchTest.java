package kuvaldis.graph.dfs;

import kuvaldis.graph.GraphUtils;
import kuvaldis.graph.domain.Graph;
import kuvaldis.graph.domain.Vertex;
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