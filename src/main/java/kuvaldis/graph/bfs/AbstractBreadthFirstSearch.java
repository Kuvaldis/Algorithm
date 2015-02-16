package kuvaldis.graph.bfs;

import kuvaldis.graph.AbstractSearch;
import kuvaldis.graph.SearchSequence;
import kuvaldis.graph.domain.Graph;
import kuvaldis.graph.domain.Vertex;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

public abstract class AbstractBreadthFirstSearch<T> extends AbstractSearch<T> {

    public AbstractBreadthFirstSearch(Graph graph, Integer rootNumber) {
        super(graph, rootNumber);
    }

    @Override
    protected final boolean sequenceContinue(Vertex v, Vertex y) {
        return false;
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
            public Vertex peekNext() {
                return vertexQueue.peek();
            }

            @Override
            public Vertex removeNext() {
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

    @Override
    protected boolean preProcessVertex(Vertex vertex) {
        return true;
    }

    @Override
    protected boolean processEdge(Vertex v, Vertex y) {
        return true;
    }

    @Override
    protected boolean postProcessVertex(Vertex vertex) {
        return true;
    }
}
