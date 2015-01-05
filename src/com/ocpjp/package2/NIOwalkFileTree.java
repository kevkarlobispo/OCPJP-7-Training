package com.ocpjp.package2;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @author Kev Obispo on 11/6/2014.
 */

public class NIOwalkFileTree {
	public static void main(String[] args) throws IOException{
		MyFileChecker myFileChecker = new MyFileChecker();
		Files.walkFileTree(Paths.get("C:/Users/Dev8/Desktop/ETS viewer with OCPJP7 exam"), myFileChecker);
		System.out.println(myFileChecker.getCount());
	}
}

class MyFileChecker extends SimpleFileVisitor<Path> {
	private final PathMatcher pathMatcher;
	public static int count;

	MyFileChecker() {
		pathMatcher = FileSystems.getDefault().getPathMatcher("glob:etsviewer.jar");
	}

	void check(Path p) {
		if (p != null && pathMatcher.matches(p)) {
			System.out.println(p);
			count++;
		}
	}

	public int getCount() {
		return count;
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
		check(file);
		return FileVisitResult.CONTINUE;
	}
}