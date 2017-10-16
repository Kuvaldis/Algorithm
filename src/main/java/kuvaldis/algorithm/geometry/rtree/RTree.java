package kuvaldis.algorithm.geometry.rtree;

import java.util.Arrays;

/**
 * https://en.wikipedia.org/wiki/R-tree (rus)
 */
public class RTree {

    private Node root;

    /**
     * max number of node's children
     */
    private final int maxEntries;

    /**
     * min number of node's children
     */
    private final int minEntries;

    public RTree(final int maxEntries, final int minEntries) {
        this.maxEntries = maxEntries;
        this.minEntries = minEntries;
    }

    public void add(final Point p) {
        if (root == null) {
            root = new LeafNode(maxEntries, minEntries);
        }
        final Node[] insertResult = root.insert(p);
        if (insertResult == null) {
            return;
        }

        final BranchNode newRoot = new BranchNode(maxEntries, minEntries);
        newRoot.insertChild(insertResult[0]);
        newRoot.insertChild(insertResult[1]);
        root = newRoot;
    }
}
