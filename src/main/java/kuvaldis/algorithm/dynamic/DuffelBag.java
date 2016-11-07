package kuvaldis.algorithm.dynamic;

public class DuffelBag {
    public int maxProfit(final int[] size, final int[] cost, final int bagSize) {
        final int[] results = new int[bagSize + 1];
        for (int i = 0; i < size.length; i++) {
            for (int j = size[i]; j < results.length; j++) {
                results[j] = Math.max(results[j], results[j - size[i]] + cost[i]);
            }
        }
        int max = 0;
        for (int result : results) {
            if (result > max) {
                max = result;
            }
        }
        return max;
    }
}
