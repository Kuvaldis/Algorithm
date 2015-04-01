package kuvaldis.algorithm.dynamic.fibonacci;

public class FibonacciSimple implements Fibonacci {

	@Override
	public Long get(final Integer n) {
		if (n == 0) return 0l;
		if (n == 1) return 1l;
		return get(n - 1) + get(n - 2);
	}
}
