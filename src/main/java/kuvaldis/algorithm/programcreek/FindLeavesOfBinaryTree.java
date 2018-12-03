package kuvaldis.algorithm.programcreek;

import java.util.*;

/*

Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.

Example:
Given binary tree
          1
         / \
        2   3
       / \
      4   5
Returns [4, 5, 3], [2], [1].

 */
public class FindLeavesOfBinaryTree {

    public static class Node {
        private final int value;
        private final Node left;
        private final Node right;

        public Node(final int value,
                    final Node left,
                    final Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }     
    }

    public List<List<Integer>> find(final Node root) {
        final List<List<Integer>> result = new ArrayList<>();
        findForSubtree(root, result);
        return result;
    }
    
    // returns depth of the subtree
    private int findForSubtree(final Node subtreeRoot, 
                               final List<List<Integer>> result) {
        if (subtreeRoot == null) {
            return -1;
        }
        
        final int leftDepth = findForSubtree(subtreeRoot.left, result);
        final int rightDepth = findForSubtree(subtreeRoot.right, result);
        final int currentLevel = Math.max(leftDepth, rightDepth) + 1;
        addToLevel(result, currentLevel, subtreeRoot.value);
        return currentLevel;
    }

    private void addToLevel(final List<List<Integer>> result,
                            final int level,
                            final int value) {
        if (level == result.size()) {
            result.add(new ArrayList<>());
        }
        final List<Integer> valuesOnLevel = result.get(level);
        valuesOnLevel.add(value);
    }
}
