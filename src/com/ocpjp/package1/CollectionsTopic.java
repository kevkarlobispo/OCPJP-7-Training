package com.ocpjp.package1;

import java.util.*;

/**
 * @author Kev Obispo on 10/19/2014.
 */

public class CollectionsTopic{

	public static void main(String[] args) {
		arrayListTrials();
	}

	private static void arrayListTrials() {
	    /**
	     * Explicitly used ArrayList reference type to call clone method
	     * */
	    ArrayList<String> list = new ArrayList<String>(){
            {
                add("Kev");
                add("Kev2");
                add("Kev3");
                add("Kev4");
                add("Kev5");
                add("Kev6");
            }
        };
	    msg("Cloned list: ", list.clone());
        String[] arr = new String[2];
        msg("arr.length: ", arr.length);
        Object[] str = list.toArray(arr);
        msg("str.length: ", str.length);
        System.out.println(Arrays.toString(str));

	    String[] bStr = (String[]) str.clone(); // explicit cast
	    msg("Is str == bStr: ", str == bStr); // check equality
	    msg("Cloned bStr: ", Arrays.toString(bStr));

        /**
         * Use the overloaded constructor to create a list and at the same time adding elements that is logically
         * correct to be added as part of this list's elements(declared type).
         *
         * Use addAll method to add a type of class at the end of this list that is logically correct to be added as part of this list's
         * elements. As we know, you cannot add different type other than the type of the declared type. This could be
         * an alternative :)
         * */
        List<Number> list2 = new ArrayList<>();
        List<Number> list3 = new ArrayList<Number>(new ArrayList<Integer>()); // Type Inference will not work here
        list2.addAll(new ArrayList<Integer>());
    }

    private static void linkedListTrials() {
        List<String> list = new LinkedList<>();
        list.add("Kev");
        list.add("Kev2");
        list.add("Kev3");
	    System.out.println(list);
        ListIterator<String> iterator = list.listIterator();
        while (iterator.hasNext()) {
	        System.out.println(iterator.next());
            iterator.add("Kev0");
        }

        List<String> bList = list.subList(0, 4);
        msg("List: ", list);
	    bList.add("Kevss");
	    msg("List: ", list);
	    msg("bList: ", bList);
	    msg("list.get(4): ", list.get(4));

	    Deque<String> deque = new LinkedList<>(list);
	    deque.add("Regine");
	    deque.addFirst("Regine2");
	    /**
	     * Behind the scene, this "null" value is actually a Node object, actually it is instantiated this way:
	     * new Node<>(l, e, null); where e is null. That's why it doesn't throw NSEE even though it is pointing to a
	     * NULL type of object.
	     * */
	    deque.add(null); // LinkedList doesn't throw NSEE if has null values
	    deque.addFirst(null);
	    msg("Deque: ", deque);

	    Deque<String> bDeque = new ArrayDeque<>();
	    bDeque.add(null); // ArrayDeque doesn't permit null values
	    msg("bDeque.element(): ", bDeque.element());
    }

	/**
	 * Using HashSet, it uses hashing algorithm of its holding type. In this case, its Integer. Integer's hashCode() is
	 * efficient that's why it is well distributed to the buckets. You could only observe the change in the natural
	 * order of its elements in this collection when you insert elements greater than the initial capacity which is 16.
	 * */
    private static void setTrials() {
	    Set<Integer> set = new HashSet<>();
	    set.add(-1); set.add(0); set.add(3); set.add(2); set.add(4); set.add(23); set.add(5); set.add(6); set.add(7);
	    set.add(8); set.add(9); set.add(10); set.add(12); set.add(16); set.add(13); set.add(24); set.add(15); set.add(17);
	    set.add(18); set.add(21); set.add(14); set.add(19); set.add(20); set.add(67); set.add(45); set.add(665);
	    set.add(11); set.add(124); set.add(41); set.add(233); set.add(98); set.add(86); set.add(754); set.add(1);
	    msg("Set: ", set);

	    SortedSet<Integer> sortedSet = new TreeSet<>(set);
	    SortedSet<Integer> bSortedSet = sortedSet.subSet(4, 23);
	    msg("Sorted Set: ", sortedSet);
	    /**
	     * Note: headSet() is NOT index based. It is value based. Meaning, all elements in the set that is less than 20.
	     * And that is based from the compareTo's return value.
	     * The same thing with tailSet() but fromElement is inclusive (if exists).
	     * */
	    msg("Sorted Set's headSet(): ", sortedSet.headSet(20)); // toElement is exlusive [0 .. 19]
	    msg("Sorted Set's tailSet(): ", sortedSet.tailSet(20)); // fromElement is inclusive [20, 21, 23]
	    msg("Sorted Set's tailSet(): ", sortedSet.tailSet(28)); // returns empty set
	    msg("Sorted Set's subSet(): ", bSortedSet); // toElement is exclusive

	    NavigableSet<Integer> navigableSet = new TreeSet<>(set);
		msg("Navigable Set's ceiling: ", navigableSet.ceiling(11)); // 12. Direct object higher than 11 (If 11 doesn't exist)
		msg("Navigable Set's floor: ", navigableSet.floor(11)); // 10. Direct object lower than 11 (If 11 doesn't exist)
	    msg("Navigable Set's lower: ", navigableSet.lower(11)); // 10. (Even if 11 exist)
	    msg("Navigable Set's higher: ", navigableSet.higher(11)); // 12. (Even if 11 exist)
	    msg("Navigable Set's pollFirst: ", navigableSet.pollFirst()); // -1
	    NavigableSet<Integer> bNavigableSet = navigableSet.descendingSet(); // equivalent descending set
	    msg("A Descending Navigable Set: ", bNavigableSet);
	    Iterator<Integer> integerIterator = navigableSet.descendingIterator();
	    while (integerIterator.hasNext()) {
		    int x = integerIterator.next();
		    msg(x + ": ", x);
	    }
        for (Integer nextInt : asDescendingIterable(navigableSet)) {
            msg(nextInt + ": ", nextInt);
        }
    }

