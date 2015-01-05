package com.ocpjp.package2;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Kev Obispo on 10/21/2014.
 */

//@SuppressWarnings("unchecked")
public class ArrayManipulationTopic<T> {

	@SuppressWarnings("unchecked")
	private T[] s = (T[]) new Object[10];

	private void init() {
//		s = (T[]) new Object[10];
	}

	public static void main(String[] args) {
		ArrayManipulationTopic<List<? extends Number>> instance = new ArrayManipulationTopic<>();
//		instance.init();
		instance.setElementData(0, instance.storeValues());
		System.out.println("List: " + instance.getElementData(0));
	}

	public void setElementData(int index, T elementData) {
		this.s[index] = elementData;
	}

	public T getElementData(int index) {
		return s[index];
	}

	public int getSize() {
		return s.length;
	}

	private static void runVarargsTrials() {


//		varargsTrials(s);

//		Double aDouble = new Double("12");
//		short sh = aDouble.shortValue();

	}

	private List<Integer> storeValues() {
		return new ArrayList<Integer>(){
			{
				add(1);
				add(2);
				add(3);
			}
		};
	}


//	@SafeVarargs // Not actually safe!
	private static void varargsTrials(List<? extends Number>... stringLists) {
		Object[] array = stringLists;
		List<String> tmpList = Arrays.asList("42");
		array[0] = tmpList; // Semantically invalid, but compiles without warnings
		Number s = stringLists[0].get(0); // Oh no, ClassCastException at runtime!
	}
}
