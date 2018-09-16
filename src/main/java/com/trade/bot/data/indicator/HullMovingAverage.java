package com.trade.bot.data.indicator;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.trade.bot.TradeData;
import com.trade.bot.util.MathUtil;
import com.trade.bot.util.ListUtil;

/**
 * @author Ozan Ay
 */
class HullMovingAverage implements Indicator {
    private final int period;
    private final int halfPeriod;
    private final int sqrtPeriod;
    
    HullMovingAverage(int period) {
        if (period < 0) {
            throw new IllegalArgumentException("Period cannot be less than zero.");
        }
        
        this.period = period;
        this.halfPeriod = (int) Math.round(period * 0.5);
        this.sqrtPeriod = (int) Math.round(Math.sqrt(period));
    }

    @Override
    public IndicatorResult apply(List<TradeData> tradeDataList) {
        double hullMovingAverage = 0.0;
        if (ListUtil.hasAnyValue(tradeDataList)) {
            List<Double> prices = tradeDataList.stream().map(TradeData::getPrice).collect(Collectors.toList());
            List<Double> hullDiffs = createHullDifferenceList(prices);

            hullMovingAverage = calculateHullMovingAverage(hullDiffs);
        }

        return new IndicatorResult<>(hullMovingAverage);
    }

    private List<Double> createHullDifferenceList(List<Double> prices) {
        LinkedList<Double> hullDiffs = new LinkedList<>();
        for (int i = 0; i < sqrtPeriod; i++) {
            double hullDiff = calculateHullDiff(prices);
            hullDiffs.addFirst(hullDiff);
            int lastPriceIndex = prices.size() - 1;
            prices.remove(lastPriceIndex);
        }

        return hullDiffs;
    }

    private double calculateHullDiff(List<Double> prices) {
        double doubleWmaWithHalfPeriod = calculateDoubleWmaWithHalfPeriod(prices);
        double wmaWithPeriod = calculateWmaWithPeriod(prices);
        return doubleWmaWithHalfPeriod - wmaWithPeriod;
    }

    private double calculateDoubleWmaWithHalfPeriod(List<Double> prices) {
        return 2 * MathUtil.calculateWeightedMovingAverage(prices, halfPeriod);
    }

    private double calculateWmaWithPeriod(List<Double> prices) {
        return MathUtil.calculateWeightedMovingAverage(prices, period);
    }

    private double calculateHullMovingAverage(List<Double> hullDiffs) {
        return MathUtil.calculateWeightedMovingAverage(hullDiffs, sqrtPeriod);
    }
}
