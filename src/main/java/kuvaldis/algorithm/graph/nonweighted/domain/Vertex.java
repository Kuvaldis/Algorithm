package kuvaldis.algorithm.graph.nonweighted.domain;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;

public class Vertex {
    private final int number;
    private boolean processed;
    private boolean discovered;
    private Vertex parent;
    private LinkedList<Vertex> edges = new LinkedList<>();

    public Vertex(final int number) {
        this.number = number;
    }

    public Vertex(final int number, final boolean processed, final boolean discovered, final Vertex parent) {
        this.number = number;
        this.processed = processed;
        this.discovered = discovered;
        this.parent = parent;
    }

    public void addEdge(final Vertex vertex) {
        edges.add(vertex);
    }

    public Iterator<Vertex> edgesIterator() {
        return edges.iterator();
    }

    public int getNumber() {
        return number;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(final boolean processed) {
        this.processed = processed;
    }

    public boolean isDiscovered() {
        return discovered;
    }

    public void setDiscovered(final boolean discovered) {
        this.discovered = discovered;
    }

    public Vertex getParent() {
        return parent;
    }

    public void setParent(final Vertex parent) {
        this.parent = parent;
    }

    public LinkedList<Vertex> getEdges() {
        return edges;
    }

    public void setEdges(final LinkedList<Vertex> edges) {
        this.edges = edges;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Vertex vertex = (Vertex) o;
        return number == vertex.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Vertex{");
        sb.append("number=").append(number);
        sb.append(", processed=").append(processed);
        sb.append(", discovered=").append(discovered);
        sb.append(", parent=").append(parent);
        sb.append('}');
        return sb.toString();
    }
}
