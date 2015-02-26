package kuvaldis.algorithm.graph.nonweighted.application;

import kuvaldis.algorithm.graph.nonweighted.bfs.AbstractBreadthFirstSearch;
import kuvaldis.algorithm.graph.domain.Graph;
import kuvaldis.algorithm.graph.domain.Vertex;

import java.util.LinkedList;
import java.util.List;

public class ShortestPathSearch extends AbstractBreadthFirstSearch<List<Vertex>> {

    private final Integer to;
    private final Integer from;

    private boolean subSearchStarted;

    public ShortestPathSearch(Graph graph, Integer from, Integer to) {
        super(graph, from);
        this.to = to;
        this.from = from;
    }

    @Override
    public List<Vertex> result() {
        final LinkedList<Vertex> result = new LinkedList<>();
        Vertex vertex = graph.getVertex(to);
        result.push(vertex);
        while (vertex.getParent() != null) {
            vertex = vertex.getParent();
            result.push(vertex);
        }
        if (!from.equals(vertex.getNumber())){
            return null;
        }
        return result;
    }

    @Override
    protected boolean startSubSearch(Vertex rootVertex) {
        return subSearchStarted = !subSearchStarted;
    }
}
