package com.trade.bot.data.indicator;

import java.io.IOException;

/**
 * @author Ozan Ay
 */
public class IndicatorFactory {
    private IndicatorFactory(){}
    
    public static Indicator getIndicator(String parameters) throws IOException {
        return new Macdas(parameters);
    }
}
