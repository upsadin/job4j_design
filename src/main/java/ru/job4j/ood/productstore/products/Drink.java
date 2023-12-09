package ru.job4j.ood.productstore.products;

import java.time.LocalDate;

public class Drink extends Products {
    public Drink(String name, LocalDate expiryDate, LocalDate createDate, double price, double discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
