package kuvaldis.graph;

import kuvaldis.graph.domain.Graph;

public interface Search<T> {
    Search<T> search();
    T result();
}
