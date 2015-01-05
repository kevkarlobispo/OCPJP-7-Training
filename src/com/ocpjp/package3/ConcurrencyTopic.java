package com.ocpjp.package3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Kev Obispo on 10/11/2014.
 */

public class ConcurrencyTopic{
	public static void main(String[] args) throws InterruptedException {
		Color color = new Color();
		ColorContainer colorContainer = new ColorContainer(color);
		ColorContainer colorContainer1 = new ColorContainer(color);
		ColorContainer colorContainer2 = new ColorContainer(color);
		ColorContainer colorContainer3 = new ColorContainer(color);
		ColorContainer colorContainer4 = new ColorContainer(color);
		colorContainer.start();
		colorContainer1.start();
		colorContainer2.start();
		colorContainer3.start();
		colorContainer4.start();
	}
}

class Color {
	Lock myLock = new ReentrantLock();
	//	static String[] colors = new String[5];
	List<String> colors = new ArrayList<>();

	public void addColor(String newColor) {
		System.out.println("Acquiring lock with newColor: " + newColor);
		myLock.lock();
		System.out.println("Acquired the lock successfully");
		try {
			colors.add(newColor);
			System.out.println("Added a color " + newColor + " -----> Color list: " + colors);
		}
		finally {
			myLock.unlock();
		}
	}
}

class ColorContainer extends Thread {
	static int index;
	static final Object LOCK = new Object();
	/**
	 * Use atomic variables instead of explicit synchronizing of incrementing
	 * a counter.
	 * */
//	AtomicInteger atomicIndex = new AtomicInteger(0);
	static String[] colorArray = {"White", "Blue", "Gray", "Yellow", "Green"};
	final Color color;

	public ColorContainer(Color color) {
		this.color = color;
	}

	@Override
	public void run() {
		int i = getIndex();
		System.out.println("Index inside run: " + i);
		color.addColor(colorArray[i]);
	}

	int getIndex() {
//		synchronized (LOCK) {
//			return index++;
//		}
		/**
		 * This is the older observations: (Not deleting for documentation purposes)
		 * As you can see, getIndex() need not to have a synchronized block
		 * to be able to become atomic with its incrementation, that's because
		 * indirectly, ReentrantLock already handles the atomicity of this
		 * method. Here: Before the call to getIndex(),
		 * addColor is first called. Calling addColor from Color shared class
		 * has a ReentrantLock in it, that's why a pause is made to other
		 * threads accessing the same shared method (addColor()) in Color
		 * class. Making the incrementation of index is done atomically.
		 *
		 * Final analysis:
		 * getIndex() is not really synchronized, it just happens that concurrent threads
		 * accessing this class/shared variable "index" is being fetched from method area instead of
		 * creating a local copy of its own in its working memory via caching.
		 * Increment never fails to do its job (to increment) but not in an organized manner. As you can
		 * see, indexes could be on this kind of arrangement: 4, 0, 3, 2, 1. It's not the way we want to
		 * increment it, but it still does its job to pass an incremented value of index in a manner
		 * that every concurrent threads has a unique value of this index. If we want to retain the order
		 * of the colorArray, we would make the getIndex() to be synchronized.
		 * */
		System.out.println("Index: " + index);
        return index++;
	}
}