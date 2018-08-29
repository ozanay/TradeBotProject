package com.trade.bot;

import com.trade.bot.data.client.*;
import com.trade.bot.data.decisionmaker.CommercialDecisionMaker;
import com.trade.bot.data.decisionmaker.CommercialDecisionMakerFactory;
import com.trade.bot.data.indicator.Indicator;
import com.trade.bot.data.indicator.IndicatorEnum;
import com.trade.bot.data.indicator.IndicatorFactory;

public class BotRunner {
    public static void main(String[] args) {
        int fmal = 3;
        int smal = 5;
        Indicator indicator = IndicatorFactory.getIndicator(IndicatorEnum.MAVILIM, fmal, smal);
        TradeClient tradeClient = TradeClientFactory.create(Market.BINANCE);
        CommercialDecisionMaker commercialDecisionMaker = CommercialDecisionMakerFactory.create(IndicatorEnum.MAVILIM, indicator, tradeClient,
                TradeSymbol.BTC_USDT, TradeClientCandleStickInterval.FIFTEEN_MINUTES);
        commercialDecisionMaker.warmUp();

        TradeWebSocketClient tradeWebSocketClient = TradeWebSocketClientFactory.create(Market.BINANCE, commercialDecisionMaker);
        tradeWebSocketClient.subscribeEvent(TradeSymbol.BTC_USDT);
    }
}
