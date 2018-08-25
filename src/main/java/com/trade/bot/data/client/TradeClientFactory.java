package com.trade.bot.data.client;

import com.trade.bot.Market;
import com.trade.bot.data.client.binance.BinanceClientFactory;

/**
 * @author Ozan Ay
 */
public class TradeClientFactory {
    public static TradeClient create(Market market) {
        if (market.equals(Market.BINANCE)) {
            return BinanceClientFactory.getClient();
        }

        return null;
    }
}
