package kuvaldis.algorithm.khan.crypto;

import junit.framework.TestCase;

public class CaesarShiftCipherTest extends TestCase {

    public void testEncrypt() throws Exception {
        final CaesarShiftCipher cipher = new CaesarShiftCipher();
        assertEquals("def", cipher.encrypt("abc", (byte) 3));
        assertEquals("a", cipher.encrypt("z", (byte) 1));
        assertEquals("datg", cipher.encrypt("khan", (byte) 19));
    }
}