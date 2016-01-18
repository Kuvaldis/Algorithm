package kuvaldis.algorithm.cake;

/*

Imagine you landed a new job as a cashier...
Your quirky boss found out that you're a programmer and has a weird request about something they've been wondering for a long time.

Write a function that, given:

an amount of money
a list of coin denominations
computes the number of ways to make amount of money with coins of the available denominations.

Example: for amount=4 (4?) and denominations=[1,2,3][1,2,3] (1?, 2? and 3?), your program would output 44—the number of ways to make 44? with those denominations:

1?, 1?, 1?, 1?
1?, 1?, 2?
1?, 3?
2?, 2?

 */
public class Task4 {
    public int solution(final int amount, final int[] denominations) {
        final int[] ways = new int[amount + 1];
        // O(amount * denominations.length)
        // to prevent repeated combinations counting,
        // we first count how many ways to get some value using 1 denomination exist, than with two etc.
        ways[0] = 1;
        for (int denomination : denominations) {
            for (int i = denomination; i < ways.length; i++) {
                // value == denomination. just one additional combination
                ways[i] += ways[i - denomination];
            }
        }
        return ways[amount];
    }
}
