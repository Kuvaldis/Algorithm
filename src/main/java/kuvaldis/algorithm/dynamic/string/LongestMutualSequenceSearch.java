package kuvaldis.algorithm.dynamic.string;

import static kuvaldis.algorithm.dynamic.string.Action.DELETE;
import static kuvaldis.algorithm.dynamic.string.Action.INSERT;

/**
 * Searches longest mutual sequence in two strings.
 * For instance, democrat and republican has ECA as the result.
 * dEmoCrAt and rEpubliCAn
 *
 * To exclude substitutions the cost of it is infinity, so between corresponding symbols
 * only deletes and inserts are performed (which will be ignored to calculate the result)
 * to move from the string to the template.
 * So each symbol when no action was taken is a part of the longest mutual tree.
 * It is the longest because the minimal path contains maximum possible actions with minimal cost.
 * If there is a path with more 0 cost actions (longer mutual string),
 * then it should've been the minimal path which is impossible since the main algorithm finds the minimal path.
 */
public class LongestMutualSequenceSearch extends AbstractStringCompare {

    @Override
    protected ActionData zerothColumnCellInit(int i, char sc) {
        return new ActionData(Cost.of(i), DELETE, sc);
    }

    @Override
    protected ActionData zerothRowCellInit(int i, char tc) {
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
