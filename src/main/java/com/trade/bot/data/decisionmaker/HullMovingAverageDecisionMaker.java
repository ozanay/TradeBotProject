package com.trade.bot.data.decisionmaker;

import com.trade.bot.CandleStickData;
import com.trade.bot.CommercialDecision;
import com.trade.bot.TradeData;
import com.trade.bot.TradeSymbol;
import com.trade.bot.data.client.TradeClient;
import com.trade.bot.data.client.TradeClientCandleStickInterval;
import com.trade.bot.data.indicator.Indicator;
import com.trade.bot.logging.LoggerProvider;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * @author Ozan Ay
 */
public class HullMovingAverageDecisionMaker extends CommercialDecisionMakerBase {
    private static final Logger logger = LoggerProvider.getLogger(HullMovingAverageDecisionMaker.class.getName());
    private final Indicator indicator;
    private final TradeClient tradeClient;
    private final TradeSymbol tradeSymbol;
    private final TradeClientCandleStickInterval candleStickInterval;
    private CommercialDecision lastDecision = CommercialDecision.NONE;
    private AtomicBoolean isRunning;

    HullMovingAverageDecisionMaker(Indicator indicator, TradeClient tradeClient, TradeSymbol tradeSymbol,
                                   TradeClientCandleStickInterval candleStickInterval) {
        this.indicator = indicator;
        this.tradeClient = tradeClient;
        this.tradeSymbol = tradeSymbol;
        this.candleStickInterval = candleStickInterval;
        this.isRunning = new AtomicBoolean();
    }
    
    @Override
    public void run() {
        while (isRunning.get()) {
            TradeData lastData = tradeClient.getLastTradeData();
            if (lastData != null) {
                decide(lastData);
            }
        }
    }
    
    @Override
    void decide(TradeData tradeData) {
        List<TradeData> closingTradeData = getTradeDataListTillPreviousBar();
        double previousHullMovingAverage = (double) indicator.apply(closingTradeData).getValue();


        closingTradeData.add(tradeData);
        double hullMovingAverage = (double) indicator.apply(closingTradeData).getValue();


        tradeIfLatestOrderChanged(tradeData, previousHullMovingAverage, hullMovingAverage);
    }
    
    @Override
    public void start() {
        if (!isRunning.get()) {
            isRunning.set(true);
        }
        
        tradeClient.subscribeTradeEvent(tradeSymbol);
    }
    
    @Override
    public void stop() {
        if (isRunning.get()) {
            isRunning.set(false);
        }
    }
    
    private void tradeIfLatestOrderChanged(TradeData tradeData, double previousHullMovingAverage, double hullMovingAverage) {
        if (hullMovingAverage > previousHullMovingAverage && !lastDecision.equals(CommercialDecision.BUY)) {
            buy(tradeData);
            logHMAs(previousHullMovingAverage, hullMovingAverage);
        } else if (hullMovingAverage < previousHullMovingAverage && !lastDecision.equals(CommercialDecision.SELL)) {
            sell(tradeData);
            logHMAs(previousHullMovingAverage, hullMovingAverage);
        }
    }
    
    private void logHMAs(double previousHullMovingAverage, double hullMovingAverage) {
        logger.log(Level.INFO, "HMA: {0}", hullMovingAverage);
        logger.log(Level.INFO, "HMA[1]: {0}", previousHullMovingAverage);
    }
    
    private void sell(TradeData tradeData) {
        tradeClient.sell(tradeData);
        lastDecision = CommercialDecision.SELL;
    }
    
    private void buy(TradeData tradeData) {
        tradeClient.buy(tradeData);
        lastDecision = CommercialDecision.BUY;
    }
    
    private List<TradeData> getTradeDataListTillPreviousBar() {
        List<CandleStickData> candleStickData = tradeClient.getCandleStickData(tradeSymbol, candleStickInterval);
        int limitedSize = candleStickData.size() - 1;
        return candleStickData.stream().map(CandleStickData::getCloseTradeData).limit(limitedSize).collect(Collectors.toList());
    }
}
