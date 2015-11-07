package kuvaldis.algorithm.khan.crypto;

import org.junit.Test;

import static org.junit.Assert.*;

public class VigenereShiftCipherTest {

    @Test
    public void testEncrypt() throws Exception {
        final VigenereShiftCipher cipher = new VigenereShiftCipher();
        assertEquals("ccc", cipher.encrypt("abc", "cba"));
        assertEquals("a", cipher.encrypt("z", "b"));
        assertEquals("nte xarwgpqze qqaze a qtibq tcq", cipher.encrypt("the porcupine wears a white tie", "umaima"));
        assertEquals("bzl libv olarf i yyeea bal", cipher.encrypt("the lion wears a green tie", "ishaan"));
        System.out.println(cipher.encrypt("the gorilla wears a yellow pair of shoes", "ishaan"));

    }
}