package kuvaldis.algorithm.geometry;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SegmentSet {

    private final Set<Segment> segments;

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
    public boolean hasIntersections() {
        // 1. Create sorted list of points, which moving line will visit.
        // Each point is connected with it's segment
        final List<PointWithSegment> pointWithSegments = segments.stream()
                .flatMap(segment -> Stream.of(new PointWithSegment(segment.bottomLeft(), segment, true),
                        new PointWithSegment(segment.topRight(), segment, false)))
                .sorted(Comparator.comparing(PointWithSegment::getPoint))
                .collect(Collectors.toList());

        // 2. Two segments may have same start or end points
        for (int i = 0; i < pointWithSegments.size() - 1; i++) {
            final PointWithSegment first = pointWithSegments.get(i);
            final PointWithSegment second = pointWithSegments.get(i + 1);
            if (first.getPoint().equals(second.getPoint())) {
                return true;
            }
        }

        // todo implement the rest
        return false;
    }

    private static class PointWithSegment {

        private final Point point;

        private final Segment segment;

        private final boolean start;

        private PointWithSegment(final Point point, final Segment segment, final boolean start) {
            this.point = point;
            this.segment = segment;
            this.start = start;
        }

        public Point getPoint() {
            return point;
        }

        public Segment getSegment() {
            return segment;
        }

        public boolean isStart() {
            return start;
        }
    }
}
