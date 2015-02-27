package kuvaldis.algorithm.graph.weighted.domain;

import lombok.Data;
import lombok.ToString;

@Data
public class WeightedEdge implements Comparable<WeightedEdge> {

    private final WeightedVertex tailVertex;
    private final WeightedVertex headVertex;
    private final Integer weight;

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
