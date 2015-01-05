package com.ocpjp.package2;

/**
 * @author Kev Obispo on 9/23/2014.
 */
public class AssertionTopic {
	public static void main(String[] args) {
		evenOdd(-10);
	}
	static void evenOdd(int num) {
		if (num%2 == 0)
			System.out.println("Even");
		else if (num%2 == 1)
			System.out.println("Odd");
		else {
			System.out.println("This should never be printed");
			assert false : new Person();
		}
	}

}
class Person {

	@Override
	public String toString () {
		return "Pirates of the Caribbean";
	}
}