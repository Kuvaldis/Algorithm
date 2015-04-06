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
    public Result compare(final String string, final String template) {
        final String s = " " + string;
        final String t = " " + template;
        final ActionData[][] matrix = init(s, t);
        for (int i = 1; i < s.length(); i++) {
            final char sc = s.charAt(i);
            for (int j = 1; j < t.length(); j++) {
                final char tc = t.charAt(j);
                final Cost matchCost = match(sc, tc);
                matrix[i][j] = asList(new ActionData(matrix[i - 1][j - 1].getCost().add(matchCost),
                                matchCost.compareTo(Cost.ZERO) > 0 ? SUBSTITUTE : NONE, tc),
                        new ActionData(matrix[i][j - 1].getCost().add(insert(tc)), INSERT, tc),
                        new ActionData(matrix[i - 1][j].getCost().add(delete(sc)), DELETE, sc)).stream()
                        .min((o1, o2) -> o1.getCost().compareTo(o2.getCost()))
                        .get();

            }
        }
        return constructResult(goalCell(s, t, matrix), matrix);
    }

    private Result constructResult(final Cell goalCell, final ActionData[][] resultMatrix) {
        int i = goalCell.getI();
        int j = goalCell.getJ();
        final Result result = new Result();
        while (resultMatrix[i][j].getAction() != null) {
            final ActionData actionData = resultMatrix[i][j];
            result.push(actionData);
            switch (actionData.getAction()) {
                case SUBSTITUTE:
                case NONE:
                    i = i - 1;
                    j = j - 1;
                    break;
                case INSERT:
                    j = j - 1;
                    break;
                case DELETE:
                    i = i - 1;
                    break;
            }
        }
        return result;
    }

    private ActionData[][] init(final String s, final String t) {
        final ActionData[][] matrix = new ActionData[s.length()][t.length()];
        matrix[0][0] = new ActionData(Cost.ZERO, null, null);
        for (int i = 1; i < s.length(); i++) {
            matrix[i][0] = colInit(i, s.charAt(i));
        }
        for (int i = 1; i < t.length(); i++) {
            matrix[0][i] = rowInit(i, t.charAt(i));
        }
        return matrix;
    }

    protected abstract ActionData colInit(int i, char sc);

    protected abstract ActionData rowInit(int i, char tc);

    protected abstract Cell goalCell(final String s, final String t, final ActionData[][] resultMatrix);

    protected abstract Cost delete(final char c);

    protected abstract Cost insert(final char c);

    protected abstract Cost match(final char sc, final char tc);
}