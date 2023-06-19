package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Set<FileProperty> set = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (Files.isRegularFile(file)) {
            int currentSetSize = set.size();
            FileProperty curr = new FileProperty(Files.size(file), file.getFileName().toString());
            set.add(curr);
            if (set.size() == currentSetSize) {
                System.out.println(file.toAbsolutePath());
            }
        }
        return super.visitFile(file, attrs);
    }
}