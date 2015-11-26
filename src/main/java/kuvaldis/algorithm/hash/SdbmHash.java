package kuvaldis.algorithm.hash;

public class SdbmHash implements StringHash {

    @Override
    public int hash(final String s) {
        int hash = 0;
        for (char c : s.toCharArray()) {
            // hash * 65599 + c
            hash = (hash << 6) + (hash << 16) - hash + c;
        }
        return hash;
    }
}
