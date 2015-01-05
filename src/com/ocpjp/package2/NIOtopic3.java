package com.ocpjp.package2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;
import java.util.*;

/**
 * @author Kev Obispo on 9/29/2014.
 */
public class NIOtopic3 {
	public static void main(String[] args) throws IOException, InterruptedException{
		tryWatchService();
	}

	private static void useFilesAttributes() throws IOException{
		File file = new File("C:/projects/ValueWebb/valuewebb/out/tmp");

		BasicFileAttributes bs2 = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
		System.out.println("lastAccessTime" + bs2.lastAccessTime());

		System.out.println(file.mkdir());

		DosFileAttributes bs = Files.readAttributes(file.toPath(), DosFileAttributes.class);
		System.out.println("tmp folder isReadOnly: " + bs.isReadOnly());

		Files.setAttribute(file.toPath(), "dos:readonly", true);
		Map<String, Object> map = Files.readAttributes(file.toPath(), "dos:*");
//		Map<String, Object> map = Files.readAttributes(file.toPath(), "dos:readOnly");
		System.out.println("tmp folder isReadOnly: " + map.get("readonly"));
		System.out.println("tmp readAttributes map: " + map);
	}

	private static void tryCopyFiles() throws IOException{
		FileInputStream stream = new FileInputStream("C:/projects/ValueWebb/valuewebb/out/tmp/sample.pdf");
		Path path = Paths.get("C:/projects/ValueWebb/valuewebb/out/tmp/sample.pdf");
		Path path2 = Paths.get("C:/projects/ValueWebb/valuewebb/out/tmp/tmp22.pdf");
		long l = Files.copy(stream, path2);
		System.out.println(l);
	}

	private static void tryMoveFiles() throws IOException {
		Path path = Paths.get("C:/projects/ValueWebb/valuewebb/out/tmp/sample.pdf");
		Path path2 = Paths.get("C:/projects/ValueWebb/valuewebb/out");
//		System.out.println(Files.move(path, path.resolveSibling("tryMove.pdf"))); // just rename the file

		System.out.println("Successfully moved into another folder with the same name: " + Files.move(path, path2.resolve(path.getFileName())));
/**
 * move() and delete()'s path arguments must be existing, else NoSuchFileException will be thrown
 *
 * */
	}

	private static void tryDeleteFiles() throws IOException {
		Path path = Paths.get("C:/projects/ValueWebb/valuewebb/out/tmp/sample.pdf");
		Files.delete(path); // throws NoSuchFileException or FileSystemException
		System.out.println("Trying to delete already deleted file: " + Files.deleteIfExists(path)); // throws FileSystemException
	}

	private static void tryCreateFiles() throws IOException {
		Path path = Paths.get("C:/projects/ValueWebb/valuewebb/out/tmp4");
//		PathMatcher pathMatcher = path.getFileSystem().getPathMatcher(""); // recommended way to call PathMatcher
		Files.createDirectory(path);
		Files.createFile(path); // throws AccessDeniedException if tried to create the same name as the directory and FileAlreadyExistsException if tried to create same file name.
	}

	private static void tryPathMatcherWatchService() throws IOException{
		Path path = Paths.get("C:/projects/ValueWebb/valuewebb/out/tmp4");

		PathMatcher pathMatcher = path.getFileSystem().getPathMatcher("");
		WatchService watchService = path.getFileSystem().newWatchService();
		watchService = FileSystems.getDefault().newWatchService();
	}

	/**
	 *
	 * Using WatchService in Java 7 only watch files contained in that directory will be watched and not the files
	 * contained in the subdirectories of that directory.
	 *
	 * */

	private static void tryWatchService() throws IOException, InterruptedException{
		Path srcPath = Paths.get("C:/projects/Trials/src/com/ocpjp/package2");
		System.out.println("Watched Directory: " + srcPath);
		WatchService watchService = srcPath.getFileSystem().newWatchService(); // get an instance of a watchService
		srcPath.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY); // register the watchService to the target directory

		for(;;) {
			WatchKey key = watchService.take();

			for (WatchEvent<?> event : key.pollEvents()) { // iterate for each event
				switch (event.kind().name()) {
					case "OVERFLOW":
						System.out.println("We lost some events");
						break;
					case "ENTRY_MODIFY":
						System.out.println("File " + event.context() + " is changed!");
						break;
				}
			}
			key.reset(); //resetting the key is important to receive subsequent notifications
		}
	}
}