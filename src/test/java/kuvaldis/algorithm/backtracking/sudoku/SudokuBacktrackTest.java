package kuvaldis.algorithm.backtracking.sudoku;

import org.junit.Test;

import static org.junit.Assert.*;

public class SudokuBacktrackTest {

    @Test
    public void testBacktrack() throws Exception {
        final Board board = new SudokuBacktrack().generate(BoardUtils.fromResource("sudoku.txt"));
        System.out.println(board);
    }
}