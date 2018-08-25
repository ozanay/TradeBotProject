package com.trade.bot.data.client;

import com.trade.bot.Market;
import com.trade.bot.TradeData;
import com.trade.bot.data.client.binance.BinanceWebSocketClientFactory;

import java.util.concurrent.BlockingQueue;

/**
 * @author Ozan Ay
 */
public class TradeWebSocketClientFactory {
    public static TradeWebSocketClient create(Market market, BlockingQueue<TradeData> queue) {
        if (market.equals(Market.BINANCE)) {
            return BinanceWebSocketClientFactory.getClient(queue);
        }

        return null;
    }
}
