package kuvaldis.algorithm.programcreek;

/*

Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example, Given n = 3, there are a total of 5 unique BST's.
   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3

 */
public class UniqueBST {

    public int calculate(final int n) {
        final int[] memo = new int[n + 1];
        // memo holds number of combinations for each number of elements
        memo[0] = 1; // memo[0] - starting point to help. memo[1] - how many combinations for 1 element, memo[2] - 2 elements etc.
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                int leftMultiplier = memo[j - 1]; // j - 1 - how many elements it is to the right of j
                int rightMultiplier = memo[i - j]; // i - j - how many elements it is to the right of j
                memo[i] += leftMultiplier * rightMultiplier;
            }
        }
        return memo[n];
    }
}
