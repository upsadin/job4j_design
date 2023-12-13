package ru.job4j.ood.lsp.parking;

import ru.job4j.ood.lsp.parking.vehicles.Vehicle;

/**
 * данный класс нужен в случае, когда под грузовой транспорт нет места среди мест для грузового транспорта,
 * этим классом с его единственным методом проверяем, можно ли разместить грузовой транспорт на парковке для легковых
 */
public class FreeInRowInArray {
    /**
     *
     * @param vehicles массив для обработки
     * @param end конечный индекс парковки для легковых, т.к. это нужно обследовать только на этом пространстве
     * @return возвращает массив из двух значений:
     * первое - это максимальное количество идущих подряд свободных мест
     * второе - позицию в массиве, с которой начинается этот отрезок пустых мест
     */
    public static int[] calc(Vehicle[] vehicles, int end) {
        int[] rsl = new int[2];
        int position = 0;
        int max = 0;
        int count = 0;
        for (int i = 0; i < end; i++) {
            if (vehicles[i] == null) {
                count++;
            } else if (vehicles[i] != null) {
                if (count > max) {
                    max = count;
                    position = i - max;
                }
                count = 0;
            }
        }
        if (count > max) {
            max = count;
            position = vehicles.length - max;
        }
        rsl[0] = max;
        rsl[1] = position;
        return rsl;
    }


}
