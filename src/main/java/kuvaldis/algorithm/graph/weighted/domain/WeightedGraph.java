package kuvaldis.algorithm.graph.weighted.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Data
public class WeightedGraph {

    @Getter(AccessLevel.PRIVATE)
    private final Map<Integer, WeightedVertex> vertices = new HashMap<>();

    private final boolean directed;

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
