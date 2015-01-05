package com.ocpjp.package2;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Kev on 9/27/2014.
 */


/**
 * <p>
 * /mydir/code - absolute path --> BE MINDFUL OF THE "/", COULD BECOME TRICKY AT TIMES!
 * mydir/code - relative path
 * </p>
 */

public class NIOtopic {
	public static void main(String[] args) throws IOException {
		exercise();
	}

	private static void toAbsolutPathSample() {
		Path file = Paths.get("/../src.txt"); // use '..' to refer to a parent,
		// then normalize to cut redundancy
		Path path = file.toAbsolutePath().normalize();
		System.out.println(Paths.get("test").toAbsolutePath());
		System.out.println(path);
	}

	private static void tryPathMethods() throws IOException {
		Path absolutePath = Paths.get("/mydir/code/sample/tmp");
		Path relativePath = Paths.get("mydir/code/sample/tmp");

		System.out.println("getRoot: " + absolutePath.getRoot());
		System.out.println("getRoot: " + relativePath.getRoot());
		System.out.println("getName(0): " + absolutePath.getName(0));
		System.out.println("getName(0): " + relativePath.getName(0));
		System.out.println("getParent: " + absolutePath.getParent()); // returns null if Path doesn't have a parent
		System.out.println("getParent: " + relativePath.getParent());
		System.out.println("getFileName(): " + absolutePath.getFileName());
		System.out.println("getFileName(): " + relativePath.getFileName());
		System.out.println("subpath(0,2): " + absolutePath.subpath(0, 2));
		System.out.println("subpath(0,2): " + relativePath.subpath(0, 2));

		WatchService watchService = absolutePath.getFileSystem().newWatchService();
		absolutePath.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
	}

	private static void resolveSample() {
		Path absolutePath = Paths.get("C:/mydir/code");
//		Path relativePath = Paths.get("mydir/code"); // if this is used as the first path, resolved path would not be an absolute path as well
		System.out.println("path2.endsWith " + absolutePath.endsWith("code")); //
		// wil result to false if endsWith("ode")

		System.out.println(absolutePath.resolve(Paths.get("world/Hello.java"))); //  passed a relative path as an argument.
		System.out.println(absolutePath.resolve(Paths.get("/world/Hello.java"))); // passed an absolute path as an argument.
		// This will not resolve the two paths.

		/**
		 *
		 * Results of "resolveSibling()" is the same with "either absolute or relative path"
		 * */
		Path renamePath = absolutePath.resolveSibling(Paths.get("newWorld.java"));
		Path copyPath = absolutePath.resolveSibling("backup/eworld.java");
		Path anotherAbsolute = absolutePath.resolveSibling("/backup/eworld.java");

		System.out.println("Parent Path: " + absolutePath);
		System.out.println("Siblin Path: " + renamePath);
		System.out.println("Sibli2 Path: " + copyPath);
		System.out.println("Sibli3 Path: " + absolutePath.resolveSibling(""));
		System.out.println("Sibli4 Path: " + anotherAbsolute); // passed an absolute path, so resolving sibling is ignored
	}

	private static void relativizeSample() {
		/**
		 *
		 * Method Relativize
		 *
		 * result will be always relative to the "parent" path (doesn't
		 * matter if it is a file / dir type of path).
		 *
		 * if working on absolute paths, it needs to go backwards first
		 * before going forward. In relative paths, it's not necessary.
		 *
		 * @throws IllegalArgumentException if tried to resolve relative
		 * path and an absolute path, vice versa.
		 *
		 * */

		Path dir = Paths.get("code");
		Path file = Paths.get("code/java/IO.java");
		System.out.println(file.relativize(dir));  // ..\..
		System.out.println(dir.relativize(file));  // java\IO.java

		dir = Paths.get("/code");
		file = Paths.get("/code2/IO.java");
		System.out.println(file.relativize(dir));  // ..\..\code
		System.out.println(dir.relativize(file));  // ..\code2\IO.java
	}

	private static void exercise() {
		Path absolutePath = Paths.get("C:/mydir/code");
		System.out.println(absolutePath.subpath(0,1)); // Returns a relative path

		Path dir = Paths.get("code");
		Path file = Paths.get("code/java/IO.java");
		Path relative2 = file.relativize(dir);
		System.out.println(relative2);
		Path relative = file.resolve(relative2);
		System.out.println(relative);
		Path absolute = relative.toAbsolutePath();
		System.out.println(absolute);
		System.out.println("sample");
	}
}
