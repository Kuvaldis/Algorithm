package kuvaldis.algorithm.graph;

import kuvaldis.algorithm.graph.domain.Graph;
import kuvaldis.algorithm.graph.domain.Vertex;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GraphUtilsTest {

    @Test
    public void testFromResource() throws IOException {
        final Graph graph = GraphUtils.fromResource("graph.txt");

        Assert.assertEquals(6, graph.size());

        Vertex vertex = graph.getVertex(1);
        assertNotNull(vertex);
        assertEquals(1, vertex.getNumber());
        assertEquals(Arrays.asList(2, 5, 6), edgeNumbers(vertex));

        vertex = graph.getVertex(2);
        assertNotNull(vertex);
        assertEquals(2, vertex.getNumber());
        assertEquals(Arrays.asList(1, 3, 5), edgeNumbers(vertex));

        vertex = graph.getVertex(3);
        assertNotNull(vertex);
        assertEquals(3, vertex.getNumber());
        assertEquals(Arrays.asList(2, 4), edgeNumbers(vertex));

        vertex = graph.getVertex(4);
        assertNotNull(vertex);
        assertEquals(4, vertex.getNumber());
        assertEquals(Arrays.asList(3, 5), edgeNumbers(vertex));

        vertex = graph.getVertex(5);
        assertNotNull(vertex);
        assertEquals(5, vertex.getNumber());
        assertEquals(Arrays.asList(1, 2, 4), edgeNumbers(vertex));

        vertex = graph.getVertex(6);
        assertNotNull(vertex);
        assertEquals(6, vertex.getNumber());
        assertEquals(Arrays.asList(1), edgeNumbers(vertex));
    }

    private List<Integer> edgeNumbers(Vertex vertex) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(vertex.edgesIterator(), Spliterator.ORDERED), false)
                .map(Vertex::getNumber)
                .collect(Collectors.toList());
    }
}