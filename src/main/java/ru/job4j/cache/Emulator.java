package ru.job4j.cache;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Emulator {
    public static final String MENU = """
                Введите 1, чтобы указать кэшируемую директорию.
                Введите 2, чтобы загрузить содержимое файла в кэш.
                Введите 3, получить содержимое файла из кэша.
                Введите любое другое число для выхода.
            """;
    public static final String SELECT = "Выберите меню";
    public static DirFileCache dirFileCache;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.println(MENU);
            System.out.println(SELECT);
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 1) {
                System.out.println("Укажите путь:");
                String inDir = scanner.nextLine();
                Path path = Paths.get(inDir);
                if (!Files.exists(path) || !Files.isRegularFile(path)) {
                    throw new IllegalArgumentException("Этот файл не существует");
                }
                System.out.println(path.toAbsolutePath());
                dirFileCache = new DirFileCache(inDir);
                System.out.println(dirFileCache.toString());
            } else if (choice == 2) {
                if (dirFileCache == null) {
                    throw new IllegalArgumentException("Невозможно загрузить null файл в кэш");
                }
                dirFileCache.get(dirFileCache.getCachingDir());
            } else if (choice == 3) {
                if (dirFileCache == null) {
                    throw new IllegalArgumentException("Невозможно выгрузить null файл");
                }
                System.out.println(dirFileCache.get(dirFileCache.getCachingDir()));
            } else {
                run = false;
            }

        }
    }
}
