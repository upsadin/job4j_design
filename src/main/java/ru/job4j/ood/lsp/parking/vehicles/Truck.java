package ru.job4j.ood.lsp.parking.vehicles;

public class Truck extends Vehicle {
    public Truck(int size) {
        super("Truck", size);
        if (size < 1 || size > 7) {
            throw new IllegalArgumentException("Invalid truck size");
        }
    }
}
