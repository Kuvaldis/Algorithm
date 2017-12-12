package kuvaldis.algorithm.string;

public class RabinKarpSubstringSearch {

    private static final int ALPHABET_SIZE = 26;

    // what a coincidence, max int is a prime number
    private static final long PRIME = Integer.MAX_VALUE;

    /*
       The algorithm is base on the idea of representing each symbol with a number.
       So we assume English alphabet, that is, E = [a..z] without upper case counterparts.
       Thus, |E| = 26 (number of symbols). Let 'a' correspond to 0, 'b' to 1 etc. with 'z' correspond to 25.
       Our numeral system's base would be 26.
       Then we need to convert a character sequence into a decimal number, which is quite obvious.
       For example, 'abc' = 0*26^2 + 1*26^1 + 2*26^0 = 28.
       Now, we can calculate decimal number for ss and for s[0..ss.length()].
       In order to find next part decimal representation we can use following approach:
         1. From the previous result we should subtract leftmost "digit" (see implementation).
         2. Multiply result by 26.
         3. Add decimal value of the next character.
       We can compare both decimal representations in O(1). However, there is one problem.
       If a string is too big, than representations will also be huge numbers and eventually we'll get overflow.
       Thus, a good idea is just to perform modulo division on intermediate results using some big prime number.
       With that said, whenever we find a match by modulo we also have to compare character sequences
       to check false matches.
     */
    public int search(final CharSequence s, final CharSequence ss) {
        if (s.length() < ss.length()) {
            return -1;
        }

        // 26^(ss.length() - 1) - used to calculate next subset of s.
        int h = 1;
        for (int i = 0; i < ss.length() - 1; i++) {
            h = (int) ((ALPHABET_SIZE * (long) h) % PRIME);
        }

        // calculate decimal representations of ss and first subset of s
        int p = 0;
        int t = 0;
        for (int i = 0; i < ss.length(); i++) {
            // assume UTF-8, so characters from a to z are next to each other in the same order as in English alphabet.
            p = (int) (((ALPHABET_SIZE * (long) p) + (ss.charAt(i) - 'a')) % PRIME);
            t = (int) (((ALPHABET_SIZE * (long) t) + (s.charAt(i) - 'a')) % PRIME);
        }

        for (int i = 0; i < s.length() - ss.length() + 1; i++) {
            if (p == t) {
                // compare the actual strings
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
            } else if (i < s.length() - ss.length()) {
                // there is next character
                // Math.floorMod is used because on the previous step t is calculated by modulo, so it actually
                // might have become less than minuend (right part of subtraction expression).
                // Math.floorMod calculates modulo for negative number in a way that it becomes positive.
                t = (int) ((((ALPHABET_SIZE * Math.floorMod(t - (((long) h * (s.charAt(i) - 'a'))), PRIME)) % PRIME) +
                        (s.charAt(i + ss.length()) - 'a')) % PRIME);
            }
        }

        return -1;
    }

}
