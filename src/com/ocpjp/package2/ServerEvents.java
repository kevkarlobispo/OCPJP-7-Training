package com.ocpjp.package2;

import java.nio.file.Path;
import java.nio.file.WatchEvent;

/**
 * @author  by Kev Obispo on 9/9/2014.
 */
public enum ServerEvents implements WatchEvent.Kind<Path>{

	OP_VIEW("View", Operation.INTERNAL_EVENTS_MAX + 1 ),
	OP_STORE("Store", Operation.INTERNAL_EVENTS_MAX + 2 ),
	OP_MODIFY_STORED("Modify Stored Data", Operation.INTERNAL_EVENTS_MAX + 3 ),
	OP_MODIFY("Modify Data", Operation.INTERNAL_EVENTS_MAX + 4 );

	private String name;
	private int number;

	ServerEvents(final String name, final int number) {
		this.name = name;
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public int getNumber() {
		return number;
	}

	@Override
	public Class<Path> type() {
		return null;
	}
}

class Operation {
	public static int INTERNAL_EVENTS_MAX = 50;
}



enum Level {;
	Level(){
		System.out.println("constructor");
	}
	public static void main(String... args){
		System.out.println(ServerEvents.OP_STORE.getNumber());
		System.out.println(ServerEvents.OP_VIEW.getName());
		System.out.println(ServerEvents.OP_MODIFY.getName());
		System.out.println(ServerEvents.OP_MODIFY_STORED.getName());
	}
}