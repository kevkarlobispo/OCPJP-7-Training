package com.ocpjp.package1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kev Obispo on 6/21/2014.
 */

final public class TypeParameterDistinction {
	public static void main(String[] args) {
		MyMap<Integer, String> myMap = new MyMapImpl<>();
		myMap.put(1, "Success");
		Gift gift = TypeParameterDistinction.wrapGift(new ArrayList<Gift>() {
			{
				add(new Book("BOOK"));
			}
		}, myMap);
		Book book = (Book) gift;
		book.printName();
	}

	public static <G, T extends Gift> T wrapGift(List<T> list,
	                                                   MyMap<Integer,	G> y) {
		//There is an idea of "declaration:
		// T extends Gift
		// and re-using of implementation:
		// MyMap<Integer, V> y"

		// Wherein "declaration, only on the "type parameter section" you can
		// only define
		// the boundary of the "formal type parameter" that is/are being used.
		// In re-using the implementation, you are only allowed to define
		// programatically the "type argument" that would satisfy the
		// declared generic type.

		// If you're going to use formal type parameter as the method's
		// return type, it's good. But if it's not, you can just use wildcard
		// (?) instead to replace the type parameter:
		// List<? extends Gift> with "void" return type
		T list2 = null;
		for (T item : list) {
			if (item instanceof Book) {
				Book book = (Book) item;
				list2 = item;
				System.out.println("BookGiftWrap - " + book.name);
			}
		}
		System.out.println(y.get(1));
		return list2;
	}
}

interface MyMap<K extends Integer, V> {
	void put(K key, V value);

	V get(K key);

	//There is an idea of "declaration:
	// MyMap<K extends Integer, V>
	// also, MyMapImpl<K extends Integer, V>

	// and re-using of implementation:
	// implements MyMap<K, V>

	// Whenever you use an already defined implementation,
	// you are not allowed to use bounding operations like "extends" and
	// "implements". Because you do not have the rights to change the "types"
	// that are allowed to use!
}

class MyMapImpl<K extends Integer, V> implements MyMap<K, V>{
	K key;
	V value;

	@Override
	public void put(K key, V value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public V get(K key) {
		if (key == this.key) {
			return value;
		}
		return null;
	}
}

class Gift{
	String name;
	protected Gift(String name) {
		this.name = name;
	}

	void printName(){ System.out.println("Inside Gift Abstract Class " + name); }
}

final class Book extends Gift {

	Book(String name) { super(name); }
}

