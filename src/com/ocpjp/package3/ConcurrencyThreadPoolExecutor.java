package com.ocpjp.package3;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Kev Obispo on 10/12/2014.
 */

public class ConcurrencyThreadPoolExecutor {
	public static void main(String[] args) {
		Order order = new Order("berry");
		HotelA hotelA = new HotelA();
		hotelA.orderFood(order);
		hotelA.orderFood(order);
		hotelA.orderFood(order);
		hotelA.orderFood(order);
		hotelA.orderFood(order);
		hotelA.orderFood(order);
		hotelA.orderFood(order);
		hotelA.orderFood(order);
		hotelA.orderFood(order);
		hotelA.orderFood(order);
		hotelA.orderFood(order);
		System.out.println("Worker threads ongoing!");
		hotelA.hotelClosedForDay();
		/**
		* If uncommented, will throw RejectedExecutionException, a
		* Runtime Exception.
		* */
//		hotelA.orderFood(order);
//		hotelA.orderFood(order);
	}
}
class Order implements Callable<Void> {
	String name;
	Order(String name) {this.name = name;}

	public Void call() throws Exception {
		/**
		 * I've put sleep in this case so I can see if
		 * there are really 5 threads running at the same time.
		 * */
		Thread.sleep(2000);
		System.out.println(name);
		if (name.equalsIgnoreCase("berry"))
			throw new Exception("Berry unavailable");
		return null;
	}
}
class HotelA {
	ExecutorService service = Executors.newFixedThreadPool(5);

	public void orderFood(Order order) {
		service.submit(order);
	}

	public void hotelClosedForDay() {
		service.shutdown();
	}

	public void hotelClosedForever() {
		service.shutdown();
		try {
			if (!service.awaitTermination(60, TimeUnit.SECONDS)) {
				service.shutdownNow(); // Cancel currently executing tasks
				if (!service.awaitTermination(60, TimeUnit.SECONDS))
				System.err.println("Pool did not terminate");
			}
		} catch (InterruptedException ie) {
			service.shutdownNow();
			Thread.currentThread().interrupt();
		}
	}
}