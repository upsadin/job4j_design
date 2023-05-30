package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader buffer = new BufferedReader(new FileReader(file))) {
            String line = buffer.readLine();
            while (line != null) {
                String[] array = line.split(" ");
                if ("404".equals(array[array.length - 2])) {
                    rsl.add(line);
                }
                line = buffer.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

/*    public static void save(List<String> log, String file) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(file))) {
            for (String string : log) {
                out.write(string + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("data/log.txt");
        log.forEach(System.out::println);
/*        save(log, "data/404.txt");*/
    }
}