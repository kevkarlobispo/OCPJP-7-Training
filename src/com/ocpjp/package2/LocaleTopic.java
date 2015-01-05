package com.ocpjp.package2;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.util.*;

/**
 * @author Kev Obispo on 10/14/2014.
 */


/**
 * Locale.Builder Class
 *  - utility class can be used to construct a Locale object that conforms to the IETF BCP 47 syntax
 * Locale Constructors
 * Locale.forLanguageTag Factory Method
 * Locale Constants
 *
 * ResourceBundle topic
 * getBundle is overloaded:
 *      method parameters:
 *      (String)
 *      (String, Locale)
 * If you change any key or value used with {@link ListResourceBundle}, you must recompile at least one class file.
 * If you change any key or value defined in .properties files, you might not recompile any class file.
 *
 * @see Locale
 * @see ResourceBundle
 * @see ListResourceBundle
 * @see PropertyResourceBundle
 */

public class LocaleTopic {
	public static void main(String[] args) {
		msg("Default Locale: " + Locale.getDefault());
//		Locale.setDefault(Locale.ITALY); // if uncomment, defaultLocale will be changed
//		Locale locale = Locale.getDefault();
		Locale locale = new Locale("fr", "FR");


		msg("Set Default Locale: " + locale);

		msg("Code Country: " + locale.getCountry());
		msg("Code Language: " + locale.getLanguage());
		msg("Display Country: " + locale.getDisplayCountry());
		msg("Display Language: " + locale.getDisplayLanguage());
		msg("Display Name: " + locale.getDisplayName());
		msg("Extension: " + locale.getExtension('u'));
		msg("");

		Locale[] locales = Locale.getAvailableLocales();
		msg(Arrays.toString(locales));

		/**
		 * Using incorrect language and country will not make error in compilation
		 * */
		Locale locale2 = new Locale("fee");
		msg("Display Country: " + locale2.getDisplayCountry());
		msg("Display Language: " + locale2.getDisplayLanguage());
		msg("Display Name: " + locale2.getDisplayName());
		msg("");

		Locale locale1 = new Locale("th", "th", "TH");
		msg("Code Country: " + locale1.getCountry());
		msg("Code Language: " + locale1.getLanguage());
		msg("Display Country: " + locale1.getDisplayCountry());
		msg("Display Language: " + locale1.getDisplayLanguage());
		msg("Display Name: " + locale1.getDisplayName());
		msg("Extension: " + locale1.getExtension('u'));
		msg("");

		Locale aLocale = new Locale.Builder().setLanguage("fr").setRegion("CA").build();
		msg("Code Country: " + aLocale.getCountry());
		msg("Code Language: " + aLocale.getLanguage());
		msg("Display Country: " + aLocale.getDisplayCountry());
		msg("Display Language: " + aLocale.getDisplayLanguage());
		msg("Display Name: " + aLocale.getDisplayName());
		msg("Extension: " + aLocale.getExtension('u'));
		msg("");

		Locale aLocale2 = new Locale("fr");
		msg("Code Country: " + aLocale2.getCountry());
		msg("Code Language: " + aLocale2.getLanguage());
		msg("Display Country: " + aLocale2.getDisplayCountry());
		msg("Display Language: " + aLocale2.getDisplayLanguage());
		msg("Display Name: " + aLocale2.getDisplayName());
		msg("Extension: " + aLocale2.getExtension('u'));
		msg("");

		/**
		 * As you will see, bLocale and cLocale has the same Locale instance even though
		 * the case of the letters differ.
		 *
		 * Case doesn't matter on Locale but case matters when used in ResourceBundle suffixing.
		 * */

		Locale bLocale = new Locale("ES", "es");
		msg("Code Country: " + bLocale.getCountry());
		msg("Code Language: " + bLocale.getLanguage());
		msg("Display Country: " + bLocale.getDisplayCountry());
		msg("Display Language: " + bLocale.getDisplayLanguage());
		msg("Display Name: " + bLocale.getDisplayName());
		msg("Extension: " + bLocale.getExtension('u'));
		msg("");

		Locale cLocale = new Locale("es", "ES");
		msg("Code Country: " + cLocale.getCountry());
		msg("Code Language: " + cLocale.getLanguage());
		msg("Display Country: " + cLocale.getDisplayCountry());
		msg("Display Language: " + cLocale.getDisplayLanguage());
		msg("Display Name: " + cLocale.getDisplayName());
		msg("Extension: " + cLocale.getExtension('u'));
		msg("");

		Locale dLocale = new Locale("ABC", "abc");
		msg("Code Country: " + dLocale.getCountry());
		msg("Code Language: " + dLocale.getLanguage()); // transformed into LOWER case!
		msg("Display Country: " + dLocale.getDisplayCountry()); // transformed into UPPER case!
		msg("Display Language: " + dLocale.getDisplayLanguage());
		msg("Display Name: " + dLocale.getDisplayName());
		msg("Extension: " + dLocale.getExtension('u'));
		msg("");
	}

	private static void loadResourceBundle() {
		ResourceBundle bundle = ResourceBundle.getBundle("SampleBundle");
		ResourceBundle bundle2 = ResourceBundle.getBundle("SampleBundle", Locale.UK);
	}

	private static void msg(String msg) {
		System.out.println(msg);
	}
}


