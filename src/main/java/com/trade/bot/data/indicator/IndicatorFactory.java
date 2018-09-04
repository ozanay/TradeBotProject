package com.trade.bot.data.indicator;

import static com.trade.bot.Constants.FIRST;
import static com.trade.bot.Constants.SECOND;

/**
 * @author Ozan Ay
 */
public class IndicatorFactory {
    public static Indicator getIndicator(IndicatorEnum indicatorEnum, Object...args) {
        Indicator indicator;
        switch (indicatorEnum) {
            case MAVILIM:
                indicator = getMavilimTransform(args);
                break;
            case HULL_MOVING_AVERAGE:
                indicator = getHullMovingAverage(args);
                break;
            default:
                indicator = getHullMovingAverage(args);
        }

        return indicator;
    }

    private static Indicator getHullMovingAverage(Object... args) {
        int period = (int) args[FIRST];
        return new HullMovingAverage(period);
    }

    private static Indicator getMavilimTransform(Object... args) {
        int fmal = (int) args[FIRST];
        int smal = (int) args[SECOND];
        Mavilim mavilim = new Mavilim(fmal, smal);
        return new MavilimTransform(mavilim);
    }
}
