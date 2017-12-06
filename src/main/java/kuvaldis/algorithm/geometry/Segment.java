package kuvaldis.algorithm.geometry;

public class Segment {

    private final Point p1;
    private final Point p2;

    public Segment(final Point p1, final Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    public boolean intersects(final Segment segment2) {
        final Segment segment1 = this;

        // 1. First of, we have to check if segment frames do not intersect.
        // if they do, then we go to the next step. Otherwise the segments cannot intersect
        final SegmentFrame frame1 = new SegmentFrame(segment1);
        final SegmentFrame frame2 = new SegmentFrame(segment2);
        if (!frame1.hasIntersectionWith(frame2)) {
            return false;
        }

        // 2. Here we know that the frames intersect, but we still don't know if the segments do
        // In order to find out it we do the following.
        // We take first segment and extend it to a line, so it splits plane on two parts.
        /*
           . p3
            \
             \  p1      p2
         -----\-.-------.---------
               \
                \
                 . p4
         */
        // Now, we need to figure out if the points of the second segment are on different sides of the line.
        // Next, we do the same for the second segment extended and first checked.
        // If in both cases the result is positive, it only means that the segments have intersection point,
        // taking into account that their frames intersect.

        // How can we figure that two points on the different side of the line?
        /*
            p3       ...
            .        /
            |\      /
            | \    /
            |  \  /
            |   \/
            |   /\
            |p2.  \
            | /    \
            |/      \
            .--------.
           /p1       p4
          /
         /
        ...
         */
        // We can find product of vectors p1p3 and p1p2, and p1p4 and p1p2.
        // If the results have the same sign, it means that p1p3 and p1p4 are on the same side of the plane.
        // In other words, (p1p3*p1p2)*(p1p4*p1p2) <= 0 is true only when p1p3 and p1p4 are on the different sides of
        // plane created by extension of p1p2.
        // On the picture above p1p3 is not on the same side as p1p4, so the result of (p1p3*p1p2)*(p1p4*p1p2) is less than 0
        // In case the result is 0 it means that one of the points either p3 or p4 lie on the segment p1p2,
        // which is considered to be an intersection.
        // The same should be calculated for the second case.

        // Just an example of one case in terms of points. We are going to use additional data structures.
        // (p3 - p1) * (p2 - p1) = |x3 - x1  x2 - x1| = (x3 - x1) * (y2 - y1) - (x2 - x1) * (y3 - y1)
        //                         |y3 - y1  y2 - y1|

        final Point p1 = segment1.p1;
        final Point p2 = segment1.p2;
        final Point p3 = segment2.p1;
        final Point p4 = segment2.p2;

        // (p1p3*p1p2)*(p1p4*p1p2) <= 0
        final Vector v13 = new Vector(p1, p3);
        final Vector v12 = new Vector(p1, p2);
        final Vector v14 = new Vector(p1, p4);

        if (v13.multiply(v12) * v14.multiply(v12) > 0) {
            return false;
        }

        // Check (p3p1*p3p4)*(p3p2*p3p4) <= 0
        final Vector v31 = new Vector(p3, p1);
        final Vector v34 = new Vector(p3, p4);
        final Vector v32 = new Vector(p3, p2);

        if (v31.multiply(v34) * v32.multiply(v34) > 0) {
            return false;
        }

        return true;
    }

    public boolean contains(final Point point) {
        // 1. Check the point within frame
        final SegmentFrame segmentFrame = new SegmentFrame(this);
        if (!segmentFrame.contains(point)) {
            return false;
        }

        // 2. Point is within the segment frame. With vector multiplication we can figure if a point lays on the segment.
        // p0 - our point
        // p1 - segment start
        // p2 - segment end
        // if p0p1 * p0p2 == 0, then the point is on the line.
        final Vector v1 = new Vector(point, getP1());
        final Vector v2 = new Vector(point, getP2());
        if (v1.multiply(v2) != 0) {
            return false;
        }

        return true;
    }

    public Point leftEnd() {
        if (p1.getX() <= p2.getX()) {
            return p1;
        }
        return p2;
    }

    public Point rightEnd() {
        if (p2.getX() >= p1.getX()) {
            return p2;
        }
        return p1;
    }
}
