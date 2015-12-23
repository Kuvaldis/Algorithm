package kuvaldis.algorithm.structure;

public class BloomFilter {

    private final int size;
    private final BitArray bitArray;
    private final Hash[] hashes;

    private static class Hash {
        private final int seed = (int) (Math.floor(Math.random() * 32) + 32);

        private int hash(final String s) {
            int result = 0;
            for (char c : s.toCharArray()) {
                result = (seed * result + c) & 0x7FFFFFFF;
            }
            return result;
        }
    }

    public BloomFilter(final int n, final float p) {
        // n - assuming number of elements
        // p - assuming false-positive probability
        // size - optimal bit array size
        // k - optimal number of hash functions
        size = (int) (-n * Math.log(p) / Math.pow(Math.log(2), 2));
        bitArray = new BitArray(size);
        final int k = (int) ((size / n) * Math.log(2));
        hashes = new Hash[k];
        for (int i = 0; i < k; i++) {
            hashes[i] = new Hash();
        }
    }

    public void add(final String s) {
        for (Hash hash : hashes) {
            bitArray.setBit(hash.hash(s) % size);
        }
    }

    public boolean contains(final String s) {
        for (Hash hash : hashes) {
            if (!bitArray.isSet(hash.hash(s) % size)) {
                return false;
            }
        }
        return true;
    }
}
