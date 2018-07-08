package com.trade.bot.data.client;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.market.TickerPrice;
import com.trade.bot.TradeData;
import com.trade.bot.TradeSymbol;

import java.util.logging.Logger;

public class BinanceClient implements TradeClient {
    private static final Logger LOGGER = Logger.getLogger(BinanceClient.class.getName());
    private static final String SECRET_KEY = "s8MlZOJKFO2XQhV9dT0lgdJjsKeNo3bPYOjrjwGPZOI5froszAJsoJOTwd4s7EX8";
    private static final String API_KEY = "pEYXmapVpBEILIJ3nnNfwJKYrNkecQVgJ17VuA5KmUTWkg6SEjqn7EyXohFIVL9A";

    private static final BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(API_KEY, SECRET_KEY);
    private static final BinanceApiRestClient client = factory.newRestClient();

    private static final BinanceClient instance = new BinanceClient();

    private BinanceClient() {}

    static BinanceClient getInstance() {
        return instance;
    }

    public TradeData getData(TradeSymbol tradeSymbol) {
        TickerPrice tickerPrice = client.getPrice(tradeSymbol.getValue());
        return new BinanceTradeDataAdapter(tickerPrice);
    }
}
