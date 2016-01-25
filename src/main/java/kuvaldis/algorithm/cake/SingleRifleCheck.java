package kuvaldis.algorithm.cake;

/*

I figured out how to get rich: online poker.
I suspect the online poker game I'm playing shuffles cards by doing a single " riffle."

To prove this, let's write a function to tell us if a full deck of cards shuffled_deck is a single riffle of two other halves half1 and half2.

We'll represent a stack of cards as a list of integers in the range 1..52 (since there are 52 distinct cards in a deck).

 */
public class SingleRifleCheck {
    public boolean isSingleRifle(final int[] shuffledDeck, final int[] half1, final int[] half2) {
        int i = 0;
        int j = 0;
        for (int card : shuffledDeck) {
            if (i < half1.length && card == half1[i]) {
                i++;
            } else if (j < half2.length && card == half2[j]) {
                j++;
            } else {
                return false;
            }
        }
        return true;
    }
}
