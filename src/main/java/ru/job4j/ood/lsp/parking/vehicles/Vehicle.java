package ru.job4j.ood.lsp.parking.vehicles;

import java.util.Objects;

public abstract class Vehicle {
    String name;
    int size;

    public Vehicle(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vehicle cars = (Vehicle) o;
        return size == cars.size && Objects.equals(name, cars.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size);
    }
}
