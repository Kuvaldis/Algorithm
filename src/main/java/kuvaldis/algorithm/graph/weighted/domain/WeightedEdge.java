package kuvaldis.algorithm.graph.weighted.domain;

public class WeightedEdge implements Comparable<WeightedEdge> {

    private final WeightedVertex tailVertex;
    private final WeightedVertex headVertex;
    private final Integer weight;

    public WeightedEdge(final WeightedVertex tailVertex, final WeightedVertex headVertex, final Integer weight) {
        this.tailVertex = tailVertex;
        this.headVertex = headVertex;
        this.weight = weight;
    }

    public WeightedVertex getTailVertex() {
        return tailVertex;
    }

    public WeightedVertex getHeadVertex() {
        return headVertex;
    }

    public Integer getWeight() {
        return weight;
    }

    @Override
    public int compareTo(WeightedEdge o) {
        return weight.compareTo(o.weight);
    }

    @Override
    public String toString() {
        return String.format("WeightedEdge(tailVertexNumber=%s, headVertexNumber=%s, weight=%s)",
                tailVertex.getNumber(), headVertex.getNumber(), weight);
    }
}
