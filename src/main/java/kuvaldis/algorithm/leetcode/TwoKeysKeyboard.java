package kuvaldis.algorithm.leetcode;

/*
Initially on a notepad only one character 'A' is present. You can perform two operations on this notepad for each step:

Copy All: You can copy all the characters present on the notepad (partial copy is not allowed).
Paste: You can paste the characters which are copied last time.
Given a number n. You have to get exactly n 'A' on the notepad by performing the minimum number of steps permitted. Output the minimum number of steps to get n 'A'.

Example 1:
Input: 3
Output: 3
Explanation:
Intitally, we have one character 'A'.
In step 1, we use Copy All operation.
In step 2, we use Paste operation to get 'AA'.
In step 3, we use Paste operation to get 'AAA'.
Note:
The n will be in the range [1, 1000].
 */
public class TwoKeysKeyboard {

    // the task is basically to calculate all prime multipliers.
    // whenever we get to a prime multiplier number of 'A's we can copy all and paste (next prime number times - 1).
    // why primes? because to rich a prime number we can only add 'A's by one, no other way.
    // Once we rich a prime number we have to make ((number / prime number) - 1) steps.
    // To make it optimal we just repeat searching for next prime (or try to divide again, because prime number might be a multiplier several times).
    public int minimalSteps(final int number) {
        int n = number;
        int div = 2;
        int steps = 0;
        while (n > 1) {
            while (n % div == 0) {
                steps += div;
                n /= div;
            }
            // next prime number can be calculated more optimally, but lets just take next
            div++;
        }
        return steps;
    }
}
