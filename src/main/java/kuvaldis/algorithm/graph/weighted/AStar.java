package kuvaldis.algorithm.graph.weighted;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A* algorithm implementation
 */
public class AStar {

    /**
     * First index is row numbers, second is for column.
     */
    private final Cell[][] cells;

    private AStar(final Cell[][] cells) {
        this.cells = cells;
    }

    public void buildPath() {
        // first, find coordinate of start and end
        Coordinates start = null;
        Coordinates end = null;

        for (int rowNumber = 0; rowNumber < cells.length; rowNumber++) {
            final Cell[] cellRow = cells[rowNumber];
            for (int columnNumber = 0; columnNumber < cellRow.length; columnNumber++) {
                final Cell cell = cellRow[columnNumber];
                if (cell.start) {
                    start = new Coordinates(rowNumber, columnNumber);
                }
                if (cell.end) {
                    end = new Coordinates(rowNumber, columnNumber);
                }
            }
        }
        if (start == null || !start.isValid()) {
            throw new IllegalArgumentException("Could not find start");
        }
        if (end == null || !end.isValid()) {
            throw new IllegalArgumentException("Could not find end");
        }

        // the main part
        // todo simple set should be enough and better
        final Queue<Coordinates> openCellsQueue = new PriorityQueue<>(new HComparator());
        final Set<Coordinates> closedCells = new HashSet<>();
        // todo add all obstacles here and remove check
        openCellsQueue.add(start);
        while (!openCellsQueue.isEmpty()) {
            final Coordinates c = openCellsQueue.poll();
            final List<Coordinates> candidates = c.neighbours()
                    .filter(n -> !n.isObstacle())
                    .collect(Collectors.toList());
            for (Coordinates candidate : candidates) {
                candidate.getCell().parent = c;
            }
            // todo
        }

    }

    public static class Cell implements Comparable<Cell> {

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

        /**
         * Compares by h value
         */
        @Override
        public int compareTo(final Cell o) {
            final int thisF = this.g + this.h;
            final int oF = o.g + o.h;
            return thisF - oF;
        }
    }

    private class Coordinates {

        private final int rowNumber;

        private final int columnNumber;

        private Coordinates(final int rowNumber, final int columnNumber) {
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
                    new Coordinates(rowNumber - 1, columnNumber - 1),
                    new Coordinates(rowNumber - 1, columnNumber),
                    new Coordinates(rowNumber - 1, columnNumber + 1),
                    new Coordinates(rowNumber, columnNumber - 1),
                    new Coordinates(rowNumber, columnNumber),
                    new Coordinates(rowNumber, columnNumber + 1),
                    new Coordinates(rowNumber + 1, columnNumber - 1),
                    new Coordinates(rowNumber + 1, columnNumber),
                    new Coordinates(rowNumber + 1, columnNumber + 1))
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
    }

    private class HComparator implements Comparator<Coordinates> {

        @Override
        public int compare(final Coordinates o1, final Coordinates o2) {
            final Cell c1 = cells[o1.rowNumber][o1.columnNumber];
            final Cell c2 = cells[o2.rowNumber][o2.columnNumber];
            return c1.compareTo(c2);
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
