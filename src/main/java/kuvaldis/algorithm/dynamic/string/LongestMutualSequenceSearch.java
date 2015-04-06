package kuvaldis.algorithm.dynamic.string;

import static kuvaldis.algorithm.dynamic.string.Action.DELETE;
import static kuvaldis.algorithm.dynamic.string.Action.INSERT;

public class LongestMutualSequenceSearch extends AbstractStringCompare {

    @Override
    protected ActionData colInit(int i, char sc) {
        return new ActionData(Cost.of(i), DELETE, sc);
    }

    @Override
    protected ActionData rowInit(int i, char tc) {
        return new ActionData(Cost.of(i), INSERT, tc);
    }

    @Override
    protected Cell goalCell(String s, String t, ActionData[][] resultMatrix) {
        return new Cell(s.length() - 1, t.length() - 1);
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
        return sc == tc ? Cost.ZERO : Cost.INFINITY;
    }
}
