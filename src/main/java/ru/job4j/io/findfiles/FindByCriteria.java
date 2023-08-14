package ru.job4j.io.findfiles;

import ru.job4j.io.ArgsName;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

public class FindByCriteria {

    public void find(ArgsName argsName) {

    }

    public static void validate(ArgsName argsName) {
        Path path = Path.of(argsName.get("d"));
        if (!Files.exists(path)) {
            throw new IllegalArgumentException(String.format("%s doesn't exist", path.toAbsolutePath()));
        }
        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException(String.format("%s is not a directory", path.toAbsolutePath()));
        }
        if (!"name".equals(argsName.get("t")) && !"mask".equals(argsName.get("t")) && !"regex".equals(argsName.get("t"))) {
            throw new IllegalArgumentException(String.format(
                    "Type %s is wrong, enter \"name\", \"mask\" or \"regex\"", argsName.get("t")));
        }
        if (!Pattern.matches("[\\da-zA-Z]+\\.[\\da-z]+", argsName.get("o"))) {
            throw new IllegalArgumentException(String.format(
                    "Invalid target file's %s, file extension required", argsName.get("o")));
        }
        if (argsName.get("n").length() < 1) {
            throw new IllegalArgumentException(String.format(
                    "%s is wrong, name cannot be empty", argsName.get("n")));
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 4) {
            throw new IllegalArgumentException("Missing input");
        }
        ArgsName argsName = ArgsName.of(args);
        validate(argsName);
        FindByCriteriaVisitor visitor = new FindByCriteriaVisitor(argsName);
        Files.walkFileTree(Path.of(argsName.get("d")), visitor);
        visitor.result();
    }
}
