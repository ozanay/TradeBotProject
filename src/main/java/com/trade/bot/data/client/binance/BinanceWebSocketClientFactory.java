package com.trade.bot.data.client.binance;

import com.trade.bot.data.client.TradeWebSocketClient;
import com.trade.bot.data.decisionmaker.CommercialDecisionMaker;

/**
 * @author Ozan Ay
 */
public class BinanceWebSocketClientFactory {
    public static TradeWebSocketClient getClient(CommercialDecisionMaker commercialDecisionMaker) {
        return new BinanceWebSocketClient(commercialDecisionMaker);
    }
}
