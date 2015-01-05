package com.webbfontaine.trials;

/**
 * Created by Kev Obispo on 5/22/2014.
 */
class EJava {
	void method() {
		try {
			int multiArray2[][];
			multiArray2 = new int[2][3];
			System.out.println("array at index 1, 3: " + multiArray2[0][1]);
			guru();
			return;
		}
		finally {
			System.out.println("finally 1");
		}
	}
	void guru() {
		System.out.println("guru");
		throw new StackOverflowError();
	}
	public static void main(String args[]) {
		EJava var = new EJava();
		var.method();
	}
}