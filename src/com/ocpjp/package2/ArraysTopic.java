package com.ocpjp.package2;

import java.util.*;

/**
 * @author Kev Obispo on 10/21/2014.
 */

public class ArraysTopic {
	public static void main(String[] args) {
		arraysTrials();
	}

	/**
	 * Using Arrays factory class
	 * @see java.util.Arrays
	 * */
	private static void arraysTrials() {

		String[] sample = {"12b", "b", "B", "&", "%"};
		Arrays.sort(sample);
		System.out.println(Arrays.toString(sample)); // ascii codes of numbers are less than alphabets but not with special characters.

		int[] iArray = {3, 4, 5, 1, 2, 6, 7, 8, 9, 10};
		Arrays.sort(iArray);
		System.out.println(Arrays.binarySearch(iArray, 5));
		List<int[]> list = Arrays.asList(iArray);
		List<int[]> bList = new ArrayList<>(list);
		bList.add(new int[1]);
		System.out.println("bList is now a type of a List wherein add does now provide concrete implementation: "
				+ bList.get(1).length);
		/*
		* Will not compile. int[] cannot be assigned to Object[]
		* */
		List<Object[]> cList = new ArrayList<>();
		cList.add(new String[1]);
//		cList.add(new int[1]); // won't compile.

		List<String>[] str = new List[1];
		/*
		* Will not compile if used generics in creation of an array
		* */
//		List<String>[] str = new List<String>[1];
		str[0] = new ArrayList<String>() {
			{
				add("Kev");
				add("Kev2");
				add("Kev3");
			}
		};
		System.out.println("str[0]: " + str[0]);
		/*
		* Will compile but will ClassCastException at runtime.
		* This is because H class is not a final class that's why compiler has to let it through.
		* But if class H is final, compiler can analyze that it cannot be a type of G, even if G is an interface
		* */
//		G[] o = (G[]) new H[1];

		/**
		 * will not compile. int[] is not a subtype of Object[]
		 * */
//		acceptArray(iArray);

		/**
		 * will not compile if used int[]
		 * */
		Integer[] intArray = {1, 2, 3, 4, 5, 6};
		acceptParameterizedTypeArray(intArray);
	}

	private static void acceptArray(Object[] objects) {}

	private static <T> void acceptParameterizedTypeArray(T[] t) {
		System.out.println(Arrays.toString(t));
	}
}

interface G {}

class H {}
