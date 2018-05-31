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

    public double cos(final Vector v2) {
        final Vector v1 = this;
        return v1.dotProduct(v2) / (v1.magnitude() * v2.magnitude());
    }

    public double magnitude() {
        return Math.sqrt(Math.pow(x(), 2) + Math.pow(y(), 2));
    }

    public Point getFrom() {
        return from;
    }

    public Point getTo() {
        return to;
    }

    private long dotProduct(final Vector v2) {
        final Vector v1 = this;
        return v1.x() * v2.x() + v1.y() * v2.y();
    }

    private int x() {
        return to.getX() - from.getX();
    }

    private int y() {
        return to.getY() - from.getY();
    }
}
