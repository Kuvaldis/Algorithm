package kuvaldis.algorithm.structure;

import org.junit.Test;

import static org.junit.Assert.*;

public class BitArrayTest {
    @Test
    public void testBitArray() throws Exception {
        assertEquals(1, new BitArray(1).data.length);
        assertEquals(1, new BitArray(64).data.length);
        assertEquals(2, new BitArray(65).data.length);
        assertEquals(2, new BitArray(128).data.length);

        final BitArray bitArray = new BitArray(1024);
        assertEquals(0, bitArray.getBit(0));
        bitArray.setBit(0);
        assertEquals(1, bitArray.getBit(0));
        bitArray.unSetBit(0);
        assertEquals(0, bitArray.getBit(0));

        assertEquals(0, bitArray.getBit(63));
        bitArray.setBit(63);
        assertEquals(1, bitArray.getBit(63));
        bitArray.unSetBit(63);
        assertEquals(0, bitArray.getBit(63));

        assertEquals(0, bitArray.getBit(64));
        bitArray.setBit(64);
        assertEquals(1, bitArray.getBit(64));
        bitArray.unSetBit(64);
        assertEquals(0, bitArray.getBit(64));

        assertEquals(0, bitArray.getBit(127));
        bitArray.setBit(127);
        assertEquals(1, bitArray.getBit(127));
        bitArray.unSetBit(127);
        assertEquals(0, bitArray.getBit(127));
    }
}