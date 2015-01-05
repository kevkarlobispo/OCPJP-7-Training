package com.ocpjp.package2;

/**
 * @author Kev on 9/28/2014.
 */

import java.io.*;
import java.nio.file.*;

/**
 *
 * Files.java is a:
 *     - final class
 *     - cannot be instantiated (private non-args constructor)
 *     - only consist of static methods
 *
 * methods:
 *
 * createDirectory()
 * createFile()
 *     - both throws FileAlreadyExistsException if the target directory/file
 *     already exists.
 *
 * createDirectories()
 *     - creates both the target directories and multiple
 *     nonexistent parent directories. No exceptions are thrown!
 *
 * */


/*
* Every relative paths(Path or File) will be resolved to be an absolute path relative to the current directory if this
* is used as an argument to Files.move or any file manipulations.
* */
public class NIOwithFilesTopic {
    public static void main(String[] args) throws Exception {
        File relativeFile = new File("nonExistingFile"); // a file could not be associated to a real file.
        File absoluteFile = relativeFile.getAbsoluteFile(); // Get the absolute path of the file.
        System.out.println(relativeFile); // nonExistingFile
        System.out.println(absoluteFile); // C:/Users/Kev/IdeaProjects/untitled/nonExistingFile
        Path filePath = Paths.get("C:/Users/Kev/Downloads/testDocument.txt");
//        if (filePath.isAbsolute()) {
//            copyPassingOutputStream(filePath);
//        }
    }

    private static void copyPassingInputStream(Path filePath) throws Exception{
        Path outputFile = Paths.get("C:/Users/Kev/Downloads/Series/", filePath.toFile().getName());
        long result = Files.copy(new BufferedInputStream(new FileInputStream(filePath.toFile())), outputFile);
        System.out.println("result = " + result);
    }

    private static void copyPassingOutputStream(Path filePath) throws Exception {
        String absolutePathParent = filePath.resolveSibling("").toString();
        File outputFile = Paths.get(absolutePathParent, "Series", filePath.toFile().getName()).toFile();
//        File outputFile = Paths.get(filePath.toFile().getName()).toFile(); // passing relative path
        System.out.println("outputFile = " + outputFile);
        /*
        * Using BufferedOutputStream doesn't actually does the job, it gets over buffering resulting
        * to unknown writing of exact number of bytes to the data destination.
        * Maybe because behind the scenes, it is not using BufferedInputStream to read the actual bytes.
        * That might really cause to a read/write issue.
        *
        * filename must be specified as the last member of the file, else FileNotFoundException will be thrown.
        * */
        long result = Files.copy(filePath, new FileOutputStream(outputFile, true)); // append true
        System.out.println("result = " + result);
    }

    /*
    * Watch out for resolve and resolveSibling when recomposing the path, could be very tricky!
    * */
    private static void moveFile(Path filePath) throws IOException {
        Path target = Files.move(filePath, filePath.resolveSibling("test.txt")); // move and rename
        Path otherLocation = Paths.get(""); // relative path will not throw exceptions if used with file manipulations
        Path target2 = Files.move(target, otherLocation.resolve(target.getFileName())); // move and keep old name
        System.out.println("target = " + target);
        System.out.println("target2 = " + target2);
    }

    private static void deleteFile() throws IOException {
        Path filePath = Paths.get("C:/Users/Kev/Downloads/testDocument.txt");
        Files.delete(filePath); // void return type
    }
}
