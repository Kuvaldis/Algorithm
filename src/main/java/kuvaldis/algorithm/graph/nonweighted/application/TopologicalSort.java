package kuvaldis.algorithm.graph.nonweighted.application;

import kuvaldis.algorithm.graph.nonweighted.dfs.AbstractDepthFirstSearch;
import kuvaldis.algorithm.graph.nonweighted.domain.Graph;
import kuvaldis.algorithm.graph.nonweighted.domain.Vertex;

import java.util.LinkedList;
import java.util.List;

public class TopologicalSort extends AbstractDepthFirstSearch<List<Vertex>> {

    private final LinkedList<Vertex> result = new LinkedList<>();

    public TopologicalSort(Graph graph) {
        super(graph);
    }

    @Override
    protected boolean postProcessVertex(Vertex vertex) {
        result.push(vertex);
        return true;
    }

    @Override
    protected boolean processEdge(Vertex v, Vertex y) {
        final AbstractDepthFirstSearch.EdgeClass edgeClass = edgeClassification(v, y);
        if (edgeClass == AbstractDepthFirstSearch.EdgeClass.BACK) {
            throw new IllegalArgumentException("Graph has loops");
        }
        return true;
    }

    @Override
    public List<Vertex> result() {
        return result;
    }
}
