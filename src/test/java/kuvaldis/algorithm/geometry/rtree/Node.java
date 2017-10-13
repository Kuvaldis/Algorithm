package kuvaldis.algorithm.geometry.rtree;

abstract class Node {

    final int maxEntries;

    final int minEntries;

    private final Area area;

    Node(final int maxEntries, final int minEntries) {
        this.maxEntries = maxEntries;
        this.minEntries = minEntries;
        this.area = new Area();
    }

    /**
     * @return null if no split happened, otherwise a non-null new node. The principle is the same as for B-tree
     */
    public abstract Node add(final Point p);

    Area getArea() {
        return this.area;
    }
}
