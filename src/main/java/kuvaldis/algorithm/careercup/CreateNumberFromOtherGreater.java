package kuvaldis.algorithm.careercup;

/*

http://careercup.com/question?id=5165229530939392

Given 2 large number A and B, create a new number C using the digits from A which needs to be grater than B.
e.g. A = 5281, B = 7443
C = 8125.

 */
public class CreateNumberFromOtherGreater {
    // assuming the length is the same
    public int solution(final int a, final int b) {
        final int[] ad = new int[10];
        for (String d : String.valueOf(a).split("")) {
            ad[Integer.valueOf(d)]++;
        }
        int result = 0;
        for (int i = ad.length - 1; i >= 0; ) {
            if (ad[i] == 0) {
                i--;
                continue;
            }
            result = result * 10 + i;
            ad[i]--;
            if (ad[i] == 0) {
                i--;
            }
        }
        if (result > b) {
            return result;
        }
        return -1;
    }
}
