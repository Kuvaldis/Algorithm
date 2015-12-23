package kuvaldis.algorithm.cake;

/*

Writing coding interview questions hasn't made me rich. Maybe trading Apple stocks will.
Suppose we could access yesterday's stock prices as an array, where:

The indices are the time in minutes past trade opening time, which was 9:30am local time.
The values are the price in dollars of Apple stock at that time.
So if the stock cost $500 at 10:30am, stockPricesYesterday[60] = 500.

Write an efficient function that takes stockPricesYesterday and returns the best profit I could have made from 1 purchase and 1 sale of 1 Apple stock yesterday.

 */
public class Task1 {

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