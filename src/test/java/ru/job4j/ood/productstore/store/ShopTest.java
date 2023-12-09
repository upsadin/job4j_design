package ru.job4j.ood.productstore.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.productstore.products.Drink;
import ru.job4j.ood.productstore.products.Products;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class ShopTest {
    @Test
    public void whenAddThenGetDrink() {
        Store store = new Shop();
        Products water = new Drink("Still water", LocalDate.now().plusMonths(7), LocalDate.now().minusMonths(9), 55, 0);
        store.add(water);
        assertThat(store.getStore()).contains(water);
    }

    @Test
    public void whenAddWithDiscountThenGetDrink() {
        Store store = new Shop();
        Products water = new Drink("Still water", LocalDate.now().plusMonths(2), LocalDate.now().minusMonths(9), 55, 0);
        store.add(water);
        assertThat(store.getStore().get(0).getPrice()).isEqualTo(44);
    }
}