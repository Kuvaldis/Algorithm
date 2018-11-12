package kuvaldis.algorithm.programcreek;

import java.util.*;

/*

From Wiki:
In computer science, edit distance is a way of quantifying how dissimilar two strings (e.g., words) are to one another by counting the minimum number of operations required to transform one string into the other.

There are three operations permitted on a word: replace, delete, insert. For example, the edit distance between "a" and "b" is 1, the edit distance between "abc" and "def" is 3. This post analyzes how to calculate edit distance by using dynamic programming.

 */
public class EditDistance {

    public int distance(final String a, final String b) {
        // init memo
        final int[][] memo = new int[a.length() + 1][];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = new int[b.length() + 1];
        }

        for (int i = 1; i < memo.length; i++) {
            final char aChar = a.charAt(i - 1);
            for (int j = 1; j < memo[i].length; j++) {
                final char bChar = b.charAt(j - 1);
                final int step = aChar == bChar ? 0 : 1;
                final int minPrev = Math.min(Math.min(memo[i - 1][j], memo[i][j - 1]), memo[i - 1][j - 1]);
                memo[i][j] = minPrev + step;
            }
        }

        return memo[a.length()][b.length()];
    }
}
