package kuvaldis.algorithm.hash;

import org.apache.commons.lang.math.RandomUtils;
import org.junit.Test;

import java.util.Arrays;

public class HashTest {

    private static final int MOD = 16;
    private static final int COUNT = 1000000;
    private static final int LENGTH = 10;

    private static final char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    @Test
    public void testDjb() throws Exception {
        final int[] buckets = getBuckets(new Djb2Hash());
        printStats(buckets);
    }

    private void printStats(final int[] buckets) {
        System.out.println(Arrays.toString(buckets));
        Arrays.sort(buckets);
        final int mean = mean(buckets);
        final int deviation = deviation(buckets, mean);

        System.out.println("Min value:         " + buckets[0]);
        System.out.println("Max value:         " + buckets[buckets.length - 1]);
        System.out.println("Mean:              " + mean);
        System.out.println("Deviation:         " + deviation);
        System.out.println("Deviation percent: " + (double) 100 * deviation / mean);
    }

    private int mean(final int[] buckets) {
        int sum = 0;
        for (final int bucket : buckets) {
            sum += bucket;
        }
        return sum / buckets.length;
    }

    private int deviation(final int[] buckets, final int mean) {
        int sum = 0;
        for (int bucket : buckets) {
            sum += (bucket - mean) * (bucket - mean);
        }
        return (int) Math.sqrt(sum / (buckets.length - 1));
    }

    private int[] getBuckets(final StringHash stringHash) throws Exception {
        final int[] buckets = new int[MOD];
        final int mod = MOD >> 1;
        for (int i = 0; i < COUNT; i++) {
            final String s = generateString(LENGTH);
            final int hash = stringHash.hash(s);
            // negative values will be distributed from 0 to 7
            if (hash < 0) {
                buckets[-(-hash % mod) + mod - 1]++;
            } else { // positive from 8 to 15
                buckets[hash % mod + mod]++;
            }
        }
        return buckets;
    }

    private String generateString(final int length) {
        final char[] chars = new char[length];
        for (int i = 0; i < length; i++) {
            chars[i] = alphabet[RandomUtils.nextInt(25)];
        }
        return new String(chars);
    }
}