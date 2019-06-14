package kuvaldis.algorithm.leetcode;

/*
Given the root of a binary tree with N nodes, each node in the tree has node.val coins, and there are N coins total.

In one move, we may choose two adjacent nodes and move one coin from one node to another.  (The move may be from parent to child, or from child to parent.)

Return the number of moves required to make every node have exactly one coin.
 */
public class DistributeCoinsInBinaryTree {

    private int moves;

    public int distribute(final TreeNode root) {
        calculateCoins(root);
        return moves;
    }

    /**
     *
     * @return negative int if required coins, positive if there are spare coins, 0 if all is okay
     */
    private int calculateCoins(final TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }

        final int leftCoins = calculateCoins(treeNode.left);
        final int rightCoins = calculateCoins(treeNode.right);
        final int currentCoins = treeNode.value - 1;
        final int coins = leftCoins + rightCoins + currentCoins;
        moves += Math.abs(coins);
        return coins;
    }
}
