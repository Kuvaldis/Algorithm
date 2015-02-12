package kuvaldis.graph.bfs;

import kuvaldis.graph.AbstractSearch;
import kuvaldis.graph.SearchSequence;
import kuvaldis.graph.domain.Graph;
import kuvaldis.graph.domain.Vertex;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public abstract class AbstractBreadthFirstSearch<T> extends AbstractSearch<T> {


    public AbstractBreadthFirstSearch(Graph graph, Integer rootNumber) {
        super(graph, rootNumber);
    }

    @Override
    protected final SearchSequence sequence() {
        return new SearchSequence() {

            final Queue<Vertex> vertexQueue = new ArrayDeque<>();

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

    @Override
    protected final Iterator<Vertex> edgesIterator(Vertex vertex) {
        return vertex.edgesIterator();
    }
}
