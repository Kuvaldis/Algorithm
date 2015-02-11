package kuvaldis.graph.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
@ToString(exclude = {"edges"})
public class Vertex {
    private final int number;
    private boolean processed;
    private boolean discovered;
    private Vertex parent;
    @Getter(AccessLevel.PRIVATE)
    private List<Vertex> edges = new ArrayList<>();

    public void addEdge(final Vertex vertex) {
        edges.add(vertex);
    }

    public Iterator<Vertex> edgesIterator() {
        return edges.iterator();
    }
}
