package kuvaldis.graph.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Data
@ToString(exclude = {"edges"})
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

    public Iterator<Vertex> edgesDescendingIterator() {
        return edges.descendingIterator();
    }
}
