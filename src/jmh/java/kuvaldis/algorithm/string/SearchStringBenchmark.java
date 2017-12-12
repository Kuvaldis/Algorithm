package kuvaldis.algorithm.string;

import org.openjdk.jmh.annotations.Benchmark;

import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;

public class SearchStringBenchmark {

    private CharSequence randomChars(int bits) {
        return new BigInteger(bits, ThreadLocalRandom.current()).toString(32);
    }

    @Benchmark
    public void run1to2() {
        new SimpleSubstringSearch().search(randomChars(1), randomChars(2));
    }

    @Benchmark
    public void run100to200() {
        new SimpleSubstringSearch().search(randomChars(100), randomChars(200));
    }

    @Benchmark
    public void run10000to20000() {
        new SimpleSubstringSearch().search(randomChars(10000), randomChars(20000));
    }
}
