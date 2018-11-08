package kuvaldis.algorithm.programcreek.graph;

import kuvaldis.algorithm.programcreek.graph.Graph.Node;

import java.util.Stack;

/*

There are a total of n courses you have to take, labeled from 0 to n - 1. Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]. Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

For example, given 2 and [[1,0]], there are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.
For another example, given 2 and [[1,0],[0,1]], there are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

 */
public class CourseSchedule {

    public boolean isValid(final Graph graph) {
        final Node[] nodes = graph.getNodes();

        final Stack<Node> dfs = new Stack<>();
        for (Node node : nodes) {
            if (node.isVisited()) {
                continue;
            }
            dfs.push(node);
            while (!dfs.isEmpty()) {
                Node current = dfs.peek();
                if (current.isVisited()) {
                    current = dfs.pop();
                    current.setProcessed(true);
                    continue;
                }
                current.setVisited(true);
                final Node[] neighbours = current.getNeighbours();
                if (neighbours == null) {
                    continue;
                }
                for (int i = neighbours.length - 1; i >= 0; i--) {
                    final Node neighbour = neighbours[i];
                    if (neighbour.isVisited() && !neighbour.isProcessed()) {
                        return false;
                    }
                    dfs.push(neighbour);
                }
            }
        }
        return true;
    }
}
