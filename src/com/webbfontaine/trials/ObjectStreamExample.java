package com.webbfontaine.trials;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kev Obispo on 5/9/2014.
 */
public class ObjectStreamExample {

	public static void main(String[] args) {
		Map<String, String> presidentsOfUS = new HashMap<>();
		presidentsOfUS.put("Barack Obama", "2009 to --, Democratic Party, 56th term");
		presidentsOfUS.put("George W. Bush", "2001 to 2009, Republican Party, 54th and 55th terms");
		presidentsOfUS.put("Bill Clinton", "1993 to 2001, Democratic Party, 52nd and 53rd terms");

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.data"))) {
			oos.writeObject(presidentsOfUS);
		} catch (FileNotFoundException fnfe)
		{
			System.err.println("Cannot create a file with the given file name");
		} catch (IOException ioe) {
			System.err.println("an I/O error occurred while processing the file");
		}

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object.data"))) {
			Object obj = ois.readObject();
			if (obj != null && obj instanceof Map) {
				@SuppressWarnings("unchecked")
				Map<String, String> presidents = (Map<String, String>) obj; // It's type-safe.
				System.out.println("President name \t Description \n");
				for (Map.Entry<String, String> entry : presidents.entrySet()) {
					System.out.printf("%s \t %s \n", entry.getKey(), entry.getValue());
				}
			}
		} catch (IOException ioe) {
			System.err.println("an I/O error occurred while processing the file");
		} catch (ClassNotFoundException cnfe) {
			System.err.println("cannot recognize the class of the object - is the file corrupted?");
		}
	}
}
