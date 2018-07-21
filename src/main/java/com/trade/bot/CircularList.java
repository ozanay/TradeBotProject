package com.trade.bot;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ozan Ay
 */
public class CircularList<E> {
    private int lastIndex;
    private int capacity;
    private List<E> elements;
    private boolean isCirculationBegin;

    @SuppressWarnings("unchecked")
    public CircularList(int capacity) {
        this.capacity = capacity;
        this.lastIndex = -1;
        this.elements = new ArrayList<>();
    }

    public void add(E element) {
        lastIndex = (lastIndex + 1) % capacity;
        if (elements.size() < capacity) {
            elements.add(element);
            if (elements.size() == capacity) {
                isCirculationBegin = true;
            }
        } else {
            elements.set(lastIndex, element);
        }
    }

    public E get(int index) {
        if (index < 0 || index > capacity - 1) {
            throw new IllegalArgumentException("Index out of boundary.");
        }

        int elementIndex = (isCirculationBegin) ? (this.lastIndex + index + 1) % this.capacity : index;
        return elements.get(elementIndex);
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return elements.get(lastIndex);
    }

    @SuppressWarnings("unchecked")
    public List<E> all() {
        List<E> all = new ArrayList<>();
        for (int i = 0; i < elements.size(); i++) {
            all.add(get(i));
        }

        return all;
    }

    public int size() {
        return elements.size();
    }
}
