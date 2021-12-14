package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index = -1;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return getNextEvenNumberIndex(index) != -1;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        index = getNextEvenNumberIndex(index);
        return data[index];
    }

    private Integer getNextEvenNumberIndex(int idx) {
        int i = ++idx;
        while (i < data.length) {
            if (data[i] % 2 == 0) {
                return i;
            }
            i++;
        }
        return -1;
    }
}
