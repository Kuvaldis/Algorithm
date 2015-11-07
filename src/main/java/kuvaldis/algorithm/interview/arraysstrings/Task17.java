package kuvaldis.algorithm.interview.arraysstrings;

public class Task17 {

    public int[][] zeroErase(final int[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0) {
            return m;
        }
        final boolean[] eraseRows = new boolean[m.length];
        final boolean[] eraseCols = new boolean[m[0].length];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; ++j) {
                if (m[i][j] == 0) {
                    eraseRows[i] = true;
                    eraseCols[j] = true;
                }
            }
        }
        for (int i = 0; i < eraseRows.length; i++) {
            for (int j = 0; j < eraseCols.length; j++) {
                if (eraseRows[i] || eraseCols[j]) {
                    m[i][j] = 0;
                }
            }
        }
        return m;
    }
}
