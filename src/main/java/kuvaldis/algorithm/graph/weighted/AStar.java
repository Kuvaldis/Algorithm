package kuvaldis.algorithm.graph.weighted;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A* algorithm implementation
 */
public class AStar {

    private final FComparator F_COMPARATOR = new FComparator();

    /**
     * First index is row numbers, second is for column.
     */
    private final Cell[][] cells;

    private AStar(final Cell[][] cells) {
        this.cells = cells;
    }

    public List<Coordinates> buildPath() {
        // preparation part
        Coordinates start = null;
        Coordinates end = null;
        final Set<Coordinates> closedCells = new HashSet<>();
        for (int rowNumber = 0; rowNumber < cells.length; rowNumber++) {
            final Cell[] cellRow = cells[rowNumber];
            for (int columnNumber = 0; columnNumber < cellRow.length; columnNumber++) {
                final Cell cell = cellRow[columnNumber];
                if (cell.start) {
                    start = new Coordinates(cells, rowNumber, columnNumber);
                }
                if (cell.end) {
                    end = new Coordinates(cells, rowNumber, columnNumber);
                }
                if (cell.obstacle) {
                    closedCells.add(new Coordinates(cells, rowNumber, columnNumber));
                }
            }
        }
        if (start == null || !start.isValid()) {
            throw new IllegalArgumentException("Could not find start");
        }
        if (end == null || !end.isValid()) {
            throw new IllegalArgumentException("Could not find end");
        }
        start.getCell().g = 0;

        // the main part
        final Set<Coordinates> openCells = new HashSet<>();
        openCells.add(start);
        while (!openCells.isEmpty()) {
            final Coordinates c = Collections.min(openCells, F_COMPARATOR);
            if (c.equals(end)) {
                break;
            }
            final List<Coordinates> candidates = c.neighbours()
                    .filter(n -> !n.isObstacle())
                    .filter(n -> !openCells.contains(n))
                    .filter(n -> !closedCells.contains(n))
                    .filter(n -> !obstacleOnPath(c, n))
                    .collect(Collectors.toList());
            for (Coordinates candidate : candidates) {
                if (candidate.getCell().parent == null) {
                    candidate.getCell().parent = c;
                }
                if (candidate.getCell().h == null) {
                    candidate.getCell().h = calculateManhattanDistance(candidate, end);
                }
                final Integer neighbourDistance = calculateNeighboursDistance(c, candidate);
                if (candidate.getCell().g == null || candidate.getCell().g > c.getCell().g + neighbourDistance) {
                    candidate.getCell().g = c.getCell().g + neighbourDistance;
                    candidate.getCell().parent = c;
                }
                openCells.add(candidate);
            }
            openCells.remove(c);
            closedCells.add(c);
        }

        // no path found
        if (end.getCell().parent == null) {
            return Collections.emptyList();
        }

        // restore path
        final LinkedList<Coordinates> result = new LinkedList<>();
        Coordinates current = end;
        while (!current.equals(start)) {
            result.push(current);
            current = current.getCell().parent;
        }
        result.push(current);
        return result;
    }

    private boolean obstacleOnPath(final Coordinates from, final Coordinates to) {
        final int coordinateDistance = calculateCoordinateDistance(from, to);
        if (coordinateDistance == 1) {
            return false;
        }
        if (coordinateDistance == 2) {
            return cells[from.rowNumber][to.columnNumber].obstacle ||
                    cells[to.rowNumber][from.columnNumber].obstacle;
        }

        throw new IllegalArgumentException("You are doing something wrong!");
    }

    private Integer calculateManhattanDistance(final Coordinates from, final Coordinates to) {
        return 10 * calculateCoordinateDistance(from, to);
    }

    private Integer calculateNeighboursDistance(final Coordinates from, final Coordinates to) {
        final int coordinateDistance = calculateCoordinateDistance(from, to);
        switch (coordinateDistance) {
            case 1:
                return 10;
            case 2:
                return 14;
            default:
                throw new IllegalArgumentException("You are doing something wrong!");
        }
    }

    private int calculateCoordinateDistance(final Coordinates from, final Coordinates to) {
        return Math.abs(from.rowNumber - to.rowNumber) + Math.abs(from.columnNumber - to.columnNumber);
    }

    public static class Cell {

        private final boolean start;

        private final boolean end;

        private final boolean obstacle;

        /**
         * Cost of moving from starting to given cell
         */
        private Integer g;

        /**
         * Approximate const of moving from given cell to end.
         * Calculated based on heuristic function.
         * In our case it is Manhattan function.
         */
        private Integer h;

        private Coordinates parent;

        public Cell(final boolean start, final boolean end, final boolean obstacle) {
            this.start = start;
            this.end = end;
            this.obstacle = obstacle;
        }
    }

    public static class Coordinates {

        private final Cell[][] cells;

        private final int rowNumber;

        private final int columnNumber;

        private Coordinates(final Cell[][] cells, final int rowNumber, final int columnNumber) {
            this.cells = cells;
            this.rowNumber = rowNumber;
            this.columnNumber = columnNumber;
        }

        public boolean isValid() {
            return rowNumber >= 0 && columnNumber >= 0 &&
                    rowNumber < cells.length && columnNumber < cells[rowNumber].length;
        }

        public boolean isObstacle() {
            return getCell().obstacle;
        }

        public Stream<Coordinates> neighbours() {
            return Stream.of(
                    new Coordinates(cells, rowNumber - 1, columnNumber - 1),
                    new Coordinates(cells, rowNumber - 1, columnNumber),
                    new Coordinates(cells, rowNumber - 1, columnNumber + 1),
                    new Coordinates(cells, rowNumber, columnNumber - 1),
                    new Coordinates(cells, rowNumber, columnNumber),
                    new Coordinates(cells, rowNumber, columnNumber + 1),
                    new Coordinates(cells, rowNumber + 1, columnNumber - 1),
                    new Coordinates(cells, rowNumber + 1, columnNumber),
                    new Coordinates(cells, rowNumber + 1, columnNumber + 1))
                    .filter(Coordinates::isValid);
        }

        private Cell getCell() {
            return cells[rowNumber][columnNumber];
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            final Coordinates that = (Coordinates) o;

            if (rowNumber != that.rowNumber) return false;
            return columnNumber == that.columnNumber;
        }

        @Override
        public int hashCode() {
            int result = rowNumber;
            result = 31 * result + columnNumber;
            return result;
        }

        @Override
        public String toString() {
            return String.format("[%s, %s]", rowNumber, columnNumber);
        }
    }

    private class FComparator implements Comparator<Coordinates> {

        @Override
        public int compare(final Coordinates o1, final Coordinates o2) {
            final Cell c1 = cells[o1.rowNumber][o1.columnNumber];
            final Cell c2 = cells[o2.rowNumber][o2.columnNumber];
            final int c1f = c1.g + c1.h;
            final int c2f = c2.g + c2.h;
            return c1f - c2f;
        }
    }

    public static Cell c() {
        return new Cell(false, false, false);
    }

    public static Cell o() {
        return new Cell(false, false, true);
    }

    public static Cell s() {
        return new Cell(true, false, false);
    }

    public static Cell e() {
        return new Cell(false, true, false);
    }

    public static Cell[] x(final Cell... cells) {
        return cells;
    }

    public static AStar init(final Cell[]... cells) {
        return new AStar(cells);
    }
}
