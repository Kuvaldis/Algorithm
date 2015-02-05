package kuvaldis.algorithm.sort;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

// only for positive. for negative we use 19 buckets (9 for negative and 10 for non-negative)
public class RadixSort extends Sort {

    private static final int BUCKETS_SIZE = 10;
    private static final int RADIX = 10;

    @Override
    public int[] sort(int[] input) {
        final int[] result = new int[input.length];
        System.arraycopy(input, 0, result, 0, result.length);
        //noinspection unchecked
        final LinkedList<Integer>[] buckets = Stream.generate(LinkedList::new)
                .limit(BUCKETS_SIZE)
                .toArray(java.util.LinkedList[]::new);
        final byte maxDigits = calculateMaxDigits(result);
        int mod = RADIX;
        int div = 1;
        for (int i = 0; i < maxDigits; i++, mod *= RADIX, div *= RADIX) {
            for (int j = 0; j < result.length; j++) {
                int bucketIndex = (result[j] % mod) / div;
                buckets[bucketIndex].add(result[j]);
            }
            int pos = 0;
            for (LinkedList<Integer> bucket : buckets) {
                Integer value = null;
                while ((value = bucket.poll()) != null) {
                    result[pos++] = value;
                }
            }
        }
        return result;
    }

    private byte calculateMaxDigits(final int[] array) {
        int max = Integer.MIN_VALUE;
        for (int el : array) {
            if (el > max) {
                max = el;
            }
        }
        byte result = 1;
        while ((max = max / RADIX) > 0) {
            result++;
        }
        return result;
    }
}
