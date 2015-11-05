package kuvaldis.algorithm.interview.arraysstrings;

public class Task14 {

    public boolean areAnagrams(final String s1, final String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }
        final int[] charCounters = new int[256 * 256];
        int uniqueCounter = 0;
        for (int i = 0; i < s1.length(); i++) {
            final char c = s1.charAt(i);
            if (charCounters[c] == 0) {
                uniqueCounter++;
            }
            charCounters[c]++;
        }
        for (int i = 0; i < s2.length(); i++) {
            final char c = s2.charAt(i);
            if (charCounters[c] == 0) {
                return false;
            }
            charCounters[c]--;
            if (charCounters[c] == 0) {
                uniqueCounter--;
            }
        }
        return uniqueCounter == 0;
    }
}
