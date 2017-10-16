package kuvaldis.algorithm.geometry.rtree;

class Area {

    private Point bottomLeft;

    private Point topRight;

    Area(final Point bottomLeft, final Point topRight) {
        this.bottomLeft = bottomLeft;
        this.topRight = topRight;
    }

    void adjustBorders(final Point p) {
        if (isEmpty()) {
            this.bottomLeft = p;
            this.topRight = p;
        } else {
            this.bottomLeft = calculateAdjustedBottomLeft(p);
            this.topRight = calculateAdjustedTopRight(p);
        }
    }

    void adjustBorders(final Area area) {
        if (isEmpty()) {
            this.bottomLeft = area.bottomLeft;
            this.topRight = area.topRight;
        } else {
            this.bottomLeft = calculateAdjustedBottomLeft(area.bottomLeft);
            this.topRight = calculateAdjustedTopRight(area.topRight);
        }
    }

    boolean contains(final Point p) {
        if (isEmpty()) {
            return false;
        }
        return bottomLeft.getX() <= p.getX() && p.getX() <= topRight.getX() &&
                bottomLeft.getY() <= p.getY() && p.getY() <= topRight.getY();
    }

    long calculateGrowAreaSize(final Point p) {
        if (isEmpty()) {
            return 0L;
        }
        final Point growBottomLeft = calculateAdjustedBottomLeft(p);
        final Point growTopRight = calculateAdjustedTopRight(p);
        final Area growArea = new Area(growBottomLeft, growTopRight);
        return growArea.area() - this.area();
    }

    long calculateGrowAreaSize(final Area area) {
        if (isEmpty()) {
            return area.area();
        }
        final Point growBottomLeft = calculateAdjustedBottomLeft(area.getBottomLeft());
        final Point growTopRight = calculateAdjustedTopRight(area.getTopRight());
        final Area growArea = new Area(growBottomLeft, growTopRight);
        return growArea.area() - this.area();
    }

    Point getBottomLeft() {
        return bottomLeft;
    }

    Point getTopRight() {
        return topRight;
    }

    int getXWidth() {
        return topRight.getX() - bottomLeft.getX();
    }

    int getYWidth() {
        return topRight.getY() - bottomLeft.getY();
    }

    private long area() {
        final long length = topRight.getX() - bottomLeft.getX();
        final long height = topRight.getY() - bottomLeft.getY();
        return length * height;
    }

    private Point calculateAdjustedBottomLeft(final Point p) {
        return new Point(Math.min(bottomLeft.getX(), p.getX()), Math.min(bottomLeft.getY(), p.getY()));
    }

    private Point calculateAdjustedTopRight(final Point p) {
        return new Point(Math.max(topRight.getX(), p.getX()), Math.max(topRight.getY(), p.getY()));
    }

    private boolean isEmpty() {
        return bottomLeft == null || topRight == null;
    }
}
