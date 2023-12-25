package ru.job4j.ood.productstore.store;

import ru.job4j.ood.productstore.products.Products;

import java.util.List;

public interface Store {
    void add(Products product);
     String getName();
     double getTopTerm();
     double getBottomTerm();
     List<Products> getStore();
     void clearStore();
}
