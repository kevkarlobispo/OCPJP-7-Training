package com.ocpjp.package2;

/**
 * @author Kev Obispo on 10/20/2014.
 */

public class CloneTopic implements Cloneable {

	String name;

	CloneTopic(String name) {
		this.name = name;
	}

	public static void main(String[] args) throws CloneNotSupportedException{
		CloneTopic collectionsTopic = new CloneTopic("Kev");
		CloneTopic clonedCloneTopic = (CloneTopic) collectionsTopic.clone();
		System.out.println(clonedCloneTopic);
		msg("1st == cloned: ", collectionsTopic == clonedCloneTopic); // false
	}
//
//	@Override
//	public CloneTopic clone(){ // Covariant return type and changed to public access modifier
//		CloneTopic c = null;
//		try {
//			c = (CloneTopic) super.clone();
//		} catch (CloneNotSupportedException e) {
//			msg("Cannot clone this object!", e);
//		}
//		return c;
//	}

	@Override
	public String toString() {
		return name;
	}

	private static void msg(String caption, Object obj) {
		System.out.println(caption + obj);
	}
}
