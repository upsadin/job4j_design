package ru.job4j.ood.isp.menu;

import java.util.Optional;
import java.util.Scanner;

public class TodoApp {
    public static final ActionDelegate STUB_ACTION = () -> System.out.println("Some action");
    Menu menu;
    MenuPrinter menuPrinter;

    public TodoApp(Menu menu, MenuPrinter menuPrinter) {
        this.menu = menu;
        this.menuPrinter = menuPrinter;
    }

    public boolean addInRoot(String item) {
        return menu.add(Menu.ROOT, item, STUB_ACTION);
    }

    public boolean addToParent(String parent, String item) {
        return menu.add(parent, item, STUB_ACTION);
    }

    public ActionDelegate actOfItem(String item) {
        var currItem = menu.select(item);
        if (currItem.isEmpty()) {
            throw new IllegalArgumentException("wrong item");
        }
        return currItem.get().getActionDelegate();
    }

    public void print() {
        menuPrinter.print(menu);
    }

    public static void main(String[] args) {
        TodoApp app = new TodoApp(new SimpleMenu(), new SimpleMenuPrinter());
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        while (run) {
            String showMenu = """
                    Choose action or press another symbol for exit:
                    1. Add item in the root
                    2. Add item to an existing
                    3. Execute an item's action
                    4. Print all items
                    """;
            System.out.println(showMenu);
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 1) {
                System.out.println("Enter name of item");
                String name = scanner.nextLine();
                if (app.addInRoot(name)) {
                    System.out.println("Successfully");
                } else {
                    System.out.println("Unsuccessfully");
                }
            } else if (choice == 2) {
                System.out.println("Enter name of parent item");
                String parentName = scanner.nextLine();
                System.out.println("Enter name of new item");
                String childName = scanner.nextLine();
                if (app.addToParent(parentName, childName)) {
                    System.out.println("Successfully");
                } else {
                    System.out.println("Unsuccessfully");
                }
            } else if (choice == 3) {
                System.out.println("Choose item");
                String itemName = scanner.nextLine();
                app.actOfItem(itemName);
            } else if (choice == 4) {
                app.print();
            } else {
                System.out.println("EXIT");
                run = false;
            }
        }
    }
}
