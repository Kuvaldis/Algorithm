package kuvaldis.algorithm.dynamic;

import java.util.HashMap;
import java.util.Map;

/**
 * Binomial coefficient are coefficient in binomial theorem (Коэффициенты разложения бинома Ньютона (1 + x) ^ n)
 * The following scheme is used:
 * 1
 * 1 1
 * 1 2 1
 * 1 3 3 1
 * 1 4 6 4 1
 * ....
 *
 * For each power there is a row, with number of elements equal to power + 1. For the power of 0 the row contains 1 coefficient
 * 0th and the last elements in a row are always 1.
 * Elements in between are calculated as a sum of i - 1 element and i element from the previous row (by the theorem)
 */
public class BinomialCoefficient {

    /**
     * Binomial coefficient for m from n picking calculate method
     *
     * @param n - power of binomial
     * @param m - number of coefficient for the binomial of power of n
     * @return coefficient
     */
    public Long calculate(final int n, final int m) {
        final Map<Integer, Map<Integer, Long>> coefficients = new HashMap<>();
        for (int i = 0; i <= n; i++) {
            final Map<Integer, Long> row = new HashMap<>();
            coefficients.put(i, row);
            row.put(0, 1L);
            row.put(i, 1L);
            for (int j = 1; j < i; j++) {
                final Map<Integer, Long> prevRow = coefficients.get(i - 1);
                row.put(j, prevRow.get(j - 1) + prevRow.get(j));
            }
        }
        return coefficients.get(n).get(m);
    }
}
