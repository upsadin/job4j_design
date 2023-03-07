package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    /**
     * Метод проверяет наличие следующего элемента для метода {@next()}
     * Сначала проверяет, не привысил ли row возможного размера массива,
     * потом пропускает пустые вложенные массивы
     * @return булево значение, показывающее возможность или нет сделать следующего шага методу {@next()}
     */
    @Override
    public boolean hasNext() {
        if (row < data.length) {
            while (data[row].length == 0) {
                if (row + 1 >= data.length) {
                return false;
                }
            row++;
            }
        }
        return row < data.length;
    }

    /**
     * Через метод {@hasNext()} проверяет наличие следующего элемента и
     * либо возвращает его, либо исключение NoSuchElementException
     * @return элемент {@data} или исключение
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Integer rsl = data[row][column];
        if (column == data[row].length - 1) {
            row++;
            column = 0;
        } else {
            column++;
        }
        return rsl;
    }
}