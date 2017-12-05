package kuvaldis.algorithm.geometry;

public class Point implements Comparable<Point> {

    private final int x;

    private final int y;

    public Point(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Point point = (Point) o;

        if (x != point.x) return false;
        return y == point.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return String.format("(%s,%s)", x, y);
    }

    @Override
    public int compareTo(final Point point) {
        final int compareX = this.getX() - point.getX();
        if (compareX != 0) {
            return compareX;
        }
        return this.getY() - point.getY();
    }
}
