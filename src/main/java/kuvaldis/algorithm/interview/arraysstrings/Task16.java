package kuvaldis.algorithm.interview.arraysstrings;

public class Task16 {

    public int[][] rotate(final int[][] m) {
        if (m == null || m.length == 0) {
            return m;
        }
        final int[][] r = new int[m[0].length][m.length];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                r[m[i].length - 1 - j][i] = m[i][j];
            }
        }
        return r;
    }
}
