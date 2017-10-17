package kuvaldis.algorithm.geometry.rtree;

import java.util.function.Consumer;

abstract class Node {

    final int maxEntries;

    final int minEntries;

    private final Area area;

    Node(final int maxEntries, final int minEntries) {
        this.maxEntries = maxEntries;
        this.minEntries = minEntries;
        this.area = new Area(null, null);
    }

    /**
     * @return null if no split happened, otherwise two nodes. The principle is the same as for B-tree
     */
    public abstract Node[] insert(final Point p);

    Area getArea() {
        return this.area;
    }

    public abstract void search(final Area area, final Consumer<Point> consumer);
}
