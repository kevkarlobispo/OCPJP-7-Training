package com.ocpjp.package1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kev Obispo on 12/2/2014.
 */

public class AnnotationsTopic {
	public static void main(String[] args) throws Exception{
		int tests = 0;
		int passed = 0;

		Class testClass = Class.forName("com.ocpjp.package1.SampleTS");
		for (Method m : testClass.getDeclaredMethods()) {
			if (m.isAnnotationPresent(Test.class)) {
				tests++;
				try {
					m.invoke(null);
					passed++;
				} catch (InvocationTargetException wrappedExc) {
					Throwable cause = wrappedExc.getCause();
					System.out.println(m + " failed: " + cause);
				} catch (Exception exc) {
					System.out.println("Invalid @Test: " + m);
				}
			}
		}
		System.out.printf("Passed: %d, Failed: %d", passed, tests - passed);
		AnnotationsTopic annotationsTopic = new AnnotationsTopic();
	}
}

class SampleTS {
	@Test public static void m1() {}  // test should PASS
	public static void m2() {}
	@Test public static void m3() {  // test should FAIL
		throw new RuntimeException("Boom");
	}
	public static void m4() {}
	@Test public void m5() {} // INVALID USE: nonstatic method
	public static void m6() {}
	@Test public static void m7() {  // test should FAIL
		throw new RuntimeException("Crash");
	}
	public static void m8() {}
}
