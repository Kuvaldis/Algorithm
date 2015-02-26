package kuvaldis.algorithm.graph.nonweighted;

import kuvaldis.algorithm.graph.nonweighted.domain.Vertex;

public interface SearchSequence {
    void put(Vertex vertex);
    Vertex peekNext();
    Vertex removeNext();
    boolean isEmpty();
}
