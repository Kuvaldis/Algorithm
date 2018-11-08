package kuvaldis.algorithm.programcreek.graph;

public class Graph {

    private Node[] nodes;

    public void setNodes(final Node... nodes) {
        this.nodes = nodes;
    }

    public static class Node {

        private boolean visited;

        private boolean processed;

        private final int value;

        private Node[] neighbours;

        public Node(final int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setNeighbours(final Node... neighbours) {
            this.neighbours = neighbours;
        }

        public Node[] getNeighbours() {
            return neighbours;
        }

        public boolean isVisited() {
            return visited;
        }

        public void setVisited(final boolean visited) {
            this.visited = visited;
        }

        public boolean isProcessed() {
            return processed;
        }

        public void setProcessed(final boolean processed) {
            this.processed = processed;
        }
    }

    public Node[] getNodes() {
        return nodes;
    }

}
