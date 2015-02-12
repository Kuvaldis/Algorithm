package kuvaldis.graph.application;

import kuvaldis.graph.bfs.AbstractBreadthFirstSearch;
import kuvaldis.graph.domain.Graph;
import kuvaldis.graph.domain.Vertex;

import java.util.LinkedList;
import java.util.List;

public class ShortestPathSearch extends AbstractBreadthFirstSearch<List<Vertex>> {

    private final Integer to;

    public ShortestPathSearch(Graph graph, Integer from, Integer to) {
        super(graph, from);
        this.to = to;
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
        return result;
    }
}
