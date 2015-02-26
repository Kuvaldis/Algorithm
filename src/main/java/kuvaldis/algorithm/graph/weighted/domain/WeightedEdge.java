package kuvaldis.algorithm.graph.weighted.domain;

import lombok.Data;

@Data
public class WeightedEdge {
    private final WeightedVertex vertex;
    private final int weight;
}
