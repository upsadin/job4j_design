package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        List<String> rsl = new ArrayList<>();
        String delimiter = argsName.get("delimiter");
        try (Scanner scanner = new Scanner(Path.of(argsName.get("path")))
                .useDelimiter(delimiter)) {
            String[] firstString = scanner.nextLine().split(delimiter);
            List<String> listOfColomn = Arrays.asList(firstString);
            List<Integer> numbers = new ArrayList<>();
            for (String string : argsName.get("filter").split(",")) {
                numbers.add(listOfColomn.indexOf(string));
            }

            String outType = argsName.get("out");
            StringJoiner stringJoiner = new StringJoiner(delimiter);
            for (int nums : numbers) {
                stringJoiner.add(firstString[nums]);
            }
            rsl.add(stringJoiner.toString());
            while (scanner.hasNext()) {
                stringJoiner = new StringJoiner(delimiter);
                String[] strings = scanner.nextLine().split(delimiter);
                for (int nums : numbers) {
                    stringJoiner.add(strings[nums]);
                }
                rsl.add(stringJoiner.toString());
            }
            writeOut(outType, rsl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для вывода результирующей коллекции в файл или на консоль в зависимости от входящего аргумента out
     * @param outType - проверяет входящий аргумент out
     * @param strings то, что будет записываться
     */
    private static void writeOut(String outType, List<String> strings) {
            if ("stdout".equals(outType)) {
                for (String string : strings) {
                    System.out.println(string);
                }
            } else {
                try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(outType, true)))) {
                    strings.forEach(out::println);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }

    /**
     * валидация входящего аргумента пути файла, с которого будут считываться данные
     * @param args входящие аргументы
     */
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
        if (!"stdout".equals(args.get("out")) && args.get("out").matches("\\.[a-z]+")) {
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