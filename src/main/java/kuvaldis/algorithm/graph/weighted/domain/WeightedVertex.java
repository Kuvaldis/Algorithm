package kuvaldis.algorithm.graph.weighted.domain;

import lombok.*;

import java.util.*;

@Data
@EqualsAndHashCode(of = "number")
@ToString(exclude = "edgeWeights")
public class WeightedVertex {
    private final int number;
    @Getter(AccessLevel.PRIVATE)
    private LinkedList<WeightedEdge> edges = new LinkedList<>();
    @Getter(AccessLevel.PRIVATE)
    private Map<WeightedVertex, Integer> edgeWeights = new HashMap<>();

    public void addEdge(final WeightedEdge edge) {
        edges.add(edge);
        edgeWeights.put(edge.getHeadVertex(), edge.getWeight());
    }

    public Iterator<WeightedEdge> edgesIterator() {
        return edges.iterator();
    }

    public Integer getWeight(final WeightedVertex toVertex) {
        return edgeWeights.get(toVertex);
    }
}
