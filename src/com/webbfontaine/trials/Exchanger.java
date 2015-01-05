package com.webbfontaine.trials;

import java.util.concurrent.Exchanger;

/**
 * Created by Kev Obispo on 5/19/2014.
 */
final class DukeThread extends Thread {
	private Exchanger<String> sillyTalk;
	public DukeThread(Exchanger<String> args) {
		sillyTalk = args;
	}

	public void run() {
		String reply;
		try {
			reply = sillyTalk.exchange("Knock knock!");
			System.out.println("CoffeeShop: " + reply);
			reply = sillyTalk.exchange("Duke");
			System.out.println("CoffeeShop: " + reply);
			reply = sillyTalk.exchange("The one who was born in this coffee shop!");
		} catch(InterruptedException ie) {
			System.err.println("Got interrupted during my silly talk");
		}
	}
}

final class CoffeeShopThread extends Thread {
	private Exchanger<String> sillyTalk;
	public CoffeeShopThread(Exchanger<String> args) {
		sillyTalk = args;
	}
	public void run() {
		String reply = null;
		try {
			reply = sillyTalk.exchange("Who's there?");
			System.out.println("Duke: " + reply);
			reply = sillyTalk.exchange("Duke who?");
			System.out.println("Duke: " + reply);
			reply = sillyTalk.exchange("");
			System.out.println("Duke: " + reply);
		} catch(InterruptedException ie) {
			System.err.println("Got interrupted during my silly talk");
		}
	}
}

final class KnockKnock {
	public static void main(String []args) {
		Exchanger<String> sillyTalk = new Exchanger<>();
		CoffeeShopThread cof = new CoffeeShopThread(sillyTalk);
		cof.setPriority(10);
		cof.start();
		DukeThread duk = new DukeThread(sillyTalk);
		duk.setPriority(1);
		duk.start();
	}
}