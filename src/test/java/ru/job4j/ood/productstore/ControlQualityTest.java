package ru.job4j.ood.productstore;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.productstore.products.Food;
import ru.job4j.ood.productstore.products.Products;
import ru.job4j.ood.productstore.store.Shop;
import ru.job4j.ood.productstore.store.Store;
import ru.job4j.ood.productstore.store.Trash;
import ru.job4j.ood.productstore.store.Warehouse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {
    Store shop = new Shop();
    Store trash = new Trash();
    Store warehouse = new Warehouse();
    List<Store> stores = List.of(shop, trash, warehouse);
    ControlQuality control = new ControlQuality(stores);

    @Test
    public void whenPutInWarehouse() {
        Products eggs = new Food("Eggs", LocalDate.now().plusDays(15), LocalDate.now(), 100, 0);
        control.check(eggs);
        assertThat(warehouse.getStore()).contains(eggs);
    }

    @Test
    public void whenPutInTrash() {
        Products badEggs = new Food("Eggs", LocalDate.now(), LocalDate.now().minusDays(15), 100, 0);
        control.check(badEggs);
        assertThat(trash.getStore()).contains(badEggs);
    }

    @Test
    public void whenPutInShopWithoutDiscount() {
        Products normalEggs = new Food("Eggs", LocalDate.now().plusDays(7), LocalDate.now().minusDays(8), 100, 0);
        control.check(normalEggs);
        assertThat(shop.getStore().get(0).getPrice()).isEqualTo(100);
    }

    @Test
    public void whenPutInShopWithDiscount() {
        Products discountEggs = new Food("Eggs", LocalDate.now().plusDays(3), LocalDate.now().minusDays(12), 100, 0);
        control.check(discountEggs);
        assertThat(shop.getStore().get(0).getPrice()).isEqualTo(80);
    }

}