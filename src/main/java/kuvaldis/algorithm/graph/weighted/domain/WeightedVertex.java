package kuvaldis.algorithm.graph.weighted.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import java.util.Iterator;
import java.util.LinkedList;

@Data
public class WeightedVertex {
    private final int number;
    @Getter(AccessLevel.PRIVATE)
    private LinkedList<WeightedEdge> edges = new LinkedList<>();

    public void addEdge(final WeightedEdge edge) {
        edges.add(edge);
    }

    public Iterator<WeightedEdge> edgesIterator() {
        return edges.iterator();
    }
}
