package com.ocpjp.package2;

import java.io.StringReader;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author Kev Obispo on 9/22/2014.
 */

/**
 * Scanner is used to tokenize simple text. Behaves more likely of split() in String.
 * */

public class ScannerTopic {
	public static void main(String[] args) {
		Scanner scanner = new Scanner("Th emeXt heirX carpe . t77BB$theCC").useDelimiter("[\\sA-Za-z]+");
		/**
		 * Although the construction of this constructor is satisfactory, this constructor is private to the Scanner class.
		 * */
//		Scanner scanner = new Scanner(new StringReader("Th emeXt heirX carpe . t77BB$theCC"), Pattern.compile("[\\sA-Za-z]+"));
		/**
		 * Doesn't put the tokenized values if already been used!
		 * */
		while (scanner.hasNextInt()) {
			System.out.println("Int: " + scanner.nextInt());
		}
		System.out.println(scanner.findInLine("[\\d]{0,5}"));
		while (scanner.hasNext()) {
			System.out.println("Any character: " + scanner.next());
		}

		String eJava = "e Java Guru";
		System.out.println("String length: " + eJava.length());
		System.out.println("substring 11: [" + eJava.substring(11) + "]"); // equivalent to empty string
	}
}
