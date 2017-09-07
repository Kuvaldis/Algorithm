package kuvaldis.algorithm.geometry;

public class Vector {

    private final Point from;
    private final Point to;

    public Vector(final Point from, final Point to) {
        this.from = from;
        this.to = to;
    }

    // v1*v2 = |x1  x2| = x1 * y2 - x2 * y1
    //         |y1  y2|
    public long multiply(final Vector v2) {
        final Vector v1 = this;
        return v1.x() * v2.y() - v2.x() * v1.y();
    }

    private int x() {
        return to.getX() - from.getX();
    }

    private int y() {
        return to.getY() - from.getY();
    }
}
