package kuvaldis.algorithm.interview.arraysstrings;

public class Task15 {

    public String replace(final String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        int newLength = s.length();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                newLength += 2;
            }
        }
        final char[] buffer = new char[newLength];
        for (int i = 0, j = 0; i < s.length() && j < newLength; i++, j++) {
            if (s.charAt(i) == ' ') {
                buffer[j++] = '%';
                buffer[j++] = '2';
                buffer[j] = '0';
            } else {
                buffer[j] = s.charAt(i);
            }
        }
        return new String(buffer);
    }
}