    private static <T> Iterable<T> asDescendingIterable(final NavigableSet<T> set) {
        return new Iterable<T>(){
            @Override
            public Iterator<T> iterator() {
                return set.descendingIterator();
            }
        };
    }

	private static void mapTrials() {
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "Kev"); map.put(2, "Kev"); map.put(3, "Kev"); map.put(4, "Kev"); map.put(5, "Kev");map.put(6, "Kev");
		map.put(13, "Kev"); map.put(14, "Kev"); map.put(15, "Kev"); map.put(16, "Kev"); map.put(17, "Kev");map.put(18, "Kev");
		map.put(7, "Kev"); map.put(8, "Kev"); map.put(9, "Kev"); map.put(10, "Kev"); map.put(11, "Kev");map.put(12, "Kev");
		map.put(43, "Kev"); map.put(22, "Kev"); map.put(124, "Kev"); map.put(65, "Kev"); map.put(87, "Kev");map.put(55, "Kev");
		msg("Map: ", map);

		/**
		 * As you would notice, putting the same entries on a LinkedHashMap maintains the order of the insertion.
		 * Note that it is not the same result if you just initialize this linked hashMap through passing a map to
		 * its constructor.
		 * */
		Map<Integer, String> lMap = new LinkedHashMap<>();
		lMap.put(1, "Kev"); lMap.put(2, "Kev"); lMap.put(3, "Kev"); lMap.put(4, "Kev"); lMap.put(5, "Kev");lMap.put(6, "Kev");
		lMap.put(13, "Kev"); lMap.put(14, "Kev"); lMap.put(15, "Kev"); lMap.put(16, "Kev"); lMap.put(17, "Kev");lMap.put(18, "Kev");
		lMap.put(7, "Kev"); lMap.put(8, "Kev"); lMap.put(9, "Kev"); lMap.put(10, "Kev"); lMap.put(11, "Kev");lMap.put(12, "Kev");
		lMap.put(43, "Kev"); lMap.put(22, "Kev"); lMap.put(124, "Kev"); lMap.put(65, "Kev"); lMap.put(87, "Kev");lMap.put(55, "Kev");
		msg("Linked HashMap: ", lMap);


		msg("Map's KeySet Iterator: ", "");
		Set<Integer> aSet = map.keySet();
		for (Integer str : aSet) {
			msg("-> ", str);
		}

		msg("Map's Values Iterator (Set encapsulated): ", "");
		Set<String> bSet = new HashSet<>(map.values()); // unique values
		for (String str : bSet) {
			msg("-> ", str);
		}

		msg("Map's EntrySet Iterator: ", "");
		Set<Map.Entry<Integer, String>> entry = map.entrySet();
		for (Map.Entry<Integer, String> str : entry) {
			msg("-> ", str);
		}

		/**
		 * Sorted Map
		 * */
		SortedMap<Number, String> sMap = new TreeMap<Number, String>(map);
		msg("Sorted Map: ", sMap);
		Set<Map.Entry<Number, String>> bEntry = sMap.entrySet();
		msg("Sorted Map's EntrySet Iterator: ", "");
		for (Map.Entry<Number, String> str : bEntry) {
			msg("-> ", str);
		}

		/**
		 * Navigable Map
		 * */
		NavigableMap<Number, String> nMap = new TreeMap<Number, String>(map).descendingMap();
		msg("Navigable Map: ", nMap);
		Set<Map.Entry<Number, String>> cEntry = nMap.entrySet();
		msg("Navigable Map's EntrySet Iterator: ", "");
		for (Map.Entry<Number, String> str : cEntry) {
			msg("-> ", str);
		}
	}

	/**
	 * Using Collections factory class
	 * @see Collections
	 * */
	private static void collectionsTrials() {
		/**
		 * Use of type witness here is used to correctly infer the type we want to relate to the left hand-side of
		 * the equation. Since using Generic types, it doesn't allow covariant relations (meaning it should be of
		 * exact type), we should use also infer same type for "T" in "nCopies()".
		 * */
		List<Number> aList = Collections.<Number>nCopies(5, 10); // returns an immutable list that is read-only.
		List<Object> bList = new ArrayList<Object>(){
			{
				add(new Object()); add(new Object()); add(new Object()); add(new Object()); add(new Object());
			}
		};

		Collections.copy(bList, aList);
		msg("Copied numbers to Object List: ", bList);
	}

	private static void msg(String caption, Object obj) {
		System.out.println(caption + obj);
	}
}
