package kuvaldis.graph.application;

import kuvaldis.graph.GraphUtils;
import kuvaldis.graph.dfs.AbstractDepthFirstSearch;
import kuvaldis.graph.domain.Graph;
import kuvaldis.graph.domain.Vertex;

import java.util.List;
import java.util.Objects;

public class LoopSearch extends AbstractDepthFirstSearch<List<Vertex>> {

    private List<Vertex> result;

    public LoopSearch(Graph graph) {
        super(graph);
    }

    @Override
    protected boolean processEdge(Vertex v, Vertex y) {
        List<Vertex> result = null;
        if (EdgeClass.BACK.equals(edgeClassification(v, y))) {
            GraphUtils.clean(graph);
            this.result = result = new ShortestPathSearch(graph, y.getNumber(), v.getNumber()).search().result();
        }
        return result == null;
    }

    @Override
    public List<Vertex> result() {
        return result;
    }
}
