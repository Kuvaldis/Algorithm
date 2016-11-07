package kuvaldis.algorithm.dynamic.string;

import static java.util.Arrays.asList;
import static kuvaldis.algorithm.dynamic.string.Action.*;

public abstract class AbstractStringCompare {

    /**
     * Finds minimal distance from s to t
     *
     * @param string   - string to find distance from
     * @param template - string to match with
     */
    /*
          t e m p l a t e
        s
        t
        r
        i
        n
        g
     */
    public Result compare(final String string, final String template) {
        // spaces to fill 0th col and row with them
        final String s = " " + string;
        final String t = " " + template;
        final ActionData[][] matrix = init(s, t);
        for (int i = 1; i < s.length(); i++) {
            final char sc = s.charAt(i);
            for (int j = 1; j < t.length(); j++) {
                final char tc = t.charAt(j);
                /*
                         | ... | j-1 |  j  | j + 1 | ...
                   i - 1 | ... | S/N    D
                                      \ |
                     i   | ... |  I  -> ?
                   i + 1 | ... |
                 */
                // sc should be substituted by tc or nothing should be done with sc
                final Cost matchCost = match(sc, tc);
                final ActionData substituteOrNone = new ActionData(matrix[i - 1][j - 1].getCost().add(matchCost),
                        matchCost.compareTo(Cost.ZERO) > 0 ? SUBSTITUTE : NONE, tc);
                // tc should be inserted into string.
                // string is getting an additional symbol, so after i-th position (after sc) there will be another one (tc)
                // in this case i-th should be compared again but with (j - 1)th position from template
                final ActionData insert = new ActionData(matrix[i][j - 1].getCost().add(insert(tc)), INSERT, tc);
                // sc should be deleted from the string.
                // string now has one less symbol in i-th position(sc), so tc should be compared again
                // but with (i - 1)th position
                final ActionData delete = new ActionData(matrix[i - 1][j].getCost().add(delete(sc)), DELETE, sc);
                // choose cheapest action
                matrix[i][j] = asList(substituteOrNone, insert, delete).stream()
                        .min((o1, o2) -> o1.getCost().compareTo(o2.getCost()))
                        .get();

            }
        }
        // each cell now is filled with optimal costs
        // to get from 0 to i substring of the string to 0 to j substring of the template
        return constructResult(goalCell(s, t, matrix), matrix);
    }

    private Result constructResult(final Cell goalCell, final ActionData[][] resultMatrix) {
        // start collecting result from goal cell
        int i = goalCell.getI();
        int j = goalCell.getJ();
        final Result result = new Result();
        while (resultMatrix[i][j].getAction() != null) {
            final ActionData actionData = resultMatrix[i][j];
            result.push(actionData);
            switch (actionData.getAction()) {
                // if comparing i and j from string and template respectively substitution was the most optimal choice,
                // then i - 1 and j - 1 should've been compared in the previous step
                case SUBSTITUTE:
                case NONE:
                    i = i - 1;
                    j = j - 1;
                    break;
                // if comparing i and j insert after i was the most optimal choice,
                // then i should've been compared in the previous step
                case INSERT:
                    j = j - 1;
                    break;
                // if comparing i and j i was deleted, then j should've been compared to i - 1 in the previous step
                case DELETE:
                    i = i - 1;
                    break;
            }
        }
        return result;
    }

    /**
     * Initializes all the cells from 0th row and 0th column
     * @param s - string
     * @param t - template
     * @return matrix to work on
     */
    private ActionData[][] init(final String s, final String t) {
        final ActionData[][] matrix = new ActionData[s.length()][t.length()];
        matrix[0][0] = new ActionData(Cost.ZERO, null, null);
        // init 0th column
        for (int i = 1; i < s.length(); i++) {
            matrix[i][0] = zerothColumnCellInit(i, s.charAt(i));
        }
        // init 0th row
        for (int i = 1; i < t.length(); i++) {
            matrix[0][i] = zerothRowCellInit(i, t.charAt(i));
        }
        return matrix;
    }

    /**
     * Gets initial data for the cell [0, i]
     *
     * @param i - row number in the column
     * @param sc - character from string
     * @return action data to fill the cell with
     */
    protected abstract ActionData zerothColumnCellInit(int i, char sc);

    /**
     * Gets initial data for the cell [0, i]
     *
     * @param i - column number in a row
     * @param tc - character from template
     * @return action data to fill the cell with
     */
    protected abstract ActionData zerothRowCellInit(int i, char tc);

    /**
     * Returns the cell to collect result from
     *
     * @param s - string
     * @param t - template
     * @param resultMatrix - matrix with filled by dynamic algorithm data
     * @return - cell to collect result (build path) from
     */
    protected abstract Cell goalCell(final String s, final String t, final ActionData[][] resultMatrix);

    /**
     * Returns delete operation cost when matrix is being filled
     *
     * @param c - character to delete
     * @return cost of delete operation
     */
    protected abstract Cost delete(final char c);

    /**
     * Returns insert operation cost when matrix is being filled
     *
     * @param c - character to insert
     * @return cost of insert operation
     */
    protected abstract Cost insert(final char c);

    /**
     * Returns substitute operation cost when matrix is being filled
     *
     * @param sc - character replaced by tc
     * @param tc - replacer
     * @return - cost of substitution
     */
    protected abstract Cost match(final char sc, final char tc);
}