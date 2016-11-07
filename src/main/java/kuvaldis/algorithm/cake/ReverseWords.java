package kuvaldis.algorithm.cake;

/*

You're working on a secret team solving coded transmissions.
Your team is scrambling to decipher a recent message, worried it's a plot to break into a major European National Cake Vault. The message has been mostly deciphered, but all the words are backwards! Your colleagues have handed off the last step to you.

Write a function reverseWords() that takes a string message and reverses the order of the words in place ↴ .

 */
public class ReverseWords {
    public String reverseWords(final String s) {
        if (s == null) {
            return null;
        }
        final char[] words = s.toCharArray();
        for (int i = 0, j = words.length - 1; i < j; i++, j--) {
            words[i] ^= words[j];
            words[j] ^= words[i];
            words[i] ^= words[j];
        }
        for (int k = 0; k < words.length; k++) {
            int i = k;
            while (k < words.length && words[k] != ' ') {
                k++;
            }
            int j = k - 1;
            for (; i < j; i++, j--) {
                words[i] ^= words[j];
                words[j] ^= words[i];
                words[i] ^= words[j];
            }
        }
        return new String(words);
    }
}
