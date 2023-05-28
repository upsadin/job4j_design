package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream file = new FileInputStream("data/even.txt"))   {
            int read;
            StringBuilder text = new StringBuilder();
            while ((read = file.read()) != -1) {
                read = read;
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            int num;
            for (String line : lines) {
                num = Integer.valueOf(line);
                if (num % 2 == 0) {
                    System.out.println("This number " + num + " is even");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
