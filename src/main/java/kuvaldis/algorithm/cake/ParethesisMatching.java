package kuvaldis.algorithm.cake;

/*
"Sometimes (when I nest them (my parentheticals) too much (like this (and this))) they get confusing."

Write a method that, given a sentence like the one above, along with the position of an opening parenthesis, finds the corresponding closing parenthesis.

Example: if the example string above is input with the number 10 (position of the first parenthesis), the output should be 79 (position of the last parenthesis).
 */
public class ParethesisMatching {

    public int findMatch(final String s, final int position) {
        int openParenthesisPosition = -1;
        int stack = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack++;
                if (i == position) {
                    openParenthesisPosition = stack;
                }
            } else if (c == ')') {
                if (openParenthesisPosition == stack) {
                    return i;
                }
                stack--;
            }
        }
        return -1;
    }
}
