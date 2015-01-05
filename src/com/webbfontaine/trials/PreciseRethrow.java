package com.webbfontaine.trials;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Kev Obispo on 5/14/2014.
 */
public class PreciseRethrow {

	public static void main(String []str) {

		PreciseRethrow p = new OverrideThrows();
		p.method1();
		try {
			p.foo();
		} catch (Exception e) {
			System.out.println("FileNotFoundException " + e);
		}
		OverrideThrows over = new OverrideThrows();
		try {
			over.foo();
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException " + e);
		}
	}

	public void foo() throws Exception{
		new FileInputStream("");
//		    int i = Integer.parseInt("ten");
	}

	public void method1() {
		System.out.println("Inside PreciseRethrow");
	}
}

class OverrideThrows extends PreciseRethrow{

	public void foo() throws FileNotFoundException {
		new FileInputStream("");
	}

	public void method1() {
		System.out.println("Inside OverrideThrows");
	}
}

//class CheckedExceptionExample3 {
//	public static void main(String []args) {
//		try {
//			openFile();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		int i = Integer.parseInt("");
//	}
//
//	private static void openFile() throws FileNotFoundException{
//		int i = 1;
//		if (i > 0) {
//			throw new FileNotFoundException();
//		}
////			System.out.println("Pass a valid file name as commandline argument!");
//	}
//}


