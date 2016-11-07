package kuvaldis.algorithm.hash;

public class LoseLoseHash implements StringHash {
    @Override
    public int hash(final String s) {
        int hash = 0;
        for (char c : s.toCharArray()) {
            hash += c;
        }
        return hash;
    }
}
