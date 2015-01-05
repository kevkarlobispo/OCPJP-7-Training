package com.webbfontaine;

/**
 * Created by Kev Obispo on 6/2/2014.
 */
public class InvalidArrayAccess {
	public static void main(String args[]) {
		int x = sampleMethod();
		System.out.println("x = " + x);
	}

	static int sampleMethod() {
		String[] students = {"Shreya", "Joseph"};
		int x = 1;
		try {
			System.out.println(students[5]);
		}
		catch (ArrayIndexOutOfBoundsException e){
			System.out.println("Exception");
		}
		System.out.println("All seems to be well");
		return x;
	}
}
