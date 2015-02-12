package kuvaldis.graph;

import kuvaldis.graph.domain.Graph;
import kuvaldis.graph.domain.Vertex;

import java.util.Iterator;

public abstract class AbstractSearch<T> implements Search<T> {

    protected final Integer rootNumber;
    protected final Graph graph;

    public AbstractSearch(Graph graph, Integer rootNumber) {
        this.rootNumber = rootNumber;
        this.graph = graph;
    }

    @Override
    public final Search<T> search() {
        final SearchSequence sequence = sequence();
        final Vertex rootVertex = graph.getVertex(rootNumber);
        sequence.put(rootVertex);
        rootVertex.setDiscovered(true);
        while (!sequence.isEmpty()) {
            final Vertex v = sequence.get();
            if (!preProcessVertex(v)) {
                break;
            }
            v.setProcessed(true);
            final Iterator<Vertex> edgesIterator = edgesIterator(v);
            boolean interrupt = false;
            while (edgesIterator.hasNext()) {
                final Vertex y = edgesIterator.next();
                if (!y.isProcessed()) {
                    if (!processEdge(v, y)) {
                        interrupt = true;
                        break;
                    }
                }
                if (!y.isDiscovered()) {
                    sequence.put(y);
                    y.setDiscovered(true);
                    y.setParent(v);
                }
            }
            if (interrupt) {
                break;
            }
            if (!postProcessVertex(v)) {
                break;
            }
        }
        return this;
    }

    protected abstract SearchSequence sequence();

    protected abstract Iterator<Vertex> edgesIterator(Vertex vertex);

    protected abstract boolean preProcessVertex(Vertex vertex);

    protected abstract boolean processEdge(Vertex v, Vertex y);

    protected abstract boolean postProcessVertex(Vertex vertex);
}
