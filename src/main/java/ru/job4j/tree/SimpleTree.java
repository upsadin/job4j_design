package ru.job4j.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class SimpleTree<E> implements Tree<E> {

    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> p = findBy(parent);
        boolean rsl = p.isPresent() && findBy(child).isEmpty();
            if (rsl) {
                Node<E> el = p.get();
                el.children.add(new Node(child));
            }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
     Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}
