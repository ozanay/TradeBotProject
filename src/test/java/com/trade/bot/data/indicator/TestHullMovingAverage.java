package com.trade.bot.data.indicator;

import com.trade.bot.TradeData;
import com.trade.bot.math.MathUtil;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;

import static com.trade.bot.Constants.FIRST;
import static org.testng.Assert.assertEquals;

/**
 * @author Ozan Ay
 */
public class TestHullMovingAverage {
    private static final int PERIOD = 4;
    private static final int HALF_PERIOD = (int) Math.round(PERIOD * 0.5);
    private static final int SQRT_PERIOD = (int) Math.round(Math.sqrt(PERIOD));

    private HullMovingAverage sut;
    
    @BeforeMethod
    public void beforeMethod() {
        sut = new HullMovingAverage(PERIOD);
    }

    @Test
    public void hull_moving_average_is_zero_with_null_trade_data_list() {
        IndicatorResult result = sut.apply(null);

        assertEquals(result.getValue(), 0.0);
    }

    @Test
    public void hull_moving_average_is_zero_with_empty_trade_data_list() {
        IndicatorResult result = sut.apply(Collections.emptyList());

        assertEquals(result.getValue(), 0.0);
    }

    @Test
    public void hull_moving_average_is_successfully_calculated() {
        List<TradeData> testTradeData = getTestTradeData();

        double actualHullMovingAverage = (double) sut.apply(testTradeData).getValue();

        double expectedHullMovingAverage = calculateHullMovingAverage(testTradeData);

        assertEquals(actualHullMovingAverage, expectedHullMovingAverage);
    }

    private List<TradeData> getTestTradeData() {
        return Arrays.asList(new TestTradeData(1.0, new Date()), new TestTradeData(2.0, new Date()), new TestTradeData(3.0, new Date()),
                new TestTradeData(4.0, new Date()), new TestTradeData(5.0, new Date()));
    }

    private double calculateHullMovingAverage(List<TradeData> tradeData) {
        List<Double> prices = tradeData.stream().map(TradeData::getPrice).collect(Collectors.toList());
        List<Double> hullDiffs = new ArrayList<>();
        for (int i = 0; i < SQRT_PERIOD; i++) {
            double hullDiff = calculateHullDiff(prices);
            hullDiffs.add(FIRST, hullDiff);
            int lastPriceIndex = prices.size() - 1;
            prices.remove(lastPriceIndex);
        }

        return calculateWeightedMovingAverageWithSqrtPeriod(hullDiffs);
    }

    private double calculateHullDiff(List<Double> prices) {
        double doubleWmaWithHalfPeriod = calculateDoubleWeightedMovingAverageWithHalfPeriod(prices);
        double wmaWithPeriod = calculateWeightedMovingAverageWithPeriod(prices);
        return doubleWmaWithHalfPeriod - wmaWithPeriod;
    }

    private double calculateDoubleWeightedMovingAverageWithHalfPeriod(List<Double> prices) {
        return 2 * MathUtil.calculateWeightedMovingAverage(prices, HALF_PERIOD);
    }

    private double calculateWeightedMovingAverageWithPeriod(List<Double> prices) {
        return  MathUtil.calculateWeightedMovingAverage(prices, PERIOD);
    }

    private double calculateWeightedMovingAverageWithSqrtPeriod(List<Double> prices) {
        return  MathUtil.calculateWeightedMovingAverage(prices, SQRT_PERIOD);
    }
}
