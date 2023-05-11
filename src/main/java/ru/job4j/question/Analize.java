package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;

        Map<Integer, String> map = new HashMap<>();
        for (User users : previous) {
            map.put(users.getId(), users.getName());
        }
        for (User users : current) {
            map.put(users.getId(), users.getName());
        }
        if (previous.size() < map.size()) {
            added++;
        }
        map.clear();

        for (User users : current) {
            map.put(users.getId(), users.getName());
        }
        for (User users : previous) {
            map.put(users.getId(), users.getName());
        }
        if (current.size() < map.size()) {
            deleted++;
        }
        map.clear();

        for (User users : previous) {
            map.put(users.getId(), users.getName());
        }
        for (User users : current) {
        String curr = map.get(users.getId());
            if (curr != null && !users.getName().equals(curr)) {
                changed++;
            }
        }

        return new Info(added, changed, deleted);
    }
}