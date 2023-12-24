package ru.job4j.ood.isp.menu;

import java.util.*;

public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        boolean rsl = true;
        if (parentName == null) {
            rootElements.add(new SimpleMenuItem(childName, actionDelegate));
        } else {
            Optional<ItemInfo> parentItem = findItem(parentName);
            if (!parentItem.isPresent()) {
                rsl = false;
            } else {
                parentItem.get().menuItem.getChildren().add(new SimpleMenuItem(childName, actionDelegate));
            }
        }
        return rsl;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        MenuItemInfo rsl = null;
        var itemInfo = findItem(itemName);
        if (itemInfo.isPresent()) {
            rsl =  new Menu.MenuItemInfo(itemInfo.get().menuItem, itemInfo.get().number);
        }
        return Optional.ofNullable(rsl);
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        List<MenuItemInfo> menuItemInfoList = new ArrayList<>();
        var iterator = new DFSIterator();
        while (iterator.hasNext()) {
            ItemInfo itemInfo = iterator.next();
            menuItemInfoList.add(new MenuItemInfo(itemInfo.menuItem, itemInfo.number));
        }
        return menuItemInfoList.iterator();
    }

    private Optional<ItemInfo> findItem(String name) {
        Optional<ItemInfo> rsl = Optional.empty();
        var iterator = new DFSIterator();
        while (iterator.hasNext()) {
            ItemInfo itemInfo = iterator.next();
            if (name.equals(itemInfo.menuItem.getName())) {
                rsl = Optional.of(itemInfo);
            }
        }
        return rsl;
    }

    private static class SimpleMenuItem implements MenuItem {

        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>();

        Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }

    }

    private class ItemInfo {

        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }
    }

}