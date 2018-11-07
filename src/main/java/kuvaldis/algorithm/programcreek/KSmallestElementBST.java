package kuvaldis.algorithm.programcreek;

import java.util.Stack;

public class KSmallestElementBST {

    public Integer search(final TreeNode root, final int k) {
        final Stack<TreeNode> stack = new Stack<>();

        TreeNode node = root;
        int counter = k;

        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                counter--;
                if (counter == 0) {
                    return node.value;
                }
                node = node.right;
            }
        }
        return null;
    }
}
