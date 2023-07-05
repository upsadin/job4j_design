package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        String delimiter = argsName.get("delimiter");
        Scanner scanner = new Scanner(Path.of(argsName.get("path")))
                .useDelimiter(delimiter);
        String[] firstString = scanner.nextLine().split(delimiter);
        List<String> listOfColomn = Arrays.asList(firstString);
        List<Integer> numbers = new ArrayList<>();
        for (String string : argsName.get("filter").split(",")) {
            numbers.add(listOfColomn.indexOf(string));
        }

            String outType = argsName.get("out");
            PrintWriter out;
               if (outType.equals("stdout")) {
                   out = new PrintWriter(System.out, true);
               } else {
                   out = new PrintWriter(new BufferedWriter(new FileWriter(outType, true)));
               }
                    StringJoiner stringJoiner = new StringJoiner(delimiter);
        for (int nums : numbers) {
            stringJoiner.add(firstString[nums]);
        }
        out.print(stringJoiner.toString());
        out.println("");
        try {
                   while (scanner.hasNext()) {
                       stringJoiner = new StringJoiner(delimiter);
                       String[] strings = scanner.nextLine().split(delimiter);

                       for (int nums : numbers) {
                           stringJoiner.add(strings[nums]);
                       }
                       out.print(stringJoiner.toString());
                       out.println("");
                   }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    public static void validate(ArgsName args) {
        Path path = Path.of(args.get("path"));
        if (!Files.exists(path)) {
            throw new IllegalArgumentException(String.format("%s doesn't exist", path.toAbsolutePath()));
        }
        if (!Files.isRegularFile(path)) {
            throw new IllegalArgumentException(String.format("%s is not a file", path.toAbsolutePath()));
        }
        if (args.get("delimiter").length() > 1) {
            throw new IllegalArgumentException("delimiter is wrong");
        }
        if (!args.get("out").equals("stdout") && args.get("out").matches("\\.[a-z]+")) {
            throw new IllegalArgumentException("out is wrong");
        }
    }


    public static void main(String[] args) throws Exception {
        if (args.length < 4) {
            throw new IllegalArgumentException("Missing input");
        }
        ArgsName argsName = ArgsName.of(args);
        validate(argsName);
        handle(argsName);
    }
}