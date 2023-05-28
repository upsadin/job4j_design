package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class PrintUsage {
    public static void main(String[] args) {
        try (PrintStream stream = new PrintStream(new FileOutputStream("data/print.txt"));
             PrintWriter writer = new PrintWriter("data/print.txt")) {
/*            stream.write("2".getBytes());
            stream.println(3);
            stream.write("4".getBytes());
            writer.write("hello");
            stream.write("2".getBytes());*/
            writer.write("hi");
            writer.write(System.lineSeparator());
            stream.write("adn784512".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
