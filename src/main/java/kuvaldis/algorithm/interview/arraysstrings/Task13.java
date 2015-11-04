package kuvaldis.algorithm.interview.arraysstrings;

public class Task13 {

    /**
     * Assume no buffer and 2^16 possible characters
     */
    public char[] removeDuplicates(final char[] s) {
        if (s == null || s.length < 2) {
            return s;
        }
        int tail = 1;
        for (int i = 1; i < s.length; i++) {
            int j;
            for (j = 0; j < tail; j++) {
                if (s[i] == s[j]) {
                    break;
                }
            }
            if (j == tail) {
                s[tail++] = s[i];
            }
        }
        final char[] result = new char[tail];
        System.arraycopy(s, 0, result, 0, tail);
        return result;
    }
}
