package kuvaldis.algorithm.programcreek;

import java.util.*;

/*

The houses form a binary tree. If the root is robbed, its left and right can not be robbed.

 */
public class HouseRobberIII {

    public int maxSum(final TreeNode root) {
        return maxSumSubtree(root, 0, 0);
    }

    private int maxSumSubtree(final TreeNode node,
                              final int parentMax,
                              final int grandparentMax) {
        if (node == null) {
            return Math.max(parentMax, grandparentMax);
        }

        final int currentValue = node.value;
        final int currentMax = Math.max(parentMax, grandparentMax + currentValue);

        final int leftMax = maxSumSubtree(node.left, currentMax, parentMax);
        final int rightMax = maxSumSubtree(node.right, currentMax, parentMax);

        return Math.max(leftMax, rightMax);
    }
}
