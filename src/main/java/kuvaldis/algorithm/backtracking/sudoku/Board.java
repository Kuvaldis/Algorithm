package kuvaldis.algorithm.backtracking.sudoku;

import java.util.*;
import java.util.stream.IntStream;

import static java.lang.System.lineSeparator;
import static java.util.stream.Collectors.joining;

public class Board {

    public static final int SIZE = 9;
    public static final int SQUARE_SIZE = 3;

    private final Set<Cell> emptyCells = new HashSet<>();

    private final Map<Cell, Integer> values = new HashMap<>();

    public void setCellValue(final Integer x, final Integer y, final Integer value) {
        setCellValue(new Cell(x, y), value);
    }

    public void setCellValue(final Cell cell, final Integer value) {
        values.put(cell, value);
        if (value == null) {
            emptyCells.add(cell);
        } else {
            emptyCells.remove(cell);
        }
    }

    public Integer getCellValue(final Integer x, final Integer y) {
        return getCellValue(new Cell(x, y));
    }

    public Integer getCellValue(final Cell cell) {
        return values.get(cell);
    }

    @Override
    public String toString() {
        return IntStream.range(0, SIZE)
                .mapToObj(y -> IntStream.range(0, SIZE)
                        .mapToObj(x -> Optional.ofNullable(getCellValue(x, y)).map(Object::toString).orElse("*"))
                        .collect(joining(" ")))
                .collect(joining(lineSeparator()));
    }

    public Set<Cell> getEmptyCells() {
        return emptyCells;
    }
}
