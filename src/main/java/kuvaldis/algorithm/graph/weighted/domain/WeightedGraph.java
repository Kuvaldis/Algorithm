package kuvaldis.algorithm.graph.weighted.domain;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

public class WeightedGraph {

    private final Map<Integer, WeightedVertex> vertices = new HashMap<>();

    public void addVertex(final WeightedVertex vertex) {
        vertices.put(vertex.getNumber(), vertex);
    }

    public WeightedVertex getVertex(final Integer number) {
        return vertices.get(number);
    }

    public int size() {
        return vertices.size();
    }
}
