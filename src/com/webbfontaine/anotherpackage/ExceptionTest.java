package com.webbfontaine.anotherpackage;

/**
 * Created by Kev Obispo on 6/3/2014.
 */
class NewException extends Exception {}

class AnotherException extends Exception {}

public class ExceptionTest{
	public static void main(String[] args) throws Exception{
		try{
			System.out.println(1);
			m2();
		} catch (NewException e){
			System.out.println(2);
		}
		finally{
			System.out.println(3);
			throw new AnotherException();
		}

	}

	public static void m2() throws NewException {
		try {
			throw new NewException();
		} catch (Exception y) {
		} finally {
			System.out.println(4);
			throw new NewException();
		}
	}
}
