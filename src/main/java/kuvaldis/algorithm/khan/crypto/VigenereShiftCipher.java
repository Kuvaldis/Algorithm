package kuvaldis.algorithm.khan.crypto;

/**
 * Only latin letters
 */
public class VigenereShiftCipher {

    final String encrypt(final String s, final String keyWord) {
        final char[] chars = s.toCharArray();
        for (int i = 0, j = 0; i < chars.length; i++) {
            final char originalChar = chars[i];
            if (originalChar == ' ') {
                continue;
            }
            final char shiftChar = keyWord.charAt(j++ % keyWord.length());
            final int shift = shiftChar - 'a';
            final char encryptedChar = (char) ((originalChar - 'a' + shift) % 26 + 'a');
            chars[i] = encryptedChar;
        }
        return new String(chars);
    }
}
