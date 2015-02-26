package kuvaldis.algorithm.graph.nonweighted.application;

import kuvaldis.algorithm.graph.nonweighted.bfs.AbstractBreadthFirstSearch;
import kuvaldis.algorithm.graph.domain.Graph;
import kuvaldis.algorithm.graph.domain.Vertex;

import java.util.*;

public class ConnectedComponentsSearch extends AbstractBreadthFirstSearch<Map<Integer, List<Vertex>>> {

    private final Map<Integer, List<Vertex>> result = new HashMap<>();
    private int currentSubSearch;

    public ConnectedComponentsSearch(Graph graph) {
        super(graph);
    }

    @Override
    protected boolean preProcessVertex(Vertex vertex) {
        List<Vertex> subSearchVertices = Optional.ofNullable(result.get(currentSubSearch)).orElse(new ArrayList<>());
        subSearchVertices.add(vertex);
        result.put(currentSubSearch, subSearchVertices);
        return true;
    }

    @Override
    protected boolean startSubSearch(Vertex rootVertex) {
        currentSubSearch++;
        return true;
    }

    @Override
    public Map<Integer, List<Vertex>> result() {
        return result;
    }
}
