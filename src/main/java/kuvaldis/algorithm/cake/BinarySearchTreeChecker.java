package kuvaldis.algorithm.cake;

import java.util.Deque;
import java.util.LinkedList;

public class BinarySearchTreeChecker {

    public String check(final BinaryTreeNode tree) {

        final Deque<Bounds> stack = new LinkedList<>();
        stack.push(new Bounds(tree, Integer.MIN_VALUE, Integer.MAX_VALUE));

        while (!stack.isEmpty()) {
            final Bounds bounds = stack.pop();
            final BinaryTreeNode node = bounds.getNode();
            if (node.getValue() < bounds.getLowBoundry() || node.getValue() > bounds.getHighBoundry()) {
                return String.valueOf(node.getValue());
            }

            if (node.getLeft() != null) {
                stack.push(new Bounds(node.getLeft(), bounds.getLowBoundry(), node.getValue()));
            }
            if (node.getRight() != null) {
                stack.push(new Bounds(node.getRight(), node.getValue(), bounds.getHighBoundry()));
            }
        }

        return "";
    }

    private static class Bounds {

        private final BinaryTreeNode node;

        private final int lowBoundry;

        private final int highBoundry;

        private Bounds(final BinaryTreeNode node, final int lowBoundry, final int highBoundry) {
            this.node = node;
            this.lowBoundry = lowBoundry;
            this.highBoundry = highBoundry;
        }

        public BinaryTreeNode getNode() {
            return node;
        }

        public int getLowBoundry() {
            return lowBoundry;
        }

        public int getHighBoundry() {
            return highBoundry;
        }
    }

    public static class BinaryTreeNode {

        private final int value;

        private final BinaryTreeNode left;

        private final BinaryTreeNode right;

        public BinaryTreeNode(final int value, final BinaryTreeNode left, final BinaryTreeNode right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public int getValue() {
            return value;
        }

        public BinaryTreeNode getLeft() {
            return left;
        }

        public BinaryTreeNode getRight() {
            return right;
        }
    }
}
