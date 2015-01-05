package com.ocpjp.package1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * @author Kev Obispo on 10/20/2014.
 */

public class OOPprinciples {
    static String[] sa = { "charlie", "bob", "andy", "dave" };
    public static void main(String[] args) {
//        System.out.println(Arrays.binarySearch(sa, "charlie"));
//        H.sampleAnonymousWithInitializationParams().printName();
	    Calendar calendar = Calendar.getInstance();
	    calendar.set(1994, Calendar.JANUARY, 31);
	    System.out.println(calendar.getTime());
    }
}
class SwitchWithForLoop {
	static void sampleSwitchWithForLoop() {
		List<String> strList = new ArrayList<>();
		strList.add("kevA");
		strList.add("kevB");
		strList.add("kevC");
		/*
		* Use this label to break from a foreach
		* */
		labelLoop:
		for (String str : strList) {
			switch (str) {
				case "kevA":
					break labelLoop;
				default:
					System.out.println("Still printing after break");
					break;
			}
		}
	}
}
// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
class JK {
    static void test(Integer i, Number number) {
        System.out.println(1);
    }
    static void test(Number i, Integer number) {
        System.out.println(2);
    }
    static void test() {
        /*
        * Will not compile, ambiguous method call
        * */
//        test(1, 1);
    }
}
// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
class J{}
class K {
    class J {} // will compile
    class L extends J {}
    void method() {
        J j = new J(); // will always see the one that is inside the encapsulating class if didn't fully qualify
        K k = new K();
        J j1 = k.new J();  J j2 = new K().new J();// another ways to instantiate J()
    }
}
// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
class Lag {
    static {
        /*
        * Will compile because it is "initializing". Once you are utilizing already the field. The compiler
        * cannot be able to resolve it, hence will say Illegal Forward Reference
        * */
        y = 1; // initializing is okay
//        ++y; //This will not compile. Illegal forward Reference.
//        System.out.println(y); //This will not compile. Illegal forward Reference.
    }
    private static int y;
    /**
     * You can initialize a final instance field using instance initializer
     * */
    {
        x = 2;
    }
    private final int x;
    void method() {
        final String str;
        int x;
        str = "Kev";
        class Lag1 {
            private int z = 0;
            public int s = 0;
            void method() {
                System.out.println(Lag.this.x);
                System.out.println(str);
            }
        }
        Lag1 lag1 = new Lag1();
    }
    static enum Services {
        FIRST
    }
    static void method(Services services) {
        System.out.println(services);
    }
}
// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
interface EnumHolder {
    public static enum Services {
    }
}
// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
class Outer {
    int x = method();
    int y = 4;
    private static Outer outerInstance = new Outer();
    public int method() {
        return ++y;
    }
    private Outer() {
        System.out.println("Outer constructor");
    }
    public static Outer getInstance() {
        System.out.println("Inside getInstance()");
        return outerInstance;
    }
    class Inner {
    }
}
// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
class Outer2 {
    static class Inner {
        class Inner2 {
            class Inner3 {
/*				enum Services {  // will not compile if uncommented

				}*/
            }
        }
    }
}
// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
/*
* Overriding
* */
interface A {
    Number method() throws FileNotFoundException, IOException;
}
interface B {
    Integer method() throws IOException;
}

class Tiger implements A, B {
    @Override
    public Integer method() throws IOException{
        return 0;
    }
	void tryMethod() {
		System.out.println(getClass().getName()); // gets the runtime type of this Object
	}
}
class Po extends Tiger {}
// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
/*
* For some reason, upper bounded here is accepted. Lower bounded is not.
* */

class Sample<K extends Number, V extends Number> {}
// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
/*
* Overriding
* */
interface G {
    Number play(Object o, Number n);
}

class H implements G {
    @Override
    public Integer play(Object o, Number n) { // overriden
        return 1;
    }
    public Integer play(Number n, Object o) { // overloaded!
        return 2;
    }
    public void test() {
        /*
        * Will not compile, ambiguous method call
        * */
//        play(1, 1);
    }
    public static InnerClass sampleAnonymousWithInitializationParams() {
        final String kev = "kev"; // final automatic variable
        class LocalInnerClass {
            private void method() {
                System.out.println(kev); // kev needs to be final
            }
        }
        LocalInnerClass local = new LocalInnerClass();
        return new InnerClass(kev){
            @Override
            void printName() {
                System.out.println(kev);
            }
        };
    }
    public static abstract class InnerClass { // static abstract class will compile
        InnerClass(String name) {}
        abstract void printName();
    }
}