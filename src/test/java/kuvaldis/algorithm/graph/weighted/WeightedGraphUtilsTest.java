package kuvaldis.algorithm.graph.weighted;

import kuvaldis.algorithm.graph.weighted.domain.WeightedGraph;
import kuvaldis.algorithm.graph.weighted.domain.WeightedVertex;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.Assert.*;

public class WeightedGraphUtilsTest {

    @Test
    public void testWeightedFromResource() throws IOException {
        final WeightedGraph graph = WeightedGraphUtils.fromSource("weighted_graph.txt");

        Assert.assertEquals(6, graph.size());

        WeightedVertex vertex = graph.getVertex(1);
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

    private List<Integer> edgeNumbers(WeightedVertex vertex) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(vertex.edgesIterator(), Spliterator.ORDERED), false)
                .map(edge -> edge.getVertex().getNumber())
                .collect(Collectors.toList());
    }
}