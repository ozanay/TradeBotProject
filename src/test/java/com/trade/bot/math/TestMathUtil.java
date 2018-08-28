package com.trade.bot.math;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;


/**
 * @author Ozan Ay
 */
public class TestMathUtil {

    @Test(dataProvider = "pricesAndWeightedMovingAverages")
    public void weighted_moving_average_is_calculated_successfully(List<Double> values, int timePeriod, double expectedAverage) {
        double average = MathUtil.calculateWeightedMovingAverage(values, timePeriod);

        assertEquals(average, expectedAverage);
    }

    @DataProvider
    private Object[][] pricesAndWeightedMovingAverages() {
        return new Object[][] {
                {Arrays.asList(10.0, 8.0, 6.0, 5.0, 4.0, 3.0, 2.0, 1.0, 7.0, 9.0), 10, 5.036363636363636},
                {Arrays.asList(12.0, 11.0, 10.0, 8.0, 6.0, 5.0, 4.0, 3.0, 2.0, 1.0, 7.0, 9.0), 10, 5.036363636363636},
                {Arrays.asList(5.0, 6.0, 7.0, 8.0, 9.0), 5, 7.666666666666667}
        };
    }
}
