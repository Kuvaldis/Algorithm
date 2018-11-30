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
        final int[] memo = new int[n];
        memo[0] = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                int leftMultiplier = j == 0 ? 1 : memo[j - 1];
                int rightMultiplier = i == j ? 1 : memo[i - j - 1];
                memo[i] += leftMultiplier * rightMultiplier;
            }
        }
        return memo[n - 1];
    }
}
