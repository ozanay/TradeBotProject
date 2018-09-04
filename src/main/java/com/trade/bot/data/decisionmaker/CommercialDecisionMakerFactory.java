package com.trade.bot.data.decisionmaker;

import com.trade.bot.TradeSymbol;
import com.trade.bot.data.client.TradeClient;
import com.trade.bot.data.client.TradeClientCandleStickInterval;
import com.trade.bot.data.indicator.Indicator;
import com.trade.bot.data.indicator.IndicatorEnum;

/**
 * @author Ozan Ay
 */
public class CommercialDecisionMakerFactory {
    public static CommercialDecisionMaker create(IndicatorEnum indicatorEnum, Indicator indicator, TradeClient tradeClient, TradeSymbol tradeSymbol,
                                          TradeClientCandleStickInterval candleStickInterval) {
        CommercialDecisionMaker commercialDecisionMaker;
        switch (indicatorEnum) {
            case MAVILIM:
                commercialDecisionMaker = new MavilimDecisionMaker(indicator, tradeClient, tradeSymbol, candleStickInterval);
                break;
            case HULL_MOVING_AVERAGE:
                commercialDecisionMaker = new HullMovingAverageDecisionMaker(indicator, tradeClient, tradeSymbol, candleStickInterval);
            default:
                commercialDecisionMaker = new HullMovingAverageDecisionMaker(indicator, tradeClient, tradeSymbol, candleStickInterval);
        }

        return commercialDecisionMaker;
    }
}
