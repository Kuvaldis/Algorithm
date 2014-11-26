package kuvaldis.test;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;

public class TestBenchmark {

     @Benchmark
     public void test() {
          new Test().returnTrue();
     }
}
