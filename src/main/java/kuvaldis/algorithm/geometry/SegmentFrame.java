package kuvaldis.algorithm.geometry;

/*
         _____________.p2                                      p1.-------------
        |         ___/|                                          |\___         |
        |     ___/    | <--- Segment frame. Can also be like --> |    \___     |
        | ___/        |                                          |        \___ |
        |/            |                                          |            \|
      p1.-------------                                            -------------.p2
*/
public class SegmentFrame extends SquareArea {

    public SegmentFrame(final Segment segment) {
        super(segment.bottomLeft(), segment.topRight());
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
        final Point bottomLeft1 = f1.getBottomLeft();
        final Point topRight1 = f1.getTopRight();
        final Point bottomLeft2 = f2.getBottomLeft();
        final Point topRight2 = f2.getTopRight();

        if (topRight1.getX() < bottomLeft2.getX() ||
                topRight2.getX() < bottomLeft1.getX() ||
                topRight2.getY() < bottomLeft1.getY() ||
                topRight1.getY() < bottomLeft2.getY()) {
            return false;
        }

        return true;
    }
}
