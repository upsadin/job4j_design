package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader buf = new BufferedReader(new FileReader(this.path))) {
            buf.lines().filter(s -> !s.isEmpty() && !s.startsWith("#"))
                    .forEach(s -> {
                        String[] array = s.split("=", 2);
                        if (!s.contains("=")
                                || array[0].isEmpty()
                                || array[1].isEmpty()) {
                            throw new IllegalArgumentException("Нарушен формат у строки " + s);
                        }
                        this.values.put(array[0], array[1]);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        if (values.get(key) == null) {
            throw new UnsupportedOperationException("Don't impl this method yet!");
        }
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }

}