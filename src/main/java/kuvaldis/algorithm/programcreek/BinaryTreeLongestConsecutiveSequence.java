package kuvaldis.algorithm.programcreek;

import java.util.*;

/*

Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).

 */
public class BinaryTreeLongestConsecutiveSequence {

    public static class Node {
        private final int value;
        private final Node left;
        private final Node right;

        public Node(final int value, final Node left, final Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    // non empty
    public int find(final Node root) {
        final Stack<Node> stack = new Stack<>();
        final Stack<Integer> sizes = new Stack<>();

        Node node = root;
        int currentSize = 1;
        int maxSize = 1;
        while (node != null || !stack.isEmpty()) {
            if (currentSize == 0) {
                currentSize = sizes.pop();
            }
            Node next = null;
            if (node == null) {
                node = stack.pop();
                if (node != null) {
                    if (node.right != null) {
                        next = node.right;
                        stack.push(null);
                    }
                } else {
                    currentSize--;
                    continue;
                }
            } else {
                stack.push(node);
                if (node.left != null) {
                    next = node.left;
                } else {
                    node = null;
                    continue;
                }
            }

            if (next == null || node.value + 1 != next.value) {
                sizes.push(currentSize);
                if (currentSize > maxSize) {
                    maxSize = currentSize;
                }
                currentSize = 1;
            } else {
                currentSize++;
            }
            node = next;
        }
        return maxSize;
    }
}
