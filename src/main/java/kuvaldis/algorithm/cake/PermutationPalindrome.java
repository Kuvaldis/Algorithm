package kuvaldis.algorithm.cake;

import java.util.HashSet;
import java.util.Set;

/*

Write an efficient function that checks whether any permutation ↴ of an input string is a palindrome ↴ .
Examples:

"civic" should return true
"ivicc" should return true
"civil" should return false
"livci" should return false


 */
public class PermutationPalindrome {

    public boolean isPermPalindrome(final String s) {
        final Set<Character> unpaired = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (unpaired.contains(c)) {
                unpaired.remove(c);
            } else {
                unpaired.add(c);
            }
        }
        return unpaired.size() <= 1;
    }
}
