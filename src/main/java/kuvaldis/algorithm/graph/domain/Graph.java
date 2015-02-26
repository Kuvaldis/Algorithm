package kuvaldis.algorithm.graph.domain;

import java.util.HashMap;
import java.util.Map;

public class Graph {
    private final Map<Integer, Vertex> vertices = new HashMap<>();

    public void addVertex(final Vertex vertex) {
        vertices.put(vertex.getNumber(), vertex);
    }

    public Vertex getVertex(final Integer number) {
        return vertices.get(number);
    }

    public int size() {
        return vertices.size();
    }
}
