package com.ocpjp.package2;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Kev Obispo on 10/14/2014.
 */

/**
 * Unlike Formatter, using the same instance of any
 * @see java.text.Format objects doesn't refer to its initial value. Meaning, a used instance of a Format object
 * will not save
 * */
public class TextNumberFormatTopic {
	public static void main(String[] args) throws Exception {
		tryNumberFormatTopic();
	}

	private static void tryCurrencyTopic() throws Exception{
		/**
		 * This example will throw ParseException unless you put "$" to the String you want to parse
		 *
		 * NOTE: the parsing technique of the NumberFormat and DateFormat instances relies heavily to
		 * the pattern it has on the creation of the instance.
		 * */
		double num = 12345.1111;
		/**
		 * As you can see, even though this fakeGB isn't a correct Locale, creating a currency instance
		 * of nf2 will only look on the region/country pattern to distinguish the correct/desired currency
		 * instance. It will actually not disregard language parameter, it will just not be able to identify the
		 * most correct currency instance/currency symbol in this example
		 * */
		Locale fakeGB = new Locale("rr", "it"); // rr is not a correct language
		System.out.println(fakeGB.getDisplayLanguage());
		NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
		NumberFormat nf2 = NumberFormat.getCurrencyInstance(fakeGB);
		System.out.println(nf.format(num));
		System.out.println(nf2.format(num)); // if language argument is correct, say it/IT for Italian, this will print € 12.345,11
		System.out.println(nf.parse("$" + Double.toString(num)));
	}

	private static void tryNumberFormatTopic() throws Exception{
		double num = -12345.1111;
		double num2 = 54321.1111;
		float num3 = 1;

		final NumberFormat nfDefault = NumberFormat.getInstance(); // same with getNumberInstance()
//		final NumberFormat nfDefault = NumberFormat.getPercentInstance();
		msg(nfDefault.format(num));
		msg(nfDefault.format(num3));
		/**
		 * If argument of parse or parseObject is not a number or is blank, {@link java.text.ParseException} would be thrown
		 **/
		msg(nfDefault.format(nfDefault.parse("100,00"))); // method chained!
		List<String> list = new ArrayList<String>(){
			{
				add(nfDefault.format(nfDefault.parse("100,00")));
			}
		};
		list.add(nfDefault.format(num));
		list.add(nfDefault.format(num3));
		System.out.println("List: " + list);
	}

	private static void msg(String msg) {
		System.out.println(msg);
	}
}

class Lag {
	Lag() {
		System.out.println("Inside Lag constructor");
	}

	static class LagStatic { // not related to its outer class
		static {
			System.out.println("Inside LagStatic static Initializer");
		}
	}

	static {
		System.out.println("Inside Lag static Initializer");
	}
}