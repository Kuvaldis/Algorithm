package kuvaldis.algorithm.geometry.rtree;

import java.util.ArrayList;
import java.util.List;

public class LeafNode extends Node {

    private final List<Point> entries;

    LeafNode(final int maxEntries, final int minEntries) {
        super(maxEntries, minEntries);
        this.entries = new ArrayList<>(maxEntries);
    }

    @Override
    public Node[] insert(final Point p) {
        getArea().adjustBorders(p);
        entries.add(p);
        if (entries.size() <= maxEntries) {
            return null;
        } else {
            return split();
        }
    }

    private Node[] split() {

        final LeafNode firstNode = new LeafNode(maxEntries, minEntries);
        final LeafNode secondNode = new LeafNode(maxEntries, minEntries);

        // it is less complicated case here, than in branch, but they are kind of the same
        // we don't have to calculate normalized distances or something like that, because
        // area of leaf node is restricted by points and does not contain any nodes with their areas.
        // in this case we view points as rectangles where top left and bottom right are the same,
        // thus normalized distances by each coordinate are always 1.
        // Therefore, we can just take leftmost point by X axis as the first entry in the first new leaf node
        // and rightmost point by X axis as the first entry in the second new leaf node

        int leftmostIndex = 0;
        int rightmostIndex = 0;
        for (int i = 0; i < entries.size(); i++) {
            final Point entry = entries.get(i);
            if (entry.getX() < entries.get(leftmostIndex).getX()) {
                leftmostIndex = i;
            }
            if (entry.getX() > entries.get(rightmostIndex).getX()) {
                rightmostIndex = i;
            }
        }

        // The rest is quite the same as for general (branch node) case

        if (leftmostIndex == rightmostIndex) {
            // for some reason they are the same. Just grab first and last as the base for new leaf nodes
            leftmostIndex = 0;
            rightmostIndex = entries.size() - 1;
        }

        firstNode.insert(entries.get(leftmostIndex));
        secondNode.insert(entries.get(rightmostIndex));

        // again, the same as for branch node

        final boolean addToFirst = entries.size() - 2 <= minEntries - 1;

        for (int i = 0; i < entries.size(); i++) {
            final Point entry = entries.get(i);
            if (i == leftmostIndex || i == rightmostIndex) {
                continue;
            }
            if (addToFirst) {
                firstNode.insert(entry);
            } else if (firstNode.entries.size() == maxEntries) {
                secondNode.insert(entry);
            } else if (secondNode.entries.size() == maxEntries) {
                firstNode.insert(entry);
            } else {
                insertPointOptimally(firstNode, secondNode, entry);
            }
        }

        return new Node[]{firstNode, secondNode};
    }

    private void insertPointOptimally(final LeafNode firstNode, final LeafNode secondNode, final Point p) {
        // insert other points the way that area grows minimally.
        // That is, if first node area grows less than second the value should be added there.
        // Otherwise, into the second
        if (firstNode.getArea().calculateGrowAreaSize(p) <= secondNode.getArea().calculateGrowAreaSize(p)) {
            firstNode.insert(p);
        } else {
            secondNode.insert(p);
        }
    }

}
