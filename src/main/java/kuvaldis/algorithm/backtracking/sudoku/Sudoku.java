package kuvaldis.algorithm.backtracking.sudoku;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

public class Sudoku {

    private final Map<Cell, Integer> values = new HashMap<>();

    public void setCellValue(final Integer x, final Integer y, final Integer value) {
        values.put(new Cell(x, y), value);
    }

    public Integer getCellValue(final Integer x, final Integer y) {
        return values.get(new Cell(x, y));
    }
}
