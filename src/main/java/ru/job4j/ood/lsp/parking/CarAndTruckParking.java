package ru.job4j.ood.lsp.parking;

import ru.job4j.ood.lsp.parking.vehicles.*;

public class CarAndTruckParking implements Parking {
    int carSpace;
    int truckSpace;
    Vehicle[] commonSpace = new Vehicle[carSpace + truckSpace];

    public CarAndTruckParking(int carSpace, int truckSpace) {
        this.carSpace = carSpace;
        this.truckSpace = truckSpace;
    }

    public int getCarSpace() {
        return carSpace;
    }

    public void setCarSpace(int carSpace) {
        this.carSpace = carSpace;
    }

    public int getTruckSpace() {
        return truckSpace;
    }

    public void setTruckSpace(int truckSpace) {
        this.truckSpace = truckSpace;
    }

    public Vehicle[] getCommonSpace() {
        return commonSpace;
    }

    public void setCommonSpace(Vehicle[] commonSpace) {
        this.commonSpace = commonSpace;
    }

    @Override
    public void add(Vehicle vehicle) {

    }

    @Override
    public boolean remove(Vehicle vehicle) {
        return false;
    }
}
