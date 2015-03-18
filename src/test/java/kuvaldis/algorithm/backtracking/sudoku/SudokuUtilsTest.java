package kuvaldis.algorithm.backtracking.sudoku;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SudokuUtilsTest {

    private Sudoku sudoku;

    @Before
    public void setUp() throws Exception {
        sudoku = SudokuUtils.fromResource("sudoku.txt");
    }

    @Test
    public void testFromResource() throws Exception {
        assertEquals(null, s(0, 0));
        assertEquals(null, s(1, 0));
        assertEquals(null, s(2, 0));
        assertEquals(i(2), s(3, 0));
        assertEquals(i(6), s(4, 0));
        assertEquals(null, s(5, 0));
        assertEquals(i(7), s(6, 0));
        assertEquals(null, s(7, 0));
        assertEquals(i(1), s(8, 0));

        assertEquals(i(6), s(0, 1));
        assertEquals(i(8), s(1, 1));
        assertEquals(null, s(2, 1));
        assertEquals(null, s(3, 1));
        assertEquals(i(7), s(4, 1));
        assertEquals(null, s(5, 1));
        assertEquals(null, s(6, 1));
        assertEquals(i(9), s(7, 1));
        assertEquals(null, s(8, 1));

        assertEquals(i(1), s(0, 2));
        assertEquals(i(9), s(1, 2));
        assertEquals(null, s(2, 2));
        assertEquals(null, s(3, 2));
        assertEquals(null, s(4, 2));
        assertEquals(i(4), s(5, 2));
        assertEquals(i(5), s(6, 2));
        assertEquals(null, s(7, 2));
        assertEquals(null, s(8, 2));

        assertEquals(i(8), s(0, 3));
        assertEquals(i(2), s(1, 3));
        assertEquals(null, s(2, 3));
        assertEquals(i(1), s(3, 3));
        assertEquals(null, s(4, 3));
        assertEquals(null, s(5, 3));
        assertEquals(null, s(6, 3));
        assertEquals(i(4), s(7, 3));
        assertEquals(null, s(8, 3));

        assertEquals(null, s(0, 4));
        assertEquals(null, s(1, 4));
        assertEquals(i(4), s(2, 4));
        assertEquals(i(6), s(3, 4));
        assertEquals(null, s(4, 4));
        assertEquals(i(2), s(5, 4));
        assertEquals(i(9), s(6, 4));
        assertEquals(null, s(7, 4));
        assertEquals(null, s(8, 4));

        assertEquals(null, s(0, 5));
        assertEquals(i(5), s(1, 5));
        assertEquals(null, s(2, 5));
        assertEquals(null, s(3, 5));
        assertEquals(null, s(4, 5));
        assertEquals(i(3), s(5, 5));
        assertEquals(null, s(6, 5));
        assertEquals(i(2), s(7, 5));
        assertEquals(i(8), s(8, 5));

        assertEquals(null, s(0, 6));
        assertEquals(null, s(1, 6));
        assertEquals(i(9), s(2, 6));
        assertEquals(i(3), s(3, 6));
        assertEquals(null, s(4, 6));
        assertEquals(null, s(5, 6));
        assertEquals(null, s(6, 6));
        assertEquals(i(7), s(7, 6));
        assertEquals(i(4), s(8, 6));

        assertEquals(null, s(0, 7));
        assertEquals(i(4), s(1, 7));
        assertEquals(null, s(2, 7));
        assertEquals(null, s(3, 7));
        assertEquals(i(5), s(4, 7));
        assertEquals(null, s(5, 7));
        assertEquals(null, s(6, 7));
        assertEquals(i(3), s(7, 7));
        assertEquals(i(6), s(8, 7));

        assertEquals(i(7), s(0, 8));
        assertEquals(null, s(1, 8));
        assertEquals(i(3), s(2, 8));
        assertEquals(null, s(3, 8));
        assertEquals(i(1), s(4, 8));
        assertEquals(i(8), s(5, 8));
        assertEquals(null, s(6, 8));
        assertEquals(null, s(7, 8));
        assertEquals(null, s(8, 8));
    }

    private Integer i(int i) {
        return i;
    }

    private Integer s(final int x, final int y) {
        return sudoku.getCellValue(x, y);
    }
}