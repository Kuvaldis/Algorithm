package kuvaldis.algorithm.geometry.rtree;

import java.util.ArrayList;
import java.util.List;

public class BranchNode extends Node{

    private final List<Node> children;

    BranchNode(final int maxEntries, final int minEntries) {
        super(maxEntries, minEntries);
        this.children = new ArrayList<>(maxEntries);
    }

    @Override
    public Node add(final Point p) {
        if (children.size() < maxEntries) {
            for (int i = 0; i < children.size(); i++) {
                final Node child = children.get(i);
                if (child.getArea().contains(p)) {
                    // add can potentially result in split
                    // basically, same as for B-Tree, the underlying child node is split on two nodes
                    final Node newChild = child.add(p);
                    children.add(i, newChild);
                    return null;
                }
            }
            // not found overlapping areas in children.
            // first, we need to find where to grow.
            // for that we search an child, which requires smallest grow delta to fit the point
            Node bestFit = children.get(0);
            long minGrowAreaSize = Long.MAX_VALUE;
            for (Node child : children) {
                final long growAreaSize = child.getArea().calculateGrowAreaSize(p);
                if (growAreaSize < minGrowAreaSize) {
                    minGrowAreaSize = growAreaSize;
                    bestFit = child;
                }
            }
            bestFit.add(p);
            getArea().adjustBounds(p);
            return null;
        } else {
            // todo split node
            return null;
        }

    }


}
