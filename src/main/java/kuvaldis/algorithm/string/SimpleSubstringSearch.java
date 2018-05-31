package kuvaldis.algorithm.string;

public class SimpleSubstringSearch {

    public int search(final CharSequence s, final CharSequence ss) {
        for (int i = 0; i < s.length() - ss.length() + 1; i++) {
            boolean allMatch = true;
            for (int j = 0; j < ss.length(); j++) {
                if (ss.charAt(j) != s.charAt(i + j)) {
                    allMatch = false;
                    break;
                }
            }
            if (allMatch) {
                return i;
            }
        }
        return -1;
    }
}
