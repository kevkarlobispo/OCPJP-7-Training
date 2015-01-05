package com.webbfontaine.trials;

/**
 * Created by Kev Obispo on 5/5/2014.
 */
class LastError<T> {
	private T lastError;
	void setError(T t){
		lastError = t;
		System.out.println("LastError: " + t);
	}
}
class StrLastError<S extends CharSequence> extends LastError<S>{
	public StrLastError(S s) {
	}

	void setError(S s){
		System.out.println("StrLastError: " + s);
	}
}
class Test {
	public static void main(String []args) {
		StrLastError<String> err = new StrLastError<>("Error");
		err.setError("Last error");
	}
}
