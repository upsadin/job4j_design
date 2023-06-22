package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Search {
    public static void main(String[] args) throws IOException {
        check(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static boolean check(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Root folder or desired file extension is null");
        }
        Path start = Paths.get(args[0]);
        if (!Files.exists(start)) {
            throw new IllegalArgumentException(String.format("%s doesn't exist", start.toAbsolutePath()));
        }
        if (!Files.isDirectory(start)) {
            throw new IllegalArgumentException(String.format("%s is not a directory", start.toAbsolutePath()));
        }
        if (args[1].length() < 2 || !args[1].startsWith(".") || !Character.isLetter(args[1].charAt(1))) {
            throw new IllegalArgumentException("File extension is incorrect");
        }
        return true;
    }
}