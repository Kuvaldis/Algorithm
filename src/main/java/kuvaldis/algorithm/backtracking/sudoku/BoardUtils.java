package kuvaldis.algorithm.backtracking.sudoku;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

public final class BoardUtils {

    private BoardUtils() { }

    public static Board fromResource(final String name) throws IOException {
        final Board board = new Board();
        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(BoardUtils.class.getResourceAsStream(name)))) {
            for (int y = 0; y < Board.SIZE; y++) {
                final String read = reader.readLine();
                processParts(board, read.split("[\\s]+"), y);
            }
        }
        return board;
    }

    private static void processParts(final Board board, final String[] parts, final int y) {
        IntStream.range(0, parts.length)
                .forEach(x -> board.setCellValue(x, y,
                        parts[x].matches("[0-9]") ? Integer.valueOf(parts[x]) : null));
    }
}
