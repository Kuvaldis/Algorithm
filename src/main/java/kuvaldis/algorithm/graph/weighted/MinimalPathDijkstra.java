package kuvaldis.algorithm.graph.weighted;

import kuvaldis.algorithm.graph.weighted.domain.WeightedEdge;
import kuvaldis.algorithm.graph.weighted.domain.WeightedGraph;
import kuvaldis.algorithm.graph.weighted.domain.WeightedVertex;

import java.util.*;

public class MinimalPathDijkstra {

    private final WeightedGraph graph;
    private final Integer startVertexNumber;
    private final Integer endVertexNumber;

    private final Map<WeightedVertex, WeightedVertex> parents = new HashMap<>();
    private final Set<WeightedVertex> inTree = new HashSet<>();
    private final Map<WeightedVertex, Integer> distances = new HashMap<>();

    public MinimalPathDijkstra(final WeightedGraph graph,
                               final Integer startVertexNumber,
                               final Integer endVertexNumber) {
        this.graph = graph;
        this.startVertexNumber = startVertexNumber;
        this.endVertexNumber = endVertexNumber;
    }

    public List<WeightedVertex> build() {
        WeightedVertex currentVertex = graph.getVertex(startVertexNumber);
        distances.put(currentVertex, 0);
        while (currentVertex != null) {
            inTree.add(currentVertex);
            final Iterator<WeightedEdge> edgesIterator = currentVertex.edgesIterator();
            final Integer currentVertexDistance = distances.get(currentVertex);
            while (edgesIterator.hasNext()) {
                final WeightedEdge currentEdge = edgesIterator.next();
                final Integer currentEdgeHeadDistance = distances.get(currentEdge.getHeadVertex());
                final Integer weight = currentEdge.getWeight();
                if (currentEdgeHeadDistance == null || currentEdgeHeadDistance > currentVertexDistance + weight) {
                    distances.put(currentEdge.getHeadVertex(), currentVertexDistance + weight);
                    parents.put(currentEdge.getHeadVertex(), currentVertex);
                }
            }
            currentVertex = null;
            Integer minDistance = Integer.MAX_VALUE;
            for (int i = 1; i <= graph.size(); i++) {
                WeightedVertex vertex = graph.getVertex(i);
                Integer distance = distances.getOrDefault(vertex, Integer.MAX_VALUE);
                if (!inTree.contains(vertex) && distance < minDistance) {
                    minDistance = distance;
                    currentVertex = vertex;
                }
            }
        }
        return collectPath();
    }

    private List<WeightedVertex> collectPath() {
        final LinkedList<WeightedVertex> result = new LinkedList<>();
        final WeightedVertex startVertex = graph.getVertex(startVertexNumber);
        WeightedVertex currentVertex = graph.getVertex(endVertexNumber);
        result.push(currentVertex);
        while (!Objects.equals(startVertex, currentVertex)) {
            currentVertex = parents.get(currentVertex);
            result.push(currentVertex);
        }
        return result;
    }
}
