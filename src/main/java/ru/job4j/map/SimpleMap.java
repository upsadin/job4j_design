package ru.job4j.map;

import ru.job4j.collection.SimpleQueue;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        int i = indexFor(hash(hashcode(key)));
        boolean rsl = table[i] == null;
        if (rsl) {
            table[i] = new MapEntry(key, value);
            count++;
            modCount++;
            if (count >= capacity * LOAD_FACTOR) {
                expand();
            }
        }
        return rsl;
    }

    private int hashcode(K key) {
        return key == null ? 0 : key.hashCode();
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private int index(K key) {
        return indexFor(hash(hashcode(key)));
    }

    private boolean check(K key) {
        int i = index(key);
        return (table[i] != null
                && hashcode(table[i].key) == hashcode(key)
                && Objects.equals(key, table[i].key));
    }

    private void expand() {
    capacity *= 2;
    count = 0;
    modCount = 0;
    MapEntry<K, V>[] oldTable = table;
    table = new MapEntry[capacity];
        for (MapEntry<K, V> cell : oldTable) {
            if (cell != null) {
                table[index(cell.key)] = cell;
            }
        }
    }

    @Override
    public V get(K key) {
        V rsl = null;
        if (check(key)) {
                rsl = table[index(key)].value;
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = check(key);
        if (rsl) {
            table[index(key)] = null;
            rsl = true;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int point = 0;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (point < capacity && table[point] == null) {
                    point++;
                }
                return point < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[point++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}