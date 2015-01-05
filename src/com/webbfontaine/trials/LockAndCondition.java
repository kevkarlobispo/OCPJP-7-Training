package com.webbfontaine.trials;

/**
 * Created by Kev Obispo on 5/20/2014.
 */
import java.util.concurrent.locks.*;
// This class simulates arrival of trains in a railway station.
class RailwayStation {
	private static Lock station = new ReentrantLock();
	private static Condition joeArrival = station.newCondition();
	static final class Train extends Thread {
		public Train(String name) {
			this.setName(name);
		}
		public void run() {
			station.lock();
			try {
				System.out.println(getName() + ": I've arrived in station ");
				if(getName().startsWith("IC1122")) {
					joeArrival.signalAll();
				}
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				station.unlock();
			}
		}
	}
	static final class WaitForJoe extends Thread {
		public void run() {
			System.out.println("Waiting in the station for IC1122 in which Joe is coming");
			station.lock();
			try {
				joeArrival.await();
				System.out.println("Pick up Joe and go home");
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
			finally {
				System.out.println("testing");
				station.unlock();
			}
		}
	}
	public static void main(String []args) throws InterruptedException {
		new WaitForJoe().start();
		new Train("IC1122 - Madrid to Paris").start();
		new Train("IC1234 - Paris to Munich").start();
		new Train("IC2211 - Paris to Madrid").start();
		new Train("IC4321 - Munich to Paris").start();
	}
}