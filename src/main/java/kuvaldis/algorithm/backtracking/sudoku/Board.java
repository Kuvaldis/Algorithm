package kuvaldis.algorithm.backtracking.sudoku;

import lombok.Data;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class Board {

    public static final int SIZE = 9;

    @Getter
    private int unfilled = SIZE * SIZE;

    private final Map<Cell, Integer> values = new HashMap<>();

    public void setCellValue(final Integer x, final Integer y, final Integer value) {
        final Cell putCell = new Cell(x, y);
        boolean contained = values.containsKey(putCell);
        final Integer oldValue = values.put(putCell, value);
        if (value != null) {
            if ((!contained || oldValue == null)) {
                unfilled--;
            }
        } else {
            if (contained && oldValue != null) {
                unfilled++;
            }
        }
    }

    public Integer getCellValue(final Integer x, final Integer y) {
        return values.get(new Cell(x, y));
    }
}
