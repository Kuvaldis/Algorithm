package kuvaldis.algorithm.leetcode;

import java.util.Arrays;

/*
Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.

If possible, output any possible result.  If not possible, return the empty string.

Example 1:

Input: S = "aab"
Output: "aba"
Example 2:

Input: S = "aaab"
Output: ""
Note:

S will consist of lowercase letters and have length in range [1, 500].
 */
public class ReorganizeString {

    public String reorganizeString(final String s) {
        final int[] counters = new int[26];
        for (int i = 0; i < s.length(); i++) {
            final char c = s.charAt(i);
            counters[c - 'a'] += 100;
        }
        for (int i = 0; i < counters.length; i++) {
            counters[i] += i;
        }
        Arrays.sort(counters);
        final int maxCommonCounter = counters[25] / 100;
        if (maxCommonCounter > s.length() - maxCommonCounter + 1) {
            return "";
        }

        final char[] result = new char[s.length()];
        int pointer = 0;
        for (int i = 0; i < counters.length; i++) {
            final int counter = counters[i] / 100;
            for (int j = 0; j < counter; j++) {
                final int offset = counters[i] - counter;
                result[pointer] = (char) (offset + 'a');
                pointer += 2;
                if (pointer >= s.length()) {
                    pointer = 1;
                }
            }
        }
        return new String(result);
    }
}
