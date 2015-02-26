package kuvaldis.algorithm.graph.nonweighted.domain;

import lombok.*;

import java.util.Iterator;
import java.util.LinkedList;

@Data
@ToString(exclude = {"edges"})
@EqualsAndHashCode(of = {"number"})
public class Vertex {
    private final int number;
    private boolean processed;
    private boolean discovered;
    private Vertex parent;
    @Getter(AccessLevel.PRIVATE)
    private LinkedList<Vertex> edges = new LinkedList<>();

    public void addEdge(final Vertex vertex) {
        edges.add(vertex);
    }

    public Iterator<Vertex> edgesIterator() {
        return edges.iterator();
    }
}
