package kuvaldis.graph.bfs;

import kuvaldis.graph.domain.Vertex;

import java.util.ArrayList;
import java.util.List;

public class SimpleBreadthFirstSearch extends AbstractBreadthFirstSearch<List<Vertex>> {

    final List<Vertex> order = new ArrayList<>();

    @Override
    protected void preProcessVertex(Vertex vertex) {
        order.add(vertex);
    }

    @Override
    protected void processEdge(Vertex v, Vertex y) {

    }

    @Override
    protected void postProcessVertex(Vertex vertex) {

    }

    @Override
    public List<Vertex> result() {
        return order;
    }
}
