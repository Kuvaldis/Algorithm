package kuvaldis.algorithm.leetcode;

/*
Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:

The root is the maximum number in the array.
The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
Construct the maximum tree by the given array and output the root node of this tree.

Example 1:
Input: [3,2,1,6,0,5]
Output: return the tree root node representing the following tree:

      6
    /   \
   3     5
    \    /
     2  0
       \
        1
Note:
The size of the given array will be in the range [1,1000].

 */
public class MaximumBinaryTree {

    public TreeNode buildTree(final int[] arr) {
        return buildSubtree(arr, 0, arr.length);
    }

    private TreeNode buildSubtree(final int[] arr, final int fromInclusive, final int toExclusive) {
        if (fromInclusive >= toExclusive) {
            return null;
        }
        int max = Integer.MIN_VALUE;
        int maxIndex = -1;
        for (int i = fromInclusive; i < toExclusive; i++) {
            if (arr[i] > max) {
                max = arr[i];
                maxIndex = i;
            }
        }

        final TreeNode leftNode = buildSubtree(arr, fromInclusive, maxIndex);
        final TreeNode rightNode = buildSubtree(arr, maxIndex + 1, toExclusive);
        return new TreeNode(max, leftNode, rightNode);
    }

}
