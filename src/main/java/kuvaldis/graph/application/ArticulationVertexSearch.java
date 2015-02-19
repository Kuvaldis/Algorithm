package kuvaldis.graph.application;

import kuvaldis.graph.dfs.AbstractDepthFirstSearch;
import kuvaldis.graph.domain.Graph;
import kuvaldis.graph.domain.Vertex;

import java.util.*;

public class ArticulationVertexSearch extends AbstractDepthFirstSearch<Map<ArticulationVertexSearch.ArticulationVertexType, Set<Vertex>>> {

    private final HashMap<ArticulationVertexType, Set<Vertex>> result = new HashMap<>();

    public enum ArticulationVertexType {
        ROOT, PARENT, BRIDGE
    }

    private final Map<Vertex, Vertex> reachableAncestor = new HashMap<>();

    private final Map<Vertex, Integer> treeOutDegree = new HashMap<>();

    public ArticulationVertexSearch(Graph graph) {
        super(graph);
    }

    @Override
    protected boolean doPreProcessVertex(Vertex vertex) {
        reachableAncestor.put(vertex, vertex);
        return true;
    }

    @Override
    protected boolean processEdge(Vertex v, Vertex y) {
        final EdgeClass edgeClass = edgeClassification(v, y);
        if (EdgeClass.TREE.equals(edgeClass)) {
            treeOutDegree.put(v, treeOutDegree.getOrDefault(v, 0) + 1);
        }
        if (EdgeClass.BACK.equals(edgeClass)) {
            if (entryTime(y) < entryTime(reachableAncestor.get(v))) {
                reachableAncestor.put(v, y);
            }
        }
        return true;
    }

    @Override
    protected boolean postProcessVertex(Vertex vertex) {
        if (vertex.getParent() == null) { // root
            if (treeOutDegree.get(vertex) > 1) {
                result.put(ArticulationVertexType.ROOT, Collections.singleton(vertex));
            }
            return true;
        }
        if (reachableAncestor.get(vertex).equals(vertex.getParent()) && vertex.getParent().getParent() != null) {
            result.putIfAbsent(ArticulationVertexType.PARENT, new HashSet<>());
            result.get(ArticulationVertexType.PARENT).add(vertex.getParent());
        }
        if (reachableAncestor.get(vertex).equals(vertex)) {
            result.putIfAbsent(ArticulationVertexType.BRIDGE, new HashSet<>());
            result.get(ArticulationVertexType.BRIDGE).add(vertex.getParent());
            if (treeOutDegree.getOrDefault(vertex, 0) > 0) { // not tree leaf
                result.get(ArticulationVertexType.BRIDGE).add(vertex);
            }
        }
        if (entryTime(reachableAncestor.get(vertex)) < entryTime(reachableAncestor.get(vertex.getParent()))) {
            reachableAncestor.put(vertex.getParent(), reachableAncestor.get(vertex));
        }
        return true;
    }

    @Override
    public Map<ArticulationVertexType, Set<Vertex>> result() {
        return result;
    }
}
