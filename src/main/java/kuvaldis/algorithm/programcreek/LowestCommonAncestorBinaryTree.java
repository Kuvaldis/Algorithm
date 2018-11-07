package kuvaldis.algorithm.programcreek;

public class LowestCommonAncestorBinaryTree {

    private Integer result = null;

    public Integer search(final TreeNode node,
                            final int v1,
                            final int v2) {
        doSearch(node, v1, v2);
        return result;
    }

    private int doSearch(final TreeNode node,
                         final int v1,
                         final int v2) {
        if (result != null) {
            return 0;
        }
        if (node == null) {
            return 0;
        }

        final int leftFound = doSearch(node.left, v1, v2);
        final int rightFound = doSearch(node.right, v1, v2);
        final int currentFound = node.value == v1 || node.value == v2 ? 1 : 0;
        final int foundTotal = leftFound + rightFound + currentFound;
        if (foundTotal == 2 && result == null) {
            result = node.value;
        }
        return foundTotal;
    }
}
