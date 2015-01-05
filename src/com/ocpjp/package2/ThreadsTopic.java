package com.ocpjp.package2;

/**
 * @author Kev Obispo on 10/7/2014.
 */

import java.util.HashSet;
import java.util.Set;

/**
 * Calling start() creates a "thread of execution".
 * Only wait() of all methods releases the lock of a monitor.
 * Calling start() on an already started Thread will throw IllegalThreadStateException.
 * wait(), join(), and sleep() throws InterruptedException.
 *
 * Calling wait, notify, and notifyAll must be inside a synchronized block and synchronized monitor's lock must be the
 * same with the instance used in wait, notify, and notifyAll. Else IllegalMonitorStateException.
 *
 * Calling wait() and notify() on a different object other than the one in the "synchronize" parameter will throw
 * IllegalMonitorStateException. But if it is an explicit this.wait() or this.notify(), or implicit wait() and notify()
 * with "this" will NOT throw IllegalMonitorStateException even though "this" is not the monitor being synchronized!
 *
 * If you have a thread class having synchronized methods, creating many instances
 * of it will have concurrent access to a same synchronized method in its own
 * class, because the synchronized methods are getting the lock of a monitor
 * from its "associated instance"!
 * Creating many instances of this thread will
 * of course create many distinct instances that's why it's like there is a
 * concurrent access with the synchronized methods of a Thread sub-class.
 *
 * Calling join() from a thread ensures that the thread on which join is called,
 * completes before the calling thread completes its execution.
 * */

 public class ThreadsTopic {
	public static void main(String[] args) {
		Friend ivan = new Friend();
		Friend andrew = new Friend();
		GoRafting goRafting = new GoRafting(ivan, "GoRafting 1");
		GoRafting goRafting1 = new GoRafting(ivan, "GoRafting 2");
		GoRafting goRafting2 = new GoRafting(ivan, "GoRafting 3");
		goRafting.start();
		goRafting1.start();
		goRafting2.start();
		/**
		 * If this ivan thread didn't start, all GoRafting threads will not execute its remaining code for execution,
		 * in run(). If the ivan thread is the one being synchronized and wait in GoRafting class.
		 * */
		ivan.start();
		andrew.start(); // start unrelated friend instance
	}
}

class Friend extends Thread {
	static boolean reached;
	/**
	 * if LOCK is an instance variable, "2 thread of executions" will have different "LOCK" instances
	 * in its heap memory. Hence, synchronizing of the said object will not be accomplished in a supposed manner.
	 * TAKE NOTE OF THAT! VERY TRICKY!
	 * */
	static final Object LOCK = new Object();
	static int x = 1;

	@Override
	public void run() {
		tryToNotifyNonStatic();
	}

	private synchronized void tryToNotifyNonStatic() {
		/**
		 * If not synchronized by this with a  LOCK object, "2 running Friend thread instances" will
		 * have concurrent access to "different instance of this method" because this class is a Thread Class.
		 * It's like there are two copies of this method, because this method is a part of a Thread type of class!
		 * */
 		synchronized (GoRafting.LOCK) {
			try {
				Thread.sleep(2000);
				reached = true;
				System.out.println(x++);
				notifyAll();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * In this case, you used "instance" of Friend object(ivan)
	 * while ivan executes, it goes through a static synchronized method wherein
	 * it gets a monitor lock from the Friend "Class" object (not from the instance
	 * of ivan).
	 *
	 * notify() is not called. Because this is a static block.
	 * If Friend.class.notify() is used here, it is not assured that one of GoRafting threads will be notified since
	 * it "aims" for owning the lock of the monitor of an ivan instance (not a Friend.class instance).
	 * */
	private static synchronized void tryToNotifyStatic() {
		try {
			reached = true;
			System.out.println(x++);
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class GoRafting extends Thread {
	static final Set LOCK = new HashSet<>();
	final Friend friend;

	GoRafting(final Friend target, String name) {
		super(name);
		friend = target;
	}

	@Override
	public void run() {
		/**
		 * Note: For some reason, if an instance of a thread (friend instance here) is the one being synchronized.
		 * Not calling notify() on that thread class (Friend class) will STILL notify "the waiting object"
		 * (friend instance here). If the  waiting object is not a thread instance, it will not be notified if NOT NOTIFIED!.
		 * */
		synchronized (LOCK) {
			try {
				/**
				 * Since wait() releases the lock of friend object's monitor, 2 more GoRafting instances will most likely
				 * get the chance of having the "friend's monitor lock". Probable output:
				 * println will immediately execute 3 times! Even though there is a sleep().
				 * */
				System.out.println(Thread.currentThread().getName());
				/**
				 * If notify() or notifyAll() is "not called" from a friend instance,
				 * it will just wait to finish its execution (like a join())
				 * (will not have a full control).
				 * */
				LOCK.wait(); // wait() RELEASES THE LOCK OF A MONITOR!
				Thread.sleep(2000);
 			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("Friend reached: " + Friend.reached + ", going rafting");
	}
}
