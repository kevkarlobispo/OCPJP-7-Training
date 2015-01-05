package com.webbfontaine.trials;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Kev Obispo on 5/7/2014.
 */
public class RegularExpression{

	public static void main(String[] args) {
		String str = "/Danny Doo, Flat no 502, Big Apartment, Wide Road, Near Huge Milestone, " +
				"Hugo-city 56010, Ph: 9876543210, Email: danny@myworld.com. Maggi Myer, Post bag no " +
				"52, Big bank post office, Big bank city 56000, ph: 9876501234, Email: maggi07@myuniverse.com.";
		String str2 = ",192.126.123.122,";
		Pattern pattern = Pattern.compile("\\.");
		Matcher matcher = pattern.matcher(str);
		while (matcher.find()) {
			System.out.println(matcher.group());
		}
		int x = ConstantValues.x;

//		String ipStr1 = "word nonword word3"; // valid IP address
//		validateIP(ipStr1);
	}

	private static void validateIP(String ipStr) {
		String regex = "\\w+\\D\\b";
//		String regex = "\\b((25[0–5]|2[0–4]\\d|[01]?\\d\\d?)(\\.)){3}(25[0–5]|2[0–4]\\d|[01]?\\d\\d?)\\b";
//		System.out.println(ipStr + " is valid? " + Pattern.matches(regex, ipStr));
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(ipStr);
		while (matcher.find()) {
			System.out.println(matcher.group());
		}
	}
}

interface ConstantValues {
	int x = 1;
}