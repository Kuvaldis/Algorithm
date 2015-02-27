package kuvaldis.algorithm.graph.weighted;

import kuvaldis.algorithm.graph.nonweighted.domain.Graph;
import kuvaldis.algorithm.graph.nonweighted.domain.Vertex;
import kuvaldis.algorithm.graph.weighted.domain.WeightedEdge;
import kuvaldis.algorithm.graph.weighted.domain.WeightedGraph;
import kuvaldis.algorithm.graph.weighted.domain.WeightedVertex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class WeightedGraphUtils {

    private WeightedGraphUtils() {
    }

    public static WeightedGraph fromSource(final String name, boolean directed) throws IOException {
        final WeightedGraph graph = new WeightedGraph(directed);
        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(WeightedGraphUtils.class.getResourceAsStream(name)))) {
            String read;
            while ((read = reader.readLine()) != null) {
                processParts(graph, read.split("[\\s]+"));
            }
        }
        return graph;
    }

    private static void processParts(WeightedGraph graph, String[] parts) {
        final Integer number = Integer.valueOf(parts[0]);
        final WeightedVertex vertex = processVertexNumber(graph, number);
        for (int i = 1; i < parts.length; i++) {
            final String part = parts[i];
            final String[] vertexAndEdge = part.split(":");
            final Integer edgeHeadVertexNumber = Integer.valueOf(vertexAndEdge[0].trim());
            final Integer edgeWeight = Integer.valueOf(vertexAndEdge[1].trim());
            final WeightedVertex edgeHeadVertex = processVertexNumber(graph, edgeHeadVertexNumber);
            vertex.addEdge(new WeightedEdge(vertex, edgeHeadVertex, edgeWeight));
        }
    }

    private static WeightedVertex processVertexNumber(WeightedGraph graph, Integer number) {
        WeightedVertex vertex = graph.getVertex(number);
        if (vertex == null) {
            vertex = new WeightedVertex(number);
            graph.addVertex(vertex);
        }
        return vertex;
    }
}