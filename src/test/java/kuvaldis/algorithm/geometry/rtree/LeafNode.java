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
    public Node add(final Point p) {
        if (entries.size() < maxEntries) {
            getArea().adjustBounds(p);
            entries.add(p);
            return null;
        } else {
            // todo split
            return null;
        }
    }


}
