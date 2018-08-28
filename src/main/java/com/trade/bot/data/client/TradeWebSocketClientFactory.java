package com.trade.bot.data.client;

import com.trade.bot.Market;
import com.trade.bot.data.client.binance.BinanceWebSocketClientFactory;
import com.trade.bot.data.decisionmaker.CommercialDecisionMaker;

/**
 * @author Ozan Ay
 */
public class TradeWebSocketClientFactory {
    public static TradeWebSocketClient create(Market market, CommercialDecisionMaker commercialDecisionMaker) {
        if (market.equals(Market.BINANCE)) {
            return BinanceWebSocketClientFactory.getClient(commercialDecisionMaker);
        }

        return null;
    }
}
