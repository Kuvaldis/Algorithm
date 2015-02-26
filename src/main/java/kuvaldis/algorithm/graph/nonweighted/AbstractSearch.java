package kuvaldis.algorithm.graph.nonweighted;

import kuvaldis.algorithm.graph.nonweighted.domain.Graph;
import kuvaldis.algorithm.graph.nonweighted.domain.Vertex;

import java.util.Iterator;

public abstract class AbstractSearch<T> implements Search<T> {

    protected final Graph graph;
    protected final Integer startVertexNumber;

    public AbstractSearch(Graph graph) {
        this(graph, 1);
    }

    public AbstractSearch(Graph graph, Integer startVertexNumber) {
        this.graph = graph;
        this.startVertexNumber = startVertexNumber;
    }

    @Override
    public final Search<T> search() {
        final SearchSequence sequence = sequence();
        for (int i = startVertexNumber; i <= graph.size(); i++) {
            final Vertex rootVertex = graph.getVertex(i);
            if (!rootVertex.isDiscovered()) {
                if (!subSearch(sequence, rootVertex)) {
                    break;
                }
            }
        }
        return this;
    }

    private boolean subSearch(SearchSequence sequence, Vertex rootVertex) {
        if (!startSubSearch(rootVertex)) {
            return false;
        }
        sequence.put(rootVertex);
        rootVertex.setDiscovered(true);
        while (!sequence.isEmpty()) {
            final Vertex v = sequence.peekNext();
            if (!preProcessVertex(v)) {
                return false;
            }
            final Iterator<Vertex> edgesIterator = edgesIterator(v);
            boolean sequenceContinue = false;
            while (edgesIterator.hasNext()) {
                final Vertex y = edgesIterator.next();
                if (!y.isDiscovered()) {
                    sequence.put(y);
                    y.setDiscovered(true);
                    y.setParent(v);
                }

                if (!processEdge(v, y)) {
                    return false;
                }

                if (sequenceContinue(v, y)) {
                    sequenceContinue = true;
                    break;
                }
            }
            if (sequenceContinue) {
                continue;
            }
            v.setProcessed(true);
            if (!postProcessVertex(v)) {
                return false;
            }
            sequence.removeNext();
        }
        return true;
    }

    protected abstract boolean startSubSearch(Vertex rootVertex);

    protected abstract boolean sequenceContinue(Vertex v, Vertex y);

    protected abstract SearchSequence sequence();

    protected abstract Iterator<Vertex> edgesIterator(Vertex vertex);

    protected abstract boolean preProcessVertex(Vertex vertex);

    protected abstract boolean processEdge(Vertex v, Vertex y);

    protected abstract boolean postProcessVertex(Vertex vertex);
}
