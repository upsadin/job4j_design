package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void validate(ArgsName arguments) {
        Path path = Path.of(arguments.get("d"));
        if (!Files.exists(path)) {
            throw new IllegalArgumentException(String.format("%s doesn't exist", path.toAbsolutePath()));
        }
        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException(String.format("%s is not a directory", path.toAbsolutePath()));
        }
        String exclude = arguments.get("e");
        if (exclude.length() < 2 || !exclude.startsWith(".") || !Character.isLetter(exclude.charAt(1))) {
            throw new IllegalArgumentException(String.format("File extension \'%s\' is incorrect", exclude));
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
/*        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );*/
        ArgsName arguments = ArgsName.of(args);
        validate(arguments);
        Path root = Paths.get(arguments.get("d"));
        Predicate<Path> condition = p -> !p.getFileName().toString().endsWith(arguments.get("e"));
        List<Path> sources = Search.search(root, condition);
        zip.packFiles(sources, new File(arguments.get("o")));
    }
}
