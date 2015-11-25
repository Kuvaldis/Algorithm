package kuvaldis.algorithm.hash;

public class Djb2Hash implements StringHash{

    @Override
    public int hash(final String s) {
        int hash = 0;
        for (final char c : s.toCharArray()) {
            hash = ((hash << 5) - hash) + c;
        }
        return hash;
    }
}
