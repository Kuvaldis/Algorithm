package kuvaldis.graph.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
