package kuvaldis.algorithm.cake;

public class Task1 {
    public static void main(final String[] args) {
        int[] stockPricesYesterday = new int[]{10, 7, 5, 8, 11, 9};

        System.out.print(new Task1().getMaxProfit(stockPricesYesterday));
    }

    public int getMaxProfit(final int[] prices) {
        return findMinMaxProfit(prices, 0, prices.length)[2];
    }

    private int[] findMinMaxProfit(final int[] prices, final int from, final int to) {
        if (from + 1 == to) {
            return new int[]{prices[from], prices[from], 0};
        }
        final int[] left = findMinMaxProfit(prices, from, (to + from) / 2);
        final int[] right = findMinMaxProfit(prices, (to + from) / 2, to);
        final int min = right[0] - ((left[0] - right[0]) >>> 31) * (right[0] - left[0]);
        final int max = right[1] - ((right[1] - left[1]) >>> 31) * (right[1] - left[1]);

        if (left[0] < right[1]) {
            final int diff = right[1] - left[0];
            if (diff > left[2] && diff > right[2]) {
                return new int[]{min, max, diff};
            }
        }
        if (left[2] > right[2]) {
            return new int[]{min, max, left[2]};
        } else {
            return new int[]{min, max, right[2]};
        }
    }
}