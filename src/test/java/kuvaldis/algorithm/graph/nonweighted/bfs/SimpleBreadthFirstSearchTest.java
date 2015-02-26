package kuvaldis.algorithm.graph.nonweighted.bfs;

import kuvaldis.algorithm.graph.GraphUtils;
import kuvaldis.algorithm.graph.domain.Graph;
import kuvaldis.algorithm.graph.domain.Vertex;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class SimpleBreadthFirstSearchTest {

    @Test
    public void testSearch() throws IOException {
        final Graph graph = GraphUtils.fromResource("graph.txt");
        final List<Vertex> result = new SimpleBreadthFirstSearch(graph).search().result();
        assertEquals(Arrays.asList(1, 2, 5, 6, 3, 4), result.stream().map(Vertex::getNumber).collect(Collectors.toList()));
    }
}