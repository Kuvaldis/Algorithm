package kuvaldis.algorithm.graph.weighted.domain;

import java.util.HashMap;
import java.util.Map;

public class WeightedGraph {

    private final Map<Integer, WeightedVertex> vertices = new HashMap<>();

    private final boolean directed;

    public WeightedGraph(final boolean directed) {
        this.directed = directed;
    }

    public void addVertex(final WeightedVertex vertex) {
        vertices.put(vertex.getNumber(), vertex);
    }

    public WeightedVertex getVertex(final Integer number) {
        return vertices.get(number);
    }

    public int size() {
        return vertices.size();
    }

    public Map<Integer, WeightedVertex> getVertices() {
        return vertices;
    }

    public boolean isDirected() {
        return directed;
    }
}
