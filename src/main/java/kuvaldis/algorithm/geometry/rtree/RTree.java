package kuvaldis.algorithm.geometry.rtree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

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
        newRoot.insertChildren(0, insertResult);
        root = newRoot;
    }

    public List<Point> search(final Area area) {
        if (root == null) {
            return Collections.emptyList();
        }
        final List<Point> result = new ArrayList<>();
        final Consumer<Point> consumer = result::add;
        root.search(area, consumer);
        return result;
    }
}
