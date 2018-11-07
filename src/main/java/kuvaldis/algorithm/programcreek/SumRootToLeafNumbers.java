package kuvaldis.algorithm.programcreek;

/*

Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number. Find the total sum of all root-to-leaf numbers.

For example,
    1
   / \
  2   3
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.
Return the sum = 12 + 13 = 25.

 */
public class SumRootToLeafNumbers {

    public long sum(final TreeNode root) {
        return doSum(root, 0);
    }

    private long doSum(final TreeNode node,
                       final int prefix) {
        final int newPrefix = prefix * 10 + node.value;
        if (isLeaf(node)) {
            return newPrefix;
        }        
        
        long sum = 0L;
        if (node.left != null) {
            sum += doSum(node.left, newPrefix);
        }
        if (node.right != null) {
            sum += doSum(node.right, newPrefix);
        }
        
        return sum;
    }

    private boolean isLeaf(final TreeNode node) {
        return node.left == null && node.right == null;
    }
}
