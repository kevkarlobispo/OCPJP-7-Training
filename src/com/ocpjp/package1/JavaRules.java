package com.ocpjp.package1;

import com.ocpjp.package2.Sample;

import java.util.*;
import java.util.concurrent.*;

/**
 * @author Kev Obispo on 11/12/2014.
 */

public class JavaRules {
    static {
        System.out.println("Static Initializer"); // printed always
    }
	public static void main(String[] args) {
        PL.method();
    }
}
class MyCallable implements Callable<String> {
    public String call() throws Exception {
        Thread.sleep(5000);
        return "DONE";
    }
}
class UseCallable {
    static void method()  throws InterruptedException, ExecutionException {
        ExecutorService es = Executors.newSingleThreadExecutor();
        Future<String> future = es.submit(new MyCallable());
        System.out.println(future.get()); // get() blocks until there is a value to return or there is an exception
        es.shutdownNow();
    }
}
class PL {
    static void method(String name, String... dob) {
        System.out.println("Inside method with varargs");
    }
    static void method(String name, Object dob) { // obviously, this method won
        System.out.println("Inside method with 2 simple strings");
    }
    static void method() {
        String str = "Kev";
        /*
        * varargs is the least to infer.
        * */
        method(str, str);
    }
}
class KJ {}
class LK extends KJ {
    void method() {
        KJ kj = new KJ();
        LK lk = (LK) new KJ(); // will actually compile but ClassCast.
    }
}
class Switch {
    void trySwitch() {
        String s = "a";
        switch (s) {
            default :
                System.out.println("default");
                break;
            case "a" :
                System.out.println("a"); // this will be printed
                break;
        }
    }
}
class Tricky1 {
	private static final Map<String, Integer> map = new HashMap<>();
	public static void insertToMap() {
		map.put("Kev", 1);
		System.out.println(getValue("Unavailable"));
	}
	private static int getValue(String s) {
		/*
		* Getting the value from the map returns null since no mapping.
		* Since null can't be assign to a primitive int type, NPE will be thrown at Runtime.
		* */
		return map.get(s);
	}
}

abstract class Type {
	class InstanceClass extends Type{
		@Override
		byte instanceMethod(int a) {
			/*
			* Explicit cast needed.
			* Returned values in primitives doesn't follow "covariance".
			* Declared return type of overriding methods that is using primitive types doesn't follow
			* covariance as well.
			*
			* return a // invalid or long, float, double
			**/
			return (byte) a;
		}
		int instanceMethod(byte a) {
			return a;
		}
	}
	abstract byte instanceMethod(int a);
	static void method() {
		class InnerClass { // access modifiers not applicable here
			private final int x = 1;
			public InnerClass() {} // any access modifiers are applicable on constructors
		}
		InnerClass in = new InnerClass();
		int x = in.x; // can access private members of local InnerClass class
//		new Type.InstanceClass(); // will not compile
	}
}
class SampleList extends ArrayList<Sample>{
	/*
	* This will not compile.
	* JVM steps to successfully declare a method to be overridden: Generic types
	* 1. Evaluates the erased type of all method parameters.
	* 2. If same, evaluates the compile-time types:
	*       If same: declare this officially as being overridden,
	*       If not:
	*          Check if type is Object.
	*              If yes: Compile-time error.
	*              If no: declare this as being one of the overloaded methods.
	*    If not: declare this as being one of the overloaded methods.
	*
	*    public boolean add(Object obj) {
	*	    return true;
	*	}
	* */
}
