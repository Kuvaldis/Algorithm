package kuvaldis.algorithm.backtracking.sudoku;

public class Cell {
    private final Integer x;
    private final Integer y;

    public Cell(final Integer x, final Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
}
