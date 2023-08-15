package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        int i = 0;
        while (i < nodes.size() && source.hasNext()) {
            nodes.get(i).add(source.next());
            i++;
            if (source.hasNext() && i == nodes.size()) {
                i = 0;
            }
        }
    }
}