package kuvaldis.graph.bfs;

import kuvaldis.graph.GraphUtils;
import kuvaldis.graph.domain.Graph;
import kuvaldis.graph.domain.Vertex;
import org.junit.Assert;
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
        final List<Vertex> result = new SimpleBreadthFirstSearch(graph, 1).search().result();
        assertEquals(Arrays.asList(1, 2, 5, 6, 3, 4), result.stream().map(Vertex::getNumber).collect(Collectors.toList()));
    }
}