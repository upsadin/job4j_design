package ru.job4j.kiss.fool;

import java.util.Scanner;
import java.util.function.Predicate;

public class Fool {

    private static int startAt = 1;

    public static int getStartAt() {
        return startAt;
    }

    public static void setStartAt(int startAt) {
        Fool.startAt = startAt;
    }

    public static String comp() {
        String rsl = "";
        if (startAt % 3 == 0 && startAt % 5 == 0) {
            rsl = "FizzBuzz";
        } else if (startAt % 3 == 0) {
            rsl = "Fizz";
        } else if (startAt % 5 == 0) {
            rsl = "Buzz";
        } else {
            rsl = String.valueOf(startAt);
        }
        return rsl;
    }

    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz.");
        var io = new Scanner(System.in);
        while (startAt < 100) {
            System.out.println(comp());
            startAt++;
            var answer = io.nextLine();
            if (!comp().equals(answer)) {
                System.out.println("Ошибка. Начинай снова.");
                startAt = 0;
            }
            startAt++;
        }
    }
}