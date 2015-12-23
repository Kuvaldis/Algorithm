package kuvaldis.algorithm.dynamic.string;

import static kuvaldis.algorithm.dynamic.string.Action.DELETE;
import static kuvaldis.algorithm.dynamic.string.Action.INSERT;

/**
 * Collects optimal actions to transform the string to the template
 * If a symbol from the string is substituted by one from the template, then the cost is 1
 * If a symbol is removed, then the cost is 1
 * If a new symbol is inserted, then the cost is 1
 * If a symbol is left as is, then the cost is 0
 */
public class StringTransformation extends AbstractStringCompare {

    // the 0th column is initiated with delete actions. So if going through each cell in the column,
    // then all the symbols in the string should be removed.
    @Override
    protected ActionData zerothColumnCellInit(int i, char sc) {
        return new ActionData(Cost.of(i), DELETE, sc);
    }

    // the 0th row is initiated with insert actions. So if going through each cell in the row,
    // then all the symbols from the template should be inserted into the string.
    @Override
    protected ActionData zerothRowCellInit(int i, char tc) {
        return new ActionData(Cost.of(i), INSERT, tc);
    }

    // the rightmost bottom cell is the goal.
    // It should contain the minimal cost of transforming the string into the template
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
        return sc == tc ? Cost.ZERO : Cost.ONE;
    }
}
