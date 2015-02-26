package kuvaldis.algorithm.graph.nonweighted.bfs;

import kuvaldis.algorithm.graph.nonweighted.AbstractSearch;
import kuvaldis.algorithm.graph.nonweighted.SearchSequence;
import kuvaldis.algorithm.graph.nonweighted.domain.Graph;
import kuvaldis.algorithm.graph.nonweighted.domain.Vertex;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

public abstract class AbstractBreadthFirstSearch<T> extends AbstractSearch<T> {

    public AbstractBreadthFirstSearch(Graph graph) {
        super(graph);
    }

    public AbstractBreadthFirstSearch(Graph graph, Integer startVertexNumber) {
        super(graph, startVertexNumber);
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

    @Override
    protected boolean startSubSearch(Vertex rootVertex) {
        return true;
    }
}
