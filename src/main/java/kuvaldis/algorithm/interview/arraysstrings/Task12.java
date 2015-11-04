package kuvaldis.algorithm.interview.arraysstrings;

public class Task12 {

    /**
     * Assume the input string is a characters array and it can be modified
     */
    public char[] revert(final char[] s) {
        if (s == null || s.length < 3) {
            return s;
        }

        int first = 0;
        int second = s.length - 2;
        while (first < second) {
            s[first] ^= s[second];
            s[second] ^= s[first];
            s[first++] ^= s[second--];
        }
        return s;
    }
}
