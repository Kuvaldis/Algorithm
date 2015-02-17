package kuvaldis.graph.dfs;

import kuvaldis.graph.domain.Graph;
import kuvaldis.graph.domain.Vertex;

import java.util.ArrayList;
import java.util.List;

public class SimpleDepthFirstSearch extends AbstractDepthFirstSearch<List<Vertex>> {

    final List<Vertex> order = new ArrayList<>();

    public SimpleDepthFirstSearch(Graph graph, Integer rootNumber) {
        super(graph, rootNumber);
    }

    @Override
    protected boolean doPreProcessVertex(Vertex vertex) {
        return order.add(vertex);
    }

    @Override
    public List<Vertex> result() {
        return order;
    }
}
