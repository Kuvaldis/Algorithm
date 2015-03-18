package kuvaldis.algorithm.backtracking.sudoku;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

public final class SudokuUtils {

    private static final Integer SIZE = 9;

    private SudokuUtils() { }

    public static Sudoku fromResource(final String name) throws IOException {
        final Sudoku sudoku = new Sudoku();
        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(SudokuUtils.class.getResourceAsStream(name)))) {
            for (int y = 0; y < SIZE; y++) {
                final String read = reader.readLine();
                processParts(sudoku, read.split("[\\s]+"), y);
            }
        }
        return sudoku;
    }

    private static void processParts(final Sudoku sudoku, final String[] parts, final int y) {
        IntStream.range(0, parts.length)
                .forEach(x -> sudoku.setCellValue(x, y,
                        parts[x].matches("[0-9]") ? Integer.valueOf(parts[x]) : null));
    }
}
