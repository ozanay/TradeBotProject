package com.trade.bot.data.client;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.market.Candlestick;
import com.binance.api.client.domain.market.CandlestickInterval;
import com.binance.api.client.domain.market.TickerPrice;
import com.trade.bot.TradeData;
import com.trade.bot.TradeSymbol;
import com.trade.bot.util.DateUtil;

import java.util.Date;
import java.util.List;
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
        List<Candlestick> candlesticks = client.getCandlestickBars(tradeSymbol.getValue().toUpperCase(), CandlestickInterval.FOUR_HOURLY);
        LOGGER.info("Stick size is " + candlesticks.size());
        int last = candlesticks.size() - 1;
        Candlestick candlestick = candlesticks.get(last);
        LOGGER.info("Open time is " + DateUtil.format(new Date(candlestick.getOpenTime())));
        LOGGER.info("Open price is " + candlestick.getOpen());
        Date closeTime = new Date(candlestick.getCloseTime());
        LOGGER.info("Close time is " + DateUtil.format(closeTime));
        LOGGER.info("Close price is " + candlestick.getClose());

        Date current = new Date();
        LOGGER.info("Current time is " + DateUtil.format(current));

        LOGGER.info("Is time in current bar " + current.compareTo(closeTime));
//        TickerPrice tickerPrice = client.getPrice(tradeSymbol.getValue());
//        return new BinanceTradeDataAdapter(tickerPrice);
        return null;
    }
}
