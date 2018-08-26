package com.trade.bot.data.indicator;

import static com.trade.bot.Constants.FIRST;
import static com.trade.bot.Constants.SECOND;

/**
 * @author Ozan Ay
 */
public class IndicatorFactory {
    public static Indicator getIndicator(IndicatorEnum indicatorEnum, Object...args) {
        Indicator indicator = null;
        if (indicatorEnum.equals(IndicatorEnum.MAVILIM)) {
            int fmal = (int) args[FIRST];
            int smal = (int) args[SECOND];
            Mavilim mavilim = new Mavilim(fmal, smal);
            indicator = new MavilimTransform(mavilim);
        }

        return indicator;
    }
}
