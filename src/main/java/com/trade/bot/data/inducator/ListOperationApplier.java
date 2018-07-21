package com.trade.bot.data.inducator;

import com.trade.bot.TradeData;

import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;

/**
 * @author Ozan Ay
 */
public final class ListOperationApplier {
    private ListOperationApplier(){}

    static OperationResult apply(List<TradeData> tradeData) {
        DoubleStream stream = getDoubleStream(tradeData);
        double min = getMin(stream);
        double max = getMax(stream);
        double average = getAverage(stream);

        return new OperationResult(min, max, average);
    }

    private static DoubleStream getDoubleStream(List<TradeData> tradeData) {
        return tradeData
                .stream()
                .mapToDouble(TradeData::getPrice);
    }

    private static double getMax(DoubleStream stream) {
        OptionalDouble max = stream.max();
        return (max.isPresent()) ? max.getAsDouble() : 0;
    }

    private static double getMin(DoubleStream stream) {
        OptionalDouble min = stream.min();
        return (min.isPresent()) ? min.getAsDouble() : 0;
    }

    private static double getAverage(DoubleStream stream) {
        OptionalDouble average = stream.average();
        return (average.isPresent()) ? average.getAsDouble() : 0;
    }
}
