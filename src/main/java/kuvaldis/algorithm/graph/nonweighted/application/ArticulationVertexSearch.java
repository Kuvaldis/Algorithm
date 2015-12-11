package kuvaldis.algorithm.graph.nonweighted.application;

import kuvaldis.algorithm.graph.nonweighted.dfs.AbstractDepthFirstSearch;
import kuvaldis.algorithm.graph.nonweighted.domain.Graph;
import kuvaldis.algorithm.graph.nonweighted.domain.Vertex;

import java.util.*;

// looks for articulation tree in connected graph
// works only for undirected graphs
public class ArticulationVertexSearch extends AbstractDepthFirstSearch<Map<ArticulationVertexSearch.ArticulationVertexType, Set<Vertex>>> {

    private final HashMap<ArticulationVertexType, Set<Vertex>> result = new HashMap<>();

    public enum ArticulationVertexType {
        ROOT, PARENT, BRIDGE
    }

    // the oldest (lesser entry time) ancestor of the key vertex which is accessible for it
    private final Map<Vertex, Vertex> reachableAncestor = new HashMap<>();

    // how many vertices the key vertex is the parent of
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
        if (y.isProcessed()) {
            return true;
        }
        final EdgeClass edgeClass = edgeClassification(v, y);
        if (EdgeClass.TREE.equals(edgeClass)) {
            treeOutDegree.put(v, treeOutDegree.getOrDefault(v, 0) + 1);
        }
        // we reached here a reachable ancestor
        if (EdgeClass.BACK.equals(edgeClass) && y != v.getParent()) {
            // check if it's older then current
            if (entryTime(y) < entryTime(reachableAncestor.get(v))) {
                reachableAncestor.put(v, y);
            }
        }
        return true;
    }

    @Override
    protected boolean postProcessVertex(Vertex vertex) {
        if (vertex.getParent() == null) { // root
            // there is at least two tree children, so it is an articulation vertex with type ROOT
            if (treeOutDegree.getOrDefault(vertex, 0) > 1) {
                result.put(ArticulationVertexType.ROOT, Collections.singleton(vertex));
            }
            return true;
        }
        // oldest reachable ancestor of the vertex is it's parent who is not the root.
        // definitely a PARENT articulation tree
        if (reachableAncestor.get(vertex).equals(vertex.getParent()) && vertex.getParent().getParent() != null) {
            result.putIfAbsent(ArticulationVertexType.PARENT, new HashSet<>());
            result.get(ArticulationVertexType.PARENT).add(vertex.getParent());
        }
        // vertex doesn't reach it's ancestors
        if (reachableAncestor.get(vertex).equals(vertex)) {
            result.putIfAbsent(ArticulationVertexType.BRIDGE, new HashSet<>());
            // root may be only a root articulation vertex
            if (vertex.getParent().getParent() != null) {
                result.get(ArticulationVertexType.BRIDGE).add(vertex.getParent());
            }
            if (treeOutDegree.getOrDefault(vertex, 0) > 0) { // not tree leaf
                result.get(ArticulationVertexType.BRIDGE).add(vertex);
            }
        }
        // propagate reachable ancestor to the parent if the ancestor is older
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
