package kuvaldis.graph.dfs;

import kuvaldis.graph.AbstractSearch;
import kuvaldis.graph.SearchSequence;
import kuvaldis.graph.domain.Graph;
import kuvaldis.graph.domain.Vertex;

import java.util.*;

public abstract class AbstractDepthFirstSearch<T> extends AbstractSearch<T> {

    private final Map<Vertex, Iterator<Vertex>> edgeIterators = new HashMap<>();

    private final Set<Vertex> preProcessedVertices = new HashSet<>();

    private final Map<Vertex, Integer> entryTime = new HashMap<>();
    private int time;

    public AbstractDepthFirstSearch(Graph graph, Integer rootNumber) {
        super(graph, rootNumber);
    }

    @Override
    protected final boolean sequenceContinue(Vertex v, Vertex y) {
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
    protected final Iterator<Vertex> edgesIterator(Vertex vertex) {
        if (!edgeIterators.containsKey(vertex)) {
            edgeIterators.put(vertex, vertex.edgesIterator());
        }
        return edgeIterators.get(vertex);
    }

    @Override
    protected final boolean preProcessVertex(Vertex vertex) {
        if (!preProcessedVertices.contains(vertex)) {
            preProcessedVertices.add(vertex);
            entryTime.put(vertex, ++time);
            return doPreProcessVertex(vertex);
        }
        return true;
    }

    protected boolean doPreProcessVertex(final Vertex vertex) {
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

    protected Integer entryTime(final Vertex vertex) {
        return entryTime.get(vertex);
    }
}
