package kuvaldis.algorithm.sort;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.ThreadLocalRandom;

@State(value = Scope.Benchmark)
public abstract class SortBenchmark {

    private int[] input1;
//    private int[] input2;
//    private int[] input3;

    @Setup
    public void setup() {
        input1 = random(100);
//        input2 = random(100000);
//        input3 = random(1000000000);
    }

    private int[] random(int length) {
        final int[] result = new int[length];
        for (int i = 0; i < result.length; i++) {
            result[i] = ThreadLocalRandom.current().nextInt(0, length);
        }
        return result;
    }

    @Benchmark
    @Measurement(iterations = 1, time = 1)
    @Warmup(iterations = 1, time = 1, batchSize = 1)
    public void run100() {
        run(input1);
    }

//    @Benchmark
//    @Measurement(iterations = 1, time = 1)
//    @Warmup(iterations = 1, time = 1, batchSize = 1)
//    public void run100000() {
//        run(input2);
//    }
//
//    @Benchmark
//    @Measurement(iterations = 1, time = 1)
//    @Warmup(iterations = 1, time = 1, batchSize = 1)
//    public void run1000000000() {
//        run(input3);
//    }

    protected abstract void run(int[] input);
}
