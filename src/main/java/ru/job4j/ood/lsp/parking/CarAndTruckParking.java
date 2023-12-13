package ru.job4j.ood.lsp.parking;

import ru.job4j.ood.lsp.parking.vehicles.*;

/**
 * Класс представляет собой одну из реализация парковки, которая работает только с двумя видами транспорта {@link Vehicle}
 * Car(легковые автомобили) и Truck (грузовые автомобили)
 * @author Павел У.
 * @version 1.0
 */
public class CarAndTruckParking implements Parking {
    /**
     * поля carSpace и truckSpace содержат количество доступных мест для легковых и грузовых авто соответственно
     * сама парковка содержится внутри массива commonSpace с разделением на пространство для легковых автомобилей
     * (от 0 до carSpace - 1) и для грузовых автомобилей (от carSpace до truckSpace)
     */
    private int carSpace;
    private int truckSpace;
    private int partition;
    Vehicle[] commonSpace;

    public CarAndTruckParking(int carSpace, int truckSpace) {
        this.carSpace = carSpace;
        this.truckSpace = truckSpace;
        this.partition = carSpace;
        this.commonSpace = new Vehicle[carSpace + truckSpace];
    }

    public int getCarSpace() {
        return carSpace;
    }

    public int getTruckSpace() {
        return truckSpace;
    }

    public Vehicle[] getCommonSpace() {
        return commonSpace;
    }

    /**
     * метод служит для добавления траспорта в массив парковки
     * @param vehicle представляет траспорт, который поступает на парковку, по его размеру определяется тип
     *                транспорта и передается в соответствующее пространство массива
     */
    @Override
    public void add(Vehicle vehicle) {
        int carSize = vehicle.getSize();
        CheckPlace.check(vehicle, this);
        if (carSize == 1) {
            for (int i = 0; i < partition; i++) {
                if (commonSpace[i] == null) {
                    commonSpace[i] = vehicle;
                    carSpace--;
                    break;
                }
            }
        } else {
            if (truckSpace > 0) {
                for (int i = partition; i < commonSpace.length; i++) {
                    if (commonSpace[i] == null) {
                        commonSpace[i] = vehicle;
                        truckSpace--;
                        break;
                    }
                }
            } else {
                int[] inRow = FreeInRowInArray.calc(commonSpace, partition);
                if (carSize <= inRow[0]) {
                    for (int i = 0; i < carSize; i++) {
                        commonSpace[inRow[1] + i] = vehicle;
                    }
                }
            }
        }
    }

    /**
     * метод убирает траспорт с парковки, освобождая место в массиве
     * @param vehicle ищется среди массива, и в зависимости от типа траспорта освобождается место в соответствующей типу  пространстве
     * @return булево значение для понимания, удалось найти и убрать транспорт или нет
     */

    @Override
    public boolean remove(Vehicle vehicle) {
        boolean rsl = false;
        for (int i = 0; i < partition; i++) {
            if (vehicle.equals(commonSpace[i])) {
                commonSpace[i] = null;
                carSpace++;
                rsl = true;
                if (vehicle.getSize() == 1) {
                    break;
                }
            }
        }
        for (int i = partition; i < commonSpace.length; i++) {
            if (vehicle.equals(commonSpace[i])) {
                commonSpace[i] = null;
                truckSpace++;
                rsl = true;
            }
        }
        return rsl;
    }
}
