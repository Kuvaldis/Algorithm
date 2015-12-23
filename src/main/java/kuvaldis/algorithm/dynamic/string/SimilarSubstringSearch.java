package kuvaldis.algorithm.dynamic.string;

import java.util.stream.IntStream;

import static kuvaldis.algorithm.dynamic.string.Action.DELETE;

/**
 * Looks for substring in the template, which is similar to the string
 * 0th row is initialized this way to track several similar sub strings even in the middle of the text,
 * so optimal path may be found in the middle of the text.
 * If it was initialized with 0 to j, the optimal path could be possible only starting from cell [1, 1].
 * The goal cell is the minimal cost cell in the last row. Restored path from this cell is the minimal path
 * transforming the string into some substring inside the template with minimal cost,
 * which means it is the most similar substring.
 */
public class SimilarSubstringSearch extends AbstractStringCompare {

    @Override
    protected ActionData zerothColumnCellInit(int i, char sc) {
        return new ActionData(Cost.of(i), DELETE, sc);
    }

    @Override
    protected ActionData zerothRowCellInit(int i, char tc) {
        return new ActionData(Cost.ZERO, null, null);
    }

    @Override
    protected Cell goalCell(String s, String t, ActionData[][] resultMatrix) {
        final int i = s.length() - 1;
        final ActionData[] lastRow = resultMatrix[i];
        // find the cell in the last row with minimal cost
        return new Cell(i, IntStream.range(1, t.length())
                .boxed()
                .min((j1, j2) -> lastRow[j1].getCost().compareTo(lastRow[j2].getCost()))
                .get());
    }

    @Override
    protected Cost delete(char c) {
        return Cost.ONE;
    }

    @Override
    protected Cost insert(char c) {
        return Cost.ONE;
    }

    @Override
    protected Cost match(char sc, char tc) {
        return sc == tc ? Cost.ZERO : Cost.ONE;
    }
}
