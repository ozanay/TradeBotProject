package com.trade.bot.data.decisionmaker;

import com.trade.bot.TradeSymbol;
import com.trade.bot.data.client.TradeClient;
import com.trade.bot.data.client.TradeClientCandleStickInterval;
import com.trade.bot.data.indicator.Indicator;
import com.trade.bot.data.indicator.IndicatorEnum;
import com.trade.bot.data.indicator.IndicatorFactory;

/**
 * @author Ozan Ay
 */
public class CommercialDecisionMakerFactory {
    private CommercialDecisionMakerFactory() {}
    
    public static CommercialDecisionMaker create(IndicatorEnum indicatorEnum, TradeClient tradeClient, TradeSymbol tradeSymbol,
                                          TradeClientCandleStickInterval candleStickInterval) {
        Indicator indicator = IndicatorFactory.getIndicator(indicatorEnum);
        CommercialDecisionMaker commercialDecisionMaker;
        switch (indicatorEnum) {
            case MAVILIM:
                commercialDecisionMaker = new MavilimDecisionMaker(indicator, tradeClient, tradeSymbol, candleStickInterval);
                break;
            case HULL_MOVING_AVERAGE:
                commercialDecisionMaker = new HullMovingAverageDecisionMaker(indicator, tradeClient, tradeSymbol, candleStickInterval);
                break;
            default:
                commercialDecisionMaker = new HullMovingAverageDecisionMaker(indicator, tradeClient, tradeSymbol, candleStickInterval);
        }

        return commercialDecisionMaker;
    }
}
