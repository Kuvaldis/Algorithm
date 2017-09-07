package kuvaldis.algorithm.geometry;

public class Segment {

    private final Point p1;
    private final Point p2;

    public Segment(final Point p1, final Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }
}
