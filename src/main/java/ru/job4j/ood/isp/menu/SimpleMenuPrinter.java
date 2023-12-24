package ru.job4j.ood.isp.menu;

import java.util.StringJoiner;

public class SimpleMenuPrinter implements MenuPrinter {
    @Override
    public void print(Menu menu) {
        var iterator = menu.iterator();
        while (iterator.hasNext()) {
            Menu.MenuItemInfo menuItemInfo = iterator.next();
            int sizeNumber = menuItemInfo.getNumber().length() / 2;
            StringBuilder joiner = new StringBuilder();
            for (int i = 0; i < sizeNumber - 1; i++) {
                joiner.append("\t");
            }
            joiner.append(menuItemInfo);
            System.out.println(joiner.toString());
/*            System.out.println(String.format("%" + (sizeNumber - 1) + "s"," ") + menuItemInfo);*/
        }
    }

    public static void main(String[] args) {
        final ActionDelegate STUB_ACTION = System.out::println;
        SimpleMenuPrinter simpleMenuPrinter = new SimpleMenuPrinter();
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        simpleMenuPrinter.print(menu);
    }
}
