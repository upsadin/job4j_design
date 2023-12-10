package ru.job4j.ood.productstore;

import ru.job4j.ood.productstore.products.Products;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CalcProductTerm {
    public static double calc(Products product) {
        long expDays = Math.abs(ChronoUnit.DAYS.between(product.getCreateDate(), product.getExpiryDate()));
        long passDays = Math.abs(ChronoUnit.DAYS.between(product.getCreateDate(), LocalDate.now()));
        return (double) passDays / expDays;
    }
}
