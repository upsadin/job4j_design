package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringJoiner;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    public String getCachingDir() {
        return cachingDir;
    }

    @Override
    public String toString() {
        return "DirFileCache{"
                + "cachingDir='" + cachingDir + '\'' + '}';
    }

    @Override
    protected String load(String key) {
        if (Paths.get(key).isAbsolute()) {
            throw new IllegalArgumentException("Path must be relative");
        }
        Path path = null;
        String rsl = null;
        try {
            path = Paths.get(key);
            rsl = Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

}