package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, List<Path>> map = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (Files.isRegularFile(file)) {
            FileProperty curr = new FileProperty(Files.size(file), file.getFileName().toString());
            if (map.containsKey(curr)) {
                map.get(curr).add(file);
            }
            map.putIfAbsent(curr, new ArrayList<Path>(Arrays.asList(file)));
        }
        return super.visitFile(file, attrs);
    }

    public void result() {
        for (FileProperty fp : map.keySet()) {
            if (map.get(fp).size() > 1) {
                for (Path path : map.get(fp)) {
                    System.out.println(path.toString());
                }
            }
        }
    }
}