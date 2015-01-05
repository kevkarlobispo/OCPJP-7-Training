package com.ocpjp.package2;


import com.webbfontaine.trials.ObjectStreamExample;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author  Kev Obispo on 9/5/2014.
 */

public class CollectionImpls {
	public static void main(String[] args) {
		Set<String> set = new HashSet<>(5);
		System.out.println(set.size());  // Will return 0 even though there is an initial capacity of 5
		Map<Integer, String> map = new HashMap<>();
		String s = map.put(1, "Kev");
		String s1 = map.put(1, "Kev2");
		String s2 = map.put(null, "Kev2");
		String s3 = map.put(null, "Kev3");
		String s4 = map.put(3, "Kev3");
		String s5 = map.put(4, "Kev3");
		String s6 = map.put(6, "Kev3");
		String s7 = map.put(5, "Kev3");
		String s8 = map.put(32, "Kev3");

		System.out.println("s = " + s);
		System.out.println("s1 = " + s1);
		System.out.println("s2 = " + s2);
		System.out.println("s3 = " + s3);
		System.out.println(map);

//		Map<Integer, String> map3 = new HashMap<>();
//		Map<Number, CharSequence> map2 = new HashMap<>(map3);
		// The preceding code will not compile. The HashMap constructor we used is the one who accepts Map type which is
		// a parameterized type of "Map<? extends K, ? extends V>". In this case, the right hand side of the equation
		// will be inferred first which this new map will use as its type argument. Since the type arguments of "map3"
		// is "Integer and String", this will be used also with a new instance of HashMap ie. new HashMap<Integer, String>(map3).
		// That is not what we want. It will really cause compiler error, since the receiving map is also a parameterized
		// type of Map<Number, CharSequence>. What it need is a type witness:
//		Map<Integer, String> map3 = new HashMap<>();
//		Map<Number, CharSequence> map2 = new HashMap<Number, CharSequence>(map3);
	}

	/**
	 *
	 * If an interface or a class implements {@link Iterable} interface, that interface or class is a candidate to use
	 * "foreach" loop
	 *
	 * */

	private static void tryIterables() {
		Path path = Paths.get("src");
		for (Path p : path) {
			System.out.println("Sample usage of an Iterable class/interface type");
		}
	}


	// Play with List interface: ArrayList and LinkedList implementations
	// Play with Set interface: HashSet, LinkedHashset and TreeSet implementations
	// Play with Deque interface: ArrayDeque and LinkedList implementations
	// Play with Map interface: HashMap, LinkedHashMap, and TreeMap implementations

	// When elements are added to a HashSet, it queries its hashCode to get the bucket in which the
	//	element would be stored. If the bucket doesn’t contain any element, it stores the new element in the bucket. If
	//	the bucket already contains elements, HashSet checks for matching hashCode values then compares object
	//	references then queries equals method to ensure that it stores unique values.
	// Note that if hashCode returns true, meaning they have equal hashCode, and equals() is not overridden, it will
	// still treat this two objects as distinct objects. If hashCode values are different per object (meaning, hashCode
	// is not overridden) it will not call equals() of that object.
}
