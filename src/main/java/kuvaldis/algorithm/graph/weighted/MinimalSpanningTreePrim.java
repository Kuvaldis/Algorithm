package kuvaldis.algorithm.graph.weighted;

import kuvaldis.algorithm.graph.weighted.domain.WeightedEdge;
import kuvaldis.algorithm.graph.weighted.domain.WeightedGraph;
import kuvaldis.algorithm.graph.weighted.domain.WeightedVertex;

import java.util.*;

public class MinimalSpanningTreePrim {

    private final WeightedGraph graph;

    public MinimalSpanningTreePrim(WeightedGraph graph) {
        this.graph = graph;
    }

    public Set<WeightedEdge> build() {
        final Set<WeightedEdge> result = new HashSet<>();
        final Set<WeightedVertex> inTree = new HashSet<>();
        final Queue<WeightedEdge> weightQueue = new PriorityQueue<>();
        WeightedVertex currentVertex = graph.getVertex(1);
        inTree.add(currentVertex);
        while (currentVertex != null) {
            final Iterator<WeightedEdge> currentEdges = currentVertex.edgesIterator();
            while (currentEdges.hasNext()) {
                final WeightedEdge currentEdge = currentEdges.next();
                if (!inTree.contains(currentEdge.getHeadVertex())) {
                    weightQueue.add(currentEdge);
                }
            }
            currentVertex = null;
            while (!weightQueue.isEmpty() && currentVertex == null) {
                final WeightedEdge cheapestEdge = weightQueue.poll();
                if (!inTree.contains(cheapestEdge.getHeadVertex())) {
                    currentVertex = cheapestEdge.getHeadVertex();
                    result.add(cheapestEdge);
                    inTree.add(currentVertex);
                }
            }
        }
        return result;
    }
}
