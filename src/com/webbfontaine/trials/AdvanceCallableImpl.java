package com.webbfontaine.trials;

/**
 * Created by Kev Obispo on 5/21/2014.
 */
import java.util.*;
import java.util.concurrent.*;
class SumOfN {
	private static long N = 1_000_000L;
	private static long calculatedSum = 0;
	private static final int NUM_THREADS = 10;
	static class SumCalc implements Callable<Long> {
		long from, to, localSum = 0;
		public SumCalc(long from, long to) {
			SumCalc s = new SumCalc(from, to);
			this.from = from;
			this.to = to;
		}
		public Long call() {
			for(long i = from; i <= to; i++) {
				localSum += i;
			}
			return localSum;
		}
	}
	public static void main(String []args) {
		ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);

		List<Future<Long>> summationTasks = new ArrayList<>();
		long nByTen = N/10;
		for(int i = 0; i < NUM_THREADS; i++) {
			long fromInInnerRange = (nByTen * i) + 1;
			long toInInnerRange = nByTen * (i+1);
			System.out.printf("Spawning thread for summing in range %d to %d %n",
					fromInInnerRange, toInInnerRange);
			Callable<Long> summationTask =
					new SumCalc(fromInInnerRange, toInInnerRange);
			Future<Long> futureSum = executorService.submit(summationTask);
			summationTasks.add(futureSum);
		}
		for(Future<Long> partialSum : summationTasks) {
			try {
				calculatedSum += partialSum.get();
			} catch(CancellationException | ExecutionException
					| InterruptedException exception) {
				exception.printStackTrace();
				System.exit(-1);
			}
		}
		long formulaSum = (N * (N + 1))/2;
		System.out.printf("Sum by threads = %d, sum using formula = %d",
				calculatedSum, formulaSum);
	}
}