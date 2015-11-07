package kuvaldis.algorithm.khan.crypto;

/**
 * Only latin letters
 */
public class CaesarShiftCipher {

    public String encrypt(final String s, final byte shift) {
        final char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            final char originalChar = chars[i];
            final char encryptedChar = (char) (((originalChar - 'a' + shift) % 26) + 'a');
            chars[i] = encryptedChar;
        }
        return new String(chars);
    }

    public String decrypt(final String encrypted, final byte shift) {
        final char[] chars = encrypted.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            final char encryptedChar = chars[i];
            final char originalChar = (char) ((encryptedChar - 'a' + 26 - shift) % 26 + 'a');
            chars[i] = originalChar;
        }
        return new String(chars);
    }
}
