package com.trade.bot.data.indicator;

import java.io.IOException;

import com.trade.bot.configuration.HullMovingAverageParameters;
import com.trade.bot.util.JsonUtil;

/**
 * @author Ozan Ay
 */
public class IndicatorFactory {
    private IndicatorFactory(){}
    
    public static Indicator getIndicator(String parameters) throws IOException {
        return getHullMovingAverage(parameters);
    }

    private static Indicator getHullMovingAverage(String parametersJson) throws IOException {
        HullMovingAverageParameters parameters = JsonUtil.parse(parametersJson, HullMovingAverageParameters.class);
        return new HullMovingAverage(parameters.getPeriod());
    }
}
