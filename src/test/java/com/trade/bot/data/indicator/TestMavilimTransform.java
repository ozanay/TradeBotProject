package com.trade.bot.data.indicator;

import com.trade.bot.TradeData;
import com.trade.bot.math.MathUtil;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * @author Ozan Ay
 */
public class TestMavilimTransform {
    private static final int FIRST = 0;
    private static final int FMAL = 1;
    private static final int SMAL = 1;
    private static final int TMAL = FMAL + SMAL;
    private static final int F_MAL = TMAL + SMAL;
    private static final int FT_MAL = F_MAL + TMAL;
    private static final int S_MAL = FT_MAL + F_MAL;
    
    private MavilimTransform sut;
    
    @BeforeMethod
    public void beforeMethod() {
        sut = new MavilimTransform(new Mavilim(FMAL, SMAL));
    }
    
    @Test
    public void test() {
        //Given
        List<Double> prices = getPrices();
        int size = prices.size();
        for (int i = 0; i < size - 1; i++) {
            sut.warmUp(new TestTradeData(prices.get(i), new Date()));
        }
        
        //When
        IndicatorResult result = sut.apply(new TestTradeData(prices.get(size - 1), new Date()));
        double actual = (double) result.getValue();
        
        //Then
        List<Double> m1s = getMs(getPrices(), FMAL);
        List<Double> m2s = getMs(m1s, SMAL);
        List<Double> m3s = getMs(m2s, TMAL);
        List<Double> m4s = getMs(m3s, F_MAL);
        List<Double> m5s = getMs(m4s, FT_MAL);
        double expectedMavi = MathUtil.calculateWeightedMovingAverage(m5s, S_MAL);
        
        assertEquals(actual, expectedMavi);
    }
    
    private List<Double> getPrices() {
        return java.util.Arrays
            .asList(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0, 13.0, 14.0, 15.0, 16.0, 17.0, 18.0, 19.0, 20.0);
    }
    
    private List<Double> getMs(List<Double> values, int period) {
        List<Double> Ms = new ArrayList<>();
        if (values != null) {
            int counterForEachWMA = 0;
            List<Double> wmaList = new ArrayList<>();
            for (double value : values) {
                if (counterForEachWMA < period) {
                    wmaList.add(value);
                    counterForEachWMA++;
                } else {
                    double m = MathUtil.calculateWeightedMovingAverage(wmaList, period);
                    Ms.add(m);
                    wmaList.remove(FIRST);
                    wmaList.add(value);
                }
            }

            double lastM = MathUtil.calculateWeightedMovingAverage(wmaList, period);
            Ms.add(lastM);
        }

        return Ms;
    }
    
    private class TestTradeData implements TradeData {
        private double price;
        private Date date;
    
        TestTradeData(double price, Date date) {
            this.price = price;
            this.date = date;
        }
    
        @Override
        public double getPrice() {
            return this.price;
        }
    
        @Override
        public Date getEventTime() {
            return this.date;
        }
    
        @Override
        public int compareByDate(TradeData tradeData) {
            return this.date.compareTo(tradeData.getEventTime());
        }
    }
}
