package ru.job4j.ood.productstore.store;

import ru.job4j.ood.productstore.products.Products;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {
    private String name;
    private final double topTerm;
    private final double bottomTerm;
    private List<Products> store = new ArrayList<>();

    public AbstractStore(String name, double topTerm, double bottomTerm) {
        this.name = name;
        this.topTerm = topTerm;
        this.bottomTerm = bottomTerm;
    }

    public String getName() {
        return name;
    }

    public double getTopTerm() {
        return topTerm;
    }

    public double getBottomTerm() {
        return bottomTerm;
    }

    public List<Products> getStore() {
        return store;
    }

    public void add(Products product) {
        store.add(product);
    }
}
