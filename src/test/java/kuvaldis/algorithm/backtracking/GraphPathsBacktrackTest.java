package kuvaldis.algorithm.backtracking;

import kuvaldis.algorithm.graph.nonweighted.GraphUtils;
import kuvaldis.algorithm.graph.nonweighted.domain.Graph;
import kuvaldis.algorithm.graph.nonweighted.domain.Vertex;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static org.junit.Assert.*;

public class GraphPathsBacktrackTest {
    @Test
    public void testBacktrack() throws Exception {
        final Graph graph = GraphUtils.fromResource("graph.txt");
        final GraphPathsBacktrack.Input input = new GraphPathsBacktrack.Input(graph, 1, 5);
        final Set<List<Vertex>> paths = new GraphPathsBacktrack().generate(input);
        final Set<List<Integer>> numbersPaths = paths.stream()
                .map(list -> list.stream().map(Vertex::getNumber).collect(toList()))
                .collect(toSet());
        assertEquals(3, numbersPaths.size());
        assertTrue(numbersPaths.contains(list(1, 5)));
        assertTrue(numbersPaths.contains(list(1, 2, 5)));
        assertTrue(numbersPaths.contains(list(1, 2, 3, 4, 5)));
    }

    private static List<Integer> list(final Integer... elements) {
        return asList(elements);
    }
}