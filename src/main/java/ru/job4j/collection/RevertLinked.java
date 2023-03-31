package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RevertLinked<T> implements Iterable {
    Node<T> head;

    public void add(T value) {
        Node<T> newNode = new Node(value, null);
        if (head == null) {
            head = newNode;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = newNode;
    }

    public boolean revert() {
        boolean rsl = head != null && head.next != null;
        if (rsl) {
            Node<T> prev = head.next;
            Node<T> next = null;
            while (prev != null) {
                head.next = next;
                next = head;
                head = prev;
                prev = head.next;
            }
            head.next = next;
        }
        return rsl;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            Node<T> node = head;
            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.item;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T item;
        Node<T> next;

        public Node(T value, Node next) {
            this.item = value;
            this.next = next;
        }
    }
}
