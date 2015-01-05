package com.ocpjp.package2;

import java.io.*;
import java.util.concurrent.Callable;

/**
 * @author Kev Obispo on 9/23/2014.
 */
public class ExceptionTopic {
	public static void main(String[] args) {
//		System.out.println("reached code! " + getResult());
		System.out.println(result());
	}

	private static String result() {
		final File file = new File("");
		try (FileInputStream fis = new FileInputStream(file);
		     FileOutputStream fout = new FileOutputStream(file)){
			System.out.println("Inside Try!");
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			System.out.println("Inside Finally!");
		}
		System.out.println("Outside try catch block");
		return "End String";
	}

//	private static String getResult() {
//		String result = "failed";
//		ExceptionTopic ex = new ExceptionTopic();
//		result = ex.persistFromXML();
//		return result;
//	}

//	protected String persistFromXML() {
//		return apply(applyPersistFromXML());
//	}
//
//	public <T> T apply(Callable<T> callable) {
//		try {
//			T result = callable.call();
//			return result;
//		} catch (Exception e) {
////			System.out.println("Inside apply");
//			throw new RuntimeException();
//		}
//	}
//
//	private Callable<String> applyPersistFromXML() {
//		return new Callable<String>() {
//			@Override
//			public String call() throws Exception {
//				final File file = new File("");
//				try (FileInputStream fis = new FileInputStream(file);
//				     FileOutputStream fout = new FileOutputStream(file)){
//
//				}
//				return "persisted";
//			}
//		};
//	}
}


