package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> phrases = readPhrases();
        List<String> log = new ArrayList<>();
        Random random = new Random();
        boolean run = true;
        Scanner input = new Scanner(System.in);
        String ans;
        String phrase;
        boolean tumbler = true;
        while (run) {
            ans = input.nextLine();
            log.add(ans);
            System.out.println(ans);
            switch (ans.toLowerCase()) {
                case OUT: run = false;
                break;
                case STOP: tumbler = false;
                break;
                case CONTINUE: tumbler = true;
                default: if (tumbler) {
                    phrase = phrases.get(random.nextInt(phrases.size()));
                    log.add(phrase);
                    System.out.println(phrase);
                }
            }

        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers))) {
            br.lines().forEach(rsl::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat(
                "C:\\projects\\job4j_design\\data\\logs.txt",
                "C:\\projects\\job4j_design\\data\\phrases.txt");
        cc.run();
    }
}