package kuvaldis.graph;

import kuvaldis.graph.domain.Graph;
import kuvaldis.graph.domain.Vertex;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
        assertEquals(vertex.getNumber(), 1);
        assertEquals(edgeNumbers(vertex), Arrays.asList(2, 5, 6));

        vertex = graph.getVertex(2);
        assertNotNull(vertex);
        assertEquals(vertex.getNumber(), 2);
        assertEquals(edgeNumbers(vertex), Arrays.asList(1, 3, 5));

        vertex = graph.getVertex(3);
        assertNotNull(vertex);
        assertEquals(vertex.getNumber(), 3);
        assertEquals(edgeNumbers(vertex), Arrays.asList(2, 4));

        vertex = graph.getVertex(4);
        assertNotNull(vertex);
        assertEquals(vertex.getNumber(), 4);
        assertEquals(edgeNumbers(vertex), Arrays.asList(3, 5));

        vertex = graph.getVertex(5);
        assertNotNull(vertex);
        assertEquals(vertex.getNumber(), 5);
        assertEquals(edgeNumbers(vertex), Arrays.asList(1, 2 ,4));

        vertex = graph.getVertex(6);
        assertNotNull(vertex);
        assertEquals(vertex.getNumber(), 6);
        assertEquals(edgeNumbers(vertex), Arrays.asList(1));
    }

    private List<Integer> edgeNumbers(Vertex vertex) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(vertex.edgesIterator(), Spliterator.ORDERED), false)
                .map(Vertex::getNumber)
                .collect(Collectors.toList());
    }
}