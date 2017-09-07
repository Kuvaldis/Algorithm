package kuvaldis.algorithm.geometry;

/*
         _____________.p2                                      p1.-------------
        |         ___/|                                          |\___         |
        |     ___/    | <--- Segment frame. Can also be like --> |    \___     |
        | ___/        |                                          |        \___ |
        |/            |                                          |            \|
      p1.-------------                                            -------------.p2
*/
public class SegmentFrame {

    private final Segment segment;

    public SegmentFrame(final Segment segment) {
        this.segment = segment;
    }

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
    public boolean hasIntersectionWith(final SegmentFrame f2) {
        final SegmentFrame f1 = this;
        // First, we find bottom-left and top-right points for the frames
        final Point bottomLeft1 = resolveFrameBottomLeftPoint(f1.segment);
        final Point topRight1 = resolveFrameTopRightPoint(f1.segment);
        final Point bottomLeft2 = resolveFrameBottomLeftPoint(f2.segment);
        final Point topRight2 = resolveFrameTopRightPoint(f2.segment);

        if (topRight1.getX() < bottomLeft2.getX() ||
                topRight2.getX() < bottomLeft1.getX() ||
                topRight2.getY() < bottomLeft1.getY() ||
                topRight1.getY() < bottomLeft2.getY()) {
            return false;
        }

        return true;
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
