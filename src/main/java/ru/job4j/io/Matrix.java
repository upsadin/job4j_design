package ru.job4j.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Matrix {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("data/matrix.txt")) {
            int size = 9;
            byte[][] table = new byte[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
            out.write(Integer.toString((i + 1) * (j + 1)).getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
