package com.trade.bot.data.indicator;

import com.trade.bot.TradeData;
import com.trade.bot.math.MathUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ozan Ay
 */
public class HullMovingAverage implements Indicator {
    private List<Double> prices = new ArrayList<>();
    private List<Double> hullDiffs = new ArrayList<>();
    private final int period;
    private final int halfPeriod;
    private final int sqrtPeriod;
    
    public HullMovingAverage(int period) {
        if (period < 0) {
            throw new IllegalArgumentException("Period cannot be less than zero.");
        }
        
        this.period = period;
        this.halfPeriod = (int) Math.round(period * 0.5);
        this.sqrtPeriod = (int) Math.round(Math.sqrt(period));
    }
    
    @Override
    public IndicatorResult apply(TradeData tradeData) {
        warmUp(tradeData);
        double hullMovingAverage = calculateHullMovingAverage();
        return new IndicatorResult<>(hullMovingAverage);
    }
    
    @Override
    public void warmUp(TradeData tradeData) {
        prices.add(tradeData.getPrice());
        double diff = calculateHullDiff();
        hullDiffs.add(diff);
    }
    
    private double calculateHullDiff() {
        double doubleWmaWithHalfPeriod = calculateDoubleWeightedMovingAverageWithHalfPeriod();
        double wmaWithFullPeriod = calculateWeightedMovingAverageWithFullPeriod();
        return doubleWmaWithHalfPeriod - wmaWithFullPeriod;
    }
    
    private double calculateDoubleWeightedMovingAverageWithHalfPeriod() {
        double result = 0.0;
        if (prices.size() >= halfPeriod) {
            result = 2 * MathUtil.calculateWeightedMovingAverage(prices, halfPeriod);
        }
        
        return result;
    }
    
    private double calculateWeightedMovingAverageWithFullPeriod() {
        double result = 0.0;
        if (prices.size() >= period) {
            result = MathUtil.calculateWeightedMovingAverage(prices, period);
        }
    
        return result;
    }
    
    private double calculateHullMovingAverage() {
        double result = 0.0;
        if (hullDiffs.size() >= sqrtPeriod) {
            result = MathUtil.calculateWeightedMovingAverage(hullDiffs, sqrtPeriod);
        }
    
        return result;
    }
}
