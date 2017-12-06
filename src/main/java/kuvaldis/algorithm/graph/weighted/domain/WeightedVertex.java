package kuvaldis.algorithm.graph.weighted.domain;

import java.util.*;

public class WeightedVertex {
    private final int number;
    private LinkedList<WeightedEdge> edges = new LinkedList<>();
    private Map<WeightedVertex, Integer> edgeWeights = new HashMap<>();

    public WeightedVertex(final int number) {
        this.number = number;
    }

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

    public int getNumber() {
        return number;
    }

    public LinkedList<WeightedEdge> getEdges() {
        return edges;
    }

    public Map<WeightedVertex, Integer> getEdgeWeights() {
        return edgeWeights;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final WeightedVertex that = (WeightedVertex) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("WeightedVertex{");
        sb.append("number=").append(number);
        sb.append(", edges=").append(edges);
        sb.append('}');
        return sb.toString();
    }
}
