package com.ocpjp.package2;

import java.io.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Kev Obispo on 11/17/2014.
 */

public class SerializationTopic {
	public static final String FILE_REVISION = "$Revision$";
	public static void main(String ar[]) throws Exception {
		Dog dOut = new Dog(20, 2);

		System.out.println("DOut Weight: " + dOut.weight + " Size: " + dOut.c.size);

		FileOutputStream fo = new FileOutputStream("C:/Users/Dev8/Downloads/streams/Dog.ser");
		ObjectOutputStream os = new ObjectOutputStream(fo);
		os.writeObject(dOut);

		FileInputStream fi = new FileInputStream("C:/Users/Dev8/Downloads/streams/Dog.ser");
		ObjectInputStream is = new ObjectInputStream(fi);
		Dog dIn = (Dog) is.readObject();

		System.out.println("DIn Weight: " + dIn.weight + " Size: " + dIn.c.size);
	}
}
class Collar implements Serializable{
	private static final long serialVersionUID = 1;
	protected int size;
}
abstract class Animal {
	Collar c;
	enum State {
		NEW, INITIALIZING, INITIALIZED
	}

	private final AtomicReference<State> init = new AtomicReference<>(State.NEW);

	static {
		System.out.println("Inside Animal static initializer..."); // will be executed as well during deserialization.
	}

//	protected Animal() {
//		/*
//		* For the "c.size" in Dog.readObject() to not throw NullPointerException during Deserialization,
//		* we should do the initialization of c here in Dog's superclass.
//		* Since the superclass Animal is not Serializable, Animal's non-args constructor is being
//		* invoked during the Deserialization of a Dog instance.
//		* */
//	}

	/**
	 * final non-access modifier should be used here
	 * to negate polymorphism
	 * */
	protected final void initialize(Collar c) {
		if (!init.compareAndSet(State.NEW, State.INITIALIZING)) {
			throw new IllegalStateException("Already initialized!");
		}
		this.c = c;
		init.set(State.INITIALIZED);
		System.out.println("init was set to Initialized!");
	}

	private void checkInit() {
		if (init.get() != State.INITIALIZED) {
			throw new IllegalStateException("Uninitialized!");
		}
	}
}
class Dog extends Animal implements Serializable {
	int weight;
	/*
	* Since Collar is not defined as being Serializable, not putting a non access modifier transient
	* here will throw an NotSerializableException(subclass of IOException) at runtime.
	* */

	private static final long serialVersionUID = 1;

	public Dog(int weight, int size) {
		this.weight = weight;
		c = new Collar();
		c.size = size;
	}
	private void writeObject(ObjectOutputStream os) throws IOException {
		os.defaultWriteObject();
		os.writeObject(c);
		os.writeInt(weight);
	}
	private void readObject(ObjectInputStream is) throws IOException, ClassNotFoundException {
		is.defaultReadObject();
//		c = new Collar(); use the c from Animal instead.
		initialize((Collar) is.readObject());
		weight = is.readInt();
	}
}
