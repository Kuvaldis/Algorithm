package kuvaldis.algorithm.graph.nonweighted.dfs;

import kuvaldis.algorithm.graph.domain.Graph;
import kuvaldis.algorithm.graph.domain.Vertex;

import java.util.ArrayList;
import java.util.List;

public class SimpleDepthFirstSearch extends AbstractDepthFirstSearch<List<Vertex>> {

    final List<Vertex> order = new ArrayList<>();

    public SimpleDepthFirstSearch(Graph graph) {
        super(graph);
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
