package kuvaldis.algorithm.geometry;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SegmentSet {

    private final Set<Segment> segments;

    public SegmentSet(final Segment... segments) {
        this(Stream.of(segments).collect(Collectors.toSet()));
    }

    public SegmentSet(final Set<Segment> segments) {
        this.segments = segments;
    }

    /**
     * Does not work when:
     * 1. There are vertical lines
     * 2. There are 3 or more segments intersecting in same point
     * <p>
     * The algorithm uses moving vertical line to determine if a pair of segments intersect.
     * .        .
     * |\  | | /|
     * | \ | |/ |
     * |  \| .  |
     * |   . |  |
     * |   | |  |
     * 1   2 3  4
     * <p>
     * In the picture there are two lines which do not intersect.
     * The moving line positions designated with numbers (1 to 4).
     */
    public boolean hasIntersection() {
        // 1. Create sorted list of points, which moving line will visit.
        // Each point is connected with it's segment
        final List<PointWithSegment> pointWithSegments = segments.stream()
                .flatMap(segment -> Stream.of(new PointWithSegment(segment.leftEnd(), segment, true),
                        new PointWithSegment(segment.rightEnd(), segment, false)))
                .sorted(Comparator.<PointWithSegment>comparingInt(p -> p.getPoint().getX())
                        .thenComparingInt(p -> p.getPoint().getY()))
                .collect(Collectors.toList());

        // 2. Two segments may have same start or end points
        for (int i = 0; i < pointWithSegments.size() - 1; i++) {
            final PointWithSegment first = pointWithSegments.get(i);
            final PointWithSegment second = pointWithSegments.get(i + 1);
            if (first.getPoint().equals(second.getPoint())) {
                return true;
            }
        }

        // 3. Main part
        final TreeSet<Segment> orderedByYSegments = new TreeSet<>(Comparator.comparing(segment -> segment.leftEnd().getY()));
        for (PointWithSegment pointWithSegment : pointWithSegments) {
            final Segment segment = pointWithSegment.getSegment();
            if (pointWithSegment.isLeft()) {
                // we check two neighbouring segments, if either of them intersect given
                final Segment above = orderedByYSegments.ceiling(segment);
                if (above != null && above.intersects(segment)) {
                    return true;
                }
                final Segment below = orderedByYSegments.floor(segment);
                if (below != null && below.intersects(segment)) {
                    return true;
                }
                orderedByYSegments.add(segment);
            } else {
                orderedByYSegments.remove(segment);
                final Segment above = orderedByYSegments.ceiling(segment);
                final Segment below = orderedByYSegments.floor(segment);
                if (above != null && below != null && above.intersects(below)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static class PointWithSegment {

        private final Point point;

        private final Segment segment;

        private final boolean left;

        private PointWithSegment(final Point point, final Segment segment, final boolean left) {
            this.point = point;
            this.segment = segment;
            this.left = left;
        }

        public Point getPoint() {
            return point;
        }

        public Segment getSegment() {
            return segment;
        }

        public boolean isLeft() {
            return left;
        }
    }
}
