package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.*;

class SimpleMenuPrinterTest {
    public static final ActionDelegate STUB_ACTION = System.out::println;
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);

    @BeforeEach
    public void setUpStream() {
        System.setOut(printStream);
    }


    @Test
    public void whenPrintAndCompare() {
        SimpleMenuPrinter printer = new SimpleMenuPrinter();
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        String expected = """
                1. Сходить в магазин
                	1.1. Купить продукты
                		1.1.1. Купить хлеб
                		1.1.2. Купить молоко
                2. Покормить собаку
                """.replaceAll("\n", System.lineSeparator());
        printer.print(menu);
        assertThat(outputStream.toString()).isEqualTo(expected);
    }

    @AfterEach
    public void cleanSetUp() {
        System.setOut(System.out);
    }

}