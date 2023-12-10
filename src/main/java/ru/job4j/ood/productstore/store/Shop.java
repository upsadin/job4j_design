package ru.job4j.ood.productstore.store;

import ru.job4j.ood.productstore.CalcProductTerm;
import ru.job4j.ood.productstore.ControlQuality;
import ru.job4j.ood.productstore.products.Products;

public class Shop extends AbstractStore {

    public Shop() {
        super("Shop", 1, 0.25f);
    }

    @Override
    public void add(Products product) {
        if (CalcProductTerm.calc(product) > 0.75f) {
            product.setPrice(product.getPrice() * 0.8);
        }
        super.add(product);
    }
}
