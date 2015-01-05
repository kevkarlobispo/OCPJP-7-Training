package com.ocpjp.package2;

/**
 * Created by Kev Obispo on 9/17/2014.
 */
public class WrapperClasses {
	public static void main(String[] args) {
		Integer integer = new Integer("123");
		Integer integer1 = new Integer("123");
		Integer a = Integer.parseInt("123");
		Integer b = Integer.parseInt("123");
		int c = Integer.valueOf("123");
		Integer d = Integer.valueOf("123");
		System.out.println("a == b " + (a == b));  // true
		System.out.println("c == d " + (c == d));  // true
		System.out.println("a == c " + (a== c));   // true
		System.out.println("integer == a " + (integer == a));  // false
		System.out.println("integer == integer1 " + (integer == integer1));  // false
	}
}
