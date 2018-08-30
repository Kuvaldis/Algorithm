package kuvaldis.algorithm.cake;

/*

A crack team of love scientists from OkEros (a hot new dating site) have devised a way to represent dating profiles as rectangles on a two-dimensional plane.

They need help writing an algorithm to find the intersection of two users' love rectangles. They suspect finding that intersection is the key to a matching algorithm so powerful it will cause an immediate acquisition by Google or Facebook or Obama or something.

 ___
|  _|__
| | |  |
|_|_|  |
  |____|

Two rectangles overlapping a little. It must be love.
Write a method to find the rectangular intersection of two given love rectangles.

As with the example above, love rectangles are always "straight" and never "diagonal." More rigorously: each side is parallel with either the x-axis or the y-axis.

 */
public class RectangularLove {

    public Rectangle intersection(final Rectangle r1, final Rectangle r2) {
        final int[] xOverlap = findXOverlap(r1, r2);
        if (xOverlap == null) {
            return null;
        }
        final int[] yOverlap = findYOverlap(r1, r2);
        if (yOverlap == null) {
            return null;
        }
        return new Rectangle(xOverlap[0], yOverlap[0], xOverlap[1] - xOverlap[0], yOverlap[1] - yOverlap[0]);
    }

    private int[] findXOverlap(final Rectangle r1, final Rectangle r2) {
        final int r1leftX = r1.leftX;
        final int r1rightX = r1.leftX + r1.width;

        final int r2leftX = r2.leftX;
        final int r2rightX = r2.leftX + r2.width;

        if (r1rightX <= r2leftX || r2rightX <= r1leftX) {
            // no intersections
            return null;
        }

        final int xLeft = Math.max(r1leftX, r2leftX);
        final int xRight = Math.min(r1rightX, r2rightX);
        return new int[]{xLeft, xRight};
    }

    private int[] findYOverlap(final Rectangle r1, final Rectangle r2) {
        final int r1bottomY = r1.bottomY;
        final int r1topY = r1.bottomY + r1.height;

        final int r2bottomY = r2.bottomY;
        final int r2topY = r2.bottomY + r2.height;

        if (r1bottomY >= r2topY || r2bottomY >= r1topY) {
            // no intersection
            return null;
        }

        final int yBottom = Math.max(r1bottomY, r2bottomY);
        final int yTop = Math.min(r1topY, r2topY);
        return new int[]{yBottom, yTop};
    }

    public static class Rectangle {

        // coordinates of bottom left corner
        private int leftX;
        private int bottomY;

        // dimensions
        private int width;
        private int height;

        public Rectangle() {
        }

        public Rectangle(int leftX, int bottomY, int width, int height) {
            this.leftX = leftX;
            this.bottomY = bottomY;
            this.width = width;
            this.height = height;
        }

        public int getLeftX() {
            return leftX;
        }

        public int getBottomY() {
            return bottomY;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
    }
}
