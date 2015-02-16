package kuvaldis.graph;

import kuvaldis.graph.domain.Vertex;

public interface SearchSequence {
    void put(Vertex vertex);
    Vertex peekNext();
    Vertex removeNext();
    boolean isEmpty();
}
