package kuvaldis.algorithm.careercup;

import java.util.*;

public class SerializeMultiTree {

    public static class Node {
        private final String value;
        private final List<Node> children;

        public Node(final String value, final List<Node> children) {
            this.value = value;
            this.children = children;
        }
    }

    public String serialize(final Node root) {
        final Queue<Node> queue = new ArrayDeque<>();
        final StringBuilder sb = new StringBuilder();
        sb.append(root.value.length()).append(":");
        sb.append(root.value).append(" ");
        queue.add(root);
        while (!queue.isEmpty()) {
            final Node node = queue.remove();
            sb.append(node.children.size()).append(" ");
            for (Node child : node.children) {
                sb.append(child.value.length()).append(":");
                sb.append(child.value).append(" ");
                queue.add(child);
            }
        }
        return sb.toString();
    }

    public Node deserialize(final String serializedTree) {
        final Queue<Node> queue = new ArrayDeque<>();
        int semicolonIndex = serializedTree.indexOf(":");
        int valueSize = Integer.valueOf(serializedTree.substring(0, semicolonIndex));
        String value = serializedTree.substring(semicolonIndex + 1, semicolonIndex + 1 + valueSize);
        final Node root = new Node(value, new ArrayList<>());
        queue.add(root);
        int i = semicolonIndex + 1 + valueSize + 1;
        while (i < serializedTree.length()) {
            final Node node = queue.remove();
            int sizeEndIndex = serializedTree.indexOf(" ", i);
            final Integer childrenSize = Integer.parseInt(serializedTree.substring(i, sizeEndIndex));
            i = sizeEndIndex + 1;
            for (int j = 0; j < childrenSize; j++) {
                semicolonIndex = serializedTree.indexOf(":", i);
                valueSize = Integer.valueOf(serializedTree.substring(i, semicolonIndex));
                value = serializedTree.substring(semicolonIndex + 1, semicolonIndex + 1 + valueSize);
                final Node child = new Node(value, new ArrayList<>());
                queue.add(child);
                node.children.add(child);
                i = semicolonIndex + 1 + valueSize + 1;
            }
        }
        return root;
    }
}
