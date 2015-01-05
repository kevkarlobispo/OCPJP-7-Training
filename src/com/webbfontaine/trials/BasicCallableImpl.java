package com.webbfontaine.trials;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Kev Obispo on 5/21/2014.
 */
class Factorial implements Callable<Long> {
	long n;
	public Factorial(long n) {
		this.n = n;
	}
	public Long call() throws Exception {
		if(n <= 0) {
			throw new Exception("for finding factorial, N should be > 0");
		}
		long fact = 1;
		for(long longVal = 1; longVal <= n; longVal++) {
			fact *= longVal;
		}
		return fact;
	}
}
class CallableTest {
	public static void main(String []args) throws Exception {
		final long N = 20;
		Factorial task = new Factorial(N);
		ExecutorService es = Executors.newSingleThreadExecutor();
		Future<Long> future = es.submit(task);
		System.out.printf("factorial of %d is %d", N, future.get());
		es.shutdown();
	}
}
