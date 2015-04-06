package kuvaldis.algorithm.dynamic.string;

import java.util.stream.IntStream;

import static kuvaldis.algorithm.dynamic.string.Action.DELETE;

public class SearchSimilarSubstring extends AbstractStringCompare {

    @Override
    protected ActionData colInit(int i, char sc) {
        return new ActionData(Cost.of(i), DELETE, sc);
    }

    @Override
    protected ActionData rowInit(int i, char tc) {
        return new ActionData(Cost.ZERO, null, null);
    }

    @Override
    protected Cell goalCell(String s, String t, ActionData[][] resultMatrix) {
        final int i = s.length() - 1;
        final ActionData[] lastRow = resultMatrix[i];
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
