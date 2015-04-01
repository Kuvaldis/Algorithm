package kuvaldis.algorithm.dynamic.fibonacci;

import java.util.List;
import java.util.ArrayList;

public class FibonacciDynamic implements Fibonacci {

    @Override
    public Long get(final Integer n) {
        final List<Long> numbers = new ArrayList<>();
        numbers.add(0l);
    	numbers.add(1l);
        for (int i = 2; i <= n; i++) {
            numbers.add(numbers.get(i - 1) + numbers.get(i - 2));
        }
        return numbers.get(n);
    }
}
