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
     * Метод проверяет наличие следующего элемента для метода {@link #next()}
     * @return булево значение, показывающее возможность или нет сделать следующего шага методу {@link #next()}
     */
    @Override
    public boolean hasNext() {
        if (column > data[row].length - 1 && data[row].length > 0) {
            row++;
            column = 0;
        }
            while (row < data.length && data[row].length == 0) {
                row++;
            }
        return row < data.length;
    }

    /**
     * Через метод {@link #hasNext()}  проверяет наличие следующего элемента и
     * либо возвращает его, либо исключение NoSuchElementException
     * @return элемент {@link #data} или исключение
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }
}