package kuvaldis.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/*

Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

 */
public class LongestSubstringWithoutRepeatingCharacters {

    public int find(final String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        final Map<Character, Integer> indexes = new HashMap<>();
        int start = 0;
        int end = 0;
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            final char c = s.charAt(i);
            final Integer cIndex = indexes.get(c);
            if (cIndex != null && cIndex >= start) {
                start = cIndex + 1;
            }
            end = i;
            indexes.put(c, i);
            if (result < end - start + 1) {
                result = end - start + 1;
            }
        }
        return result;
    }
}
