package kuvaldis.algorithm.graph.weighted;

import kuvaldis.algorithm.graph.weighted.domain.WeightedEdge;
import kuvaldis.algorithm.graph.weighted.domain.WeightedVertex;

import java.util.Set;

public abstract class AbstractSpanningTreeTest {

    protected boolean contains(final Set<WeightedEdge> edges,
                             final Integer vertexNumber1,
                             final Integer vertexNumber2,
                             final Integer weight) {
        return edges.contains(edge(vertexNumber1, vertexNumber2, weight)) ||
                edges.contains(edge(vertexNumber2, vertexNumber1, weight));
    }

    private WeightedEdge edge(final Integer tailNumber, final Integer headNumber, final Integer weight) {
        return new WeightedEdge(new WeightedVertex(tailNumber), new WeightedVertex(headNumber), weight);
    }
}
