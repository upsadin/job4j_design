package ru.job4j.ood.productstore.products;

import java.time.LocalDate;

public class Food extends Products {
    public Food(String name, LocalDate expiryDate, LocalDate createDate, double price, double discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
