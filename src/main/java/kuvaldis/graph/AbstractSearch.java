package kuvaldis.graph;

import kuvaldis.graph.domain.Graph;
import kuvaldis.graph.domain.Vertex;

import java.util.Iterator;

public abstract class AbstractSearch<T> implements Search<T> {

    @Override
    public final Search<T> search(final Graph graph, final Integer rootVertexNumber) {
        final SearchSequence sequence = sequence();
        final Vertex rootVertex = graph.getVertex(rootVertexNumber);
        sequence.put(rootVertex);
        rootVertex.setDiscovered(true);
        while (!sequence.isEmpty()) {
            final Vertex v = sequence.get();
            preProcessVertex(v);
            v.setProcessed(true);
            final Iterator<Vertex> edgesIterator = edgesIterator(v);
            edgesIterator.forEachRemaining(y -> {
                if (!y.isProcessed()) {
                    processEdge(v, y);
                }
                if (!y.isDiscovered()) {
                    sequence.put(y);
                    y.setDiscovered(true);
                    y.setParent(v);
                }
            });
            postProcessVertex(v);
        }
        return this;
    }

    protected abstract SearchSequence sequence();

    protected abstract Iterator<Vertex> edgesIterator(Vertex vertex);

    protected abstract void preProcessVertex(Vertex vertex);

    protected abstract void processEdge(Vertex v, Vertex y);

    protected abstract void postProcessVertex(Vertex vertex);
}
