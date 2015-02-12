package kuvaldis.graph.bfs;

import kuvaldis.graph.domain.Graph;
import kuvaldis.graph.domain.Vertex;

import java.util.ArrayList;
import java.util.List;

public class SimpleBreadthFirstSearch extends AbstractBreadthFirstSearch<List<Vertex>> {

    final List<Vertex> order = new ArrayList<>();

    public SimpleBreadthFirstSearch(Graph graph, Integer rootNumber) {
        super(graph, rootNumber);
    }

    @Override
    protected boolean preProcessVertex(Vertex vertex) {
        return order.add(vertex);
    }

    @Override
    public List<Vertex> result() {
        return order;
    }
}
