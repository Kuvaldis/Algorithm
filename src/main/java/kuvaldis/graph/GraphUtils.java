package kuvaldis.graph;

import kuvaldis.graph.domain.Graph;
import kuvaldis.graph.domain.Vertex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class GraphUtils {

    private GraphUtils() {
    }

    public static Graph fromResource(final String name) throws IOException {
        final Graph graph = new Graph();
        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(GraphUtils.class.getResourceAsStream(name)))) {
            String read;
            while ((read = reader.readLine()) != null) {
                processParts(graph, read.split("[\\s]+"));
            }
        }
        return graph;
    }

    private static Vertex processParts(Graph graph, String[] parts) {
        final Integer number = Integer.valueOf(parts[0]);
        Vertex vertex = processNumber(graph, number);
        for (int i = 1; i < parts.length; i++) {
            final Integer edgeToNumber = Integer.valueOf(parts[i]);
            vertex.addEdge(processNumber(graph, edgeToNumber));
        }
        return vertex;
    }

    private static Vertex processNumber(Graph graph, Integer number) {
        Vertex vertex = graph.getVertex(number);
        if (vertex == null) {
            vertex = new Vertex(number);
            graph.addVertex(vertex);
        }
        return vertex;
    }

    public static void clean(final Graph graph) {
        for (int i = 1; i <= graph.size(); i++) {
            final Vertex vertex = graph.getVertex(i);
            vertex.setDiscovered(false);
            vertex.setProcessed(false);
            vertex.setParent(null);
        }
    }
}