package com.ocpjp.package2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;

/**
 * @author Kev Obispo on 9/25/2014.
 */
public class IOFundamentals {
	public static void main(String[] args) throws IOException{
		useInputOutputStreamImpls();
	}

	private static void createDirAndFile() throws IOException {
		File file = new File("C:/projects/ValueWebb/valuewebb/out/tmp");
		System.out.println(file.mkdir());

		File file2 = new File(file, "MyText.txt");
		if (!file2.exists()) {
			boolean result = file2.createNewFile();
		}
	}

	private static void useInputOutputStreamImpls() throws IOException {
		try (
				FileInputStream in = new FileInputStream("C:/projects/ValueWebb/valuewebb/out/tmp/sample.pdf");
				FileOutputStream out = new FileOutputStream("C:/projects/ValueWebb/valuewebb/out/tmp/sampleCopied.pdf");
				BufferedInputStream bit = new BufferedInputStream(in);
				BufferedOutputStream bot = new BufferedOutputStream(out)
		) {
			int data;
			byte[] byteArr = new byte[2048];

			Date startDate2 = new Date();
			System.out.println("File I/O started: " + startDate2);
			while ((data = bit.read(byteArr)) != -1) { // this will eventually fall into BufferedInputStream's read
				bot.write(byteArr, 0, data);  // much faster to use BufferedOutputStream way of write
				System.out.println("Data: " + data);
			}
			Date endDate2 = new Date();
			System.out.println("File I/O completed: " + endDate2);
			System.out.println("Milliseconds elapsed using buffered streams: " + (endDate2.getTime() - startDate2.getTime()));


//			Date startDate = new Date();
//			System.out.println("File I/O started: " + startDate);
//			while ((data = in.read(byteArr)) != -1) {
//				out.write(byteArr, 0, data);
//				System.out.println("Data: " + data);
//			}
//			Date endDate = new Date();
//			System.out.println("File I/O completed: " + endDate);
//			System.out.println("Milliseconds elapsed using array of bytes: " + (endDate.getTime() - startDate.getTime()));
		}
	}

	private static void readFileWithBufferedReader() throws IOException {
		try (
				FileReader fr = new FileReader("ReadFile.java");
				BufferedReader br = new BufferedReader(fr)
		){
			String line;
			while ((line = br.readLine())!= null)
				System.out.println (line);
		}
	}
}
