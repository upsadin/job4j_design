package ru.job4j.ood.productstore;

import ru.job4j.ood.productstore.products.Products;
import ru.job4j.ood.productstore.store.Store;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ControlQuality {
    private List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void check(Products product) {
        for (Store store : stores) {
            double foodTerm = CalcProductTerm.calc(product);
            if (foodTerm >= store.getBottomTerm() && foodTerm < store.getTopTerm()) {
                store.add(product);
            }
        }
    }

}
