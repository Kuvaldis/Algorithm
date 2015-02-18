package kuvaldis.graph.application;

import kuvaldis.graph.dfs.AbstractDepthFirstSearch;
import kuvaldis.graph.domain.Graph;
import kuvaldis.graph.domain.Vertex;

import java.util.*;

public class ArticulationVertexSearch extends AbstractDepthFirstSearch<Map<ArticulationVertexSearch.ArticulationVertexType, Set<Vertex>>> {

    private final HashMap<ArticulationVertexType, Set<Vertex>> result = new HashMap<>();

    private enum EdgeClass {
        TREE, BACK, FORWARD, CROSS
    }

    public enum ArticulationVertexType {
        ROOT, PARENT, BRIDGE
    }

    private final Map<Vertex, Vertex> reachableAncestor = new HashMap<>();

    private final Map<Vertex, Integer> treeOutDegree = new HashMap<>();

    public ArticulationVertexSearch(Graph graph, Integer rootNumber) {
        super(graph, rootNumber);
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
            treeOutDegree.put(v, Optional.ofNullable(treeOutDegree.get(v)).orElse(0) + 1);
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
            if (Optional.ofNullable(treeOutDegree.get(vertex)).orElse(0) > 0) { // not tree leaf
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

    private EdgeClass edgeClassification(final Vertex v, final Vertex y) {
        if (v.equals(y.getParent())) return EdgeClass.TREE;
        if (y.isDiscovered() && !y.isProcessed()) return EdgeClass.BACK;
        if (y.isProcessed() && entryTime(y) > entryTime(v)) return EdgeClass.FORWARD;
        if (y.isProcessed() && entryTime(y) < entryTime(v)) return EdgeClass.CROSS;
        throw new IllegalStateException(String.format("Can't determine edge class (%s, %s)", v, y));
    }
}
