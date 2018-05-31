package kuvaldis.algorithm.cake;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
Given an undirected graph ↴ with maximum degree ↴ DD, find a graph coloring ↴ using at most D+1D+1 colors.
 */
public class GraphColoring {

    public void colorGraph(final GraphNode[] graph, final Set<String> colors) {
        // It is possible to color all nodes with no more than D+1 colors.
        // Each node has maximum D neighbours. In the worst case, it's surrounded by D different colors.
        // It means that we can pick D+1th color and successfully apply it to the node.
        // The solution is based on simple loop, not even BFS.
        // If a node is not colored, we should pick one of the possible colors.
        // A color is valid when it's possible to color current node with it.

        // O(N) for visiting all nodes in total
        for (GraphNode node : graph) {
            // O(number of neighbors for a node), O(M) in total, i.e. proportional to number of edges.
            final Set<String> illegalColors = node.getNeighbors().stream()
                    .filter(GraphNode::hasColor)
                    .map(GraphNode::getColor)
                    .collect(Collectors.toSet());
            // even though we scroll over all colors, once we find a legal color we assign it to the node.
            // in fact, loop cannot be longer than number of node's neighbours, which again gives O(M) complexity
            // in total
            colors.stream()
                    .filter(color -> !illegalColors.contains(color))
                    .findFirst()
                    .ifPresent(node::setColor);
        }
    }

    public static class GraphNode {

        // consider label always unique
        private String label;
        private Set<GraphNode> neighbors = new HashSet<>();
        private String color;

        public GraphNode(final String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }

        public Set<GraphNode> getNeighbors() {
            return Collections.unmodifiableSet(neighbors);
        }

        public void addNeighbor(GraphNode neighbor) {
            neighbors.add(neighbor);
        }

        public boolean hasColor() {
            return color != null;
        }

        public String getColor() {
            return color;
        }

        public void setColor(final String color) {
            this.color = color;
        }
    }

}
