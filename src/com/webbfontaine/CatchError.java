package com.webbfontaine;

/**
 * Created by Kev Obispo on 6/2/2014.
 */
public class CatchError {
	public static void main(String args[]) {
		try {
			myMethod();
		}
		catch (StackOverflowError s) {
			System.out.println("Error is: " + s);
		}
	}
	public static void myMethod() {
		System.out.println("myMethod");
		myMethod();
	}
}
