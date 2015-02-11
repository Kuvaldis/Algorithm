package kuvaldis.graph;

import kuvaldis.graph.domain.Graph;

public interface Search<T> {
    Search<T> search(Graph graph, Integer rootVertexNumber);
    T result();
}
