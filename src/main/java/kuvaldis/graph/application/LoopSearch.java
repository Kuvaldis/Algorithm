package kuvaldis.graph.application;

import kuvaldis.graph.AbstractSearch;
import kuvaldis.graph.dfs.AbstractDepthFirstSearch;
import kuvaldis.graph.domain.Graph;
import kuvaldis.graph.domain.Vertex;

import java.util.List;
import java.util.Objects;

public class LoopSearch extends AbstractDepthFirstSearch<List<Vertex>> {

    private List<Vertex> result;

    public LoopSearch(Graph graph, Integer rootNumber) {
        super(graph, rootNumber);
    }

    @Override
    protected boolean processEdge(Vertex v, Vertex y) {
        List<Vertex> result = null;
        if (!Objects.equals(v, y.getParent())) {
            this.result = result = new ShortestPathSearch(graph, y.getNumber(), v.getNumber()).search().result();
        }
        return result == null;
    }

    @Override
    public List<Vertex> result() {
        return result;
    }
}
