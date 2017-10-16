package kuvaldis.algorithm.geometry.rtree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BranchNode extends Node {

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
        final Node[] insertResult = bestFit.insert(p);
        getArea().adjustBorders(p);
        if (insertResult == null) {
            return null;
        }

        // basically, same as for B-Tree, the child node is split on two nodes
        // in this case we substitute old child by two new ones and split current node if needed
        children.remove(bestFitIndex);
        children.addAll(bestFitIndex, Arrays.asList(insertResult));
        // no need to adjust borders, it was done before
        if (children.size() < maxEntries) {
            return null;
        } else {
            return split();
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

    void insertChildren(final int index, final Node... nodes) {
        children.addAll(index, Arrays.asList(nodes));
        for (Node node : nodes) {
            this.getArea().adjustBorders(node.getArea());
        }
    }

    private Node[] split() {

        final BranchNode firstNode = new BranchNode(maxEntries, minEntries);
        final BranchNode secondNode = new BranchNode(maxEntries, minEntries);

        // for each coordinate (x and y) we do following:
        // 1. find nodes with minimal top-right point and maximal bottom-left
        // 2. calculate distance by coordinate between these points (min top-right and max bottom-left)
        // 3. calculate overall width by coordinate
        // 4. normalize distance from the 2 step by the width from 3 step. Let's call it spread
        // 5. compare results for x and y coordinates.
        //    Whatever coordinate has higher spread is the one, which nodes from step 1 become first children in new branch nodes.

        // 1
        int xMinTopRightIndex = 0;
        int xMaxBottomLeftIndex = 0;
        int yMinTopRightIndex = 0;
        int yMaxBottomLeftIndex = 0;
        for (int i = 0; i < children.size(); i++) {
            final Node node = children.get(i);

            if (children.get(xMinTopRightIndex).getArea().getTopRight().getX() > node.getArea().getTopRight().getX()) {
                xMinTopRightIndex = i;
            }
            if (children.get(xMaxBottomLeftIndex).getArea().getBottomLeft().getX() < node.getArea().getBottomLeft().getX()) {
                xMaxBottomLeftIndex = i;
            }

            if (children.get(yMinTopRightIndex).getArea().getTopRight().getY() > node.getArea().getTopRight().getY()) {
                yMinTopRightIndex = i;
            }
            if (children.get(yMaxBottomLeftIndex).getArea().getBottomLeft().getY() < node.getArea().getBottomLeft().getY()) {
                yMaxBottomLeftIndex = i;
            }
        }

        // 2
        int xDistance = Math.abs(children.get(xMaxBottomLeftIndex).getArea().getBottomLeft().getX() -
                children.get(xMinTopRightIndex).getArea().getTopRight().getX());
        int yDistance = Math.abs(children.get(yMaxBottomLeftIndex).getArea().getBottomLeft().getY() -
                children.get(yMinTopRightIndex).getArea().getTopRight().getY());

        // 3
        int xWidth = getArea().getXWidth();
        int yWidth = getArea().getYWidth();

        // 4
        double xSpread = xDistance / xWidth;
        double ySpread = yDistance / yWidth;

        // 5
        int firstNodeChildIndex = xSpread > ySpread ? xMinTopRightIndex : yMinTopRightIndex;
        int secondNodeChildIndex = xSpread > ySpread ? xMaxBottomLeftIndex : yMaxBottomLeftIndex;

        // same node, which is actually possible
        // for instance only two nodes exist and they both shape left and right x borders.
        // one node is under the other with minimal distance in between
        // in such case ySpread is really small, while xSpread is 1 (maximum possible value for spread).
        // since x borders are the same the algorithm on step 1 picks same index for min top-right and max bottom-left
        if (firstNodeChildIndex == secondNodeChildIndex) {
            firstNodeChildIndex = 0;
            secondNodeChildIndex = children.size() - 1;
        }

        firstNode.insertChildren(0, children.get(firstNodeChildIndex));
        secondNode.insertChildren(0, children.get(secondNodeChildIndex));

        // Insert as follows:
        // 1. If number of children left is not enough to have minEntries number of children even in one node,
        // then all children left are added to the first node
        // 2. If some node has already got maxEntries number of children, then add the rest to another one
        // 3. Otherwise insert node optimally, that is node area should grow minimally

        final boolean addToFirst = children.size() - 2 /* 2 are already in the new nodes */ <= minEntries - 1 /* 1 is already in the node */;
        for (int i = 0; i < children.size(); i++) {
            final Node node = children.get(i);
            if (i == firstNodeChildIndex || i == secondNodeChildIndex) {
                continue;
            }
            if (addToFirst) {
                firstNode.insertChildren(firstNode.children.size() - 1, node);
            } else if (firstNode.children.size() == maxEntries) {
                secondNode.insertChildren(secondNode.children.size() - 1, node);
            } else if (secondNode.children.size() == maxEntries) {
                firstNode.insertChildren(firstNode.children.size() - 1, node);
            } else {
                insertNodeOptimally(firstNode, secondNode, node);
            }
        }

        return new Node[]{firstNode, secondNode};
    }

    private void insertNodeOptimally(final BranchNode firstNode, final BranchNode secondNode, final Node child) {
        if (firstNode.getArea().calculateGrowAreaSize(child.getArea()) <= secondNode.getArea().calculateGrowAreaSize(child.getArea())) {
            firstNode.insertChildren(firstNode.children.size() - 1, child);
        } else {
            secondNode.insertChildren(secondNode.children.size() - 1, child);
        }
    }
}
