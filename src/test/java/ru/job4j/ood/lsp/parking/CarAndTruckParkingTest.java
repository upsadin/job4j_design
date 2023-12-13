package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import ru.job4j.ood.lsp.parking.vehicles.Car;
import ru.job4j.ood.lsp.parking.vehicles.Truck;
import ru.job4j.ood.lsp.parking.vehicles.Vehicle;

@Disabled
class CarAndTruckParkingTest {

    @Test
    public void whenAddCarThenCkeckThem() {
        CarAndTruckParking parking = new CarAndTruckParking(1, 1);
        Vehicle car = new Car();
        Vehicle truck = new Truck(2);
        parking.add(car);
        parking.add(truck);
        assertThat(parking.getCommonSpace()).contains(car, truck);
    }

    @Test
    public void whenHaveOnlyCarSpaceForTruck() {
        CarAndTruckParking parking = new CarAndTruckParking(2, 0);
        Vehicle truck = new Truck(2);
        parking.add(truck);
        assertThat(parking.getCommonSpace()).contains(truck);
    }

    @Test
    public void whenHaveOnlyCarSpaceForTruckThenRemoveIt() {
        CarAndTruckParking parking = new CarAndTruckParking(2, 0);
        Vehicle truck = new Truck(2);
        parking.add(truck);
        parking.remove(truck);
        assertThat(parking.getCommonSpace()).doesNotContain(truck);
    }


    @Test
    public void whenHaveOnlyTuckSpaceForCar() {
        CarAndTruckParking parking = new CarAndTruckParking(0, 1);
        Vehicle car = new Car();
        assertThatThrownBy(() -> parking.add(car)).isInstanceOf(ArrayStoreException.class)
                .hasMessageContaining("Parking doesn't have free space");
    }

    @Test
    public void whenAddCarsThenRemove() {
        CarAndTruckParking parking = new CarAndTruckParking(1, 1);
        Vehicle car = new Car();
        Vehicle truck = new Truck(2);
        parking.add(car);
        parking.add(truck);
        parking.remove(truck);
        assertThat(parking.getCommonSpace()).doesNotContain(truck);
    }

    @Test
    public void whenDoesntHavePlaceForTruck() {
        CarAndTruckParking parking = new CarAndTruckParking(1, 0);
        Vehicle car = new Car();
        Vehicle truck = new Truck(2);
        parking.add(car);
        assertThatThrownBy(() -> parking.add(truck)).isInstanceOf(ArrayStoreException.class)
                .hasMessageContaining("Parking doesn't have free space");
    }

    @Test
    public void whenRemoveNonExistedCar() {
        CarAndTruckParking parking = new CarAndTruckParking(1, 0);
        Vehicle car = new Car();
        assertThat(parking.remove(car)).isFalse();
    }

}