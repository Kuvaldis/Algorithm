package kuvaldis.algorithm.geometry;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JarvisConvexHull {

    private final List<Point> points;

    public JarvisConvexHull(final Point... points) {
        this.points = Stream.of(points).collect(Collectors.toList());
    }

    public List<Point> build() {
        // 0. It's possible to create a convex hull only out of 3 or more points
        if (points.size() < 3) {
            return Collections.emptyList();
        }

        // 1. Pick p0, the point with lowest y coordinate.
        // If there are two or more points with lowest coordinate, pick one with lowest x coordinate.
        final Point p0 = points.stream()
                .min(Comparator.comparingInt(Point::getY)
                        .thenComparingInt(Point::getX))
                .orElseThrow(IllegalStateException::new);

        // 2. The idea is to "wrap" points. Thus, we look for a point having smallest polar angle relatively to the current one.
        // That is, the angle between vectors, both are starting from the current point, but one is directed strictly to the right,
        // i.e. a horizontal, the other one is to the next point.
        // If there are several points with the same lowest angle, then we pick farthest.
        // We do it until we reach a point with highest y value.
        // Then we do quite the same, but this time we search lowest angle from upside down point of view,
        // that is, between horizontal vector directed to the left and vector constituted by current point and a potential one.

        final int maxY = points.stream()
                .mapToInt(Point::getY)
                .max()
                .orElse(Integer.MAX_VALUE);

        final LinkedList<Point> result = new LinkedList<>();
        result.add(p0);
        Point current = p0;
        int direction = 1;
        while (true) {
            final Vector horizontalVector = new Vector(current, new Point(current.getX() + direction, current.getY()));
            final Point p1 = current;
            final int sign = direction;
            current = points.stream()
                    .filter(point -> !p1.equals(point))
                    // take into account only those having y higher or equal to current for right chain
                    // and lower or equal for left chain
                    .filter(point -> Integer.compare(point.getY(), p1.getY()) * sign >= 0)
                    .map(p2 -> new Vector(p1, p2))
                    .max(Comparator.comparingDouble(horizontalVector::cos)
                            .thenComparingDouble(Vector::magnitude))
                    .map(Vector::getTo)
                    .orElseThrow(IllegalStateException::new);
            if (current.getY() == maxY) {
                direction = -1;
            }
            if (p0.equals(current)) {
                break;
            }
            result.add(current);
        }

        return result;
    }
}
