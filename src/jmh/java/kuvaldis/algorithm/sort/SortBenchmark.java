package kuvaldis.algorithm.sort;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.ThreadLocalRandom;

@State(value = Scope.Benchmark)
public abstract class SortBenchmark {

    private int[] input1;
    private int[] input100;
    private int[] input10000;

    @Setup
    public void setup() {
        input1 = random(1);
        input100 = random(100);
        input10000 = random(10000);
    }

    private int[] random(int length) {
        final int[] result = new int[length];
        for (int i = 0; i < result.length; i++) {
            result[i] = ThreadLocalRandom.current().nextInt(0, length);
        }
        return result;
    }

    @Benchmark
    public void run1() throws InstantiationException, IllegalAccessException {
        run(input1);
    }

    @Benchmark
    public void run100() throws InstantiationException, IllegalAccessException {
        run(input100);
    }

    @Benchmark
    public void run10000() throws InstantiationException, IllegalAccessException {
        run(input10000);
    }

    private void run(final int[] input) throws IllegalAccessException, InstantiationException {
        instance().newInstance().sort(input);
    }

    protected abstract Class<? extends Sort> instance();
}
