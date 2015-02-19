package kuvaldis.graph.application;

import kuvaldis.graph.GraphUtils;
import kuvaldis.graph.domain.Graph;
import kuvaldis.graph.domain.Vertex;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class TopologicalSortTest {

    @Test
    public void testSort() throws Exception {
        Graph graph = GraphUtils.fromResource("topo_graph.txt");
        List<Vertex> result = new TopologicalSort(graph).search().result();
        assertEquals(Arrays.asList(7, 1, 2, 3, 6, 5, 4),
                result.stream().map(Vertex::getNumber).collect(Collectors.toList()));
    }
}