package com.trade.bot.data.client.binance;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.market.Candlestick;
import com.binance.api.client.domain.market.CandlestickInterval;
import com.binance.api.client.domain.market.TickerPrice;
import com.trade.bot.CandleStickData;
import com.trade.bot.TradeData;
import com.trade.bot.TradeSymbol;
import com.trade.bot.data.client.TradeClient;
import com.trade.bot.data.client.TradeClientCandleStickInterval;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

class BinanceClient implements TradeClient {
    private static final Logger LOGGER = Logger.getLogger(BinanceClient.class.getName());
    private static final String SECRET_KEY = "s8MlZOJKFO2XQhV9dT0lgdJjsKeNo3bPYOjrjwGPZOI5froszAJsoJOTwd4s7EX8";
    private static final String API_KEY = "pEYXmapVpBEILIJ3nnNfwJKYrNkecQVgJ17VuA5KmUTWkg6SEjqn7EyXohFIVL9A";

    private static final BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(API_KEY, SECRET_KEY);
    private static final BinanceApiRestClient client = factory.newRestClient();

    @Override
    public TradeData getData(TradeSymbol tradeSymbol) {
        TickerPrice tickerPrice = client.getPrice(tradeSymbol.getValue());
        return new BinanceTradeDataAdapter(tickerPrice);
    }

    @Override
    public List<CandleStickData> getCandleStickData(TradeSymbol tradeSymbol, TradeClientCandleStickInterval tradeInterval) {
        CandlestickInterval candlestickInterval = BinanceCandleStickIntervalConverter.from(tradeInterval);
        List<Candlestick> candlestickBars = client.getCandlestickBars(tradeSymbol.getValue().toUpperCase(), candlestickInterval);
        List<CandleStickData> candleStickData = new ArrayList<>();
        candlestickBars.forEach(candlestick -> candleStickData.add(new BinanceCandleStickDataAdapter(candlestick)));
        return candleStickData;
    }

    @Override
    public void buy(TradeData tradeData) {
        LOGGER.info("BOUGHT at " + tradeData.getPrice());
    }

    @Override
    public void sell(TradeData tradeData) {
        LOGGER.info("SOLD at " + tradeData.getPrice());
    }
}
