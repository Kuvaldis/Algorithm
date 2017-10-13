package kuvaldis.algorithm.geometry.rtree;

class Area {

    private Point topLeft;

    private Point bottomRight;

    Area(final Point topLeft, final Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public boolean isEmpty() {
        return topLeft == null || bottomRight == null;
    }

    void adjustBounds(final Point p) {
        this.topLeft = calculateAdjustedTopLeft(p);
        this.bottomRight = calculateAdjustedBottomRight(p);
    }

    boolean contains(final Point p) {
        return topLeft.getX() <= p.getX() && p.getX() <= bottomRight.getX() &&
                bottomRight.getY() <= p.getY() && p.getY() <= topLeft.getY();
    }

    long calculateGrowAreaSize(final Point p) {
        final Point growTopLeft = calculateAdjustedTopLeft(p);
        final Point growBottomRight = calculateAdjustedBottomRight(p);
        final Area growArea = new Area(growTopLeft, growBottomRight);
        return growArea.area() - this.area();
    }

    private long area() {
        final long length = bottomRight.getX() - topLeft.getX();
        final long height = topLeft.getY() - bottomRight.getY();
        return length * height;
    }

    private Point calculateAdjustedTopLeft(final Point p) {
        return new Point(Math.min(topLeft.getX(), p.getX()), Math.max(topLeft.getY(), p.getY()));
    }

    private Point calculateAdjustedBottomRight(final Point p) {
        return new Point(Math.max(bottomRight.getX(), p.getX()), Math.min(bottomRight.getY(), p.getY()));
    }
}
