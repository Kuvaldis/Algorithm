package kuvaldis.algorithm.cake;

import kuvaldis.algorithm.cake.GraphColoring.GraphNode;
import org.junit.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;

public class GraphColoringTest {

    @Test
    public void testComplex() throws Exception {
        // given
        final GraphNode[] graph = new GraphNode[12];
        for (int i = 0; i < 12; i++) {
            graph[i] = new GraphNode(String.valueOf(i));
        }
        graph[0].addNeighbor(graph[1]);
        graph[0].addNeighbor(graph[2]);
        graph[0].addNeighbor(graph[6]);

        graph[1].addNeighbor(graph[0]);
        graph[1].addNeighbor(graph[2]);
        graph[1].addNeighbor(graph[3]);

        graph[2].addNeighbor(graph[0]);
        graph[2].addNeighbor(graph[1]);
        graph[2].addNeighbor(graph[5]);

        graph[3].addNeighbor(graph[1]);
        graph[3].addNeighbor(graph[4]);
        graph[3].addNeighbor(graph[9]);

        graph[4].addNeighbor(graph[3]);
        graph[4].addNeighbor(graph[5]);
        graph[4].addNeighbor(graph[9]);

        graph[5].addNeighbor(graph[2]);
        graph[5].addNeighbor(graph[4]);
        graph[5].addNeighbor(graph[8]);

        graph[6].addNeighbor(graph[0]);
        graph[6].addNeighbor(graph[7]);
        graph[6].addNeighbor(graph[11]);

        graph[7].addNeighbor(graph[6]);
        graph[7].addNeighbor(graph[8]);
        graph[7].addNeighbor(graph[11]);

        graph[8].addNeighbor(graph[5]);
        graph[8].addNeighbor(graph[7]);
        graph[8].addNeighbor(graph[10]);

        graph[9].addNeighbor(graph[3]);
        graph[9].addNeighbor(graph[4]);
        graph[9].addNeighbor(graph[10]);

        graph[10].addNeighbor(graph[8]);
        graph[10].addNeighbor(graph[9]);
        graph[10].addNeighbor(graph[11]);

        graph[11].addNeighbor(graph[6]);
        graph[11].addNeighbor(graph[7]);
        graph[11].addNeighbor(graph[10]);

        final Set<String> colors = Stream.of("red", "green", "blue", "yellow")
                .collect(Collectors.toSet());

        // when
        new GraphColoring().colorGraph(graph, colors);

        // then
        for (GraphNode node : graph) {
            assertTrue(validColoring(node));
        }
        printNodeLabelsWithColors(graph);
    }

    private boolean validColoring(final GraphNode node) {
        return node.getNeighbors().stream()
                .noneMatch(neighbour -> neighbour.getColor().equals(node.getColor()));
    }

    private void printNodeLabelsWithColors(final GraphNode[] graph) {
        for (GraphNode node : graph) {
            System.out.println(node.getLabel() + " : " + node.getColor());
        }
    }
}