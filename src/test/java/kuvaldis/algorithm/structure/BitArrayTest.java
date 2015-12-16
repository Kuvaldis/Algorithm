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
        assertEquals(false, bitArray.getBit(0));
        bitArray.setBit(0, true);
        assertEquals(true, bitArray.getBit(0));
        bitArray.setBit(0, false);
        assertEquals(false, bitArray.getBit(0));

        assertEquals(false, bitArray.getBit(63));
        bitArray.setBit(63, true);
        assertEquals(true, bitArray.getBit(63));
        bitArray.setBit(63, false);
        assertEquals(false, bitArray.getBit(63));

        assertEquals(false, bitArray.getBit(64));
        bitArray.setBit(64, true);
        assertEquals(true, bitArray.getBit(64));
        bitArray.setBit(64, false);
        assertEquals(false, bitArray.getBit(64));

        assertEquals(false, bitArray.getBit(127));
        bitArray.setBit(127, true);
        assertEquals(true, bitArray.getBit(127));
        bitArray.setBit(127, false);
        assertEquals(false, bitArray.getBit(127));
    }
}