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
        return hashCode ^ (hashCode >>> table.length);
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private void expand() {
    capacity *= 2;
    count = 0;
    modCount = 0;
    MapEntry<K, V>[] oldTable = table;
    table = new MapEntry[capacity];
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] != null) {
                put(oldTable[i].key, oldTable[i].value);
            }
        }
    }

    @Override
    public V get(K key) {
        int i = indexFor(hash(hashcode(key)));
        V rsl = null;
        if (table[i] != null) {
            MapEntry<K, V> exist = table[i];
            if (hash(hashcode(exist.key)) == hash(hashcode(key))
            && Objects.equals(key, exist.key)) {
                rsl = exist.value;
            }
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        int i = indexFor(hash(hashcode(key)));
        MapEntry<K, V> exist = table[i];
        boolean rsl = exist != null;
        if (rsl
        && hash(hashcode(key)) == hash(hashcode(exist.key))
        && Objects.equals(key, exist.key)) {
            table[i] = null;
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