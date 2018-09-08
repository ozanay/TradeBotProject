package com.trade.bot.data.client.binance;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.BinanceApiWebSocketClient;
import com.binance.api.client.domain.event.AggTradeEvent;
import com.binance.api.client.domain.market.Candlestick;
import com.binance.api.client.domain.market.CandlestickInterval;
import com.trade.bot.CandleStickData;
import com.trade.bot.TradeData;
import com.trade.bot.TradeSymbol;
import com.trade.bot.data.client.TradeClient;
import com.trade.bot.data.client.TradeClientCandleStickInterval;
import com.trade.bot.logging.LoggerProvider;
import com.trade.bot.util.DateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

class BinanceClient implements TradeClient {
    private static final Logger logger = LoggerProvider.getLogger(BinanceClient.class.getName());
    private static final String SECRET_KEY = "s8MlZOJKFO2XQhV9dT0lgdJjsKeNo3bPYOjrjwGPZOI5froszAJsoJOTwd4s7EX8";
    private static final String API_KEY = "pEYXmapVpBEILIJ3nnNfwJKYrNkecQVgJ17VuA5KmUTWkg6SEjqn7EyXohFIVL9A";

    private static final BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(API_KEY, SECRET_KEY);
    private static final BinanceApiRestClient restClient = factory.newRestClient();
    private static final BinanceApiWebSocketClient webSocketClient = factory.newWebSocketClient();
    private final BlockingQueue<TradeData> tradeDataQueue = new LinkedBlockingQueue<>();
    
    BinanceClient() {}
    
    @Override
    public List<CandleStickData> getCandleStickData(TradeSymbol tradeSymbol, TradeClientCandleStickInterval tradeInterval) {
        CandlestickInterval candlestickInterval = BinanceCandleStickIntervalConverter.from(tradeInterval);
        List<Candlestick> candlestickBars = restClient
            .getCandlestickBars(tradeSymbol.getValue().toUpperCase(), candlestickInterval);
        List<CandleStickData> candleStickData = new ArrayList<>();
        candlestickBars.forEach(candlestick -> candleStickData.add(new BinanceCandleStickDataAdapter(candlestick)));
        return candleStickData;
    }

    @Override
    public CandleStickData getCurrentCandleStickData(TradeSymbol tradeSymbol, TradeClientCandleStickInterval interval) {
        CandlestickInterval candlestickInterval = BinanceCandleStickIntervalConverter.from(interval);
        List<Candlestick> candlestickBars = restClient
            .getCandlestickBars(tradeSymbol.getValue().toUpperCase(), candlestickInterval);
        int currentCandleStickBarIndex = candlestickBars.size() - 1;
        return new BinanceCandleStickDataAdapter(candlestickBars.get(currentCandleStickBarIndex));
    }

    @Override
    public TradeData getCurrentCloseTradeData(TradeSymbol tradeSymbol, TradeClientCandleStickInterval interval) {
        return getCurrentCandleStickData(tradeSymbol, interval).getCloseTradeData();
    }

    @Override
    public void buy(TradeData tradeData) {
        logger.log(Level.INFO, () -> "BOUGHT price: " + tradeData.getPrice() + " at " + DateUtil.format(tradeData.getEventTime()));
    }
    
    @Override
    public void sell(TradeData tradeData) {
        logger.log(Level.INFO,
            () -> "SOLD price: " + tradeData.getPrice() + " at " + DateUtil.format(tradeData.getEventTime()));
    }
    
    @Override
    public void subscribeTradeEvent(TradeSymbol tradeSymbol) {
        webSocketClient.onAggTradeEvent(tradeSymbol.getValue().toLowerCase(), this::addSubscribedData);
    }
    
    @Override
    public TradeData getLastTradeData() {
        return tradeDataQueue.poll();
    }
    
    private void addSubscribedData(AggTradeEvent response) {
        TradeData tradeData = new BinanceTradeDataAdapter(response);
        boolean isDataPutToQ = tradeDataQueue.offer(tradeData);
        if (!isDataPutToQ) {
            logger.log(Level.SEVERE, () -> "Trade data " + tradeData.toString() + " could not be out to Queue.");
        }
    }
}
