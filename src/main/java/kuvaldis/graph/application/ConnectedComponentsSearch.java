package kuvaldis.graph.application;

import kuvaldis.graph.Search;
import kuvaldis.graph.bfs.SimpleBreadthFirstSearch;
import kuvaldis.graph.domain.Graph;
import kuvaldis.graph.domain.Vertex;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ConnectedComponentsSearch implements Search<Map<Integer, List<Vertex>>> {

    private final Graph graph;
    private final Map<Integer, List<Vertex>> result = new HashMap<>();

    public ConnectedComponentsSearch(Graph graph) {
        this.graph = graph;
    }

    @Override
    public Search<Map<Integer, List<Vertex>>> search() {
        int c = 1;
        for (int i = 1; i <= graph.size(); i++) {
            final Vertex vertex = graph.getVertex(i);
            if (!vertex.isDiscovered()) {
                result.put(c++, new SimpleBreadthFirstSearch(graph, i).search().result());
            }
        }
        return this;
    }

    @Override
    public Map<Integer, List<Vertex>> result() {
        return result;
    }
}
