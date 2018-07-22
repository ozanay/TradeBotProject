package com.trade.bot.data.inducator;

import com.trade.bot.TradeData;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * @author Ozan Ay
 */
public class TestListOperationApplier {

    @Test(dataProvider = "testTradeData")
    public void operation_applier_successfully_returns_min_max_and_average(List<TradeData> testData, double expectedMin, double expectedMax, double expectedAverage) {
        OperationResult result = ListOperationApplier.apply(testData);

        assertEquals(result.getMin(), expectedMin);
        assertEquals(result.getMax(), expectedMax);
        assertEquals(result.getAverage(), expectedAverage);
    }

    @DataProvider
    private Object[][] testTradeData() {
        return new Object[][] {
                {Arrays.asList(new TestTradeData(3), new TestTradeData(3), new TestTradeData(3), new TestTradeData(3), new TestTradeData(3)), 3, 3, 3},
                {Arrays.asList(new TestTradeData(3), new TestTradeData(4), new TestTradeData(5), new TestTradeData(6), new TestTradeData(7)), 3, 7, 5},
                {Arrays.asList(new TestTradeData(3), new TestTradeData(3), new TestTradeData(5), new TestTradeData(6), new TestTradeData(7)), 3, 7, 4.8},
                {Arrays.asList(new TestTradeData(3), new TestTradeData(4), new TestTradeData(5), new TestTradeData(7), new TestTradeData(7)), 3, 7, 5.2},
                {Arrays.asList(new TestTradeData(22), new TestTradeData(44), new TestTradeData(44), new TestTradeData(55), new TestTradeData(99)), 22, 99, 52.8},
        };
    }

    private static class TestTradeData implements TradeData {
        private final double price;

        TestTradeData(double price) {
            this.price = price;
        }

        @Override
        public double getPrice() {
            return this.price;
        }
    }
}
