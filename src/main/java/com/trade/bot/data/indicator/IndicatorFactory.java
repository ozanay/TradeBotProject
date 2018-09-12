package com.trade.bot.data.indicator;

import com.trade.bot.configuration.HullMovingAverageParameters;
import com.trade.bot.configuration.MavilimParameters;
import com.trade.bot.util.JsonUtil;

import java.io.IOException;

/**
 * @author Ozan Ay
 */
public class IndicatorFactory {
    private IndicatorFactory(){}
    
    public static Indicator getIndicator(IndicatorEnum indicatorEnum, String parameters) throws IOException {
        Indicator indicator;
        switch (indicatorEnum) {
            case MAVILIM:
                indicator = getMavilimTransform(parameters);
                break;
            case HULL_MOVING_AVERAGE:
                indicator = getHullMovingAverage(parameters);
                break;
            default:
                indicator = getHullMovingAverage(parameters);
        }

        return indicator;
    }

    private static Indicator getHullMovingAverage(String parametersJson) throws IOException {
        HullMovingAverageParameters parameters = JsonUtil.parse(parametersJson, HullMovingAverageParameters.class);
        return new HullMovingAverage(parameters.getPeriod());
    }

    private static Indicator getMavilimTransform(String parametersJson) throws IOException {
        MavilimParameters parameters = JsonUtil.parse(parametersJson, MavilimParameters.class);
        Mavilim mavilim = new Mavilim(parameters.getFmal(), parameters.getSmal());
        return new MavilimTransform(mavilim);
    }
}
