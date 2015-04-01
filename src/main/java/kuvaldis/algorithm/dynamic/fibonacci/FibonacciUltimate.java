package kuvaldis.algorithm.dynamic.fibonacci;

public class FibonacciUltimate implements Fibonacci {
	
	@Override
	public Long get(final Integer n) {
		if (n == 0) return 0l;
		if (n == 1) return 1l;
		long back2 = 0;
		long back1 = 1;
		long c;
		for (int i = 2; i < n; i++) {
			c = back1 + back2;			
			back2 = back1;
			back1 = c;
		}	
		return back1 + back2;
	}
}
