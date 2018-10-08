package com.trade.bot.util.math;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * @author Ozan Ay
 */
public class TestExponentialMovingAverage {
    private static final int PERIOD = 10;
    private ExponentialMovingAverage sut;

    @BeforeMethod
    public void beforeMethod() {
        sut = new ExponentialMovingAverage(PERIOD);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void null_value_is_illegal() {
        sut.calculateInitiallyThroughList(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void empty_values_is_illegal() {
        sut.calculateInitiallyThroughList(Collections.emptyList());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void values_with_count_les_than_period_are_illegal() {
        sut.calculateInitiallyThroughList(Arrays.asList(3.0, 4.0, 5.0));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void calculation_with_previous_ema_without_initially_calculation_is_illegal() {
        sut.calculateWithPreviousEma(5.0);
    }

    private static List<Double> getInitialValues() {
        return Arrays.asList(22.27, 22.19, 22.08, 22.17, 22.18, 22.13, 22.23, 22.43, 22.24, 22.29);
    }

    @Test
    public void initial_calculation_through_list_with_same_size_period_is_successful() {
        List<Double> initialValues = getInitialValues();
        double initialEma = sut.calculateInitiallyThroughList(initialValues);

        double expected = 22.221;

        assertEquals(initialEma, expected);
    }

    private static List<Double> getInitialValuesWithSizeGreaterThanPeriod() {
        return Arrays.asList(22.27, 22.19, 22.08, 22.17, 22.18, 22.13, 22.23, 22.43, 22.24, 22.29, 22.15, 22.39, 22.38);
    }

    @Test
    public void initial_calculation_through_list_with_size_greater_than_period_is_successful() {
        List<Double> initialValues = getInitialValuesWithSizeGreaterThanPeriod();
        double initialEma = roundToTwoDecimal(sut.calculateInitiallyThroughList(initialValues));

        double expected = 22.27;

        assertEquals(initialEma, expected);
    }

    @DataProvider
    public Object[][] getTestData() {
        return new Object[][] {
                {Arrays.asList(22.15, 22.39, 22.38, 22.61, 23.35, 24.05, 23.75, 23.83, 23.95, 23.63, 23.82, 23.87, 23.65, 23.18, 23.09, 23.32, 22.68, 23.09, 22.40, 22.17),
                Arrays.asList(22.21, 22.24, 22.27, 22.33, 22.51, 22.79, 22.97, 23.12, 23.27, 23.34, 23.43, 23.51, 23.53, 23.47, 23.40, 23.39, 23.26, 23.23, 23.08, 22.91)}
        };
    }

    @Test(dataProvider = "getTestData")
    public void calculation_with_previous_ema_and_initial_configuration_is_successful(List<Double> incomingValues, List<Double> expectedEmas) {
        sut.calculateInitiallyThroughList(getInitialValues());

        assertEquals(incomingValues.size(), expectedEmas.size());

        for (int i = 0; i < incomingValues.size(); i++) {
            double value = incomingValues.get(i);
            double ema = roundToTwoDecimal(sut.calculateWithPreviousEma(value));

            Double expected = expectedEmas.get(i);
            assertEquals(ema, expected);
        }
    }

    private static double roundToTwoDecimal(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
