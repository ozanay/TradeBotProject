package com.trade.bot.util;

import com.trade.bot.util.CircularArray;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * @author Ozan Ay
 */
public class TestCircularArray {
    private static final int CAPACITY = 5;
    private CircularArray<Integer> sut;

    @BeforeMethod
    public void beforeMethod() {
        sut = new CircularArray<>(CAPACITY);
    }

    @Test
    public void length_is_1() {
        sut.add(1);

        assertEquals(sut.size(), 1);
    }

    @Test
    public void first_element_is_1() {
        sut.add(1);

        assertEquals((int) sut.get(0), 1);
    }

    @Test
    public void first_element_is_2() {
        sut.add(2);

        assertEquals((int) sut.getFirst(), 2);
    }

    @Test
    public void latest_element_with_circular_adding_is_6() {
        List<Integer> testData = Arrays.asList(0, 1, 2, 3, 4, 5, 6);
        testData.forEach(integer -> sut.add(integer));

        assertEquals((int) sut.getLast(), 6);
    }

    @Test
    public void second_element_with_circular_adding_is_2() {
        List<Integer> testData = Arrays.asList(-1, 0, 1, 2, 3, 4, 5);
        testData.forEach(integer -> sut.add(integer));

        assertEquals((int) sut.get(1), 2);
    }

    @Test
    public void second_element_without_circular_adding_is_2() {
        List<Integer> testData = Arrays.asList(1, 2);
        testData.forEach(integer -> sut.add(integer));

        assertEquals((int) sut.get(1), 2);
    }

    @Test
    public void fourth_element_with_circular_adding_is_4() {
        List<Integer> testData = Arrays.asList(-1, 0, 1, 2, 3, 4, 5);
        testData.forEach(integer -> sut.add(integer));

        assertEquals((int) sut.get(3), 4);
    }

    @Test
    public void all_elements_are_in_ordered_according_to_indices_after_circular_adding_elements() {
        List<Integer> testData = Arrays.asList(-1, 0, 1, 2, 3, 4, 5);
        testData.forEach(integer -> sut.add(integer));

        int[] expectedValues = new int[] {1, 2, 3, 4, 5};

        List<Integer> all = sut.toList();
        for (int i = 0; i <all.size(); i++) {
            assertEquals(all.get(i).intValue(), expectedValues[i]);
        }
    }
}
