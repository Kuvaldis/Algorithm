package kuvaldis.algorithm.geometry;

public class SegmentIntersection {

    public boolean hasIntersection(final Segment segment1, final Segment segment2) {
        // 1. First of, we have to check if segment frames do not intersect.
        // if they do, then we go to the next step. Otherwise the segments cannot intersect
        /*
             _____________.p2                                      p1.-------------
            |         ___/|                                          |\___         |
            |     ___/    | <--- Segment frame. Can also be like --> |    \___     |
            | ___/        |                                          |        \___ |
            |/            |                                          |            \|
          p1.-------------                                            -------------.p2
         */
        // First, we are ready to find bottom-left and top-right points for the frames
        final Point bottomLeft1 = resolveFrameBottomLeftPoint(segment1);
        final Point topRight1 = resolveFrameTopRightPoint(segment1);
        final Point bottomLeft2 = resolveFrameBottomLeftPoint(segment2);
        final Point topRight2 = resolveFrameTopRightPoint(segment2);
        // Now, there are several cases, when it's guaranteed that frames do not intersect,
        // which is obviously means that the segments do not either
        // Otherwise, the segments still might have an intersection
        /*
          1. tr1.x < bl2.x    2. tr2.x < bl1.x

                tr1                 tr2
             ----.  ----         ----.  ----
            | f1 | | f2 |       | f2 | | f1 |
             ----  .----         ----  .----
                  bl2                 bl1

          3. tr2.y < bl1.y    4. tr1.y < bl2.y

                ----                ----
               | f1 |              | f2 |
               .----               .----
              bl1                 bl2
                   tr2                 tr1
                ----.               ----.
               | f2 |              | f1 |
                ----                ----
         */
        if (topRight1.getX() < bottomLeft2.getX() ||
                topRight2.getX() < bottomLeft1.getX() ||
                topRight2.getY() < bottomLeft1.getY() ||
                topRight1.getY() < bottomLeft2.getY()) {
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
        // In other words (p1p3*p1p2)*(p1p4*p1p2) <= 0.
        // In case the result is 0 it means that one of the points either p3 or p4 lie on the segment p1p2,
        // which is considered to be an intersection.
        // The same should be calculated for the second case.
        // todo implement this part
        return false;
    }

    private Point resolveFrameBottomLeftPoint(final Segment segment) {
        return new Point(Math.min(segment.getP1().getX(), segment.getP2().getX()),
                Math.min(segment.getP1().getY(), segment.getP2().getY()));
    }

    private Point resolveFrameTopRightPoint(final Segment segment) {
        return new Point(Math.max(segment.getP1().getX(), segment.getP2().getX()),
                Math.max(segment.getP1().getY(), segment.getP2().getY()));
    }
}
