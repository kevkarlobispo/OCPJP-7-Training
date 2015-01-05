package com.ocpjp.package2;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author Kev Obispo on 11/5/2014.
 */

public class DirectoryStreamTopic {

	public static void main(String[] args) throws IOException {
		Path path = Paths.get("C:/Users/Dev8/Desktop/ETS viewer with OCPJP7 exam");
		for (Path file : listSourceFiles(path)) {
			System.out.println(file);
		}
	}

	private static List<Path> listSourceFiles(Path dir) throws IOException{
		List<Path> files = new ArrayList<>();
		DirectoryStream<Path> directoryStream = Files.newDirectoryStream(dir, "etsviewer.jar");
		for (Path path : directoryStream) {
			files.add(path);
		}
//		directoryStream.iterator(); // will throw IllegalStateException: Iterator already obtained
		return files;
	}
}