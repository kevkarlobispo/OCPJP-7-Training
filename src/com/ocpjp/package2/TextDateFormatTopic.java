package com.ocpjp.package2;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Kev Obispo on 10/15/2014.
 */

/**
 * parse always throw ParseException
 *
 * Legend 1:
 * Formatting of date is only a way to be able to show to a console an specific format, not showing
 * the raw format of a date wherein it shows all. Date on the other hand is only a representation
 * of the system date that's why trying to format a "Date" as a date instance is impossible.
 * */
public class TextDateFormatTopic {
	public static void main(String[] args) throws ParseException {
		String date1 = "20140101070000";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddkkmmss");
		/**
		 * Customized pattern must be set first during instantiation of SimpleDateFormat through its constructor
		 * to be able to "parse" correctly the String we want to parse, else ParseException will be thrown
		 * because the date instance could not be create correctly
		 * */
		System.out.println(simpleDateFormat.parse(date1)); // the system date! Regardless of the pattern
		System.out.println(simpleDateFormat.format(simpleDateFormat.parse(date1)));
		System.out.println();

		DateFormat df = DateFormat.getInstance(); // df and df5 shares common pattern
		DateFormat df5 = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
		DateFormat df2 = DateFormat.getDateInstance();
		DateFormat df3 = DateFormat.getDateInstance(DateFormat.SHORT);
		DateFormat df4 = DateFormat.getDateInstance(DateFormat.FULL);
		System.out.println("getInstance()                    : " + df.format(new Date())); // 10/16/14 7:58 PM
		System.out.println("getDateInstance()                : " + df2.format(new Date()));// 10 16, 14
		System.out.println("getDateInstance(DateFormat.SHORT): " + df3.format(new Date()));// 10/16/14
		System.out.println("getDateInstance(DateFormat.FULL) : " + df4.format(new Date()));// Thursday, October 16, 2014
		System.out.println("df.parse                         : " + df.parse("10/16/14 7:54 PM")); // the system date!
		System.out.println("df5.parse                        : " + df5.parse("10/16/14 7:54 PM")); // the system date!
		System.out.println();

		/**
		 * Legend 1: Sample
		 * */
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		String dateAsString = simpleDateFormat1.format(new Date());
		System.out.println(dateAsString);
		Date date = simpleDateFormat1.parse(dateAsString);
		System.out.println(date);
		System.out.println();

		/**
		 * Using "''" single quotes to avoid some text interpretation
		 * Here, I used it for 'Day'  ', '  'th'
		 * */
		SimpleDateFormat sf = new SimpleDateFormat("'Day' EE', 'dd'th' MMM yyyy");
		System.out.println(sf.format(new Date()));

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		System.out.println("Current date time in a Calendar instance: " + calendar.getTime());
	}
}

/**
 *
 * Date and Time Patterns
 *  G	Era designator	    Text	AD
 *  y	Year	            Year	1996; 96
 *  Y	Week year	        Year	2009; 09
 *  M	Month in year	    Month	July; Jul; 07
 *  w	Week in year	    Number	27
 *  W	Week in month	    Number	2
 *  D	Day in year	        Number	189
 *  d	Day in month	    Number	10
 *  F	Day of week in month	Number	2
 *  E	Day name in week	Text	Tuesday; Tue
 *  u	Day number of week (1 = Monday, ..., 7 = Sunday)	Number	1
 *  a	Am/pm marker	    Text	PM
 *  H	Hour in day (0-23)	Number	0
 *  k	Hour in day (1-24)	Number	24
 *  K	Hour in am/pm (0-11)	Number	0
 *  h	Hour in am/pm (1-12)	Number	12
 *  m	Minute in hour	    Number	30
 *  s	Second in minute	Number	55
 *  S	Millisecond	        Number	978
 *  z	Time zone	        General time zone	Pacific Standard Time; PST; GMT-08:00
 *  Z	Time zone	        RFC 822 time zone	-0800
 *  X	Time zone	        ISO 8601 time zone	-08; -0800; -08:00
 *
 * */
