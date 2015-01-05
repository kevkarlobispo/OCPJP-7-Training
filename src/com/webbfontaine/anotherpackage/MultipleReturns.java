package com.webbfontaine.anotherpackage;

/**
 * Created by Kev Obispo on 6/2/2014.
 */
class MultipleReturns {
	int getInt() {
		Name nam1 = new Name("Kev");

//		try {
//			String[] students = {"Harry", "Paul"};
//			System.out.println("About to return: " + nam1);
//			return nam1;
////			System.out.println(students[5]);
//		}
//		catch (Exception e) {
//			System.out.println("About to return: " + nam1);
//			return nam1;
//		}
//		finally {
//			nam1 = new Name("Albert");
//			System.out.println("Return value is now :" + nam1);
//		}
		int x = 10;
		try {
			String[] students = {"Harry", "Paul"};
			System.out.println(students[1]);
		} catch (Exception e) {
			System.out.println("blablabla");
		} finally {
			x += 10;
			System.out.println("Executed this, but not modified x which is 10: " + x);
		}
		System.out.println("Another Line");
		return x;
	}
	public static void main(String args[]) {
		MultipleReturns var = new MultipleReturns();
		System.out.println("In Main: " + var.getInt());
	}
}

class Name {
	String name;

	public Name(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
}
//The output of the preceding code is as follows:
//		About to return :10
//		Return value is now :20
//		In Main:10
