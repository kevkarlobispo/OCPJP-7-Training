package com.webbfontaine.trials;

/**
 * Created by Kev Obispo on 5/15/2014.
 */
class Counter {
	public static long count = 0;
}
// This class implements Runnable interface
// Its run method increments the counter three times
class UseCounter implements Runnable {
	public void increment() {
// increments the counter and prints the value
// of the counter shared between threads
		System.out.print(Counter.count++ + " ");
	}
	public void run() {
		increment();
		increment();
		increment();
	}
}
// This class creates three threads
class DataRace {
	public static void main(String args[]) {
		UseCounter c = new UseCounter();
		Thread t1 = new Thread(c);
		Thread t2 = new Thread(c);
		Thread t3 = new Thread(c);
		t1.start();
		t2.start();
		t3.start();
//		UseCounter c = new UseCounter();
//		c.increment();
//		c.increment();
//		c.increment();
//
//		UseCounter d = new UseCounter();
//		d.increment();
//		d.increment();
//		d.increment();
	}
}
