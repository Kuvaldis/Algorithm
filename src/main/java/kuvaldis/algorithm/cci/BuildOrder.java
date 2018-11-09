package kuvaldis.algorithm.cci;

import java.util.*;

// build projects in a correct order.
public class BuildOrder {

    private static class Node {

        private final String project;
    
        private int incomingNumber;

        // means current node depends on these nodes
        private final List<Node> dependsOn = new ArrayList<>();

        private boolean visited;

        private boolean processed;

        private Node(final String project) {
            this.project = project;
        }
    }

    private Map<String, Node> projectIndex = new HashMap<>();

    public List<String> buildOrder(final String[] projects, final String[][] dependencies) {
        // build nodes first
        for (String project : projects) {
            projectIndex.put(project, new Node(project));    
        }
        // add dependencies
        for (String[] dependency: dependencies) {
            final String first = dependency[0];
            final String second = dependency[1];
            final Node firstNode = getNode(first);
            final Node secondNode = getNode(second);
            addDependency(secondNode, firstNode);
        }
        // collect roots, i.e. nodes without incoming edges
        // then dfs
        final LinkedList<String> result = new LinkedList<>();
        final Stack<Node> dfs = new Stack<>();
        for (Node projectNode : projectIndex.values()) {
            if (projectNode.incomingNumber == 0) {
                dfs.push(projectNode);
            }
        }
        while (!dfs.isEmpty()) {
            final Node node = dfs.peek();
            if (!node.visited) {
                node.visited = true;
            } else {
                dfs.pop();
                node.processed = true;
                result.add(node.project);
            }
            for (Node dependsOn: node.dependsOn) {
                if (dependsOn.visited && !dependsOn.processed) {
                    // consider null as not possible to build all the projects
                    return null;
                }
                if (!dependsOn.processed) {
                    dfs.push(dependsOn);
                }
            }
        }
        return result;
    }

    private Node getNode(final String project) {
        Node node = projectIndex.get(project);
        if (node == null) {
            node = new Node(project);
            projectIndex.put(project, node);
        }
        return node;
    }

    private void addDependency(final Node node, final Node dependsOn) {
        node.dependsOn.add(dependsOn);
        dependsOn.incomingNumber++;
    }
}
