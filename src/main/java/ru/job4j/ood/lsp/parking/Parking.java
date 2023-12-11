package ru.job4j.ood.lsp.parking;

import ru.job4j.ood.lsp.parking.vehicles.Vehicle;

public interface Parking {
    void add(Vehicle vehicle);
    boolean remove(Vehicle vehicle);
}
