package kuvaldis.graph.dfs;

import kuvaldis.graph.AbstractSearch;
import kuvaldis.graph.SearchSequence;
import kuvaldis.graph.domain.Graph;
import kuvaldis.graph.domain.Vertex;

import java.util.*;

public abstract class AbstractDepthFirstSearch<T> extends AbstractSearch<T> {

    private final Map<Vertex, Iterator<Vertex>> edgeIterators = new HashMap<>();

    public AbstractDepthFirstSearch(Graph graph, Integer rootNumber) {
        super(graph, rootNumber);
    }

    @Override
    protected boolean sequenceContinue(Vertex v, Vertex y) {
        return y.isDiscovered() && v.equals(y.getParent());
    }

    @Override
    protected final SearchSequence sequence() {
        return new SearchSequence() {

            final Deque<Vertex> vertexStack = new ArrayDeque<>();

            @Override
            public void put(Vertex vertex) {
                vertexStack.push(vertex);
            }

            @Override
            public Vertex peekNext() {
                return vertexStack.peek();
            }

            @Override
            public Vertex removeNext() {
                Vertex first = vertexStack.pop();
                edgeIterators.remove(first);
                return first;
            }

            @Override
            public boolean isEmpty() {
                return vertexStack.isEmpty();
            }
        };
    }

    @Override
    protected Iterator<Vertex> edgesIterator(Vertex vertex) {
        if (!edgeIterators.containsKey(vertex)) {
            edgeIterators.put(vertex, vertex.edgesIterator());
        }
        return edgeIterators.get(vertex);
    }

    @Override
    protected boolean preProcessVertex(Vertex vertex) {
        return true;
    }

    @Override
    final protected boolean processEdge(Vertex v, Vertex y) {
        return true;
    }

    @Override
    protected boolean postProcessVertex(Vertex vertex) {
        return true;
    }
}
