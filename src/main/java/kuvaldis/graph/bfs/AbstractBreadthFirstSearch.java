package kuvaldis.graph.bfs;

import kuvaldis.graph.AbstractSearch;
import kuvaldis.graph.SearchSequence;
import kuvaldis.graph.domain.Vertex;

import java.util.LinkedList;
import java.util.Queue;

public abstract class AbstractBreadthFirstSearch<T> extends AbstractSearch<T> {
    @Override
    protected SearchSequence sequence() {
        return new SearchSequence() {
            final Queue<Vertex> vertexQueue = new LinkedList<>();
            @Override
            public void put(Vertex vertex) {
                vertexQueue.offer(vertex);
            }

            @Override
            public Vertex get() {
                return vertexQueue.poll();
            }

            @Override
            public boolean isEmpty() {
                return vertexQueue.isEmpty();
            }
        };
    }
}
