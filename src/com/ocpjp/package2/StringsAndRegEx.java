package com.ocpjp.package2;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Kev Obispo on 9/6/2014.
 */

class StringsAndRegEx {
	public static void main(String[] args) {
		String s = "oboo:and:foo";
		String[] array = s.split("o", -10);
		String[] array1 = s.split("o", -2);
		String[] array2 = s.split("o", -1);
		String[] array3 = s.split("o", 5);
		System.out.println(Arrays.toString(array));
		System.out.println(Arrays.toString(array1));
		System.out.println(Arrays.toString(array2));
		System.out.println(Arrays.toString(array3));

		List<String> list = new ArrayList<>();
		list.add("kev");
		list.add("kev3");
		list.add("kev4");
		String[] strArr = new String[2];
		strArr[0] = "Errors: ";
		for (String s1 : list) {
			strArr[0] += "; " + s1;
		}
		System.out.println("strArr = " + strArr[0]);

		String targetString = "b234a bA6Z abc ";
		String regex = "/\\*[^\\/]*?Copyrights[\\s\\S]*?\\*/";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(targetString);
		while (matcher.find()) {
			System.out.println(matcher.group() + " starts at " + matcher.start() + ", ends at " + matcher.end());
		}

		String str = "Kev is a great Kev Java programmer";
		System.out.println(Arrays.toString(str.split(" ")));
		String str2 = str.replace(new StringBuilder("Kev"), "Regine");  // Matches same exact string value
		String str3 = str.replaceAll("e.t\\b", "EAT"); // accepts a regex as method parameter
		System.out.println("str2 = " + str2);
		System.out.println("str3 = " + str3);

		System.out.println(str.matches("^K.*r$")); //true


		/*
		indexOf - lots of overloaded methods
		lastIndexOf - lots of overloaded methods
		-> returns -1 if invalid
		*/

		/*
		contains(CharSequence)
		-> returns boolean
		*/

		/*
		* String's matches(String regex)
		* uses Pattern.matches
		*           uses m.matches
		* */

		/*
		subSequence(int begIndex, int endIndex) - calls subString behind
		the scene.
		subString(int begIndex)
		subString(int begIndex, int endIndex)
		-> throws StringIndexOutOfBoundsException if invalid.
		*/

		/*
		split(String regex) - returns String[]
		split(String regex, int limit) - if "limit" is 2,
		regex pattern is matched only once.
		*/
		/*
		Doesn’t contain the values that it matches to split the
		target String.
		*/

		/*
		replace(char,char)
		replace(CharSequence,CharSequence)
		replaceAll(String regex, String new)
		replaceFirst(String regex, String new)
		*/


		/*
		Regular Expressions:

		Character Classes:
		/d  digits
		/D  non-digits
		/s  space (white space)
		/S  non-space
		/w  word character
		/W  non-word character

		Boundary Classes:
		/b  word boundary
		/B  non-word boundary
		^   Beginning of the line
		$   End of the line

		Quatifiers:
		X?  One or non at all
		X*  0 or more
		X+  1 or more
		X{min,max}  range
		 */
	}
}