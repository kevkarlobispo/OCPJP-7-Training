package com.webbfontaine.trials;

/**
 * Created by Kev Obispo on 5/22/2014.
 */
import java.util.concurrent.*;
class SearchUsingForkJoin {
	private static int N = 10000;
	private static final int NUM_THREADS = 10; // number of threads to create for
	private static int searchKey= 100;
	private static int[] arrayToSearch;
	static class SearchTask extends RecursiveAction {
		private static final long serialVersionUID = 1L;
		int from, to;
		public SearchTask(int from, int to) {
			this.from = from;
			this.to = to;
		}
		public void compute() {
			if( (to - from) <= N/NUM_THREADS) {
				for(int i = from; i <= to; i++) {
					if(arrayToSearch[i] == searchKey)
						System.out.println("Search key found at index:" +i);
				}
			}
			else {
				int mid = (from + to)/2;
				System.out.printf("Forking computation into two ranges: " +
						"%d to %d and %d to %d %n", from, mid, mid, to);
				invokeAll(new SearchTask(from, mid),new SearchTask(mid + 1, to));
			}
		}
	}

	public static void main(String []args) {
		arrayToSearch = new int[N];
		for(int i=0; i<N; i++){
			arrayToSearch[i] = ThreadLocalRandom.current().nextInt(0,1000);
		}
		ForkJoinPool pool = new ForkJoinPool(NUM_THREADS);
		pool.invoke(new SearchTask(0, N-1));
	}
}