package com.trade.bot.math;

import com.trade.bot.Price;
import com.trade.bot.math.WeightedMovingAverage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;


/**
 * @author Ozan Ay
 */
public class TestWeightedMovingAverage {

    @Test(dataProvider = "pricesAndWeightedMovingAverages")
    public void weighted_moving_average_is_calculated_successfully(List<Price> prices, int timePeriod, double expectedAverage) {
        double average = WeightedMovingAverage.calculate(prices, timePeriod);

        assertEquals(average, expectedAverage);
    }

    @DataProvider
    private Object[][] pricesAndWeightedMovingAverages() throws ParseException {
        return new Object[][] {
                {Arrays.asList(
                        new Price(1, "31-08-1982 10:20:56"),
                        new Price(2, "31-08-1982 10:21:56"),
                        new Price(3, "31-08-1982 10:22:56"),
                        new Price(4, "31-08-1982 10:23:56"),
                        new Price(5, "31-08-1982 10:24:56"),
                        new Price(6, "31-08-1982 10:25:56"),
                        new Price(7, "31-08-1982 10:19:56"),
                        new Price(8, "31-08-1982 10:27:56"),
                        new Price(9, "31-08-1982 10:18:56"),
                        new Price(10, "31-08-1982 10:30:56")), 10, 5.036363636363636}
        };
    }
}
