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
                int fmal = (int) args[FIRST];
                int smal = (int) args[SECOND];
                indicator = getMavilimTransform(fmal, smal);
                break;
            case HULL_MOVING_AVERAGE:
                indicator = getHullMovingAverage((int) args[FIRST]);
                break;
            default:
                indicator = getHullMovingAverage((int) args[FIRST]);
        }

        return indicator;
    }

    private static Indicator getHullMovingAverage(int period) {
        return new HullMovingAverage(period);
    }

    private static Indicator getMavilimTransform(int fmal, int smal) {
        Mavilim mavilim = new Mavilim(fmal, smal);
        return new MavilimTransform(mavilim);
    }
}
