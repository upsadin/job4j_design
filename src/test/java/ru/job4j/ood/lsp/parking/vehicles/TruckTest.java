package ru.job4j.ood.lsp.parking.vehicles;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@Disabled
class TruckTest {
    @Test
    public void whenCreateInvalidTruck() {
        assertThatThrownBy(() -> new Truck(0)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenCreateInvalidTruck2() {
        assertThatThrownBy(() -> new Truck(10)).isInstanceOf(IllegalArgumentException.class);
    }

}