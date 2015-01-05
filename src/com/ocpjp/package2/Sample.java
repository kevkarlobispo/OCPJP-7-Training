package com.ocpjp.package2;

/**
 * @author Kev Obispo on 10/10/2014.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Simple stuffs
 * */

public class Sample extends SampleExtends{
	static int x = 2;
	private static final List<String> list = new ArrayList<>();

	public static void main(String[] args) {
		B b = new B();
		C c = new C();
		b = (C)(I) c;
		I i = c;
		tryListMutability();
	}

	private static void tryListMutability() {
		SampleExtends sampleExtends = new SampleExtends();
		list.add("Kev1");
		System.out.println("list = " + list);
		sampleExtends.passAList(list);
		System.out.println("list = " + list);
	}
}

class SampleExtends {
	static int x = 1;

	List<String> passAList(List<String> list) {
		list.add("KevToAddOutside invariant");
		return list;
	}
}

interface I {}

class A implements I {}
class B extends A {}
class C extends B {}

interface LK {}