package kuvaldis.graph.dfs;

import kuvaldis.graph.AbstractSearch;
import kuvaldis.graph.SearchSequence;
import kuvaldis.graph.domain.Graph;
import kuvaldis.graph.domain.Vertex;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class AbstractDepthFirstSearch<T> extends AbstractSearch<T> {

    public AbstractDepthFirstSearch(Graph graph, Integer rootNumber) {
        super(graph, rootNumber);
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
            public Vertex get() {
                return vertexStack.pop();
            }

            @Override
            public boolean isEmpty() {
                return vertexStack.isEmpty();
            }
        };
    }

    @Override
    protected Iterator<Vertex> edgesIterator(Vertex vertex) {
        return vertex.edgesDescendingIterator();
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
