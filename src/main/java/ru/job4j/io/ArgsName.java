package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(String.format(
                    "This key: \'%s\' is missing", key));
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String arg : args) {
            check(arg);
            String[] str = arg.split("=", 2);
            values.put(str[0].substring(1), str[1]);
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    private void check(String string) {
        if (!string.startsWith("-")) {
            throw new IllegalArgumentException(String.format(
                    "Error: This argument \'%s\' does not start with a '-' character", string));
        }
        if (!string.contains("=")) {
            throw new IllegalArgumentException(String.format(
                    "Error: This argument \'%s\' does not contain an equal sign", string));
        }
        String[] str = string.split("=", 2);
        if (str[0].length() == 1) {
            throw new IllegalArgumentException(String.format(
                    "Error: This argument \'%s\' does not contain a key", string));
        }
        if (str[1].isEmpty()) {
            throw new IllegalArgumentException(String.format(
                    "Error: This argument \'%s\' does not contain a value", string));
        }
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}