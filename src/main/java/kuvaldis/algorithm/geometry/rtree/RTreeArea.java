package kuvaldis.algorithm.geometry.rtree;

import kuvaldis.algorithm.geometry.SquareArea;
import kuvaldis.algorithm.geometry.Point;

class RTreeArea extends SquareArea {

    RTreeArea(final Point bottomLeft, final Point topRight) {
        super(bottomLeft, topRight);
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

    void adjustBorders(final RTreeArea area) {
        if (isEmpty()) {
            this.bottomLeft = area.bottomLeft;
            this.topRight = area.topRight;
        } else {
            this.bottomLeft = calculateAdjustedBottomLeft(area.bottomLeft);
            this.topRight = calculateAdjustedTopRight(area.topRight);
        }
    }

    long calculateGrowAreaSize(final Point p) {
        if (isEmpty()) {
            return 0L;
        }
        final Point growBottomLeft = calculateAdjustedBottomLeft(p);
        final Point growTopRight = calculateAdjustedTopRight(p);
        final SquareArea growArea = new SquareArea(growBottomLeft, growTopRight);
        return growArea.area() - this.area();
    }

    long calculateGrowAreaSize(final SquareArea area) {
        if (isEmpty()) {
            return area.area();
        }
        final Point growBottomLeft = calculateAdjustedBottomLeft(area.getBottomLeft());
        final Point growTopRight = calculateAdjustedTopRight(area.getTopRight());
        final SquareArea growArea = new SquareArea(growBottomLeft, growTopRight);
        return growArea.area() - this.area();
    }

    private Point calculateAdjustedBottomLeft(final Point p) {
        return new Point(Math.min(bottomLeft.getX(), p.getX()), Math.min(bottomLeft.getY(), p.getY()));
    }

    private Point calculateAdjustedTopRight(final Point p) {
        return new Point(Math.max(topRight.getX(), p.getX()), Math.max(topRight.getY(), p.getY()));
    }
}
