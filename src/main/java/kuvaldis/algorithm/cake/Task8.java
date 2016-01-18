package kuvaldis.algorithm.cake;

/*

Write a function to see if a binary tree ? is "superbalanced" (a new tree property we just made up).
A tree is "superbalanced" if the difference between the depths of any two leaf nodes is no greater than one.

Here's a sample binary tree node class:

  public static class BinaryTreeNode {

    public int value;
    public BinaryTreeNode left;
    public BinaryTreeNode right;

    public BinaryTreeNode(int value) {
        this.value = value;
    }

    public BinaryTreeNode insertLeft(int leftValue) {
        this.left = new BinaryTreeNode(leftValue);
        return this.left;
    }

    public BinaryTreeNode insertRight(int rightValue) {
        this.right = new BinaryTreeNode(rightValue);
        return this.right;
    }
}

 */
public class Task8 {

    public static class Tree {
        private final Tree left;
        private final Tree right;

        public Tree(final Tree left, final Tree right) {
            this.left = left;
            this.right = right;
        }
    }

    public boolean isSuperbalanced(final Tree tree) {
        final int[] minMax = minMax(tree);
        return minMax[1] - minMax[0] <= 1;
    }

    private int[] minMax(final Tree tree) {
        if (tree == null) {
            return null;
        }
        final int[] leftMinMax = minMax(tree.left);
        final int[] rightMinMax = minMax(tree.right);
        final int[] result;
        if (leftMinMax == null && rightMinMax == null) {
            result = new int[2];
        } else {
            if (leftMinMax == null) {
                result = rightMinMax;
            } else if (rightMinMax == null) {
                result = leftMinMax;
            } else {
                result = new int[]{Math.min(leftMinMax[0], rightMinMax[0]), Math.max(leftMinMax[1], rightMinMax[1])};
            }
        }
        result[0]++;
        result[1]++;
        return result;
    }
}
