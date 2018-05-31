package kuvaldis.algorithm.geometry;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClosestPoints {

    private final List<Point> points;

    public ClosestPoints(final List<Point> points) {
        this.points = points;
    }

    public Result find() {
        // 0. Preparation step:
        //   1. Convert initial list to internal representation of points, allowing to distinguish between left and right subset
        //   2. Create two auxiliary arrays, one sorted by X, another by Y.
        final List<SubsetPoint> ps = points.stream()
                .map(SubsetPoint::new)
                .collect(Collectors.toList());
        final List<SubsetPoint> xs = ps.stream()
                .sorted(Comparator.comparing(point -> point.getPoint().getX()))
                .collect(Collectors.toList());
        final List<SubsetPoint> ys = ps.stream()
                .sorted(Comparator.comparing(point -> point.getPoint().getY()))
                .collect(Collectors.toList());

        return doFind(xs, ys);
    }

    private Result doFind(final List<SubsetPoint> xs, final List<SubsetPoint> ys) {
        // 1. If there are 3 or less points in ps list we compare distances between all pairs (3 pairs) to find
        // the smallest one.
        if (xs.size() < 4) {
            return doFindForLessThanFourPoints(xs);
        }

        // 2. Split. Split points into two subsets with "equal" size.
        // Same is done for xs and ys.
        // Also a vertical line splitting ps is required.
        final int lastLeftExclusive = xs.size() / 2;
        for (int i = 0; i < lastLeftExclusive; i++) {
            xs.get(i).setLeft(true);
        }
        for (int i = lastLeftExclusive; i < xs.size(); i++) {
            xs.get(i).setLeft(false);
        }
        final Map<Boolean, List<SubsetPoint>> splitXs = splitOnLeftAndRight(xs);
        final Map<Boolean, List<SubsetPoint>> splitYs = splitOnLeftAndRight(ys);
        final List<SubsetPoint> leftXs = splitXs.get(Boolean.TRUE);
        final int l = leftXs.get(leftXs.size() - 1).getPoint().getX();

        // 3. Conquer. Find points with smallest distance within each subset.
        final Result leftResult = doFind(splitXs.get(Boolean.TRUE), splitYs.get(Boolean.TRUE));
        final Result rightResult = doFind(splitXs.get(Boolean.FALSE), splitYs.get(Boolean.FALSE));

        final Result subsetsSmallestResult = leftResult.getDistance() < rightResult.getDistance() ? leftResult : rightResult;

        // 4. At this point we found smallest distance for each subset.
        // However, it might happen that the points we are looking for are located in different subsets.
        // We know the smallest distance for subsets, so our potentially even smaller distance should be less than that.
        // It means that the point from left subset should be restricted by vertical lines (l - delta) and (l),
        // while the one from right should be within (l) and (l + delta) boundaries.
        // .     |  . |    |  .
        //       |    |    |        .
        //       | pl | .  |
        //       |  . | pr |
        //   .   |    |    |    .
        //      -d    l   +d
        //
        // Also, these points should have distance between them by y axis not more than delta.
        // There may be maximum 8 points within such an area.
        //   Pl   Pr
        //  |   |   |
        // _.___.___._
        //  |   |   |
        // d|   |   |d
        // _.___.___._
        //  | d | d |
        //      l
        //
        // 4 points from Pl (2 leftmost, 2 central), 4 points from Pr (2 rightmost, 2 central).
        // Thus in order to find smallest distance we have to do two things:
        // 1. Find points laying within (l - delta, l + delta) area. It can be derived from Y, lets call it Y'.
        // 2. For each point in Y' find distances between it and 7 following ones.
        //    Find smallest distance and compare it with delta.

        final double delta = subsetsSmallestResult.getDistance();
        final double leftBoundary = l - delta;
        final double rightBoundary = l + delta;
        final List<Point> yPrimes = ys.stream()
                .map(SubsetPoint::getPoint)
                .filter(point -> leftBoundary <= point.getX() && point.getX() <= rightBoundary)
                .collect(Collectors.toList());
        Result result = subsetsSmallestResult;
        for (int i = 0; i < yPrimes.size(); i++) {
            final Point p1 = yPrimes.get(i);
            for (int j = i + 1; j < i + 8 && j < yPrimes.size(); j++) {
                final Point p2 = yPrimes.get(j);
                final double distance = distance(p1, p2);
                if (distance < result.getDistance()) {
                    result = new Result(p1, p2, distance);
                }
            }
        }

        return result;
    }

    private Result doFindForLessThanFourPoints(final List<SubsetPoint> ps) {
        if (ps.size() < 2) {
            return null;
        }

        final Point p1 = ps.get(0).getPoint();
        final Point p2 = ps.get(1).getPoint();
        final double d12 = distance(p1, p2);
        if (ps.size() == 2) {
            return new Result(p1, p2, d12);
        } else {
            final Point p3 = ps.get(2).getPoint();
            final double d13 = distance(p1, p3);
            final double d23 = distance(p2, p3);
            if (d12 < d13) {
                if (d12 < d23) {
                    return new Result(p1, p2, d12);
                } else {
                    return new Result(p2, p3, d23);
                }
            } else {
                if (d13 < d23) {
                    return new Result(p1, p3, d13);
                } else {
                    return new Result(p2, p3, d23);
                }
            }
        }
    }

    private double distance(final Point p1, final Point p2) {
        final int x = Math.abs(p2.getX() - p1.getX());
        final int y = Math.abs(p2.getY() - p1.getY());
        return Math.sqrt(x * x + y * y);
    }

    private Map<Boolean, List<SubsetPoint>> splitOnLeftAndRight(final List<SubsetPoint> points) {
        return points.stream()
                .collect(Collectors.partitioningBy(SubsetPoint::isLeft));
    }

    private static class SubsetPoint {

        private final Point point;

        private boolean left;

        private SubsetPoint(final Point point) {
            this.point = point;
        }

        public Point getPoint() {
            return point;
        }

        public boolean isLeft() {
            return left;
        }

        public boolean isRight() {
            return !left;
        }

        public void setLeft(final boolean left) {
            this.left = left;
        }
    }

    public static class Result {

        private final List<Point> points;

        private final double distance;

        private Result(final Point p1, final Point p2, final double distance) {
            this.points = Stream.of(p1, p2).collect(Collectors.toList());
            this.distance = distance;
        }

        public double getDistance() {
            return distance;
        }

        public List<Point> getPoints() {
            return points;
        }
    }

}
