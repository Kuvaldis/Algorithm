package kuvaldis.algorithm.khan.crypto;

public class CaesarShiftCipher {

    /**
     * Only latin letters
     */
    public String encrypt(final String s, final byte shift) {
        final char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            final char originalChar = chars[i];
            final char shiftedChar = (char) (((originalChar - 'a' + shift) % 26) + 'a');
            chars[i] = shiftedChar;
        }
        return new String(chars);
    }
}
