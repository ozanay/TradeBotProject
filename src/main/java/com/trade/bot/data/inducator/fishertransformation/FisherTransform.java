package com.trade.bot.data.inducator.fishertransformation;

import com.trade.bot.TradeData;
import com.trade.bot.data.inducator.IndicatorResult;
import com.trade.bot.data.inducator.Indicator;
import com.trade.bot.data.inducator.ListOperationApplier;
import com.trade.bot.data.inducator.OperationResult;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author Ozan Ay
 */
public class FisherTransform implements Indicator {
    private static final Logger LOGGER = Logger.getLogger(FisherTransform.class.getName());
    private static final int TOTAL_BAR_COUNT = 10;
    private static final double UPPER_LIMIT_FOR_VALUE = 0.999;
    private static final double LOWER_LIMIT_FOR_VALUE = -0.999;
    private static FisherTransformationVariable trigger = new FisherTransformationVariable();
    private static double counter = 0;
    private static double previousMax = 0;
    private static double previousMin = 0;
    private static double previousValue = 0;
    private static double previousFish = 0;
    private static double previousAveragePrice = 0;

    @Override
    public IndicatorResult apply(List<TradeData> oneBarTradeData) {
        OperationResult operationResult = ListOperationApplier.apply(oneBarTradeData);

        double minPriceInOneBar = operationResult.getMin();
        LOGGER.info( "MIN: " + minPriceInOneBar);

        double maxPriceInOneBar = operationResult.getMax();
        LOGGER.info("MAX: " + maxPriceInOneBar);

        IndicatorResult result;
        if (counter >= TOTAL_BAR_COUNT) {
            double currentMax = Math.max(previousMax, maxPriceInOneBar);
            double currentMin = Math.min(previousMin, minPriceInOneBar);
            double averagePriceInOneBar = operationResult.getAverage();
            LOGGER.info( "AVERAGE: " + averagePriceInOneBar);

            double currentValue = calculateValue(currentMin, currentMax, averagePriceInOneBar);
            double currentFish = calculateFish(currentValue);
            previousValue = currentValue;
            previousFish = currentFish;
            previousAveragePrice = averagePriceInOneBar;

            result = new IndicatorResult(previousFish, currentFish);
        } else {
            previousMax = Math.max(previousMax, maxPriceInOneBar);
            previousMin = Math.min(previousMin, minPriceInOneBar);
            counter ++;
            if (counter == TOTAL_BAR_COUNT) {
                previousAveragePrice = operationResult.getAverage();
                LOGGER.info( "TRIGGER AVG: " + previousAveragePrice);

                previousValue = calculateValue(previousMin, previousMax, previousAveragePrice);
                previousFish = calculateFish(previousValue);
            }

            result = IndicatorResult.emptyResult();
        }

        return result;
    }

    private static double calculateFish(double value) {
        return 0.5 * Math.log((1 + value) / (1 - value)) + 0.5 * previousFish;
    }

    private static double calculateValue(double min, double max, double price) {
        double currentValue = 0.5 * 2 * ((price - min)/ (max - min) - 0.5) + 0.5 * previousValue;

        if (currentValue > UPPER_LIMIT_FOR_VALUE) {
            currentValue = UPPER_LIMIT_FOR_VALUE;
        } else if (currentValue < LOWER_LIMIT_FOR_VALUE) {
            currentValue = LOWER_LIMIT_FOR_VALUE;
        }

        previousValue = currentValue;

        return currentValue;
    }
}
