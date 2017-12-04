package kuvaldis.algorithm.geometry;

public class SquareArea {

    protected Point bottomLeft;

    protected Point topRight;

    public SquareArea(final Point bottomLeft, final Point topRight) {
        this.bottomLeft = bottomLeft;
        this.topRight = topRight;
    }

    public boolean contains(final Point p) {
        if (isEmpty()) {
            return false;
        }
        return bottomLeft.getX() <= p.getX() && p.getX() <= topRight.getX() &&
                bottomLeft.getY() <= p.getY() && p.getY() <= topRight.getY();
    }

    public boolean intersects(final SquareArea area) {
        if (isEmpty()) {
            return false;
        }

        if (this.bottomLeft.getX() > area.topRight.getX() ||
                this.topRight.getX() < area.bottomLeft.getX() ||
                this.bottomLeft.getY() > area.topRight.getY() ||
                this.topRight.getY() < area.bottomLeft.getY()) {
            return false;
        }

        return true;
    }

    public Point getBottomLeft() {
        return bottomLeft;
    }

    public Point getTopRight() {
        return topRight;
    }

    public int getXWidth() {
        return topRight.getX() - bottomLeft.getX();
    }

    public int getYWidth() {
        return topRight.getY() - bottomLeft.getY();
    }

    public long area() {
        final long length = topRight.getX() - bottomLeft.getX();
        final long height = topRight.getY() - bottomLeft.getY();
        return length * height;
    }

    public boolean isEmpty() {
        return bottomLeft == null || topRight == null;
    }
}
