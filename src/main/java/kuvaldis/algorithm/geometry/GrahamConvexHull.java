package kuvaldis.algorithm.geometry;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GrahamConvexHull {

    private final List<Point> points;

    public GrahamConvexHull(final Point... points) {
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

        // 2. Sort other points (pi) by angle between horizontal vector starting from p0 and p0pi, lowest first.
        // Note, that possible angle values are from 0 to 180 degrees, since p0 is lowest point or all points are to the right.
        // Additionally, cos(0) is 1.0 and cos(180) is -1.0.
        // Thus, if we sort the points by cos in descendant order, i.e. from highest to lowest values, it would be the same.

        // this is our horizontal vector
        final Vector v0 = new Vector(p0, new Point(p0.getX() + 1, p0.getY()));
        final LinkedList<Point> sortedPoints = points.stream()
                .filter(point -> !p0.equals(point))
                .sorted(Comparator.<Point>comparingDouble(p -> v0.cos(new Vector(p0, p)))
                        .reversed())
                .collect(Collectors.toCollection(LinkedList::new));

        // 3. Put 3 first points, including p0 to the resulting stack
        // They are base for calculation.
        // Note, that if we take only these 3 points into account, the stack basically contains the solution,
        // that is, 3 points out of 3 in total make a convex hull.
        // Thus, on the first step stack contains correct result for it.
        final LinkedList<Point> stack = new LinkedList<>();
        // p0
        stack.push(p0);
        // p1
        stack.push(sortedPoints.pollFirst());
        // p2
        stack.push(sortedPoints.pollFirst());

        // 4.
        for (Point point : sortedPoints) {
            while (true) {
                final Point top = stack.pop();
                final Point nextToTop = stack.peek();

                final Vector v1 = new Vector(nextToTop, top);
                final Vector v2 = new Vector(nextToTop, point);
                // It means that v2 is located to the right of v1, that is, we are moving to the right.
                // Thus, with the fact that all points are sorted by angle, the 'point' spans the 'top',
                // i.e. 'point' puts 'top' inside the convex hull if included into the resulting set.
                if (v1.multiply(v2) < 0) {
                    // we already popped top point, so nothing to do here
                    continue;
                }
                // otherwise push top back, since if we are here it means that we 'moved to the left',
                // that is, both top and point add up to the result for this step.
                stack.push(top);
                stack.push(point);
                break;
            }
        }

        Collections.reverse(stack);

        return stack;
    }
}
