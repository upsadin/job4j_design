package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class CyclicIterator<T> implements Iterator<T> {

    private List<T> data = new ArrayList<>();
    private int index;

    public CyclicIterator(List<T> data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (data.size() != 0) {
            if (index > data.size() - 1) {
                index = 0;
            }
        }
        return index < data.size();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("There is no element");
        }
        return data.get(index++);
    }
}
