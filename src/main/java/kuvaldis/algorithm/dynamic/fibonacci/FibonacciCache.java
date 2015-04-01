package kuvaldis.algorithm.dynamic.fibonacci;

import java.util.HashMap;
import java.util.Map;

public class FibonacciCache implements Fibonacci {

    final Map<Integer, Long> cache = new HashMap<>();

    @Override
    public Long get(final Integer n) {
        cache.put(0, 0l);
        cache.put(1, 1l);
        return doGet(n);
    }

    private Long doGet(final Integer n) {
        if (!cache.containsKey(n)) {
            cache.put(n, doGet(n - 1) + doGet(n - 2));
        }
        return cache.get(n);
    }
}
