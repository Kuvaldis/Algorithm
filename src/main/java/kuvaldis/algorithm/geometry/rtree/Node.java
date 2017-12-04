package kuvaldis.algorithm.geometry.rtree;

import kuvaldis.algorithm.geometry.SquareArea;
import kuvaldis.algorithm.geometry.Point;

import java.util.function.Consumer;

abstract class Node {

    final int maxEntries;

    final int minEntries;

    private final RTreeArea area;

    Node(final int maxEntries, final int minEntries) {
        this.maxEntries = maxEntries;
        this.minEntries = minEntries;
        this.area = new RTreeArea(null, null);
    }

    /**
     * @return null if no split happened, otherwise two nodes. The principle is the same as for B-tree
     */
    public abstract Node[] insert(final Point p);

    RTreeArea getArea() {
        return this.area;
    }

    public abstract void search(final SquareArea area, final Consumer<Point> consumer);
}
