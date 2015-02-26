package kuvaldis.algorithm.graph.nonweighted.bfs;

import kuvaldis.algorithm.graph.nonweighted.domain.Graph;
import kuvaldis.algorithm.graph.nonweighted.domain.Vertex;

import java.util.ArrayList;
import java.util.List;

public class SimpleBreadthFirstSearch extends AbstractBreadthFirstSearch<List<Vertex>> {

    final List<Vertex> order = new ArrayList<>();

    public SimpleBreadthFirstSearch(Graph graph) {
        super(graph);
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
