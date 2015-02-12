package kuvaldis.graph.application;

import kuvaldis.graph.GraphUtils;
import kuvaldis.graph.domain.Graph;
import kuvaldis.graph.domain.Vertex;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class BipartiteCheckTest {

    @Test
    public void testNonBipartite() throws Exception {
        final Graph graph = GraphUtils.fromResource("graph.txt");
        assertFalse(new BipartiteCheck(graph).search().result());
    }

    @Test
    public void testBipartite() throws Exception {
        final Graph graph = GraphUtils.fromResource("bipartite_graph.txt");
        assertTrue(new BipartiteCheck(graph).search().result());
    }
}