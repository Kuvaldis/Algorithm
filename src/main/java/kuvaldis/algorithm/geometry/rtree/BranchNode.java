package kuvaldis.algorithm.geometry.rtree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BranchNode extends Node{

    private final List<Node> children;

    BranchNode(final int maxEntries, final int minEntries) {
        super(maxEntries, minEntries);
        this.children = new ArrayList<>(maxEntries);
    }

    @Override
    public Node[] insert(final Point p) {
        final int bestFitIndex = chooseLeaf(p);
        final Node bestFit = children.get(bestFitIndex);
        // add can potentially result in split
        // basically, same as for B-Tree, the underlying child node is split on two nodes
        final Node[] insertResult = bestFit.insert(p);
        getArea().adjustBounds(p);
        if (insertResult == null) {
            return null;
        }

        // child node was split on two
        children.remove(bestFitIndex);
        children.addAll(bestFitIndex, Arrays.asList(insertResult));
        if (children.size() < maxEntries) {
            return null;
        } else {
            return split(p);
        }
    }

    private int chooseLeaf(final Point p) {
        for (int i = 0; i < children.size(); i++) {
            final Node child = children.get(i);
            if (child.getArea().contains(p)) {
                return i;
            }
        }
        // not found overlapping areas in children.
        // first, we need to find where to grow.
        // for that we search an child, which requires smallest grow delta to fit the point
        int bestFitIndex = 0;
        long minGrowAreaSize = Long.MAX_VALUE;
        for (int i = 0; i < children.size(); i++) {
            final Node child = children.get(i);
            final long growAreaSize = child.getArea().calculateGrowAreaSize(p);
            if (growAreaSize < minGrowAreaSize) {
                minGrowAreaSize = growAreaSize;
                bestFitIndex = i;
            }
        }
        return bestFitIndex;
    }

    void insertChild(final Node node) {
        children.add(node);
        this.getArea().adjustBounds(node.getArea());
    }

    private Node[] split(final Point p) {
        // todo implement
        return new Node[0];
    }
}
