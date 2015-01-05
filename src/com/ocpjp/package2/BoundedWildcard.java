package com.ocpjp.package2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kev Obispo on 6/21/2014.
 */

public class BoundedWildcard {
	public static void main(String[] args) {
		BoundedWildcard.wrapGift(new ArrayList<Gift>() {
			{
				add(new Book("BOOK"));
				add(new Phone("PHONE"));
			}
		});
//
//		List<Gift> gift = new ArrayList<Gift>();
//		gift.add(new Book("")); // You are free to assign any type of objects
//		// that is an instance of "Gift" class in this case,
//		// since compiler is sure that the type parameter of
//		// the parameterized type "List" will never change. By means of
//		// Covariance.

//		List<? extends Gift> gift = new ArrayList<Gift>();
//		List<? extends Gift> gift1 = new ArrayList<Book>();
//		gift.add(new Book(""));   // "?" could be a type of "HarryPotter".
//		// An object of Book cannot be handled by type "HarryPotter". By
//		// means of covariance. That's why it is not type-safe,
//		// compiler has not to let it through.

//		List<? super Gift> gift = new ArrayList<Gift>();
//		List<? super Gift> gift1 = new ArrayList<Object>(); // Using "super" in
//		// this case tries to limit the type of object that can be assigned
//		// to "gift" reference variable (only Gift and Object).
//		gift.add(new Book(""));
//		// Why you can add Book object? It is because,
//		// only the reference variable
//		// "range of types" were being bounded, you are now rest assured
//		// that all reference types are only on that boundary. That's why you
//		// can now assign objects of "derived" classes of class Gift. By
//		// means of Covariance

		//However, if you add:
		// gift.add(new Object());
		// It will not compile, because of covariance also. The reference
		// type could be at range Object and Gift. What if the object at
		// run-time is of type Gift? Adding objects of type Object will not
		// satisfy the idea of covariance. Thus, compiler has to declare
		// compilation error.
	}

	public static <T extends Gift> void wrapGift(List<T> list) {
		for (T item : list) {
			if (item instanceof Book) {
				System.out.println("BookGiftWrap - " + ((Book) item).name);
			} else if (item instanceof Phone) {
				System.out.println("PhoneGiftWrap - " + item.name);
			}
		}
	}
}

interface MyMap{
	<V, K> String mapMaterial(K t);
}

class MyMapImpl implements MyMap {

	@Override
	public <V, K> String mapMaterial(K t) {
		return t.toString();
	}
}

class Gift{
	String name;
	protected Gift(String name) {
		this.name = name;
	}

	void printName(){
		System.out.println("Inside Gift Abstract Class " + name);
	}
}

class Book extends Gift {

	Book(String name) {
		super(name);
	}
}

class Phone extends Gift {

	Phone(String name) {
		super(name);
	}
}

class HarryPotter extends Book {

	HarryPotter(String name) {
		super(name);
	}
}

