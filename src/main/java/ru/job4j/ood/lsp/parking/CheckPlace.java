package ru.job4j.ood.lsp.parking;

import ru.job4j.ood.lsp.parking.vehicles.Vehicle;

/**
 * класс и его единственный метод служит с единственной целью - сразу проверить наличие места на парковке под транспорт
 */
public class CheckPlace {
    public static boolean check(Vehicle vehicle, CarAndTruckParking parking) {
        int vehSize = vehicle.getSize();
        int carSpace = parking.getCarSpace();
        int truckSpace = parking.getTruckSpace();
        if (
                (carSpace + truckSpace == 0)
        || (vehSize == 1 && carSpace < 1)
        || ((vehSize > 1 && carSpace < vehSize) && (vehSize > 1 && truckSpace < 1))
        )  {
            throw new ArrayStoreException("Parking doesn't have free space");
        }
        return true;
    }
}
