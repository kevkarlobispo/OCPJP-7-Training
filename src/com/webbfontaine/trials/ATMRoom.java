package com.webbfontaine.trials;

import java.util.concurrent.Semaphore;

/**
 * Created by Kev Obispo on 5/19/2014.
 */
final class ATMRoom {
	public static void main(String []args) {
// assume that only two ATM machines are available in the ATM room
		final Semaphore machines = new Semaphore(2);
// list of people waiting to access the machine
		new Person(machines, "Mickey");
		new Person(machines, "Donald");
		new Person(machines, "Tom");
		new Person(machines, "Jerry");
		new Person(machines, "Casper");
	}
}
// Each Person is an independent thread; but their access to the common resource
// (two ATM machines in the ATM machine room in this case) needs to be synchronized.
final class Person extends Thread {
	private Semaphore machines;
	public Person(Semaphore machines, String name) {
		this.machines = machines;
		this.setName(name);
		this.start();
	}

	public void run() {
		try {
			System.out.println(getName() + " waiting to access an ATM machine");
			machines.acquire();
			System.out.println(getName() + " is accessing an ATM machine");
			Thread.sleep(10000); // simulate the time required for withdrawing amount
			System.out.println(getName() + " is done using the ATM machine");
			machines.release();
		} catch(InterruptedException ie) {
			System.err.println(ie);
		}
	}
}
