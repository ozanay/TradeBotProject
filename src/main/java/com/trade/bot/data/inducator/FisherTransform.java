package com.trade.bot.data.inducator;

import com.trade.bot.CircularList;
import com.trade.bot.TradeData;

import java.util.List;
import java.util.OptionalDouble;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Ozan Ay
 */
public class FisherTransform implements Inducator{
    private static final Logger LOGGER = Logger.getLogger(FisherTransform.class.getName());
    private static final int TOTAL_BAR_COUNT = 10;
    private static final CircularList<Double> FisherMaxs = new CircularList<>(TOTAL_BAR_COUNT);
    private static final CircularList<Double> FisherMins = new CircularList<>(TOTAL_BAR_COUNT);
    private static final CircularList<Double> TriggerMaxs = new CircularList<>(TOTAL_BAR_COUNT);
    private static final CircularList<Double> TriggerMins = new CircularList<>(TOTAL_BAR_COUNT);

    @Override
    public AppliedInducatorResult apply(List<TradeData> oneBarTradeData) {
        OptionalDouble optionalAverage = oneBarTradeData
                .stream()
                .mapToDouble(TradeData::getPrice)
                .average();
        double average = (optionalAverage.isPresent()) ? optionalAverage.getAsDouble() : 0;
        LOGGER.log(Level.INFO, "Average: " + average);

        return null;
    }

}
