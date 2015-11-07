package kuvaldis.algorithm.interview.arraysstrings;

public class Task18 {

    public boolean isRotation(final String s1, final String s2) {
        //noinspection SimplifiableIfStatement
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }
        return isSubstring(s1 + s1, s2);
    }

    private boolean isSubstring(String s1, String s2) {
        return s1.contains(s2);
    }
}
