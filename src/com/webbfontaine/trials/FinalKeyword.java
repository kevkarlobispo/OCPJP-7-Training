package com.webbfontaine.trials;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by Kev Obispo on 5/16/2014.
 */
public class FinalKeyword {

	private final List<String> loans = new ArrayList<>();

	public static void main(String[] args) {
		FinalKeyword f = new FinalKeyword();
		f.loans.add("home loan");  //valid
		f.loans.add("personal loan"); //valid
		System.out.println("f.loans = " + f.loans);
//		f.loans = new Vector(); //invalid
	}
}
