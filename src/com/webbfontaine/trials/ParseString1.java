package com.webbfontaine.trials;

/**
 * Created by Kev Obispo on 5/6/2014.
 */
public class ParseString1 {
	public static void main(String[] s) {
		String quote = "Never lend books-nobody ever returns them!";
		String [] words = quote.split(" "); // split strings based on the delimiter " " (space)
		for (String word : words) {
			System.out.println(word);
		}
	}
}
