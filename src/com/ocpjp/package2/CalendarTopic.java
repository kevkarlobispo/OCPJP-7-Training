package com.ocpjp.package2;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Kev Obispo on 11/7/2014.
 */

public class CalendarTopic {
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		Date toDate = cal.getTime();
		cal.add(Calendar.MONTH, -1);
		Date fromDate = cal.getTime();

		System.out.println(fromDate);
		System.out.println(toDate);
	}
}
