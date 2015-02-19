package kuvaldis.graph.application;

import kuvaldis.graph.GraphUtils;
import kuvaldis.graph.domain.Graph;
import kuvaldis.graph.domain.Vertex;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class LoopSearchTest {

    @Test
    public void testLoopSearch() throws Exception {
        final Graph graph = GraphUtils.fromResource("loop_graph.txt");
        final List<Vertex> result = new LoopSearch(graph).search().result();
        assertEquals(Arrays.asList(1, 2, 3, 4), result.stream().map(Vertex::getNumber).collect(Collectors.toList()));
    }

    @Test
    public void testNoLoopSearch() throws Exception {
        final Graph graph = GraphUtils.fromResource("no_loop_graph.txt");
        final List<Vertex> result = new LoopSearch(graph).search().result();
        assertNull(result);
    }
}