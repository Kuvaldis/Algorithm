package kuvaldis.algorithm.khan.crypto;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CaesarShiftCipherTest {

    @Test
    public void testEncrypt() throws Exception {
        final CaesarShiftCipher cipher = new CaesarShiftCipher();
        assertEquals("def", cipher.encrypt("abc", (byte) 3));
        assertEquals("a", cipher.encrypt("z", (byte) 1));
        assertEquals("datg", cipher.encrypt("khan", (byte) 19));
    }

    @Test
    public void testDecrypt() throws Exception {
        final CaesarShiftCipher cipher = new CaesarShiftCipher();
        assertEquals("abc", cipher.decrypt("def", (byte) 3));
        assertEquals("z", cipher.decrypt("a", (byte) 1));
        assertEquals("khan", cipher.decrypt("datg", (byte) 19));
    }
}