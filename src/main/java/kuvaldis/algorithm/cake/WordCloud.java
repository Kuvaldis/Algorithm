package kuvaldis.algorithm.cake;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Character.isLetter;

/*

You want to build a word cloud, an infographic where the size of a word corresponds to how often it appears in the body of text.

To do this, you'll need data. Write code that takes a long string and builds its word cloud data in a hash map ↴ , where the keys are words and the values are the number of times the words occurred.

Think about capitalized words. For example, look at these sentences:

  "After beating the eggs, Dana read the next step:"
"Add milk and eggs, then add flour and sugar."
What do we want to do with "After", "Dana", and "add"? In this example, your final hash map should include one "Add" or "add" with a value of 22. Make reasonable (not necessarily perfect) decisions about cases like "After" and "Dana".

Assume the input will only contain words and standard punctuation.

 */
public class WordCloud {

    public Map<String, Integer> count(final String s) {
        final Map<String, Integer> result = new HashMap<>();

        if (s == null) {
            return result;
        }

        int wordStartInclusive = -1;
        int wordEndExclusive = -1;
        boolean hasApostrophe = false;
        boolean wordEnded = false;
        for (int i = 0; i < s.length(); i++) {
            final char currentChar = s.charAt(i);
            if (wordContinues(s, i, currentChar)) {
                if (wordStartInclusive == -1) {
                    wordStartInclusive = i;
                }
            } else {
                if (wordStartInclusive != -1) {
                    if (currentChar == '\'') {
                        hasApostrophe = true;
                        wordEndExclusive = i;
                    } else {
                        if (hasApostrophe) {
                            hasApostrophe = false;
                        } else {
                            wordEndExclusive = i;
                        }
                        wordEnded = true;
                    }
                }
            }

            if (wordEnded) {
                final String originalWord = s.substring(wordStartInclusive, wordEndExclusive);
                final String word = originalWord.toLowerCase();
                result.compute(word, (k, v) -> v == null ? 1 : v + 1);
                wordStartInclusive = -1;
                wordEndExclusive = -1;
                hasApostrophe = false;
                wordEnded = false;
            }
        }

        return result;
    }

    private boolean wordContinues(final String s, final int i, final char c) {
        if (Character.isLetter(c)) {
            return true;
        }

        if (c == '-') {
            final int prev = i - 1;
            if (prev >= 0) {
                return Character.isLetter(s.charAt(prev));
            }
        }

        return false;
    }
}
