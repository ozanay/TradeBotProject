package com.trade.bot.data.inducator;

import com.trade.bot.TradeData;

import java.util.DoubleSummaryStatistics;
import java.util.List;

/**
 * @author Ozan Ay
 */
public final class ListOperationApplier {
    private ListOperationApplier(){}

    public static OperationResult apply(List<TradeData> tradeData) {
        DoubleSummaryStatistics statistics = getDoubleStream(tradeData);
        double min = statistics.getMin();
        double max = statistics.getMax();
        double average = statistics.getAverage();

        return new OperationResult(min, max, average);
    }

    private static DoubleSummaryStatistics getDoubleStream(List<TradeData> tradeData) {
        return tradeData
                .stream()
                .mapToDouble(TradeData::getPrice).summaryStatistics();
    }
}
