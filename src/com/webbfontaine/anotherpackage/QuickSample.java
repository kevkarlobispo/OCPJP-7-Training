package com.webbfontaine.anotherpackage;

import com.webbfontaine.trials.TestInterface;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Kev Obispo on 5/26/2014.
 */
public strictfp class QuickSample extends SampleAbstractClass {
	String name;
	static volatile int y = 2;

	static {System.out.println("Inside static Initializer of QuickSample");}
	{System.out.println("Inside class Initializer of QuickSample");}

	public QuickSample(String name) {
		this.name = name;
		System.out.println("QUICK");
	}


	static Integer method2() {
		return 100;
	}

	static strictfp int[] getArray() {return new int[] {1,2,9};}

	static void castClass(ClassThatExtends obj) {
		if (obj instanceof TestInterface) {
//			QuickSample t = (QuickSample) (SampleAbstractClass) obj;
		}
	}

	public void sampleMethod() {
		System.out.println("Quick Sample method");
	}

	static int out(String s, int i) {
		System.out.println(s + "=" + i);
		return i;
	}
}

interface I {
	int i = 1, ii = QuickSample.out("ii", 2);
}
interface J extends I {
	int j = QuickSample.out("j", 3), jj = QuickSample.out("jj", 4);
}
interface K extends J {
	int k = QuickSample.out("k", 5);
}

abstract class SampleAbstractClass implements TestInterface{
	String name;

	static {System.out.println("Inside static Initializer of SampleAbstractClass");}
	{System.out.println("Inside class Initializer of SampleAbstractClass");}

	public SampleAbstractClass() {System.out.println("SAMPLE");}

	public SampleAbstractClass(String name) {
		this();
		this.name = name;
	}

	native void methodNative();

	static int method1() {
		return 2;
	}

	public void sampleMethod() {
		System.out.println("Inside Sample ");
	}
}

class A implements TestInterface{
//	public static String ID = "QBANK";
	protected static int x = A.out("Sample String");

	static {System.out.println("In A static Initializer");}

	{System.out.println("Inside A Class initializer");}

	A() {System.out.println("Calling super A constructor");}

	static int out(String s) {
		System.out.println("Inside static method of A");
		return 1;
	}

	A(String s) {this();}

	int $() {
		return 5;
	}
}
class B extends A {
//	protected static int x;

	static {System.out.println("In B static Initializer");}
	{System.out.println("Inside B Class initializer");}

	B() {System.out.println("Calling super B constructor");}

	int B(String s) {  // a method
		System.out.println();
		return 0;
	}

	public static void main(String[] args) {
		SampleAbstractClass s = new QuickSample("");
		s.sampleMethod();
	}

	int $() {
		System.out.println("Inside B's $ method");
		return super.$();
	}
}
class C extends B {
//	public static int x = 1;

	static {System.out.println("In C static Initializer");}
	{System.out.println("Inside C Class initializer");}

	C() {System.out.println("Calling super C constructor");}

	C(String s) {this();}

	int $() {
		return super.$();
	}
}
class D extends C {}

class ClassThatExtends {
	public ClassThatExtends(String name) {}

	ClassThatExtends() {}

	static {System.out.println("Inside ClassThatExtends static initializer");}

	public static void sampleMethod() {
		System.out.println("Sample Method");

		try {
			int x = 1 / 0;
		} catch (ArithmeticException e) {
			System.out.println("ArithmeticException has been thrown");
//			e = new ArithmeticException();
		}
	}
}
// The behavior of implementing an interface varies on where you are putting the interface.
// Implementing the interface in the base class is different in implementing the same interface in the subclass.
// If Instance variables in an Abstract class and final variables in the Interface are same with their "name",
// this variable will resolve in compile time differently depending on where you implement the interface. Field Shadowing