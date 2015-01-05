package com.ocpjp.package3;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Kev Obispo on 10/12/2014.
 */
public class ConcurrencyShoppingCart {
	public static void main(String args[]) throws Exception {
		Book book = new Book("Java");
		Thread task1 = new OnlineBuy(book); task1.start();
		Thread task3 = new OnlineReturn(book); task3.start();
		Thread task2 = new OnlineBuy(book); task2.start();
	}
}

class Book{
	String title;
//	int copiesSold = 0;
	AtomicInteger copiesSold = new AtomicInteger(0);
	Book(String title) {
		this.title = title;
	}
	public void newSale() {
		System.out.println(copiesSold.incrementAndGet());
	}
	public void returnBook() {
		System.out.println(copiesSold.decrementAndGet());
	}
}

class OnlineBuy extends Thread{
	private Book book;
	public OnlineBuy(Book book) {
		this.book = book;
	}
	@Override
	public void run() {  
		book.newSale();  
	}  
}
class OnlineReturn extends Thread{
	private Book book;
	public OnlineReturn(Book book) {
		this.book = book;
	}
	@Override
	public void run() {  
		book.returnBook();  
	}  
}