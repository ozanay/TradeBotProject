package com.trade.bot.data.client;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiWebSocketClient;
import com.binance.api.client.domain.event.AggTradeEvent;
import com.trade.bot.TradeData;
import com.trade.bot.TradeSymbol;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

/**
 * @author Ozan Ay
 */
public class BinanceWebSocketClient implements TradeWebSocketClient {
    private static final Logger LOGGER = Logger.getLogger(BinanceWebSocketClient.class.getName());
    private static final BinanceApiWebSocketClient client = BinanceApiClientFactory.newInstance().newWebSocketClient();
    private final BlockingQueue<TradeData> tradeData;

    public BinanceWebSocketClient(BlockingQueue<TradeData> tradeData) {
        this.tradeData = tradeData;
    }

    @Override
    public void subscribeEvent() {
        client.onAggTradeEvent(TradeSymbol.BTC_USDT.getValue().toLowerCase(), this::addSubscribedData);
    }

    private void addSubscribedData(AggTradeEvent response) {
        double price = Double.parseDouble(response.getPrice());
        BinanceTradeData binanceTradeData = new BinanceTradeData(price);
        tradeData.add(binanceTradeData);
    }
}
