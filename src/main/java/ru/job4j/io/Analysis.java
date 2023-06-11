package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
               Boolean check = false;
                 for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                     String[] arr = line.split(" ");
                     if ("400".equals(arr[0]) || "500".equals(arr[0])) {
                         if (!check) {
                             writer.write(String.format("%s;", arr[1]));
                             check = true;
                         }
                         continue;
                     }
                     if (check) {
                         writer.write(String.format("%s;%n", arr[1]));
                         check = false;
                     }
                 }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}