package com.ocpjp.package3;

import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Kev Obispo on 10/11/2014.
 */

public class ConcurrencyTopic2 {
	public static void main(String[] args) throws InterruptedException{
		Rainbow rainbow = new Rainbow();
		RainbowGetter rainbowGetter = new RainbowGetter(rainbow);
		RainbowGetter rainbowGetter1 = new RainbowGetter(rainbow);
		RainbowGetter rainbowGetter2 = new RainbowGetter(rainbow);
		RainbowSetter rainbowSetter = new RainbowSetter(rainbow);
		rainbowSetter.start();
		rainbowGetter.start();
		rainbowGetter1.start();
		rainbowGetter2.start();
	}
}

class RainbowSetter extends Thread {

	private static String[] colors = {"A", "B", "C", "D", "E", "F", "G", "H",
			"I", "J"};
	static int index;
	static final Object LOCK = new Object();
	private final Rainbow rainbow;

	RainbowSetter(Rainbow rainbow) {
		this.rainbow = rainbow;
	}

	@Override
	public void run() {
		int x;
		while ((x = getIndex()) < colors.length) {
			rainbow.addColor(colors[x]);
		}
	}

	int getIndex() {
		synchronized (LOCK) {
			return index++;
		}
	}
}

class RainbowGetter extends Thread {

	private final Rainbow rainbow;

	RainbowGetter(Rainbow rainbow) {
		this.rainbow = rainbow;
	}

	@Override
	public void run() {
		System.out.println("List from an anonymous Rainbow Getter: " + rainbow.display());
	}
}

class Rainbow {
	private final ReadWriteLock myLock = new ReentrantReadWriteLock();
	private static int pos;
	private static Map<Integer, String> colors = new HashMap<>();

	public void addColor(String newColor) {
		myLock.writeLock().lock();
		try {
			colors.put(++pos, newColor);
		}
		finally {
			myLock.writeLock().unlock();
		}
	}

	public List<String> display() {
		myLock.readLock().lock();
		try {
			List<String> list = new ArrayList<>();
			list.addAll(colors.values());
			return list;
		}
		finally {
			myLock.readLock().unlock();
		}
	}
}