package ru.job4j.io.findfiles;

import ru.job4j.io.ArgsName;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindByCriteriaVisitor extends SimpleFileVisitor<Path> {
    List<Path> list = new ArrayList<>();
    ArgsName args;

    public FindByCriteriaVisitor(ArgsName args) {
        this.args = args;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        String type = args.get("t");
        if ("name".equals(type)) {
            if (args.get("n").equals(file.getFileName())) {
                list.add(file);
            }
        }
        if ("regex".equals(type)) {
            Pattern pattern = Pattern.compile(args.get("n"));
            Matcher matcher = pattern.matcher(file.getFileName().toString());
            if (matcher.matches()) {
                list.add(file);
            }
        }
        if ("mask".equals(type)) {
            String regex = args.get("n").replace("*", "[\\da-zA-Z]*");
            regex = regex.replace("?", "[\\da-zA-Z]{1}");
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(file.getFileName().toString());
            if (matcher.matches()) {
                list.add(file);
            }
        }


        return super.visitFile(file, attrs);
    }

    public void result() {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(args.get("o"))))) {
            for (Path path : list) {
                out.println(path.toAbsolutePath().toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
