package kuvaldis.algorithm.graph.nonweighted.application;

import kuvaldis.algorithm.graph.GraphUtils;
import kuvaldis.algorithm.graph.nonweighted.dfs.AbstractDepthFirstSearch;
import kuvaldis.algorithm.graph.nonweighted.domain.Graph;
import kuvaldis.algorithm.graph.nonweighted.domain.Vertex;

import java.util.List;

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
