package kuvaldis.algorithm.dynamic;

import java.util.HashMap;
import java.util.Map;

public class BinomialCoefficient {

    /**
     * Binomial coefficient for m from n picking calculate method
     *
     * @param n - all elements quantity
     * @param m - choose elements quantity
     * @return subsets quantity
     */
    public Long calculate(final int n, final int m) {
        final Map<Integer, Map<Integer, Long>> coefficients = new HashMap<>();
        for (int i = 0; i <= n; i++) {
            final Map<Integer, Long> row = new HashMap<>();
            coefficients.put(i, row);
            row.put(0, 1l);
            row.put(i, 1l);
            for (int j = 1; j < i; j++) {
                final Map<Integer, Long> prevRow = coefficients.get(i - 1);
                row.put(j, prevRow.get(j - 1) + prevRow.get(j));
            }
        }
        return coefficients.get(n).get(m);
    }
}
