package kuvaldis.algorithm.structure;

public final class BitArray {
    private final int arraySize;
    final long[] data;

    public BitArray(final int arraySize) {
        this.arraySize = arraySize;
        // (arraySize >> 6) - integer division by 64 part
        // (arraySize & ((1 << 6) - 1)) - modulo from division by 64
        // (0 - (arraySize & ((1 << 6) - 1)) - would be zero if modulo is zero, else negative number
        // (0 - (arraySize & ((1 << 6) - 1)) >>> 31 - 32th bit is 1 if number is negative, so it gives us 0 if modulo 0
        // and 1 if modulo is not zero
        int dataSize = (arraySize >> 6) + ((0 - (arraySize & ((1 << 6) - 1))) >>> 31);
        this.data = new long[dataSize];
    }

    public void setBit(final int index, final int val) {
        final int cell = index >> 6;
        final long cellBits = data[cell];
        final long bitToSet = 1l << (index & ((1 << 6) - 1));
        final long valToSet = val << (index & ((1 << 6) - 1));
        data[cell] = (cellBits - (cellBits & bitToSet)) | valToSet;
    }

    public int getBit(final int index) {
        return (int) ((data[index >> 6] >>> (index & ((1 << 6) - 1))) & 1);
    }
}
