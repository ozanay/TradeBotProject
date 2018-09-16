package com.trade.bot.data.decisionmaker;

import java.io.IOException;

import com.trade.bot.TradeSymbol;
import com.trade.bot.data.client.TradeClient;
import com.trade.bot.data.client.TradeClientCandleStickInterval;
import com.trade.bot.data.indicator.Indicator;
import com.trade.bot.data.indicator.IndicatorFactory;

/**
 * @author Ozan Ay
 */
public class CommercialDecisionMakerRunnerFactory {
    private CommercialDecisionMakerRunnerFactory() {
    }

    public static CommercialDecisionMakerRunner create(String indicatorParameters, TradeClient tradeClient,
                    TradeSymbol tradeSymbol, TradeClientCandleStickInterval candleStickInterval) throws IOException {
        Indicator indicator = IndicatorFactory.getIndicator(indicatorParameters);
        CommercialDecisionMaker commercialDecisionMaker = new HullMovingAverageDecisionMaker(indicator, tradeClient, tradeSymbol, candleStickInterval);

        return new CommercialDecisionMakerRunner(commercialDecisionMaker);
    }
}
