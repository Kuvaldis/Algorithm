package kuvaldis.algorithm.cake;

import java.util.Stack;

/*
https://www.interviewcake.com/question/java/balanced-binary-tree

Write a method to see if a binary tree is "superbalanced" (a new tree property we just made up).

A tree is "superbalanced" if the difference between the depths of any two leaf nodes  is no greater than one.
 */
public class SuperbalancedBinaryTreeChecker {

    public static class BinaryTreeNode {

        public int value;
        public BinaryTreeNode left;
        public BinaryTreeNode right;

        public BinaryTreeNode(final int value, final BinaryTreeNode left, final BinaryTreeNode right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public boolean isSuperbalanced(final BinaryTreeNode root) {
        if (root == null) {
            return false;
        }

        final Stack<BinaryTreeNode> nodesStack = new Stack<>();
        final Stack<Integer> depthsStack = new Stack<>();
        int maxDepth = 0;
        int minDepth = Integer.MAX_VALUE;
        BinaryTreeNode next = root;
        int currentDepth = 0;
        while (next != null || !nodesStack.isEmpty()) {
            BinaryTreeNode node;
            if (next != null) {
                node = next;
                currentDepth++;
                nodesStack.push(node);
                depthsStack.push(currentDepth);
                if (node.left != null) {
                    next = node.left;
                } else {
                    next = node.right;
                }
            } else {
                node = nodesStack.pop();
                currentDepth = depthsStack.pop();
                next = node.right;
            }

            if (node.left == null && node.right == null) {
                // leaf node
                if (currentDepth > maxDepth) {
                    maxDepth = currentDepth;
                }
                if (currentDepth < minDepth) {
                    minDepth = currentDepth;
                }
                next = null;
            }

            if (minDepth != Integer.MAX_VALUE && maxDepth != 0 && maxDepth - minDepth > 1) {
                return false;
            }
        }

        return true;
    }
}
