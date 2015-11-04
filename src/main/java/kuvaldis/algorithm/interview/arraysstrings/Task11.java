package kuvaldis.algorithm.interview.arraysstrings;

public class Task11 {

    /**
     * Assuming it's only English alphabet characters
     */
    public boolean charUnique(final String s) {
        if (s == null) return true;
        int checker = 0;
        for (int i = 0; i < s.length(); i++) {
            final char c = s.charAt(i);
            final short position = (short) (c - 'a');
            final int checkerPosition = 1 << position;
            if ((checker & checkerPosition) > 0) {
                return false;
            } else {
                checker += checkerPosition;
            }
        }
        return true;
    }
}
