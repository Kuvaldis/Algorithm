package kuvaldis.algorithm.geometry.rtree;

class Area {

    private Point topLeft;

    private Point bottomRight;

    Area(final Point topLeft, final Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    void adjustBounds(final Point p) {
        if (isEmpty()) {
            this.topLeft = p;
            this.bottomRight = p;
        } else {
            this.topLeft = calculateAdjustedTopLeft(p);
            this.bottomRight = calculateAdjustedBottomRight(p);
        }
    }

    void adjustBounds(final Area area) {
        if (isEmpty()) {
            this.topLeft = area.topLeft;
            this.bottomRight = area.bottomRight;
        } else {
            this.topLeft = calculateAdjustedTopLeft(area.topLeft);
            this.bottomRight = calculateAdjustedBottomRight(area.bottomRight);
        }
    }

    boolean contains(final Point p) {
        if (isEmpty()) {
            return false;
        }
        return topLeft.getX() <= p.getX() && p.getX() <= bottomRight.getX() &&
                bottomRight.getY() <= p.getY() && p.getY() <= topLeft.getY();
    }

    long calculateGrowAreaSize(final Point p) {
        if (isEmpty()) {
            return 0L;
        }
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

    private boolean isEmpty() {
        return topLeft == null || bottomRight == null;
    }
}
