package kuvaldis.algorithm.dynamic.string;

public class StringDifference extends AbstractStringCompare {
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
