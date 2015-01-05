package com.ocpjp.package2;

import java.util.Formatter;

/**
 * @author Kev Obispo on 9/21/2014.
 */
public class FormatterTopic {
	public static void main(String[] args) {
		String sampleString = "Kev";

		Formatter formatter = new Formatter();
		formatter.format("Amt [%-10f]", 12.12345);
		/**
		 * If not use new Formatter, calling format each time concatenates the previous format
		 * */
		/*
		* If argument is positive, "+" will take precedence, else "(" will take precedence
		* */
 		System.out.println(formatter.format("Amt [%2$+(020.4f]", 12.12345, -1.1)); // extra arguments are ignored!
		System.out.println(formatter.format("Amt [%-20f]", 12.12345));
//		System.out.println(formatter.format("Amt [%10.2f]", 12)); // int can't be assign to a float format
		System.out.println(formatter.format("Amt [%,(f]", 123456789.12345));
		System.out.println(new Formatter().format("%-5c", 'c'));
		System.out.println(new Formatter().format("%5b", true));

		double num1 = 7.12345678;
		int num2 = (int)8.12345678;
	}
	/*
	* java.util.Formatter
	* - Interpreter for printf-style format strings
	* - provides support for layout justification and alignment
	*
	* Formatting parameters: %b %c %d(integer) %f(float and double) %s
	* Using overloaded constructors will send formatted data to that file.
	* */

	/*
	* CONVERSION FORMAT SPECIFIERS:
	* General form: %[argument_index$][flags][width][.precision]conversion char
	*
	* Numeric Specifiers:
	*   %d - accepts  literal values or variables of type byte, short, int, long,
	 *       Byte, Short, Integer or Long
	 *     - will throw runtime exceptions for all other type of values.
	*   %f - by default, prints six digits after the decimal.
	* Other:
	*   %b - if null, false; otherwise "true"
	*      - accepts any type of variable-primitive or object reference
	*   %c - outputs the result as a Unicode character.
	*      - If, you pass on object of any other type, IllegalFormatConversionException
	*      - You can only pass literals and variables of type char or Character to
	 *       specifier %c.
	*   %s - accepts any type of variable-primitive or object reference
	*      - if primitive, actual value; if object reference, uses toString()
	*
	* argument_index - marks who comes first; sample: %1$d, %2$s
	* flags: (for numeric specifiers only!)
	*   (Can appear in any order) - throws IllegalFormatFlagsException if two flags were used! Except ,(
	*    +   - Include a sign (+ or -) with this argument. Only for %d or %f
	*    0   - Pad this argument with zeroes - Must specify width as well. If not, it throws MissingFormatWidthException
	*    -   - Left justify this argument - Must specify width as well. If not, it throws MissingFormatWidthException
	*    (   - Enclose negative numbers in parentheses
	*    ,   - Use locale-specific grouping separators (that is, the comma in 123,456)
	* width: non-negative decimal integer  indicating the minimum
	*        number of characters
	* .precision: decimal digits precision
	*           - IllegalFormatPrecisionException if used with %d
	*
	* */

// Notes in Enthuware:
//	a. "-" Left justify this argument - Must specify width as well.
//	b. "+" Include a sign (+ or -) with this argument - Applicable only if conversion char is d or f (i.e. for numbers).
//	c. "0" Pad this argument with zeroes  - Applicable only if conversion char is d or f (i.e. for numbers). Must specify width as well.
//	d. "," Use locale-specific grouping separators (i.e., the comma in 123,456)  - Applicable only if conversion char is d or f (i.e. for numbers).
//	e. "(" Enclose negative numbers in parentheses  - Applicable only if conversion char is d or f (i.e. for numbers).

	/*
	*  If there is a mismatch in the number of arguments that are passed to the
	*  method format and the number of times %b appears in the format String.
	*  If the number of arguments exceeds the required count,
	*  the extra variables are quietly ignored by the compiler
	*  and JVM.
	*
	*  However, if the number of the required arguments fall short, the JVM
	*  throws the following exception at runtime:
	*
	*  MissingFormatArgumentException
	*/
 }
